package com.github.CorporateCraft.necessities.CCBot;

public class CCBot
{
	CCBotLog Log = new CCBotLog();
	public CCBot()
	{
		
	}
	public void LogChat(String Player, String Message)
	{
		Message = Player + ": " + Message;
		Log.Log(Message);
	}
	public void LogCom(String Player, String Message)
	{
		Message = Player + " issued server command: " + Message;
		Log.Log(Message);
	}
	public void LogConsole(String Message)
	{
		if(Message.startsWith("say"))
		{
			Message = "Server:" + Message.replaceFirst("say", "");
		}
		else
		{
			Message = "Server issued command: " + Message;
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