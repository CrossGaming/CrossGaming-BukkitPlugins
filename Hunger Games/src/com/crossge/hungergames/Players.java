package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Players
{
	Game g = new Game();
	Stats s = new Stats();
	private static ArrayList<String> alive = new ArrayList<String>();
	private static ArrayList<String> dead = new ArrayList<String>();
	private static ArrayList<String> queued = new ArrayList<String>();
	private static ArrayList<String> spectating = new ArrayList<String>();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public Players()
	{
		
	}
	
	public String leftAlive()
	{
		String temp = "";
		for(int i = 0; i < alive.size(); i++)
		{
			temp += alive.get(i) + ", ";
		}
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 2);
		temp += ".";
		return temp;
	}
	public boolean gameGoing()
	{
		return alive.size() > 1;
	}
	public boolean isAlive(String name)
	{
		return alive.contains(name);
	}
	public boolean isSpectating(String name)
	{
		return spectating.contains(name);
	}
	public void addSpectating(String name)
	{
		String world = g.getNext();
		spectating.add(name);
		String pathx = world + ".s25.x";//temporarily only have support for one map name
		String pathy = world + ".s25.y";//temporarily only have support for one map name
		String pathz = world + ".s25.z";//temporarily only have support for one map name
		customConfig.get(pathx);
		customConfig.get(pathy);
		customConfig.get(pathz);
		Bukkit.getPlayer(name).teleport(new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz)));
	}
	public void delSpectating(String name)
	{
		spectating.remove(name);
		Bukkit.getPlayer(name).chat("/spawn");
	}
	public void addDead(String name)
	{
		alive.remove(name);
		dead.add(name);
		Bukkit.getPlayer(name).chat("/spawn");
	}
	public String deceased()
	{
		if(dead.isEmpty())
			return "none";
		String temp = "";
		for(int i = 0; i < dead.size(); i++)
		{
			temp += dead.get(i) + ", ";
		}
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 1);
		temp += ".";
		return temp;
	}
	public String breathing()
	{
		if(alive.isEmpty())
			return "none";
		String temp = "";
		for(int i = 0; i < alive.size(); i++)
		{
			temp += alive.get(i) + ", ";
		}
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 1);
		temp += ".";
		return temp;
	}
	public String amount()
	{
		try
		{
			return Integer.toString(alive.size()) + " alive out of " + Integer.toString(alive.size() + dead.size()) + " total.";
		}
		catch(Exception e)
		{
			return "Game not started";
		}
	}
	public boolean onePlayerLeft()
	{
		return alive.size() == 1;
	}
	public String winner()
	{
		s.addWin(alive.get(0));
		s.addPoints(alive.get(0), 20);
		return alive.get(0);
	}
	public void endGame()
	{
		alive.clear();
		dead.clear();
		spectating.clear();
		g.end();
	}
	public void addToQueue(String name)
	{
		if(g.getNext().equals(""))
		{
		//EMPTY IF :D	
		}
		queued.add(name);
	}
	public void removeFromQueue(String name)
	{
		queued.remove(name);
	}
	public boolean queueFull()
	{
		return queued.size() == 24;
	}
	public int posInQueue(String name)
	{
		return queued.indexOf(name) + 1;
	}	
	public void gameStart()
	{
		alive.clear();
		dead.clear();
		spectating.clear();
		for(int i = 0; i < queued.size(); i++)
		{
			alive.add(queued.get(i));
		}
		queued.clear();
		joinGame();
	}
	private void joinGame()
	{
		Player temp;
		for(int i = 0; i < alive.size(); i++)
		{
			temp = Bukkit.getPlayer(alive.get(i));
			temp.setGameMode(GameMode.ADVENTURE);
			temp.setFoodLevel(10);
			temp.setHealth(10);
			temp.setFlying(false);
			temp.teleport(loc(i + 1));
			temp.getInventory().clear();
			temp.getEquipment().clear();
			temp.setExp(0);
			s.addGame(alive.get(i));
		}
	}
	private Location loc(int number)
	{
		String world = g.getNext();
		String pathx = world + ".s" + Integer.toString(number) + ".x";//temporarily only have support for one map name
		String pathy = world + ".s" + Integer.toString(number) + ".y";//temporarily only have support for one map name
		String pathz = world + ".s" + Integer.toString(number) + ".z";//temporarily only have support for one map name
		customConfig.get(pathx);
		customConfig.get(pathy);
		customConfig.get(pathz);
		return new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz));
	}
}