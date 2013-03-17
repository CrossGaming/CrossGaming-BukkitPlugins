package com.crossge.necessities;

import org.bukkit.ChatColor;

public class ArrayLists
{
	public ArrayLists()
	{
		
	}
	private final String profanityFile = "plugins/Necessities/BadWords.txt";
	private final String allowedFile = "plugins/Necessities/GoodWords.txt";
	private ChatColor messages = ChatColor.GREEN;
	private ChatColor error = ChatColor.DARK_RED;
	private ChatColor errorMsg = ChatColor.RED;
	
	public String getProf()
	{
		return profanityFile;
	}
	public String getAl()
	{
		return allowedFile;
	}
	public ChatColor getCol()
	{
		return messages;
	}
	public ChatColor getEr()
	{
		return error;
	}
	public ChatColor getErMsg()
	{
		return errorMsg;
	}
}