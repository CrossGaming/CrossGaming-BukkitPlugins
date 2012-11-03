package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		Balances.clear();
		try
		{
		    FileReader reader = new FileReader(CCEconomy.balfile);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        Balances.add(inputText);
		    }
		}
		catch (IOException ex){}
		Collections.sort(Balances);
	}
	
	private static void UpdateSellPrices()
	{
		SellPrices.clear();
		try
		{
		    FileReader reader = new FileReader(CCEconomy.sellfile);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        SellPrices.add(inputText);
		    }
		}
		catch (IOException ex){}
	}
	
	private static void UpdateBuyCosts()
	{
		BuyPrices.clear();
		try
		{
		    FileReader reader = new FileReader(CCEconomy.buyfile);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        BuyPrices.add(inputText);
		    }
		}
		catch (IOException ex){}
	}
}