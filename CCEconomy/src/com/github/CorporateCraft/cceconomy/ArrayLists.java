package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayLists
{
	public static ArrayList<String> Balances = new ArrayList<String>();
	public static ArrayList<String> SellPrices = new ArrayList<String>();
	public static ArrayList<String> BuyPrices = new ArrayList<String>();
	
	public static void StartLists()
	{
		UpdateBalances();
		UpdateSellPrices();
		UpdateBuyCosts();
	}
	
	private static void UpdateBalances()
	{
		Formatter.ReadFile(CCEconomy.balfile, Balances);
		Collections.sort(Balances);
	}
	
	private static void UpdateSellPrices()
	{
		Formatter.ReadFile(CCEconomy.sellfile, SellPrices);
	}
	
	private static void UpdateBuyCosts()
	{
		Formatter.ReadFile(CCEconomy.buyfile, BuyPrices);
	}
}