package com.crossge.hungergames;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sponsor
{
	Game g = new Game();
	private File customConfigFileSponsor = new File("plugins/Hunger Games", "sponsors.yml");
	private YamlConfiguration customConfigSponsor = YamlConfiguration.loadConfiguration(customConfigFileSponsor);
	private File customConfFile = new File("plugins/Hunger Games", "config.yml");
	private YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
	private static ArrayList<Integer> blockIds = new ArrayList<Integer>();
	private static ArrayList<Double> percentChance = new ArrayList<Double>();
	private static ArrayList<Short> damageValue = new ArrayList<Short>();
	
	public void giveItems(Player p)
	{
		setLists();
		int times = customConf.getInt("itemsPerSponsor");
		int loc;
		int mat;
		short data;
		for(int i = 0; i < times; i++)
		{
			loc = sponsorIdLocation();
			mat = 0;
			data = 0;
			if(loc != -1)
			{
				mat = blockIds.get(loc);
				data = damageValue.get(loc);
			}
			p.getInventory().addItem(new ItemStack(Material.getMaterial(mat), 1, data));
		}
	}	
	
	public int sponsorIdLocation()
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
	
	private void balance()
	{
		double total = 0;
		for(double d : percentChance)
			total += d;			
		if(total == 0)
			return;
		for(int i = 0; i < percentChance.size(); i++)
			percentChance.set(i, percentChance.get(i)*100/total);
		int i = 0;
		for(String path : customConfigSponsor.getKeys(false))
		{
			if(i >= percentChance.size())
				break;
			customConfigSponsor.set(path, percentChance.get(i));
			i++;
		}
		try
	   	{
			customConfigSponsor.save(customConfigFileSponsor);
		}
	   	catch (IOException e) {}
	}
	
	private void setLists()
	{
		blockIds.clear();
		percentChance.clear();
		short data = 0;
		for(String path : customConfigSponsor.getKeys(false))
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
			percentChance.add(customConfigSponsor.getDouble(path));
		}
		balance();
	}
}