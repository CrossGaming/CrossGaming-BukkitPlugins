package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
	public boolean isTool(ItemStack is)
	{
		//Wood tools
		if(is.getData().getItemType().equals(Material.WOOD_AXE))
			return true;
		if(is.getData().getItemType().equals(Material.WOOD_HOE))
			return true;
		if(is.getData().getItemType().equals(Material.WOOD_PICKAXE))
			return true;
		if(is.getData().getItemType().equals(Material.WOOD_SWORD))
			return true;
		if(is.getData().getItemType().equals(Material.WOOD_SPADE))
			return true;
		//Stone tools
		if(is.getData().getItemType().equals(Material.STONE_AXE))
			return true;
		if(is.getData().getItemType().equals(Material.STONE_HOE))
			return true;
		if(is.getData().getItemType().equals(Material.STONE_PICKAXE))
			return true;
		if(is.getData().getItemType().equals(Material.STONE_SWORD))
			return true;
		if(is.getData().getItemType().equals(Material.STONE_SPADE))
			return true;
		//Iron tools
		if(is.getData().getItemType().equals(Material.IRON_AXE))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_HOE))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_PICKAXE))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_SWORD))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_SPADE))
			return true;
		//Gold tools
		if(is.getData().getItemType().equals(Material.GOLD_AXE))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_HOE))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_PICKAXE))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_SWORD))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_SPADE))
			return true;
		//Diamond tools
		if(is.getData().getItemType().equals(Material.DIAMOND_AXE))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_HOE))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_PICKAXE))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_SWORD))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_SPADE))
			return true;
		//Leather Armor
		if(is.getData().getItemType().equals(Material.LEATHER_BOOTS))
			return true;
		if(is.getData().getItemType().equals(Material.LEATHER_CHESTPLATE))
			return true;
		if(is.getData().getItemType().equals(Material.LEATHER_HELMET))
			return true;
		if(is.getData().getItemType().equals(Material.LEATHER_LEGGINGS))
			return true;
		//Chainmail Armor
		if(is.getData().getItemType().equals(Material.CHAINMAIL_BOOTS))
			return true;
		if(is.getData().getItemType().equals(Material.CHAINMAIL_CHESTPLATE))
			return true;
		if(is.getData().getItemType().equals(Material.CHAINMAIL_HELMET))
			return true;
		if(is.getData().getItemType().equals(Material.CHAINMAIL_LEGGINGS))
			return true;
		//Iron Armor
		if(is.getData().getItemType().equals(Material.IRON_BOOTS))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_CHESTPLATE))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_HELMET))
			return true;
		if(is.getData().getItemType().equals(Material.IRON_LEGGINGS))
			return true;
		//Gold Armor
		if(is.getData().getItemType().equals(Material.GOLD_BOOTS))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_CHESTPLATE))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_HELMET))
			return true;
		if(is.getData().getItemType().equals(Material.GOLD_LEGGINGS))
			return true;
		//Diamond Armor
		if(is.getData().getItemType().equals(Material.DIAMOND_BOOTS))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_CHESTPLATE))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_HELMET))
			return true;
		if(is.getData().getItemType().equals(Material.DIAMOND_LEGGINGS))
			return true;
		//Other
		if(is.getData().getItemType().equals(Material.ANVIL))
			return true;
		if(is.getData().getItemType().equals(Material.CARROT_STICK))
			return true;
		if(is.getData().getItemType().equals(Material.FISHING_ROD))
			return true;
		if(is.getData().getItemType().equals(Material.FLINT_AND_STEEL))
			return true;
		if(is.getData().getItemType().equals(Material.SHEARS))
			return true;
		if(is.getData().getItemType().equals(Material.BOW))
			return true;
		return false;
	}
	public String idToName(int id)
	{
	    return Material.getMaterial(id).name();
	}
}