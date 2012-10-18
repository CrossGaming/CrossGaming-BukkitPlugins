package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class BalChecks
{
	public static String Bal(String name)
	{
		String file;
		file = "plugins/CCEconomy/moneytracker.txt";
		String playersbal= "";
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        if(inputText.startsWith((name + " ")))
		        {
		        	playersbal = inputText.replace(name + " ", "");
		        	return playersbal;
		        }
		    }
		}
		catch (IOException ex)
		{
		    return null;
		}
		return null;
	}
	
	public static String BalTop(int page, int time)
	{
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Double> balsort = new ArrayList<Double>();
		String file = "plugins/CCEconomy/moneytracker.txt";
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        list.add(inputText);
		        balsort.add(Double.parseDouble(inputText.split(" ")[1]));
		    }
		}
		catch (IOException ex){}
		Collections.sort(list);
		Collections.sort(balsort);
		Collections.reverse(balsort);
		page = page * 10;
		if (list.size() < time + page + 1)
		{
			return null;
		}
		if (time == 10)
		{
			return null;
		}
		int occurrence = 1;
		for (int i = 0; i < page+time; i++)
		{
			if(balsort.get(i).equals(balsort.get(page + time)))
			{
				occurrence++;
			}
		}
		String StrBal = Formatter.roundTwoDecimals(balsort.get(page+time));
		int BalSpot = BaltopCords(StrBal, occurrence);
		if (BalSpot == -1)
		{
			return null;
		}
		return list.get(BalSpot);
	}
	
	public static int BaltopCords(String money, int occurrence)
	{
		ArrayList<String> list = new ArrayList<String>();
		String file = "plugins/CCEconomy/moneytracker.txt";
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        list.add(inputText);
		    }
		}
		catch (IOException ex){}
		Collections.sort(list);
		int counter = 1;
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).contains(" " + money))
			{
				if(counter == occurrence)
				{
					return i;
				}
				counter++;
			}
		}
		return -1;
	}
	
	public static int BaltopPages()
	{
		ArrayList<String> list = new ArrayList<String>();
		String file = "plugins/CCEconomy/moneytracker.txt";
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        list.add(inputText);
		    }
		}
		catch (IOException ex){}
		int rounder = 0;
		if (list.size()%10 != 0)
		{
			rounder = 1;
		}
		return (list.size()/10) + rounder;
	}
}