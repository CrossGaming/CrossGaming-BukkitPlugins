package com.github.CorporateCraft.cceconomy;

import java.util.HashMap;

public class Trade
{
	private static HashMap<String,String> trades = new HashMap<String,String>();
	public Trade()
	{
		
	}
	public boolean hasTrade(String pname, String offerpname)
	{
		return trades.containsKey(pname + " " + offerpname);
	}
	
	public void createTrade(String Inform)
	{
		String key = Inform.split(" ")[0] + " " + Inform.split(" ")[1];
		String value = Inform.split(" ")[2] + " " + Inform.split(" ")[3] + " " + Inform.split(" ")[4] + " " + Inform.split(" ")[5];
		trades.put(key, value);
	}
	
	public String acceptTrade(String pname, String offerpname)
	{
		String info = trades.get(pname + " " + offerpname);
		removeTrade(pname, offerpname);
		return info;
	}
	
	public void denyTrade(String pname, String offerpname)
	{
		removeTrade(pname, offerpname);
	}
	
	public void removeTrade(String pname, String offerpname)
	{
		trades.remove(pname + " " + offerpname);
	}
}