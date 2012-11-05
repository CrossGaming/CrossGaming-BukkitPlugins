package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;
import org.bukkit.Material;

public class Materials
{	
	private static ArrayList<String> MaterialNames = new ArrayList<String>();
	public static final int MaxItems = 2366;
	
	public static void UpdateMats()
	{
		ArrayLists.SetMaterials();
		UpdateFiles();
		SetMaterialNames();
	}
	
	private static void UpdateFiles()
	{
		if(Formatter.FileEmpty(CCEconomy.sellfile))
		{
			Formatter.WriteFile(CCEconomy.sellfile, ArrayLists.MaterialList);
		}
		else
		{
			Formatter.WriteFile(CCEconomy.sellfile, UpdateForNew(CCEconomy.sellfile));
		}
		if(Formatter.FileEmpty(CCEconomy.buyfile))
		{
			Formatter.WriteFile(CCEconomy.buyfile, ArrayLists.MaterialList);
		}
		else
		{
			Formatter.WriteFile(CCEconomy.buyfile, UpdateForNew(CCEconomy.buyfile));
		}
	}
	
	public static Boolean ItemExists(String Item)
	{
		if(Item == null)
		{
			return false;
		}
		if(Material.getMaterial(Item) == null)
		{
			return false;
		}
		return true;
	}
	
	public static String FindItem(String Item)
	{
		for(int i = 0; i<ArrayLists.MaterialList.size(); i++)
		{
			if(ArrayLists.MaterialList.get(i).split(" ")[0].equalsIgnoreCase(Item))
			{
				return MaterialNames.get(i);
			}
			if(MaterialNames.get(i).equalsIgnoreCase(Item))
			{
				return MaterialNames.get(i);
			}
		}
		return null;
	}
	
	private static void SetMaterialNames()
	{
		for(int i = 0; i < MaxItems; i++)
		{
			try
			{
				MaterialNames.add(Materials.idToName(Material.getMaterial(i).getId()));
			}
			catch(Exception e){}
		}
	}
	
	private static ArrayList<String> UpdateForNew(String file)
	{
		ArrayList<String> New = new ArrayList<String>();
		ArrayList<String> Current = new ArrayList<String>();
		Formatter.ReadFile(file, Current);
		for(int i = 0; i < Current.size(); i++)
		{
			Current.get(i).replaceAll("_", "");
		}
		for(int i = 0; i < ArrayLists.MaterialList.size(); i++)
		{
			for(int j = 0; j < Current.size(); j++)
			{
				if(ArrayLists.MaterialList.get(i).split(" ")[0].equalsIgnoreCase(Current.get(j).split(" ")[0]))
				{
					New.add(Current.get(j));
					break;
				}
				if(j + 1 == Current.size())
				{
					New.add(ArrayLists.MaterialList.get(i));
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