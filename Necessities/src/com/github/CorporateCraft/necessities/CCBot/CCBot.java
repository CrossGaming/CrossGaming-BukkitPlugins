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
	EditString Edit = new EditString();
	private HashMap<String,Long> LastChat = new HashMap<String,Long>();
	private HashMap<String,Long> LastCmd = new HashMap<String,Long>();
	private ArrayList<String> Allowed = new ArrayList<String>();
	private int ChatSpam = 2;
	private int CmdSpam = 3;
	public CCBot()
	{
		form.ReadFile(arl.GetProf(), Allowed);
	}
	private void RemovePlayer(String name)
	{
		LastChat.remove(name);
		LastCmd.remove(name);
	}
	private void CheckChatSpam(String Player)
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
		Player player = Bukkit.getServer().getPlayer(Player) ;
		player.kickPlayer(arl.GetCol() + "Don't spam the chat!");
		Log.Log(player.getName() + " was kicked for spamming the chat.");
		Bukkit.broadcastMessage(arl.GetCol() + player.getName() + " was kicked for spamming the chat.");
	}
	private void CheckCmdSpam(String Player)
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
		Player player = Bukkit.getServer().getPlayer(Player) ;
		player.kickPlayer(arl.GetCol() + "Don't spam commands!");
		Log.Log(player.getName() + " was kicked for spamming commands.");
		Bukkit.broadcastMessage(arl.GetCol() + player.getName() + " was kicked for spamming commands.");
	}
	private void Caps(String Player, String Message)
	{
		Message = Edit.RemoveNonLet(Message);
		if(Message.equals(Message.toUpperCase()))
		{
			if(Message.length() > 15)
			{
				Player player = Bukkit.getServer().getPlayer(Player) ;
				player.kickPlayer(arl.GetCol() + "Don't use all caps!");
				Log.Log(player.getName() + " was kicked for using caps.");
				Bukkit.broadcastMessage(arl.GetCol() + player.getName() + " was kicked for using caps.");
			}
		}
	}
	private void LangCheck(String Player, String Message)
	{
		ArrayList<String> langList = new ArrayList<String>();
		Message = Edit.RemoveNonLet(Message);
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
                	Player player = Bukkit.getServer().getPlayer(Player);
        			player.kickPlayer(arl.GetCol() + "Watch your language!");
        			Log.Log(player.getName() + " was kicked for using bad language.");
        			Bukkit.broadcastMessage(arl.GetCol() + player.getName() + " was kicked for using bad language.");
                    break;
                }
            }
        }
	}
	public void LogChat(String Player, String Message)
	{
		String MessageOrig = Message;
		Message = Player + ": " + Message;
		Log.Log(Message);		
		Player player = Bukkit.getServer().getPlayer(Player);
		if(!player.isOp())
		{
			Caps(Player, MessageOrig);
			LangCheck(Player, MessageOrig);
		}
		CheckChatSpam(Player);
	}
	public void LogCom(String Player, String Message)
	{
		Message = Player + " issued server command: " + Message;
		Log.Log(Message);
		CheckCmdSpam(Player);
	}
	public void LogConsole(String Message)
	{
		if(Message.startsWith("say"))
		{
			Message = "Console:" + Message.replaceFirst("say", "");
		}
		else
		{
			Message = "Console issued command: " + Message;
		}
		Log.Log(Message);
	}
	public void LogIn(String Player)
	{
		String Message = Player + " joined the game.";
		Log.Log(Message);
	} 
	public void LogOut(String Player)
	{
		RemovePlayer(Player);
		String Message = Player + " left the game.";
		Log.Log(Message);
	}
}