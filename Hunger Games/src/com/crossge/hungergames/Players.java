package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Players
{
	Game g = new Game();
	Stats s = new Stats();
	Variables var = new Variables();
	Sponsor spons = new Sponsor();
	ChestRandomizer cr = new ChestRandomizer();
	private static ArrayList<String> alive = new ArrayList<String>();
	private static ArrayList<String> origalive = new ArrayList<String>();
	private static ArrayList<String> dead = new ArrayList<String>();
	private static ArrayList<String> queued = new ArrayList<String>();
	private static ArrayList<String> spectating = new ArrayList<String>();
	private static ArrayList<String> sponsored = new ArrayList<String>();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	private static boolean alreadySponsor = false;
	private static boolean deathStarted = false;
	private static Timer t = new Timer();
	public Players()
	{
		
	}
	
	public boolean alreadySponsored(String name)
	{
		return sponsored.contains(name);
	}
	
	public void endTimer()
	{
		t.cancel();
	}
	
	public void addSponsored(String name)
	{
		sponsored.add(name);
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
	public void startSponsor()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "The players left now recieve sponsorships.");
		for(int i = 0; i < alive.size(); i++)
		{
			spons.giveItems(Bukkit.getPlayer(alive.get(i)));
		}
	}
	public void startDeath()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match started.");
		Player temp;
		for(int i = 0; i < alive.size(); i++)
		{
			temp = Bukkit.getPlayer(alive.get(i));
			temp.teleport(loc(i + 1));
		}
	}
	public boolean deathMatch()
	{
		return alive.size() <= 3;
	}
	public boolean sponsorStart()
	{
		if(((origalive.size() / 5) >= alive.size() || alive.size() == 4) && !alreadySponsor)
		{
			alreadySponsor = true;
			return true;
		}
		return false;
	}
	public boolean gameGoing()
	{
		return alive.size() > 0;
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
		String pathx = world + ".s25.x";
		String pathy = world + ".s25.y";
		String pathz = world + ".s25.z";
		spectating.add(name);
		hideSpec();
		Player p = Bukkit.getPlayer(name);
		p.setGameMode(GameMode.SURVIVAL);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(true);
		p.setCanPickupItems(false);
		p.teleport(new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz)));
	}
	public void delSpectating(String name)
	{
		spectating.remove(name);
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!p.canSee(Bukkit.getPlayer(name)))
				p.hidePlayer(Bukkit.getPlayer(name));
		}
		Player p = Bukkit.getPlayer(name);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(false);
		p.setCanPickupItems(true);
		p.performCommand("/spawn");
	}
	public void addDead(String name)
	{
		alive.remove(name);
		dead.add(name);
		Player p = Bukkit.getPlayer(name);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(false);
		p.setCanPickupItems(true);
		p.performCommand("/spawn");
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
		Player p = Bukkit.getPlayer(alive.get(0));
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(false);
		p.setCanPickupItems(true);
		p.performCommand("/spawn");
		return alive.get(0);
	}
	public void hideSpectators(Player p)
	{
		if(spectating.size() == 0)
			return;
		for(int i = 0; i < spectating.size(); i++)
		{
			p.hidePlayer(Bukkit.getPlayer(spectating.get(i)));
		}
	}
	public void unhideSpectators(Player p)
	{
		if(spectating.size() == 0)
			return;
		for(int i = 0; i < spectating.size(); i++)
		{
			if(!p.canSee(Bukkit.getPlayer(spectating.get(i))))
				p.showPlayer(Bukkit.getPlayer(spectating.get(i)));
		}
	}
	private void hideSpec()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			for(int i = 0; i < spectating.size(); i++)
			{
				p.hidePlayer(Bukkit.getPlayer(spectating.get(i)));
			}
		}
	}
	private void unhideSpec()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			for(int i = 0; i < spectating.size(); i++)
			{
				p.showPlayer(Bukkit.getPlayer(spectating.get(i)));
			}
		}
	}
	public void endGame()
	{
		alive.clear();
		origalive.clear();
		dead.clear();
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.setCanPickupItems(true);
		}
		unhideSpec();
		alreadySponsor = false;
		deathStarted = false;
		spectating.clear();
		sponsored.clear();
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
	public String district(String name)
	{
		for(int i = 0; i < origalive.size(); i++)
		{
			if(origalive.get(i).equals(name))
			{
				int temp = i + 1;
				if(temp + 1 > 12)
					temp = temp - 11;
				return Integer.toString(temp);
			}
		}
		return null;
	}
	public void gameStart()
	{
		alive.clear();
		origalive.clear();
		dead.clear();
		spectating.clear();
		for(int i = 0; i < queued.size(); i++)
		{
			alive.add(queued.get(i));
			origalive.add(queued.get(i));
		}
		queued.clear();
		cr.randomizeChests();
		joinGame();
		if(deathMatch())
		{
			deathCountdown();
		}
	}
	private void joinGame()
	{
		Player temp;
		for(int i = 0; i < alive.size(); i++)
		{
			temp = Bukkit.getPlayer(alive.get(i));
			temp.setGameMode(GameMode.SURVIVAL);
			temp.setFoodLevel(20);
			temp.setHealth(20);
			temp.setFlying(false);
			temp.teleport(loc(i + 1));
			temp.getInventory().clear();
			temp.getEquipment().clear();
			temp.setExp(-temp.getExp());
			s.addGame(alive.get(i));
		}
	}
	private Location loc(int number)
	{
		String world = g.getNext();
		String pathx = world + ".s" + Integer.toString(number) + ".x";//temporarily only have support for one map name
		String pathy = world + ".s" + Integer.toString(number) + ".y";//temporarily only have support for one map name
		String pathz = world + ".s" + Integer.toString(number) + ".z";//temporarily only have support for one map name
		return new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz));
	}
	public void deathCountdown()
	{
		if(!deathStarted)
		{
			deathStarted = true;
			Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 60 seconds.");
			t.schedule(new TimerTask(){public void run() {deathCountdown2();}}, 15000);
		}
	}
	private void deathCountdown2()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 45 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown3();}}, 15000);
	}
	private void deathCountdown3()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 30 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown4();}}, 15000);
	}
	private void deathCountdown4()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 15 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown5();}}, 5000);
	}
	private void deathCountdown5()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 10 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown6();}}, 5000);
	}
	private void deathCountdown6()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 5 seconds.");
		t.schedule(new TimerTask(){public void run() {startDeath();}}, 5000);
	}
}