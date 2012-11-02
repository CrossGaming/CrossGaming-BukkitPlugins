package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;
import java.util.Collections;

public class BalChecks
{
	public static String Bal(String name)
	{
		for(int i = 0; i < ArrayLists.Balances.size(); i++)
		{
			if(ArrayLists.Balances.get(i).startsWith(name))
			{
				return ArrayLists.Balances.get(i).split(" ")[1];
			}
		}
		return null;
	}
	
	public static String BalTop(int page, int time)
	{
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Double> balsort = new ArrayList<Double>();
		for(int i = 0; i < ArrayLists.Balances.size(); i++)
		{
			list.add(ArrayLists.Balances.get(i));
	        balsort.add(Double.parseDouble(ArrayLists.Balances.get(i).split(" ")[1]));
		}
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
		for(int i = 0; i < ArrayLists.Balances.size(); i++)
		{
			list.add(ArrayLists.Balances.get(i));
		}
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
		int rounder = 0;
		if (ArrayLists.Balances.size()%10 != 0)
		{
			rounder = 1;
		}
		return (ArrayLists.Balances.size()/10) + rounder;
	}
}