package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;
import org.bukkit.Material;

public class Materials
{	
	ArrayLists arl = new ArrayLists();
	Formatter form = new Formatter();
	private static ArrayList<String> materialNames = new ArrayList<String>();
	private static ArrayList<String> materialList = new ArrayList<String>();
	private final int maxItems = 2366;
	public Materials()
	{
		
	}
	public void updateMats()
	{
		setMaterials();
		updateFiles();
	}
	private void updateFiles()
	{
		String sell = arl.getSellFile();
		String buy = arl.getBuyFile();
		if(form.fileEmpty(sell))
		{
			form.writeFile(sell, materialList);
		}
		else
		{
			form.writeFile(sell, updateForNew(sell));
		}
		if(form.fileEmpty(buy))
		{
			form.writeFile(buy, materialList);
		}
		else
		{
			form.writeFile(buy, updateForNew(buy));
		}
	}
	
	public boolean itemExists(String item)
	{
		if(item == null)
		{
			return false;
		}
		if(Material.getMaterial(item) == null)
		{
			return false;
		}
		return true;
	}
	
	public String findItem(String item)
	{
		item = item.toUpperCase().replaceAll("_", "");
		String temp = null;
		for(int i = 0; i < materialList.size(); i++)
		{
			if(materialList.get(i).split(" ")[0].equalsIgnoreCase(item))
			{
				temp = materialNames.get(i);
			}
			if(materialList.get(i).split(" ")[0].equalsIgnoreCase(item + "item"))
			{
				temp = materialNames.get(i);
				break;
			}
		}
		return temp;
	}
	
	private void setMaterials()
	{
		for(int i = 0; i < maxItems; i++)
		{
			try
			{
				materialList.add(idToName(Material.getMaterial(i).getId()).replaceAll("_", "") + " null");
			}
			catch(Exception e){}
			try
			{
				materialNames.add(idToName(Material.getMaterial(i).getId()));
			}
			catch(Exception e){}
		}
	}
	
	private ArrayList<String> updateForNew(String file)
	{
		ArrayList<String> neww = new ArrayList<String>();
		ArrayList<String> current = new ArrayList<String>();
		form.readFile(file, current);
		for(int i = 0; i < current.size(); i++)
		{
			current.get(i).replaceAll("_", "");
		}
		for(int i = 0; i < materialList.size(); i++)
		{
			for(int j = 0; j < current.size(); j++)
			{
				if(materialList.get(i).split(" ")[0].equalsIgnoreCase(current.get(j).split(" ")[0]))
				{
					neww.add(current.get(j));
					break;
				}
				if(j + 1 == current.size())
				{
					neww.add(materialList.get(i));
				}
			}
		}
		return neww;
	}
	public String idToName(int id)
	{
	    return Material.getMaterial(id).name();
	}
}