package com.github.CorporateCraft.necessities.CCBot;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CCBot
{
	ArrayLists arl = new ArrayLists();
	CCBotLog Log = new CCBotLog();
	Formatter form = new Formatter();
	CCBotWarn Warns = new CCBotWarn();
	private HashMap<String,Long> LastChat = new HashMap<String,Long>();
	private HashMap<String,Long> LastCmd = new HashMap<String,Long>();
	private ArrayList<String> Allowed = new ArrayList<String>();
	private double ChatSpam = 2;
	private double CmdSpam = 2;
	public CCBot()
	{
		form.readFile(arl.getProf(), Allowed);
	}
	private void removePlayer(String name)
	{
		LastChat.remove(name);
		LastCmd.remove(name);
	}
	private void checkChatSpam(String Player)
	{
		Long Time = System.currentTimeMillis();
		if(!LastChat.containsKey(Player))
		{
			LastChat.put(Player, Time);
			return;
		}
		Long LastTime = LastChat.get(Player);
		if((Time - LastTime)/1000 > ChatSpam)
		{
			LastChat.put(Player, Time);
			return;
		}
		Warns.warn(Player, "ChatSpam", "CCBot");
	}
	private void checkCmdSpam(String Player)
	{
		Long Time = System.currentTimeMillis();
		if(!LastCmd.containsKey(Player))
		{
			LastCmd.put(Player, Time);
			return;
		}
		Long LastTime = LastCmd.get(Player);
		if((Time - LastTime)/1000 > CmdSpam)
		{
			LastCmd.put(Player, Time);
			return;
		}
		Warns.warn(Player, "CmdSpam", "CCBot");
	}
	private void caps(String Player, String Message)
	{
		Message = Message.replaceAll("[^a-zA-Z]","");
		if(Message.equals(Message.toUpperCase()))
		{
			if(Message.length() > 15)
			{
				Warns.warn(Player, "Caps", "CCBot");
			}
		}
	}
	private void langCheck(String Player, String Message)
	{
		ArrayList<String> langList = new ArrayList<String>();
		Message = Message.replaceAll("[^a-zA-Z]","");
		for(int i = 0; i < Message.length(); i++)
		{
			try
			{
				langList.add(Message.split(" ")[i]);
			}
			catch(Exception e)
			{
				break;
			}
		}
        for(int i = 0; i < langList.size(); i++)
        {
            for(int j = 0; j < Allowed.size(); j++)
            {
                if (langList.get(i).toUpperCase().startsWith((Allowed.get(j)).toUpperCase()))
                {
                	Warns.warn(Player, "Language", "CCBot");
                    break;
                }
            }
        }
	}
	public void logChat(String Player, String Message)
	{
		String MessageOrig = Message;
		Message = Player + ": " + Message;
		Log.log(Message);		
		Player player = Bukkit.getServer().getPlayer(Player);
		if(!player.isOp())
		{
			caps(Player, MessageOrig);
			langCheck(Player, MessageOrig);
		}
		checkChatSpam(Player);
	}
	public void logCom(String Player, String Message)
	{
		Message = Player + " issued server command: " + Message;
		Log.log(Message);
		checkCmdSpam(Player);
	}
	public void logConsole(String Message)
	{
		if(Message.startsWith("say"))
		{
			Message = "Console:" + Message.replaceFirst("say", "");
		}
		else
		{
			Message = "Console issued command: " + Message;
		}
		Log.log(Message);
	}
	public void logIn(String Player)
	{
		String Message = Player + " joined the game.";
		Log.log(Message);
	} 
	public void logOut(String Player)
	{
		removePlayer(Player);
		Warns.removePlayer(Player);
		String Message = Player + " left the game.";
		Log.log(Message);
	}
}