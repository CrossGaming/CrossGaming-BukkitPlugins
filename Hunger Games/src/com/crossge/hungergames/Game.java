package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;

public class Game
{
	Variables var = new Variables();
	Language lang = new Language();
	Kits kit = new Kits();
	private static String nextMap = "";
	private static boolean voting = false;
	private static int map1 = 0;
	private static int map2 = 0;
	private static int map3 = 0;
	private static ArrayList<String> maps = new ArrayList<String>();
	private static ArrayList<Integer> mvote = new ArrayList<Integer>();
	private static HashMap<String, Integer> votes = new HashMap<String, Integer>();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	
	public String getNext()
	{
		if(nextMap.equals(""))
		{
			if(maps.isEmpty())
				nextMap = null;
			else if(mvote.isEmpty())
				nextMap = maps.get(0);
			else
				nextMap = maps.get(mvote.get(0));
		}
		return nextMap;
	}
	public boolean canVote()
	{
		return !(maps.isEmpty() || maps.size() == 1);
	}
	private void m()
	{
		Random r = new Random();
		mvote.clear();
		ArrayList<Integer> mid = new ArrayList<Integer>();
		for(int i = 0; i < maps.size(); i++)
			mid.add(i);
		int temp = 0;
		for(int i = 0; i < 3; i++)
		{
			temp = r.nextInt(mid.size());
			mvote.add(mid.get(temp));
			mid.remove(temp);
		}
	}
	public void initMaps()
	{
		maps.clear();
		Set<String> temp = customConfig.getKeys(false);
		for(String r : temp)
			if(!r.equalsIgnoreCase("worldS"))
				maps.add(r);
	}
	public String maps()
	{
		String m = "";
		for(int i = 0; i < maps.size(); i++)
			m += maps.get(i) + ", ";
		m = m.trim();
		m = m.substring(0, maps.size() - 1).trim();
		m = m + ".";
		return m;
	}
	public void holdVote()
	{
		if(maps.isEmpty())
			initMaps();
		if(maps.size() == 1)
			return;
		if(!voting)
			m();
		voting = true;
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Maps you can vote for are:"));
		for(int i = 0; i < maps.size(); i++)
		{
			if(i == 3)
				break;
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Vote") + " " + Integer.toString(i + 1) + " " +
									lang.translate("for map") + " " + maps.get(mvote.get(i)) +
					" " + lang.translate("current votes") + ": " + votes(i));
		}
	}
	private String votes(int map)
	{
		map = map + 1;
		if(map == 1)
			return Integer.toString(map1);
		else if(map == 2)
			return Integer.toString(map2);
		else if(map == 3)
			return Integer.toString(map3);
		return "";
	}
	public String addVote(String name, int map)
	{
		int temp = 0;
		if(votes.containsKey(name))
			temp = votes.get(name);
		votes.put(name, map);
		if(map == 1)
			map1 = map1 + 1;
		else if(map == 2)
			map2 = map2 + 1;
		else if(map == 3)
			map3 = map3 + 1;
		if(temp == 1)
			map1 = map1 - 1;
		else if(temp == 2)
			map2 = map2 - 1;
		else if(temp == 3)
			map3 = map3 - 1;
		if(map1 > map2 && map1 > map3)
			nextMap = maps.get(mvote.get(0));
		else if(map2 > map1 && map2 > map3)
			nextMap = maps.get(mvote.get(1));
		else if(map3 > map1 && map3 > map2)
			nextMap = maps.get(mvote.get(2));
		else
			nextMap = maps.get(mvote.get(0));
		return maps.get(mvote.get(map - 1));
	}
	public void delVote(String name)
	{
		int temp = 0;
		if(votes.containsKey(name))
			temp = votes.get(name);
		if(temp == 1)
			map1 = map1 - 1;
		else if(temp == 2)
			map2 = map2 - 1;
		else if(temp == 3)
			map3 = map3 - 1;
		votes.remove(name);
	}
	public void end()
	{
		Bukkit.unloadWorld(nextMap, false);
		Bukkit.createWorld(new WorldCreator(nextMap));
		nextMap = "";
		kit.clearKits();
		map1 = 0;
		map2 = 0;
		map3 = 0;
		start();
	}
	public void start()
	{
		initMaps();
		if(maps.isEmpty())
			return;
		new Players().gameStart();
		votes.clear();
		voting = false;
		disableSave();
	}
	public boolean voteHappening()
	{
		return voting;
	}
	public void disableSave()
	{
		World w;
		for(String map : maps)
		{
			w = Bukkit.getWorld(map);
			if(w == null)
			{
				Bukkit.createWorld(new WorldCreator(map));
				w = Bukkit.getWorld(map);
			}
			w.setAutoSave(false);
		}
	}
}