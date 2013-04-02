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
	public Kits()
	{
		
	}
	
	public boolean exists(String kitName)
	{
		return classes.contains(kitName);
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
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(String path : customConfig.getKeys(true))
			if(!path.equalsIgnoreCase(kit) && path.toUpperCase().startsWith(kit.toUpperCase()))
				temp.add(Integer.parseInt(path.substring(kit.length() + 1, path.length())));
		for(int i = 0; i < temp.size(); i++)
			inv.addItem(new ItemStack(Material.getMaterial(temp.get(i)), customConfig.getInt(kit + "." + Integer.toString(temp.get(i)))));
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
		classes.clear();
		for(String kit : customConfig.getKeys(false))
			classes.add(kit);
	}
}