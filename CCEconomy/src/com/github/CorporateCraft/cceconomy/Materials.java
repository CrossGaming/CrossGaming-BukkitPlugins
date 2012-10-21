package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;

public class Materials
{
	public static final int MaxItems = 2366;
	public static void UpdateIds()
	{
		ArrayList<String> MatList = new ArrayList<String>();
		for(int i = 0; i < MaxItems; i++)
		{
			try
			{
				MatList.add(idToName(Material.getMaterial(i).getId()).replaceAll("_", "") + " " + Material.getMaterial(i).getId());
			}
			catch(Exception e){}
		}
		WriteFile("plugins/CCEconomy/itemids.txt", MatList);
	}
	
	public static void UpdateSell()
	{
		ArrayList<String> SellList = new ArrayList<String>();
		SellList = GetNew("plugins/CCEconomy/sellprices.txt");
		WriteFile("plugins/CCEconomy/sellprices.txt", SellList);
	}
	
	public static void UpdateBuy()
	{
		ArrayList<String> BuyList = new ArrayList<String>();
		BuyList = GetNew("plugins/CCEconomy/buyprices.txt");
		WriteFile("plugins/CCEconomy/buyprices.txt", BuyList);
	}
	
	public static void StartFiles()
	{
		if(FileEmpty("plugins/CCEconomy/itemids.txt"))
		{
			UpdateIds();
		}
		if(FileEmpty("plugins/CCEconomy/sellprices.txt"))
		{
			ArrayList<String> SellList = new ArrayList<String>();
			for(int i = 0; i < MaxItems; i++)
			{
				try
				{
					SellList.add(idToName(Material.getMaterial(i).getId()).replaceAll("_", "") + " null");
				}
				catch(Exception e){}
			}
			WriteFile("plugins/CCEconomy/sellprices.txt", SellList);
		}
		if(FileEmpty("plugins/CCEconomy/buyprices.txt"))
		{
			ArrayList<String> BuyList = new ArrayList<String>();
			for(int i = 0; i < MaxItems; i++)
			{
				try
				{
					BuyList.add(idToName(Material.getMaterial(i).getId()).replaceAll("_", "") + " null");
				}
				catch(Exception e){}
			}
			WriteFile("plugins/CCEconomy/buyprices.txt", BuyList);
		}
	}
	
	public static Boolean FileEmpty(String file)
	{
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    String inputText = buff.readLine();
		    if(inputText == null)
		    {
		    	return true;
		    }
		}
		catch (IOException ex){}
		return false;
	}
	
	public static void WriteFile(String file, ArrayList<String> Info)
	{
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			for (int i = 0; i < Info.size(); i++)
			{
				bw.write(Info.get(i));
				bw.newLine();
			}
			bw.close();
		}
		catch (Exception e){}
	}
	
	public static ArrayList<String> GetNew(String file)
	{
		ArrayList<String> New = new ArrayList<String>();
		ArrayList<String> Current = new ArrayList<String>();
		ArrayList<String> MatList = new ArrayList<String>();
		for(int i = 0; i < MaxItems; i++)
		{
			try
			{
				MatList.add(idToName(Material.getMaterial(i).getId()));
			}
			catch(Exception e){}
		}
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
		        Current.add(inputText);
		    }
		}
		catch (IOException ex){}
		Boolean count = false;
		for(int i = 0; i < MatList.size(); i++)
		{
			for(int j = 0; j < Current.size(); j++)
			{
				if(MatList.get(i).equalsIgnoreCase(Current.get(j).split(" ")[0]))
				{
					New.add(Current.get(j).replaceAll("_", ""));
					count = true;
				}
				if(count == false)
				{
					if(j + 1 == Current.size())
					{
						New.add(MatList.get(i).replaceAll("_", "") + " null");
					}
				}
			}
			count = false;
		}
		return New;
	}
	
	public static String idToName(int id)
	{
	    return Material.getMaterial(id).name();
	}
}