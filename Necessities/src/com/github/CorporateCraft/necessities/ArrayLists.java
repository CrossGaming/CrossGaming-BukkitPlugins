package com.github.CorporateCraft.necessities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;

public class ArrayLists
{
	public ArrayLists()
	{
		
	}
	private HashMap<String,String> Tps = new HashMap<String,String>();
	private ArrayList<String> LastTp = new ArrayList<String>();
	//Not just lists now but more a variables file
	private final String Motdfile = "plugins/Necessities/MOTD.txt";
	private final String Rulesfile = "plugins/Necessities/Rules.txt";
	private ChatColor messages = ChatColor.GREEN;
	
	public void StartLists()
	{

	}
	public HashMap<String,String> GetTps()
	{
		return this.Tps;
	}
	public ArrayList<String> GetLastTp()
	{
		return this.LastTp;
	}
	public String GetMotd()
	{
		return this.Motdfile;
	}
	public String GetRules()
	{
		return this.Rulesfile;
	}
	public ChatColor GetCol()
	{
		return this.messages;
	}
	public void UpdateTps(String key)
	{
		this.Tps.remove(key);
	}
	public void AddTps(String key, String value)
	{
		this.Tps.put(key, value);
	}
	public void UpdateLastTp(int i)
	{
		this.LastTp.remove(i);
	}
	public void AddLastTp(String key)
	{
		this.LastTp.add(key);
	}
}