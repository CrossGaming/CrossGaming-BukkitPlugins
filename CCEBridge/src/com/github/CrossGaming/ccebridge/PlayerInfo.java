package com.github.CrossGaming.ccebridge;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
	   	if(temp == null || temp.equalsIgnoreCase("default"))
	   		temp = "guest";
	   	return temp;
	}
	public boolean hasCmd(String name, String node)
	{
	   	Player p = Bukkit.getPlayer(name);
	   	return p.hasPermission(node);
	}
}