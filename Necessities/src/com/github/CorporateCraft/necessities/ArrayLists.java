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
	//Not just lists now but more a variables file
	public final String Motdfile = "plugins/Necessities/MOTD.txt";
	public final String Rulesfile = "plugins/Necessities/Rules.txt";
	public ChatColor messages = ChatColor.GREEN;
	
	public void StartLists()
	{

	}
}