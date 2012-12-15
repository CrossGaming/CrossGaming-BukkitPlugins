package com.github.CorporateCraft.necessities.CCBot;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CCBotWarn
{
	ArrayLists arl = new ArrayLists();
	CCBotLog log = new CCBotLog();
	private HashMap<String,Integer> warnCount = new HashMap<String,Integer>();
	private int warns = 3;
	private String CCBotTitle = ChatColor.BLACK + "[" + ChatColor.GREEN + "CCBot" + ChatColor.BLACK + "]";
	private String CCBotName = CCBotTitle + ChatColor.BLACK + " CCBot: " + ChatColor.WHITE;
	private String CCBotLogName = "[CCBot] " + "CCBot" + ": ";
	public CCBotWarn()
	{
		
	}
	public void removePlayer(String name)
	{
		warnCount.remove(name);
	}
	public void ban(String name, String r)
	{
		log.log(CCBotLogName + name + " was banned for " + r + ".");
		Bukkit.broadcastMessage(CCBotName + name + " was banned for " + r + ".");
	}
	public void banIp(String ip, String r)
	{
		log.log(CCBotLogName + "the ip " + ip + " was banned for " + r + ".");
		Bukkit.broadcastMessage(CCBotName + "the ip " + ip + " was banned for " + r + ".");
	}
	public void kick(String name, String r)
	{
		log.log(CCBotLogName + name + " was kicked for " + r + ".");
		Bukkit.broadcastMessage(CCBotName + name + " was kicked for " + r + ".");
	}
	public void warn(String name, String r, String Warner)
	{
		if(!warnCount.containsKey(name))
		{
			warnCount.put(name, 1);
			if(r.equals("Caps"))
			{
				capsMsg(name);
			}
			else if(r.equals("Language"))
			{
				langMsg(name);
			}
			else if(r.equals("ChatSpam"))
			{
				chatMsg(name);
			}
			else if(r.equals("CmdSpam"))
			{
				cmdMsg(name);
			}
			else
			{
				warnMessage(name, r, Warner);
			}
			timesLeft(name);
		}
		else
		{
			warnCount.put(name, warnCount.get(name)+1);
			if(warnCount.get(name)==warns)
			{
				if(r.equals("Caps"))
				{
					caps(name);
				}
				else if(r.equals("Language"))
				{
					language(name);
				}
				else if(r.equals("ChatSpam"))
				{
					chatSpam(name);
				}
				else if(r.equals("CmdSpam"))
				{
					cmdSpam(name);
				}
				else
				{
					other(name,r);
				}
			}
			else
			{
				if(r.equals("Caps"))
				{
					capsMsg(name);
				}
				else if(r.equals("Language"))
				{
					langMsg(name);
				}
				else if(r.equals("ChatSpam"))
				{
					chatMsg(name);
				}
				else if(r.equals("CmdSpam"))
				{
					cmdMsg(name);
				}
				else
				{
					warnMessage(name, r, Warner);
				}
				timesLeft(name);
			}
		}
	}
	private void timesLeft(String name)
	{
		String left = Integer.toString(warns-warnCount.get(name));
		Bukkit.broadcastMessage(CCBotName + "Do it " + left + " more times and you will be kicked.");
		log.log(CCBotLogName + "Do it " + left + " more times and you will be kicked.");
	}
	private void capsMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for using caps.");
		log.log(CCBotLogName + player.getName() + " was warned for using caps.");
	}
	private void langMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for using bad language.");
		log.log(CCBotLogName + player.getName() + " was warned for using bad language.");
	}
	private void chatMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for spamming the chat.");
		log.log(CCBotLogName + player.getName() + " was warned for spamming the chat.");
	}
	private void cmdMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for spamming commands.");
		log.log(CCBotLogName + player.getName() + " was warned for spamming commands.");
	}
	private void warnMessage(String name, String Reason, String Warner)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned by " + Warner + " for " + Reason + ".");
		log.log(player.getName() + " was warned by " + Warner + " for " + Reason + ".");
	}
	private void other(String name, String reason)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		player.kickPlayer("Don't " + reason);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for " + reason + ".");
		log.log(CCBotLogName + player.getName() + " was kicked for " + reason + ".");
	}
	private void chatSpam(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name) ;
		player.kickPlayer("Don't spam the chat!");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for spamming the chat.");
		log.log(CCBotLogName + player.getName() + " was kicked for spamming the chat.");
	}
	private void cmdSpam(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		player.kickPlayer("Don't spam commands!");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for spamming commands.");
		log.log(CCBotLogName + player.getName() + " was kicked for spamming commands.");
	}
	private void caps(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name) ;
		player.kickPlayer("Don't use all caps!");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for using caps.");
		log.log(CCBotLogName + player.getName() + " was kicked for using caps.");
	}
	private void language(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		player.kickPlayer("Watch your language!");		
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for using bad language.");
		log.log(CCBotLogName + player.getName() + " was kicked for using bad language.");
	}
}