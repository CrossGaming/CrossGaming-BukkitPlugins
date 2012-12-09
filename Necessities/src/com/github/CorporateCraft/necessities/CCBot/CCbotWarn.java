package com.github.CorporateCraft.necessities.CCBot;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.ArrayLists;

public class CCBotWarn
{
	ArrayLists arl = new ArrayLists();
	CCBotLog Log = new CCBotLog();
	private HashMap<String,Integer> WarnCount = new HashMap<String,Integer>();
	private int Warns = 3;
	private String CCBotTitle = ChatColor.BLACK + "[" + ChatColor.GREEN + "CCBot" + ChatColor.BLACK + "]";
	private String CCBotName = CCBotTitle + ChatColor.BLACK + " CCBot: " + ChatColor.WHITE;
	private String CCBotLogName = "[CCBot] " + "CCBot" + ": ";
	public CCBotWarn()
	{
		
	}
	public void RemovePlayer(String name)
	{
		WarnCount.remove(name);
	}
	public void Ban(String name, String r)
	{
		Bukkit.broadcastMessage(CCBotName + name + " was banned for " + r + ".");
		Log.Log(CCBotLogName + name + " was banned for " + r + ".");
	}
	public void BanIp(String ip, String r)
	{
		Bukkit.broadcastMessage(CCBotName + "the ip " + ip + " was banned for " + r + ".");
		Log.Log(CCBotLogName + "the ip " + ip + " was banned for " + r + ".");
	}
	public void Kick(String name, String r)
	{
		Bukkit.broadcastMessage(CCBotName + name + " was kicked for " + r + ".");
		Log.Log(CCBotLogName + name + " was kicked for " + r + ".");
	}
	public void warn(String name, String r, String Warner)
	{
		if(!WarnCount.containsKey(name))
		{
			WarnCount.put(name, 1);
			if(r.equals("Caps"))
			{
				CapsMsg(name);
			}
			else if(r.equals("Language"))
			{
				LangMsg(name);
			}
			else if(r.equals("ChatSpam"))
			{
				ChatMsg(name);
			}
			else if(r.equals("CmdSpam"))
			{
				CmdMsg(name);
			}
			else
			{
				WarnMessage(name, r, Warner);
			}
			TimesLeft(name);
		}
		else
		{
			WarnCount.put(name, WarnCount.get(name)+1);
			if(WarnCount.get(name)==Warns)
			{
				if(r.equals("Caps"))
				{
					Caps(name);
				}
				else if(r.equals("Language"))
				{
					Language(name);
				}
				else if(r.equals("ChatSpam"))
				{
					ChatSpam(name);
				}
				else if(r.equals("CmdSpam"))
				{
					CmdSpam(name);
				}
				else
				{
					Other(name,r);
				}
			}
			else
			{
				if(r.equals("Caps"))
				{
					CapsMsg(name);
				}
				else if(r.equals("Language"))
				{
					LangMsg(name);
				}
				else if(r.equals("ChatSpam"))
				{
					ChatMsg(name);
				}
				else if(r.equals("CmdSpam"))
				{
					CmdMsg(name);
				}
				else
				{
					WarnMessage(name, r, Warner);
				}
				TimesLeft(name);
			}
		}
	}
	private void TimesLeft(String name)
	{
		String left = Integer.toString(Warns-WarnCount.get(name));
		Bukkit.broadcastMessage(CCBotName + "Do it " + left + " more times and you will be kicked.");
		Log.Log(CCBotLogName + "Do it " + left + " more times and you will be kicked.");
	}
	private void CapsMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for using caps.");
		Log.Log(CCBotLogName + player.getName() + " was warned for using caps.");
	}
	private void LangMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for using bad language.");
		Log.Log(CCBotLogName + player.getName() + " was warned for using bad language.");
	}
	private void ChatMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for spamming the chat.");
		Log.Log(CCBotLogName + player.getName() + " was warned for spamming the chat.");
	}
	private void CmdMsg(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned for spamming commands.");
		Log.Log(CCBotLogName + player.getName() + " was warned for spamming commands.");
	}
	private void WarnMessage(String name, String Reason, String Warner)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was warned by " + Warner + " for " + Reason + ".");
		Log.Log(player.getName() + " was warned by " + Warner + " for " + Reason + ".");
	}
	private void Other(String name, String reason)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		player.kickPlayer("Don't " + reason);
		Log.Log(CCBotLogName + player.getName() + " was kicked for " + reason + ".");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for " + reason + ".");
	}
	private void ChatSpam(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name) ;
		player.kickPlayer("Don't spam the chat!");
		Log.Log(CCBotLogName + player.getName() + " was kicked for spamming the chat.");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for spamming the chat.");
	}
	private void CmdSpam(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name) ;
		player.kickPlayer("Don't spam commands!");
		Log.Log(CCBotLogName + player.getName() + " was kicked for spamming commands.");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for spamming commands.");
	}
	private void Caps(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name) ;
		player.kickPlayer("Don't use all caps!");
		Log.Log(CCBotLogName + player.getName() + " was kicked for using caps.");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for using caps.");
	}
	private void Language(String name)
	{
		Player player = Bukkit.getServer().getPlayer(name);
		player.kickPlayer("Watch your language!");
		Log.Log(CCBotLogName + player.getName() + " was kicked for using bad language.");
		Bukkit.broadcastMessage(CCBotName + player.getName() + " was kicked for using bad language.");
	}
}