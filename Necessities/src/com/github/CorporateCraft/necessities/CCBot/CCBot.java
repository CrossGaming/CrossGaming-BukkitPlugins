package com.github.CorporateCraft.necessities.CCBot;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CCBot
{
	ArrayLists arl = new ArrayLists();
	CCBotLog log = new CCBotLog();
	Formatter form = new Formatter();
	CCBotWarn warns = new CCBotWarn();
	private HashMap<String,Long> lastChat = new HashMap<String,Long>();
	private HashMap<String,Long> lastCmd = new HashMap<String,Long>();
	private ArrayList<String> allowed = new ArrayList<String>();
	private double chatSpam = 2;
	private double cmdSpam = 2;
	public CCBot()
	{
		form.readFile(arl.getProf(), allowed);
	}
	private void removePlayer(String name)
	{
		lastChat.remove(name);
		lastCmd.remove(name);
	}
	private void checkChatSpam(String player)
	{
		Long Time = System.currentTimeMillis();
		if(!lastChat.containsKey(player))
		{
			lastChat.put(player, Time);
			return;
		}
		Long LastTime = lastChat.get(player);
		if((Time - LastTime)/1000 > chatSpam)
		{
			lastChat.put(player, Time);
			return;
		}
		warns.warn(player, "ChatSpam", "CCBot");
	}
	private void checkCmdSpam(String player)
	{
		Long time = System.currentTimeMillis();
		if(!lastCmd.containsKey(player))
		{
			lastCmd.put(player, time);
			return;
		}
		Long LastTime = lastCmd.get(player);
		if((time - LastTime)/1000 > cmdSpam)
		{
			lastCmd.put(player, time);
			return;
		}
		warns.warn(player, "CmdSpam", "CCBot");
	}
	private void caps(String player, String message)
	{
		message = message.replaceAll("[^a-zA-Z]","");
		if(message.equals(message.toUpperCase()))
		{
			if(message.length() > 15)
			{
				warns.warn(player, "Caps", "CCBot");
			}
		}
	}
	private void langCheck(String player, String message)
	{
		ArrayList<String> langList = new ArrayList<String>();
		message = message.replaceAll("[^a-zA-Z]","");
		for(int i = 0; i < message.length(); i++)
		{
			try
			{
				langList.add(message.split(" ")[i]);
			}
			catch(Exception e)
			{
				break;
			}
		}
        for(int i = 0; i < langList.size(); i++)
        {
            for(int j = 0; j < allowed.size(); j++)
            {
                if (langList.get(i).toUpperCase().startsWith((allowed.get(j)).toUpperCase()))
                {
                	warns.warn(player, "Language", "CCBot");
                    break;
                }
            }
        }
	}
	public void logChat(String player, String message)
	{
		String messageOrig = message;
		message = player + ": " + message;
		log.log(message);		
		Player p = Bukkit.getServer().getPlayer(player);
		if(!p.isOp())
		{
			caps(player, messageOrig);
			langCheck(player, messageOrig);
		}
		checkChatSpam(player);
	}
	public void logCom(String player, String message)
	{
		message = player + " issued server command: " + message;
		log.log(message);
		checkCmdSpam(player);
	}
	public void logConsole(String message)
	{
		if(message.startsWith("say"))
		{
			message = "Console:" + message.replaceFirst("say", "");
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