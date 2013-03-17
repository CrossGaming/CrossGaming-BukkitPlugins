package com.crossge.ccebridge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CmdPrices
{
	private FileConfiguration customConfig = null;
   	private File customConfigFile = null;
   	RankPrices rp = new RankPrices();
   	private static ArrayList<String> co = new ArrayList<String>();
   	private RankPrices pr = new RankPrices();
	public CmdPrices()
	{
		upList();
	}
	public boolean canBuy(String cmd, String rank)
	{
		rank = rank.toUpperCase();
		cmd = cmd.toUpperCase();
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	if(!customConfig.contains(cmd))
	   		return false;
		String price = customConfig.getString(cmd);
		if(rp.hasRank(rank, price.split(" ")[0]))
			return true;
		return false;
	}
	public boolean realCommand(String cmd)
	{
		cmd = cmd.toUpperCase();
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		return customConfig.contains(cmd);
	}
	
	public String cost(String cmd)
	{
		cmd = cmd.toUpperCase();
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	if(!customConfig.contains(cmd))
	   		return null;
	   	String price = customConfig.getString(cmd);
		return price.split(" ")[1];
	}
	public double getCost(String cmd)
	{
		cmd = cmd.toUpperCase();
		String costPerUnit = cost(cmd);
		if(costPerUnit == null)
			return -1.00;
		double cost = Double.parseDouble(costPerUnit);
		return cost;
	}
	private void upList()
	{
		co.clear();
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	Set<String> temp = customConfig.getKeys(false);
		for(String r : temp)
		{
			co.add(r + " " + customConfig.getString(r));
		}
		ordList();
	}
	private void ordList()
	{
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();
		for(String cmd : co)
		{
			temp.add(cmd.split(" ")[1] + " " + cmd.split(" ")[0] + " " + cmd.split(" ")[2]);
		}
		ArrayList<String> l = new ArrayList<String>();
		l.add("GUEST");
		for(String d : pr.rOrd())
		{
			l.add(d);
		}
		for(int i = 0; i < l.size(); i++)
		{
			for(String m : temp)
			{
				if(l.get(i).equals(m.split(" ")[0]))
					temp2.add(m.split(" ")[1] + " " + m.split(" ")[0] + " " + m.split(" ")[2]);
			}
		}
		co = temp2;
	}
	public void addCommand(String rank, String cmd, String price)
	{
		rank = rank.toUpperCase();
		cmd = cmd.toUpperCase();
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	customConfig.set(cmd, rank + " " + price);
	   	try
	   	{
			customConfig.save(customConfigFile);
		}
	   	catch (IOException e) {}
	   	upList();
	}
	
	public void removeCommand(String cmd)
	{
		cmd = cmd.toUpperCase();
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	if(customConfig.contains(cmd))
	   		customConfig.set(cmd, null);
	   	try
	   	{
			customConfig.save(customConfigFile);
		}
	   	catch (IOException e) {}
	   	upList();
	}
	public int priceListPages()
	{
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		int rounder = 0;
		if (co.size()%10 != 0)
			rounder = 1;
		return (co.size()/10) + rounder;
	}
	public String priceLists(int page, int time)
	{
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	Set<String> temp = customConfig.getKeys(false);
		page = page * 10;
		if (temp.size() < time + page + 1)
			return null;
		if (time == 10)
			return null;
		return co.get(page + time);
	}
}