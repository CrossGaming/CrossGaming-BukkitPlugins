package com.github.CorporateCraft.necessities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;

public class ArrayLists
{
	public ArrayLists()
	{
		
	}
	public HashMap<String,String> Tps = new HashMap<String,String>();
	public ArrayList<String> LastTp = new ArrayList<String>();
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
	public void RemoveTps(String key)
	{
		this.Tps.remove(key);
	}
	public void AddTps(String key, String value)
	{
		this.Tps.put(key, value);
	}
	public void RemoveLastTp(int i)
	{
		this.LastTp.remove(i);
	}
	public void AddLastTp(String key)
	{
		this.LastTp.add(key);
	}
}