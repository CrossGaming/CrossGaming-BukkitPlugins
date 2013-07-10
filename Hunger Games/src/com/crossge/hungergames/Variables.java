package com.crossge.hungergames;

import org.bukkit.ChatColor;

public class Variables
{
	private ChatColor def = ChatColor.BLUE;
	private ChatColor error = ChatColor.DARK_RED;
	private ChatColor death = ChatColor.DARK_RED;
	private ChatColor points = ChatColor.GOLD;
	private ChatColor district = ChatColor.DARK_GREEN;
	
	public String defaultCol()
	{
		return ChatColor.GREEN + "[" + ChatColor.GOLD + "HG" + ChatColor.GREEN + "] " + def;
	}
	public ChatColor trueDefaultCol()
	{
		return def;
	}
	public ChatColor deathCol()
	{
		return death;
	}
	public String errorCol()
	{
		return ChatColor.GREEN + "[" + ChatColor.GOLD + "HG" + ChatColor.GREEN + "] " + error;
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