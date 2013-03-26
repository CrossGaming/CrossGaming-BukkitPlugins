package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestRandomizer
{
	Game g = new Game();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	private File customConfigFileChest = new File("plugins/Hunger Games", "chests.yml");
	private YamlConfiguration customConfigChest = YamlConfiguration.loadConfiguration(customConfigFileChest);
	private File customConfFile = new File("plugins/Hunger Games", "config.yml");
	private YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
	private static ArrayList<Integer> blockIds = new ArrayList<Integer>();
	private static ArrayList<Double> percentChance = new ArrayList<Double>();
	public ChestRandomizer()
	{
		
	}
	
	public int chestId()
    {
		Random r = new Random();
        int temp = r.nextInt(1000);//Gives a number to find the random.
        double totalPercent = 0;
        for(int x = 0; x < blockIds.size(); x++)//Runs through 5 times, 5 items.
        {
        	totalPercent = 0;
            for(int y = x; y > 0; y--)//Calculates for total percentage of all items up to the current item.
                totalPercent = totalPercent + percentChance.get(y);
            if(temp < 10*totalPercent && temp > 10*totalPercent - 10*percentChance.get(x))
                return blockIds.get(x);
        }
        return 0;
    }
	
	public void randomizeChests()
	{
		setLists();
		String world = g.getNext();
		int x1 = customConfig.getInt(world + ".corner1.x");
		int y1 = customConfig.getInt(world + ".corner1.y");
		int z1 = customConfig.getInt(world + ".corner1.z");
		int x2 = customConfig.getInt(world + ".corner2.x");
		int y2 = customConfig.getInt(world + ".corner2.y");
		int z2 = customConfig.getInt(world + ".corner2.z");
		int temp;
		if(x1 < x2)
		{
			temp = x2;
			x2 = x1;
			x1 = temp;
		}
		if(y1 < y2)
		{
			temp = y2;
			y2 = y1;
			y1 = temp;
		}
		if(z1 < z2)
		{
			temp = z2;
			z2 = z1;
			z1 = temp;
		}
		World w = Bukkit.getWorld(world);
		for(int x = x2; x <= x1; x++)
			for(int y = y2; y <= y1; y++)
				for(int z = z2; z <= z1; z++)
				{
					Block b = w.getBlockAt(x, y, z);
					if(b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST)
					{
						if(b.getState() instanceof Chest)
						{
							Chest c = (Chest) b.getState();
							Inventory inv = c.getBlockInventory();
							inv.clear();
							int chestAmount = items();
							for(int i = 0; i < chestAmount; i++)
								inv.addItem(new ItemStack(Material.getMaterial(chestId()), 1));
						}
					}
				}
	}
	
	private int items()
	{
		Random r = new Random();
		int min = customConf.getInt("minPerChest");
		int max = customConf.getInt("maxPerChest");
		if(min >= max)
			return max;
		int temp = r.nextInt(max) + 1;
		while(min > temp)
			temp = r.nextInt(max) + 1;
		return temp;
	}
	
	private void setLists()
	{
		blockIds.clear();
		percentChance.clear();
		for(String path : customConfigChest.getKeys(false))
		{
			blockIds.add(Integer.parseInt(path));
			percentChance.add(customConfigChest.getDouble(path));
		}
	}
}