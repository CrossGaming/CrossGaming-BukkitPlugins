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
	
	public void StartLists()
	{
		
	}
	public String GetMotd()
	{
		return this.Motdfile;
	}
	public String GetRules()
	{
		return this.Rulesfile;
	}
	public String GetProf()
	{
		return this.Profanityfile;
	}
	public ChatColor GetCol()
	{
		return this.messages;
	}
}