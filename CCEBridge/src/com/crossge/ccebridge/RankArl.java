package com.crossge.ccebridge;

import org.bukkit.ChatColor;

public class RankArl
{
	public RankArl()
	{
		
	}
	private final String rankPriceFile = "plugins/CCEBridge/rankprices.txt";
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
	public String getRankPriceFile()
	{
		return rankPriceFile;
	}
}