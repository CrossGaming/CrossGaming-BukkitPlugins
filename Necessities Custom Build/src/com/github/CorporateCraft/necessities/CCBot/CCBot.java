package com.github.CorporateCraft.necessities.CCBot;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CCBot
{
	ArrayLists arl = new ArrayLists();
	CCBotLog log = new CCBotLog();
	Formatter form = new Formatter();
	static CCBotWarn warns = new CCBotWarn();
	CCBotIRC irc;
	private HashMap<String,Long[]> lastChat = new HashMap<String,Long[]>();
	private HashMap<String,Long[]> lastCmd = new HashMap<String,Long[]>();
	private static ArrayList<String> allowed = new ArrayList<String>();
	private static ArrayList<String> good = new ArrayList<String>();
	private double chatSpam = 1;//1 seconds for 10 messages
	private double cmdSpam = 1;//1 seconds for 10 messages
	public CCBot()
	{
		form.readFile(arl.getProf(), allowed);
		form.readFile(arl.getAl(), good);
		upperAll();
	}
	private void removePlayer(String name)
	{
		lastChat.remove(name);
		lastCmd.remove(name);
	}
	private boolean checkChatSpam(String player)
	{
		Long time = System.currentTimeMillis();
		if(!lastChat.containsKey(player))
		{
			Long[] t = new Long[9];
			t[0] = time;
			lastChat.put(player, t);
			return false;
		}
		if(!isFull(lastChat.get(player)))
		{
			putProp(lastChat.get(player), time);
			return false;
		}
		Long FirstTime = lastChat.get(player)[0];
		if((time - FirstTime)/1000 > chatSpam)
		{
			putProp(lastChat.get(player), time);
			return false;
		}
		putProp(lastChat.get(player), time);
		warns.warn(player, "ChatSpam", "CCBot");
		return true;
	}
	private boolean checkCmdSpam(String player)
	{
		Long time = System.currentTimeMillis();
		if(!lastCmd.containsKey(player))
		{
			Long[] t = new Long[9];
			t[0] = time;
			lastCmd.put(player, t);
			return false;
		}
		if(!isFull(lastCmd.get(player)))
		{
			putProp(lastCmd.get(player), time);
			return false;
		}
		Long FirstTime = lastCmd.get(player)[0];
		if((time - FirstTime)/1000 > cmdSpam)
		{
			putProp(lastCmd.get(player), time);
			return false;
		}
		putProp(lastCmd.get(player), time);
		warns.warn(player, "CmdSpam", "CCBot");
		return true;
	}
	private void putProp(Long[] l, Long toPut)
	{
		if(l[1] == null)
			l[1] = toPut;
		else if(l[2] == null)
			l[2] = toPut;
		else if(l[3] == null)
			l[3] = toPut;
		else if(l[4] == null)
			l[4] = toPut;
		else if(l[5] == null)
			l[5] = toPut;
		else if(l[6] == null)
			l[6] = toPut;
		else if(l[7] == null)
			l[7] = toPut;
		else if(l[8] == null)
			l[8] = toPut;
		else
		{
			l[0] = l[1];
			l[1] = l[2];
			l[2] = l[3];
			l[3] = l[4];
			l[4] = l[5];
			l[5] = l[6];
			l[6] = l[7];
			l[7] = l[8];
			l[8] = toPut;
		}
	}
	private boolean isFull(Long[] l)
	{
		if(l[0] == null)
			return false;
		if(l[1] == null)
			return false;
		if(l[2] == null)
			return false;
		if(l[3] == null)
			return false;
		if(l[4] == null)
			return false;
		if(l[5] == null)
			return false;
		if(l[6] == null)
			return false;
		if(l[7] == null)
			return false;
		if(l[8] == null)
			return false;
		return true;
	}
	private boolean caps(String player, String message)
	{
		String orig = message.replaceAll("[^A-Z]","");
		int s = orig.length();
		message = message.replaceAll("[^a-zA-Z]","");
		int f = message.length();
		if(f * 3/5 <= s)// 3/5 is capital
		{
			if(f > 5)
			{
				warns.warn(player, "Caps", "CCBot");
				return true;
			}
		}
		return false;
	}
	private void upperAll()
	{
		for(int i = 0; i < allowed.size(); i++)
		{
			allowed.set(i, allowed.get(i).toUpperCase());
		}
		for(int i = 0; i < good.size(); i++)
		{
			good.set(i, good.get(i).toUpperCase());
		}
	}
	private boolean langCheck(String player, String message)
	{
		String[] orig = message.replaceAll("[^a-zA-Z ]","").toUpperCase().split(" ");
		message = message.replaceAll("[^a-zA-Z]","").toUpperCase();
		ArrayList<String> bad = new ArrayList<String>();
		ArrayList<String> other = new ArrayList<String>();
		for(int i = 0; i < allowed.size(); i++)
		{
			for(int j = 0; j < orig.length; j++)
			{
				if (orig[j].startsWith(allowed.get(i)))
				{
					bad.add(orig[j]);
					other.add(orig[j]);
				}
				else if(orig[j].length() >= 10)
				{
					if (orig[j].contains(allowed.get(i)))
					{
						bad.add(orig[j]);
						other.add(orig[j]);
					}
				}
			}
		}
		for(int j = 0; j < bad.size(); j++)
		{
			for(int i = 0; i < good.size(); i++)
			{
				if(bad.get(j).startsWith(good.get(i)))
				{
					other.remove(bad.get(j));
					break;
				}
			}
		}
		if(other.size() > 0)
		{
			warns.warn(player, "Language", "CCBot");
			return true;
		}
		return false;
	}
	public void warnP(String t, String reason, String p)
	{
		warns.warn(t, reason, p);
	}
	private boolean adds(String player, String message)
	{
		String[] orig = message.split(" ");
		String temp = "";
		for(int i = 0; i < orig.length; i++)
		{
			temp = orig[i].split("\\:")[0];
			if(validateIPAddress(temp))
			{
				warns.warn(player, "Adds", "CCBot");
				return true;
			}
			else
			{
				if(!temp.contains("http://") && !temp.equalsIgnoreCase("smp.crossge.com") && (temp.split("\\.").length == 3 || temp.split("\\.").length == 3))
				{
					try
					{
						URLConnection urlCon = new URL("http://" + temp).openConnection();
						urlCon.connect();
						InputStream is = urlCon.getInputStream();
						String u = urlCon.getURL().toString().replaceFirst("http://", "");
						is.close();
						if(validateIPAddress(u))
						{
							warns.warn(player, "Adds", "CCBot");
							return true;
						}
					}
					catch (MalformedURLException e){} 
					catch (IOException e){}
				}
			}
		}
		return false;
	}
	private boolean validateIPAddress(String ipAddress)
	{
		try
		{
			final Pattern ipAdd = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
													"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
			return ipAdd.matcher(ipAddress).matches();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public void logChat(String player, String message)
	{
		CCBotIRC.irc.sendIRC(player, message);
		String messageOrig = message;
		message = player + ": " + message;
		log.log(message);		
		Player p = Bukkit.getServer().getPlayer(player);
		boolean stop = false;
		if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.caps"))
		{
			if(!p.hasPermission("Necessities.caps"))
			{
				stop = caps(player, messageOrig);
			}
		}
		if(!stop)
		{
			if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.language"))
			{
				if(!p.hasPermission("Necessities.language"))
				{
					stop = langCheck(player, messageOrig);
				}
			}
		}
		if(!stop)
		{
			if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.chatSpam"))
			{
				if(!p.hasPermission("Necessities.spamchat"))
				{
					stop = checkChatSpam(player);
				}
			}
		}
		if(!stop)
		{
			if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.advertise"))
			{
				if(!p.hasPermission("Necessities.advertise"))
				{
					stop = adds(player, messageOrig);
				}
			}
		}
	}
	public void logCom(String player, String message)
	{
		String messageOrig = message;
		message = player + " issued server command: " + message;
		log.log(message);
		Player p = Bukkit.getServer().getPlayer(player);
		if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.cmdSpam"))
		{
			if(!p.hasPermission("Necessities.spamcommands"))
			{
				checkCmdSpam(player);
			}
		}
		if(messageOrig.split(" ")[0].equalsIgnoreCase("/me") || messageOrig.split(" ")[0].equalsIgnoreCase("/eme") ||
		    messageOrig.split(" ")[0].equalsIgnoreCase("/tell") || messageOrig.split(" ")[0].equalsIgnoreCase("/etell") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/ewhisper") || messageOrig.split(" ")[0].equalsIgnoreCase("/whisper") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/msg") || messageOrig.split(" ")[0].equalsIgnoreCase("/emsg") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/action") || messageOrig.split(" ")[0].equalsIgnoreCase("/eaction") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/describe") || messageOrig.split(" ")[0].equalsIgnoreCase("/edescribe") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/reply") || messageOrig.split(" ")[0].equalsIgnoreCase("/ereply") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/r") || messageOrig.split(" ")[0].equalsIgnoreCase("/er") ||
			messageOrig.split(" ")[0].equalsIgnoreCase("/t"))
		{
			if(messageOrig.split(" ")[0].equalsIgnoreCase("/me"))
				messageOrig = messageOrig.replaceFirst("/me", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/t"))
				messageOrig = messageOrig.replaceFirst("/t", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/tell"))
				messageOrig = messageOrig.replaceFirst("/tell", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/emsg"))
				messageOrig = messageOrig.replaceFirst("/emsg", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/eme"))
				messageOrig = messageOrig.replaceFirst("/eme", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/me"))
				messageOrig = messageOrig.replaceFirst("/me", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/etell"))
				messageOrig = messageOrig.replaceFirst("/etell", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/whisper"))
				messageOrig = messageOrig.replaceFirst("/whisper", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/ewhisper"))
				messageOrig = messageOrig.replaceFirst("/ewhisper", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/msg"))
				messageOrig = messageOrig.replaceFirst("/msg", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/action"))
				messageOrig = messageOrig.replaceFirst("/action", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/eaction"))
				messageOrig = messageOrig.replaceFirst("/eaction", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/describe"))
				messageOrig = messageOrig.replaceFirst("/describe", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/edescribe"))
				messageOrig = messageOrig.replaceFirst("/edescribe", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/r"))
				messageOrig = messageOrig.replaceFirst("/r", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/er"))
				messageOrig = messageOrig.replaceFirst("/er", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/reply"))
				messageOrig = messageOrig.replaceFirst("/reply", "").trim();
			else if(messageOrig.split(" ")[0].equalsIgnoreCase("/ereply"))
				messageOrig = messageOrig.replaceFirst("/ereply", "").trim();
			boolean stop = false;
			if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.caps"))
			{
				if(!p.hasPermission("Necessities.caps"))
				{
					stop = caps(player, messageOrig);
				}
			}
			if(!stop)
			{
				if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.language"))
				{
					if(!p.hasPermission("Necessities.language"))
					{
						stop = langCheck(player, messageOrig);
					}
				}
			}
			if(!stop)
			{
				if(Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getBoolean("CCBot.advertise"))
				{
					if(!p.hasPermission("Necessities.advertise"))
					{
						stop = adds(player, messageOrig);
					}
				}
			}
		}
	}
	public void logConsole(String message)
	{
		if(message.startsWith("say"))
		{
			message = "Console:" + message.replaceFirst("say", "");
			CCBotIRC.irc.sendIRC("Console" + "[" + Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.cState") + "]", message);
		}
		else
		{
			message = "Console issued command: " + message;
		}
		log.log(message);
	}
	public void logIn(String player)
	{
		String message = player + " joined the game.";
		log.log(message);
	} 
	public void logOut(String player)
	{
		removePlayer(player);
		warns.removePlayer(player);
		String message = player + " left the game.";
		log.log(message);
	}
}