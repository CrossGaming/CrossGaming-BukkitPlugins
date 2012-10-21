package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Prices
{
	public static String Cost(String file, String ItemName)
	{
		String ItemCost = "";
		ItemName.toUpperCase();
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
		        //STONE null
		        if(inputText.startsWith((ItemName + " ")))
		        {
		        	ItemCost = inputText.replace(ItemName + " ", "");
		        	return ItemCost;
		        }
		    }
		}
		catch (IOException ex){}
		return null;
	}
	
	public static void SetCost(String file, String itemname, String amount)
	{
		itemname = itemname.toUpperCase();
		ArrayList<String> list = new ArrayList<String>();
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
		int spotinlist = list.indexOf(itemname + " " + Cost(file, itemname));
		String newcost;
		newcost = itemname + " " + amount;
		list.set(spotinlist, newcost);
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
	}
}