package com.crossge.hungergames;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sponsor
{
	ChestRandomizer chest = new ChestRandomizer();
	private File customConfFile = new File("plugins/Hunger Games", "config.yml");
	private YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
	public Sponsor()
	{
		
	}
	
	public void giveItems(Player p)
	{
		int times = customConf.getInt("itemsPerSponsor");
		for(int i = 0; i < times; i++)//We use the chest randomizer to randomly pick the user items
			p.getInventory().addItem(new ItemStack(Material.getMaterial(chest.chestId()), 1));
	}	
}