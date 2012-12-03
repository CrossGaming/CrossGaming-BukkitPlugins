package com.github.CorporateCraft.necessities.CCBot;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CCBot
{
	ArrayLists arl = new ArrayLists();
	CCBotLog Log = new CCBotLog();
	Formatter form = new Formatter();
	EditString Edit = new EditString();
	private String[] LastChat = new String[10];
	private String[] LastCmd = new String[20];
	private ArrayList<String> Allowed = new ArrayList<String>();
	private final Timer timer = new Timer();
	public CCBot()
	{
		form.ReadFile(arl.GetProf(), Allowed);
	}
	public void CancelTimer()
	{
		timer.cancel();
	}
	public void StartTimer()
	{
		timer.schedule(new TimerTask() {public void run() {ResetSpam();}}, 15000/*15 seconds*/);
	}
	private void ResetSpam()
	{
		for(int i = 0; i < LastCmd.length; i++)
		{
			LastCmd[i] = "";
		}
		for(int i = 0; i < LastChat.length; i++)
		{
			LastChat[i] = "";
		}
		timer.schedule(new TimerTask() {public void run() {ResetSpam();}}, 15000/*15 seconds*/);
	}
	private void CheckChatSpam()
	{
		Boolean Spam = true;
		for(int i = 0; i < LastChat.length; i++)
		{
			for(int j = i+1; j < LastChat.length; j++)
			{
				if(!LastChat[j].equalsIgnoreCase(LastChat[i]))
				{
					Spam = false;
					break;
				}
			}
		}
		if(Spam)
		{
			Player player = Bukkit.getServer().getPlayer(LastChat[0]) ;
			player.kickPlayer(arl.GetCol() + "Don't spam the chat!");
			Log.Log(player.getName() + " was kicked for spamming the chat.");
			Bukkit.broadcastMessage(arl.GetCol() + player.getName() + " was kicked for spamming the chat.");
			for(int i = 0; i < LastChat.length; i++)
			{
				LastChat[i] = "";
			}
		}
	}
	private void CheckCmdSpam()
	{
		Boolean Spam = true;
		for(int i = 0; i < LastCmd.length; i++)
		{
			for(int j = i+1; j < LastCmd.length; j++)
			{
				if(!LastCmd[j].equalsIgnoreCase(LastCmd[i]))
				{
					Spam = false;
					break;
				}
			}
		}
		if(Spam)
		{
			Player player = Bukkit.getServer().getPlayer(LastCmd[0]) ;
			player.kickPlayer(arl.GetCol() + "Don't spam commands!");
			Log.Log(player.getName() + " was kicked for spamming commands.");
			Bukkit.broadcastMessage(arl.GetCol() + player.getName() + " was kicked for spamming commands.");
			for(int i = 0; i < LastCmd.length; i++)
			{
				LastCmd[i] = "";
			}
		}
	}
	private void SpamCmdCheck(String Player)
	{
		if(LastCmd[0] == null)
		{
			for(int i = 0; i < LastCmd.length; i++)
			{
				LastCmd[i] = "";
			}
		}
		for(int i = LastCmd.length - 1; i > 0; i--)
		{
			LastCmd[i] = LastCmd[i-1];
		}
		LastCmd[0] = Player;
		CheckCmdSpam();
	}
	private void SpamChatCheck(String Player)
	{
		if(LastChat[0] == null)
		{
			for(int i = 0; i < LastChat.length; i++)
			{
				LastChat[i] = "";
			}
		}
		for(int i = LastChat.length - 1; i > 0; i--)
		{
			LastChat[i] = LastChat[i-1];
		}
		LastChat[0] = Player;
		CheckChatSpam();
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
		SpamChatCheck(Player);
		if(!player.isOp())
		{
			Caps(Player, MessageOrig);
			LangCheck(Player, MessageOrig);
		}
	}
	public void LogCom(String Player, String Message)
	{
		Message = Player + " issued server command: " + Message;
		Log.Log(Message);
		SpamCmdCheck(Player);
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
		String Message = Player + " left the game.";
		Log.Log(Message);
	}
}