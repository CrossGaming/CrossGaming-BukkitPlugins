package com.crossge.hungergames;

import org.bukkit.ChatColor;

public class Variables
{
	public Variables()
	{
		
	}
	private ChatColor def = ChatColor.BLUE;
	private ChatColor error = ChatColor.DARK_RED;
	private ChatColor points = ChatColor.GOLD;
	private ChatColor district = ChatColor.DARK_GREEN;
	
	public ChatColor defaultCol()
	{
		return def;
	}
	public ChatColor errorCol()
	{
		return error;
	}
	public ChatColor pointCol()
	{
		return points;
	}
	public ChatColor districtCol()
	{
		return district;
	}
}