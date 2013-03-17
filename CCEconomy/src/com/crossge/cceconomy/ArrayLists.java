package com.crossge.cceconomy;

import org.bukkit.ChatColor;

public class ArrayLists
{
	public ArrayLists()
	{
		
	}
	Formatter form = new Formatter();
	private final String balFile = "plugins/CCEconomy/moneytracker.txt";
	private final String sellFile = "plugins/CCEconomy/sellprices.txt";
	private final String buyFile = "plugins/CCEconomy/buyprices.txt";
	private ChatColor messages = ChatColor.GREEN;
	private ChatColor money = ChatColor.AQUA;
	
	public ChatColor getMoney()
	{
		return money;
	}
	public ChatColor getMessages()
	{
		return messages;
	}
	public String getBalFile()
	{
		return balFile;
	}
	public String getSellFile()
	{
		return sellFile;
	}
	public String getBuyFile()
	{
		return buyFile;
	}
}