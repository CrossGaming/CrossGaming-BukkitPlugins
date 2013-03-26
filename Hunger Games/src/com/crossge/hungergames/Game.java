package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class Game
{
	Variables var = new Variables();
	private static String nextMap = "";
	private static boolean voting = false;
	private static ArrayList<String> maps = new ArrayList<String>();
	private static ArrayList<Integer> mvote = new ArrayList<Integer>();
	private static HashMap<String, Integer> votes = new HashMap<String, Integer>();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public Game()
	{
		
	}
	
	public String getNext()
	{
		if(maps.size() == 1)
			nextMap = maps.get(0);
		else if(nextMap.equals(""))
			holdVote();
		return nextMap;
	}
	
	private void m()
	{//Todo add randomized picking
		mvote.clear();
		mvote.add(0);
		mvote.add(1);
		mvote.add(2);
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
	public void holdVote()
	{
		m();
		voting = true;
		Bukkit.broadcastMessage(var.defaultCol() + "Maps you can vote for are:");
		for(int i = 0; i < maps.size(); i++)
		{
			if(i == 3)
				break;
			Bukkit.broadcastMessage(var.defaultCol() + Integer.toString(i + 1) + " " + maps.get(mvote.get(i)));
		}
	}
	public void addVote(String name, int map)
	{
		votes.put(name, map);
		int map1 = 0;
		int map2 = 0;
		int map3 = 0;
		for(String vote : votes.keySet())
		{
			if(votes.get(vote) == 1)
				map1++;
			else if(votes.get(vote) == 2)
				map2++;
			else if(votes.get(vote) == 3)
				map3++;
		}
		if(map1 > map2 && map1 > map3)
			nextMap = maps.get(mvote.get(0));
		else if(map2 > map1 && map2 > map3)
			nextMap = maps.get(mvote.get(1));
		else if(map3 > map1 && map3 > map2)
			nextMap = maps.get(mvote.get(2));
		else
			nextMap = maps.get(mvote.get(0));
	}
	public void delVote(String name)
	{
		votes.remove(name);
	}
	public void end()
	{
		nextMap = "";
	}
	public void start()
	{
		new Players().gameStart();
		votes.clear();
		voting = false;
	}
	public boolean voteHappening()
	{
		return voting;
	}
}