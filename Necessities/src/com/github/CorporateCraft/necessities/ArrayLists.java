package com.github.CorporateCraft.necessities;

import org.bukkit.ChatColor;

public class ArrayLists
{
	public ArrayLists()
	{
		
	}
	private final String Motdfile = "plugins/Necessities/MOTD.txt";
	private final String Rulesfile = "plugins/Necessities/Rules.txt";
	private final String Profanityfile = "plugins/Necessities/BadWords.txt";
	private ChatColor messages = ChatColor.GREEN;
	private ChatColor Error = ChatColor.DARK_RED;
	private ChatColor ErrorMsg = ChatColor.RED;
	
	
	public String getMotd()
	{
		return this.Motdfile;
	}
	public String getRules()
	{
		return this.Rulesfile;
	}
	public String getProf()
	{
		return this.Profanityfile;
	}
	public ChatColor getCol()
	{
		return this.messages;
	}
	public ChatColor getEr()
	{
		return this.Error;
	}
	public ChatColor getErMsg()
	{
		return this.ErrorMsg;
	}
}