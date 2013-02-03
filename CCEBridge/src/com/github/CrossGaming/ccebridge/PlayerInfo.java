package com.github.CrossGaming.ccebridge;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerInfo
{
	private FileConfiguration customConfig = null;
   	private File customConfigFile = null;
	public PlayerInfo()
	{
		
	}
	public String curRank(String name)
	{
		customConfigFile = new File("plugins/GroupManager/worlds/world", "users.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	String temp =  customConfig.getString("users." + name + ".group");
	   	if(temp == null)
	   	{
	   		temp = "guest";
	   	}
	   	return temp;
	}
	public boolean hasCmd(String name, String node)
	{
		customConfigFile = new File("plugins/GroupManager/worlds/world", "users.yml");
	   	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	   	List<String> temp = customConfig.getStringList("users." + name + ".permissions");
	   	if(temp.contains(node))
	   	{
	   		return true;
	   	}
	   	return false;
	}
}