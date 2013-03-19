package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import org.bukkit.configuration.file.YamlConfiguration;

public class Game
{
	private static String nextMap = "";
	private static ArrayList<String> maps = new ArrayList<String>();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public Game()
	{
		
	}
	
	public String getNext()
	{
		if(maps.size() == 1)
			nextMap = maps.get(0);
		return nextMap;
	}
	
	public void initMaps()
	{
		maps.clear();
		Set<String> temp = customConfig.getKeys(false);
		for(String r : temp)
		{
			maps.add(r);
		}
	}
	
	public String maps()
	{
		String m = "";
		for(int i = 0; i < maps.size(); i++)
		{
			m += maps.get(i) + ", ";
		}
		m = m.trim();
		m = m.substring(0, maps.size() - 1).trim();
		m = m + ".";
		return m;
	}
	
}