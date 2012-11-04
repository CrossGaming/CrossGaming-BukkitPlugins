package com.github.CorporateCraft.cceconomy;

public class Trade
{
	public static boolean hasTrade(String pname, String offerpname)
	{
		return ArrayLists.Trades.containsKey(pname + " " + offerpname);
	}
	
	public static void CreateTrade(String Inform)
	{
		String key = Inform.split(" ")[0] + " " + Inform.split(" ")[1];
		String value = Inform.split(" ")[2] + " " + Inform.split(" ")[3] + " " + Inform.split(" ")[4] + " " + Inform.split(" ")[5];
		ArrayLists.Trades.put(key, value);
	}
	
	public static void CreateItemTrade(String Inform)
	{
		String key = Inform.split(" ")[0] + " " + Inform.split(" ")[1];
		String value = Inform.split(" ")[2] + " " + Inform.split(" ")[3] + " " + Inform.split(" ")[4] + " " + Inform.split(" ")[5];
		ArrayLists.Trades.put(key, value);
	}
	
	public static String AcceptTrade(String pname, String offerpname)
	{
		String Info = ArrayLists.Trades.get(pname + " " + offerpname);
		removeTrade(pname, offerpname);
		return Info;
	}
	
	public static void DenyTrade(String pname, String offerpname)
	{
		removeTrade(pname, offerpname);
	}
	
	public static void removeTrade(String pname, String offerpname)
	{
		ArrayLists.Trades.remove(pname + " " + offerpname);
	}
}