package com.github.CorporateCraft.cceconomy;

import java.util.Collections;

public class PlayerToFile
{
	public static boolean DoesPlayerExist(String name)
	{
		for(int i = 0; i < ArrayLists.Balances.size(); i++)
		{
			if(ArrayLists.Balances.get(i).startsWith(name))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void AddPlayerToList(String name)
	{
		String file = "plugins/CCEconomy/moneytracker.txt";
		ArrayLists.Balances.add(name + " 0.00");
		Collections.sort(ArrayLists.Balances);
		Formatter.WriteFile(file, ArrayLists.Balances);
	}
}