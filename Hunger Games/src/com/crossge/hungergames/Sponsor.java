package com.crossge.hungergames;

import java.io.File;
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
	public Sponsor()
	{
		
	}
	
	public void giveItems(Player p)
	{
		setLists();
		int times = customConf.getInt("itemsPerSponsor");
		for(int i = 0; i < times; i++)
			p.getInventory().addItem(new ItemStack(Material.getMaterial(sponsorId()), 1));
	}	
	
	
	public int sponsorId()
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
	
	private void setLists()
	{
		blockIds.clear();
		percentChance.clear();
		for(String path : customConfigSponsor.getKeys(false))
		{
			blockIds.add(Integer.parseInt(path));
			percentChance.add(customConfigSponsor.getDouble(path));
		}
	}
}