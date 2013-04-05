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
	private static ArrayList<Short> damageValue = new ArrayList<Short>();
	private static ArrayList<Integer> chestSpots = new ArrayList<Integer>();
	public ChestRandomizer()
	{
		
	}
	
	public int chestIdLocation()
    {
		Random r = new Random();
        int temp = r.nextInt(1000);//Gives a number to find the random.
        double totalPercent = 0;
        for(int x = 0; x < blockIds.size(); x++)//Runs through 5 times, 5 items.
        {
        	totalPercent = totalPercent + percentChance.get(x);//Calculates for total percentage of all items up to the current item.
            if(temp < 10*totalPercent && temp > 10*totalPercent - 10*percentChance.get(x))
                return x;
        }
        return -1;
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
		int chestAmount;
		int loc;
		int mat;
		short data;
		for(int x = x2; x <= x1; x++)
			for(int y = y2; y <= y1; y++)
				for(int z = z2; z <= z1; z++)
				{
					Block b = w.getBlockAt(x, y, z);
					if(b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST)
					{
						resetSpots();
						Chest c = (Chest) b.getState();
						Inventory inv = c.getBlockInventory();
						inv.clear();
						chestAmount = items();
						for(int i = 0; i < chestAmount; i++)
						{
							loc = chestIdLocation();
							mat = 0;
							data = 0;
							if(loc != -1)
							{
								mat = blockIds.get(loc);
								data = damageValue.get(loc);
							}
							inv.setItem(chestLoc(), new ItemStack(Material.getMaterial(mat), 1, data));
						}
					}
				}
	}
	
	public void emptyChests()
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
						Chest c = (Chest) b.getState();
						Inventory inv = c.getBlockInventory();
						inv.clear();
					}
				}
	}
	
	private void resetSpots()
	{
		chestSpots.clear();
		for(int i = 0; i < 27; i++)
			chestSpots.add(i);
	}
	
	private int chestLoc()
	{
		Random r = new Random();
		int temp = r.nextInt(chestSpots.size());
		int ret = chestSpots.get(temp);
		chestSpots.remove(temp);
		return ret;
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
		short data = 0;
		for(String path : customConfigChest.getKeys(false))
		{
			if(path.split(":").length > 1)
			{
				blockIds.add(Integer.parseInt(path.split(":")[0]));
				data = (short) Integer.parseInt(path.split(":")[1]);
			}
			else
			{
				blockIds.add(Integer.parseInt(path));
				data = 0;
			}
			damageValue.add(data);
			percentChance.add(customConfigChest.getDouble(path));
		}
	}
}