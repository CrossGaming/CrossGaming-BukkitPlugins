package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
		    list.add(name + " 0.00");
		}
		catch (IOException ex){}
		Collections.sort(list);
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			for (int i = 0; i < list.size(); i++)
			{
				bw.write(list.get(i));
				bw.newLine();
			}
			bw.close();
		}
		catch (Exception e){}
		ArrayLists.UpdateBalances();
	}
}