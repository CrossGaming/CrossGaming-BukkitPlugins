package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Material;

public class Materials
{
	private static final int MaxItems = 2366;
	private static ArrayList<String> Materials = new ArrayList<String>();
	
	public static void UpdateMats()
	{
		SetMaterials();
		UpdateFiles();
	}
	
	private static void UpdateFiles()
	{
		if(Formatter.FileEmpty(CCEconomy.sellfile))
		{
			Formatter.WriteFile(CCEconomy.sellfile, Materials);
		}
		else
		{
			ArrayList<String> SellList = UpdateForNew(CCEconomy.sellfile);
			Formatter.WriteFile(CCEconomy.sellfile, SellList);
		}
		if(Formatter.FileEmpty(CCEconomy.buyfile))
		{
			Formatter.WriteFile(CCEconomy.buyfile, Materials);
		}
		else
		{
			ArrayList<String> BuyList = UpdateForNew(CCEconomy.buyfile);
			Formatter.WriteFile(CCEconomy.buyfile, BuyList);
		}
	}
	
	private static void SetMaterials()
	{
		for(int i = 0; i < MaxItems; i++)
		{
			try
			{
				Materials.add(idToName(Material.getMaterial(i).getId()).replaceAll("_", "") + " null");
			}
			catch(Exception e){}
		}
	}
	
	private static ArrayList<String> UpdateForNew(String file)
	{
		ArrayList<String> New = new ArrayList<String>();
		ArrayList<String> Current = new ArrayList<String>();
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
		        Current.add(inputText.replaceAll("_", ""));
		    }
		}
		catch (IOException ex){}
		for(int i = 0; i < Materials.size(); i++)
		{
			for(int j = 0; j < Current.size(); j++)
			{
				if(Materials.get(i).split(" ")[0].equalsIgnoreCase(Current.get(j).split(" ")[0]))
				{
					New.add(Current.get(j));
					break;
				}
				if(j + 1 == Current.size())
				{
					New.add(Materials.get(i));
				}
			}
		}
		return New;
	}
	
	public static String idToName(int id)
	{
	    return Material.getMaterial(id).name();
	}
}