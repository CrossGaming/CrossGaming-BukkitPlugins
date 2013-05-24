package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Kits
{
	Variables var = new Variables();
	private static ArrayList<String> classes = new ArrayList<String>();
	private static ArrayList<String> alreadyChose = new ArrayList<String>();
	private File customConfigFile = new File("plugins/Hunger Games", "kits.yml");
	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	
	public boolean exists(String kitName)
	{
		for(String path : customConfig.getKeys(false))
			if(path.equalsIgnoreCase(kitName))
				return true;
		return false;
	}
	
	public boolean chose(String name)
	{
		return alreadyChose.contains(name);
	}
	
	public void clearKits()
	{
		alreadyChose.clear();
	}
	
	public void giveKit(Player p, String kit)
	{
		kit = kit.trim();
		setLists();
		alreadyChose.add(p.getName());
		PlayerInventory inv = p.getInventory();
		String truePath = "";
		short data = 0;
		for(String path : customConfig.getKeys(true))
			if(!path.equalsIgnoreCase(kit) && path.toUpperCase().startsWith(kit.toUpperCase()))
			{
				truePath = path.substring(kit.length() + 1, path.length());
				if(truePath.split(":").length > 1)
					data = (short) Integer.parseInt(truePath.split(":")[1]);
				else
					data = 0;
				inv.addItem(new ItemStack(Material.getMaterial(Integer.parseInt(truePath.split(":")[0])), customConfig.getInt(kit + "." + truePath), data));
			}
	}
	public void listKits(Player p)
	{
		setLists();
		String temp = "";
		for(String kit : classes)
			temp = temp + kit + ", ";
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 1);
		p.sendMessage(var.defaultCol() + "Available kits: " + temp + ".");
	}
	private void setLists()
	{
		if(!classes.isEmpty())
			classes.clear();
		for(String kit : customConfig.getKeys(false))
			classes.add(kit);
	}
}