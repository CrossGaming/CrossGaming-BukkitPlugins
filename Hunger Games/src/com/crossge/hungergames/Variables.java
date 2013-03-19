package com.crossge.hungergames;

import org.bukkit.ChatColor;

public class Variables
{
	public Variables()
	{
		
	}
	private ChatColor def = ChatColor.BLUE;
	private ChatColor error = ChatColor.DARK_RED;
	
	public ChatColor defaultCol()
	{
		return def;
	}
	public ChatColor errorCol()
	{
		return error;
	}
}