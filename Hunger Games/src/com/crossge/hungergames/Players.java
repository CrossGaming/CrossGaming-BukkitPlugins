package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
	private File customConfFile = new File("plugins/Hunger Games", "config.yml");
   	private YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
	private static boolean alreadySponsor = false;
	private static boolean deathStarted = false;
	private static boolean death = false;
	private static boolean gameStarted = false;
	private static int xmin = 0;
	private static int xmax = 0;
	private static int zmin = 0;
	private static int zmax = 0;
	private static int xCornMin = 0;
	private static int xCornMax = 0;
	private static int zCornMin = 0;
	private static int zCornMax = 0;
	private static String worldName = "";
	private static Timer t = new Timer();
	private static Timer count = new Timer();
	private static Timer day = new Timer();
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
		t.purge();
		t = new Timer();
		count.cancel();
		count.purge();
		count = new Timer();
		day.cancel();
		day.purge();
		day = new Timer();
	}
	public void addSponsored(String name)
	{
		sponsored.add(name);
	}
	public String leftAlive()
	{
		String temp = "";
		for(int i = 0; i < alive.size(); i++)
			temp += alive.get(i) + ", ";
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 2);
		temp += ".";
		return temp;
	}
	public void startSponsor()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "The players left now recieve sponsorships.");
		for(int i = 0; i < alive.size(); i++)
			spons.giveItems(Bukkit.getPlayer(alive.get(i)));
	}
	public void escaping(Player p)
	{
		int x = p.getLocation().getBlockX();
		int z = p.getLocation().getBlockZ();
		if(x >= xmax || x <= xmin || z >= zmax || z <= zmin)
		{
			Bukkit.broadcastMessage(var.defaultCol() + p.getName() + " is trying to escape the death match.");
			Player temp;
			for(int i = 0; i < alive.size(); i++)
			{
				temp = Bukkit.getPlayer(alive.get(i));
				temp.teleport(loc(i + 1));
			}
		}
	}
	public void startDeath()
	{
		death = true;
		String world = g.getNext();
		String pathx = "";
		String pathz = "";
		int tempxmin = 30000000;
		int tempxmax = -30000000;
		int tempzmin = 30000000;
		int tempzmax = -30000000;
		int x = 0;
		int z = 0;
		for(int i = 1; i < customConf.getInt("maxPlayers"); i++)
		{
			pathx = world + ".s" + Integer.toString(i) + ".x";
			pathz = world + ".s" + Integer.toString(i) + ".z";
			if(customConfig.get(pathz) != null && customConfig.get(pathx) != null)
			{
				x = customConfig.getInt(pathx);
				z = customConfig.getInt(pathz);
				if(z > tempzmax)
					tempzmax = z;
				else if(z < tempzmin)
					tempzmin = z;
				if(x > tempxmax)
					tempxmax = x;
				else if(x < tempxmin)
					tempxmin = x;
			}
		}
		tempzmax = tempzmax + 5;
		tempzmin = tempzmin - 5;
		tempxmax = tempxmax + 5;
		tempxmin = tempxmin - 5;
		zmax = tempzmax;
		zmin = tempzmin;
		xmax = tempxmax;
		xmin = tempxmin;
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
		return alive.size() > 1;
	}
	public boolean allowStart()
	{
		return gameStarted;
	}
	public boolean isAlive(String name)
	{
		return alive.contains(name);
	}
	public boolean isSpectating(String name)
	{
		return spectating.contains(name);
	}
	public void spectate(Player p, Player target)
	{
		p.teleport(target);
	}
	public void addSpectating(String name)
	{
		String world = g.getNext();
		String pathx = world + ".s0.x";
		String pathy = world + ".s0.y";
		String pathz = world + ".s0.z";
		spectating.add(name);
		hideSpec();
		Player p = Bukkit.getPlayer(name);
		p.setGameMode(GameMode.CREATIVE);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setCanPickupItems(false);
		p.teleport(new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz)));
	}
	public boolean deathstarted()
	{
		return death;
	}
	public void escapingArena(Player p)
	{
		String world = g.getNext();
		if(!worldName.equalsIgnoreCase(world))
		{
			int x1 = customConfig.getInt(world + ".corner1.x");
			int y1 = customConfig.getInt(world + ".corner1.y");
			int z1 = customConfig.getInt(world + ".corner1.z");
			int x2 = customConfig.getInt(world + ".corner2.x");
			int y2 = customConfig.getInt(world + ".corner2.y");
			int z2 = customConfig.getInt(world + ".corner2.z");
			int temp;
			if(x1 < x2)
			{
				temp = x2;
				x2 = x1;
				x1 = temp;
			}
			if(y1 < y2)
			{
				temp = y2;
				y2 = y1;
				y1 = temp;
			}
			if(z1 < z2)
			{
				temp = z2;
				z2 = z1;
				z1 = temp;
			}
			xCornMax = x1;
			xCornMin = x2;
			zCornMax = z1;
			zCornMin = z2;
		}
		int x = p.getLocation().getBlockX();
		int z = p.getLocation().getBlockZ();
		if(x >= xCornMax)
		{
			p.sendMessage(var.defaultCol() + "You may not leave the arena.");
			Location l = new Location(p.getWorld(), x - 1, p.getLocation().getBlockY(), z);
			p.teleport(l);
		}
		else if(x <= xCornMin)
		{
			p.sendMessage(var.defaultCol() + "You may not leave the arena.");
			Location l = new Location(p.getWorld(), x + 1, p.getLocation().getBlockY(), z);
			p.teleport(l);
		}
		else if(z >= zCornMax)
		{
			p.sendMessage(var.defaultCol() + "You may not leave the arena.");
			Location l = new Location(p.getWorld(), x, p.getLocation().getBlockY(), z - 1);
			p.teleport(l);
		}
		else if(z <= zCornMin)
		{
			p.sendMessage(var.defaultCol() + "You may not leave the arena.");
			Location l = new Location(p.getWorld(), x, p.getLocation().getBlockY(), z + 1);
			p.teleport(l);
		}
	}
	public void delSpectating(String name)
	{
		spectating.remove(name);
		for(Player p : Bukkit.getOnlinePlayers())
			if(!p.canSee(Bukkit.getPlayer(name)))
				p.showPlayer(Bukkit.getPlayer(name));
		Player p = Bukkit.getPlayer(name);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(false);
		p.setCanPickupItems(true);
		p.performCommand("spawn");
	}
	public void addDead(String name)
	{
		alive.remove(name);
		dead.add(name);
		Player p = Bukkit.getPlayer(name);
		p.setGameMode(GameMode.SURVIVAL);
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(false);
		p.setCanPickupItems(true);
		PlayerInventory inv = p.getInventory();
		inv.clear();
		inv.setBoots(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setHelmet(new ItemStack(Material.AIR));
		p.performCommand("spawn");
	}
	public String deceased()
	{
		if(dead.isEmpty())
			return "none";
		String temp = "";
		for(int i = 0; i < dead.size(); i++)
			temp += dead.get(i) + ", ";
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
			temp += alive.get(i) + ", ";
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
		s.addWin(alive.get(0), 1);
		s.addPoints(alive.get(0), 20);
		Player p = Bukkit.getPlayer(alive.get(0));
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFlying(false);
		p.setCanPickupItems(true);
		p.performCommand("spawn");
		return alive.get(0);
	}
	public void hideSpectators(Player p)
	{
		if(spectating.size() == 0)
			return;
		for(int i = 0; i < spectating.size(); i++)
			p.hidePlayer(Bukkit.getPlayer(spectating.get(i)));
	}
	public void unhideSpectators(Player p)
	{
		if(spectating.size() == 0)
			return;
		for(int i = 0; i < spectating.size(); i++)
			if(Bukkit.getPlayer(spectating.get(i)) != null)
				p.showPlayer(Bukkit.getPlayer(spectating.get(i)));
	}
	private void hideSpec()
	{
		for(Player p : Bukkit.getOnlinePlayers())
			for(int i = 0; i < spectating.size(); i++)
				p.hidePlayer(Bukkit.getPlayer(spectating.get(i)));
	}
	public void unhideSpec()
	{
		for(Player p : Bukkit.getOnlinePlayers())
			for(Player j : Bukkit.getOnlinePlayers())
				p.showPlayer(j);
	}
	public void endGame()
	{
		alive.clear();
		origalive.clear();
		dead.clear();
		for(Player p : Bukkit.getOnlinePlayers())
			p.setCanPickupItems(true);
		unhideSpec();
		death = false;
		alreadySponsor = false;
		deathStarted = false;
		gameStarted = false;
		cr.emptyChests();
		spectating.clear();
		sponsored.clear();
		g.end();
	}
	public void addToQueue(String name)
	{
		queued.add(name);
	}
	public void removeFromQueue(String name)
	{
		queued.remove(name);
	}
	public boolean queueFull()
	{
		return queued.size() == customConf.getInt("maxPlayers");
	}
	public int posInQueue(String name)
	{
		return queued.indexOf(name) + 1;
	}
	public String district(String name)
	{
		for(int i = 0; i < origalive.size(); i++)
			if(origalive.get(i).equals(name))
			{
				int temp = i + 1;
				if(temp + 1 > 12)
					temp = temp - 11;
				return Integer.toString(temp);
			}
		return null;
	}
	public void gameStart()
	{
		alive.clear();
		origalive.clear();
		dead.clear();
		spectating.clear();
		gameStarted = true;
		vote1();
	}
	private void checkPlayers()
	{
		if(queued.size() < 2)
		{
			Bukkit.broadcastMessage(var.defaultCol() + "Not enough players, need at least 2. Restarting countdown.");
			vote1();
		}
		else
			finishGameStart();
	}
	private void finishGameStart()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will now start.");
		cr.randomizeChests();
		for(int i = 0; i < queued.size(); i++)
		{
			alive.add(queued.get(i));
			origalive.add(queued.get(i));
		}
		queued.clear();
		joinGame();
		if(deathMatch())
			deathCountdown();
		Bukkit.getWorld(g.getNext()).setTime(70584000);//70584000: sunrise. 70620000: evening
		count.schedule(new TimerTask(){public void run() {refillChests();}}, 660000);//11 minutes
	}
	private void refillChests()
	{
		cr.randomizeChests();
		Bukkit.broadcastMessage(var.defaultCol() + "The chests have now been refilled.");
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
			PlayerInventory inv = temp.getInventory();
			inv.clear();
			inv.setBoots(new ItemStack(Material.AIR));
			inv.setLeggings(new ItemStack(Material.AIR));
			inv.setChestplate(new ItemStack(Material.AIR));
			inv.setHelmet(new ItemStack(Material.AIR));
			temp.setExp(-temp.getExp());
			s.addGame(alive.get(i), 1);
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
	public void vote1()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will start in 3 minutes please use /hg join to join.");
		g.holdVote();
		count.schedule(new TimerTask(){public void run() {vote2();}}, 30000);
	}
	public void vote2()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will start in 2 minutes 30 seconds please use /hg join to join.");
		g.holdVote();
		count.schedule(new TimerTask(){public void run() {vote3();}}, 30000);
	}
	public void vote3()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will start in 2 minutes please use /hg join to join.");
		g.holdVote();
		count.schedule(new TimerTask(){public void run() {vote4();}}, 30000);
	}
	public void vote4()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will start in 1 minute 30 seconds please use /hg join to join.");
		g.holdVote();
		count.schedule(new TimerTask(){public void run() {vote5();}}, 30000);
	}
	public void vote5()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will start in 1 minute please use /hg join to join.");
		g.holdVote();
		count.schedule(new TimerTask(){public void run() {vote6();}}, 30000);
	}
	public void vote6()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Game will start in 30 seconds please use /hg join to join.");
		g.holdVote();
		count.schedule(new TimerTask(){public void run() {checkPlayers();}}, 30000);
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
		t.schedule(new TimerTask(){public void run() {deathCountdown6();}}, 1000);
	}
	private void deathCountdown6()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 9 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown7();}}, 1000);
	}
	private void deathCountdown7()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 8 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown8();}}, 1000);
	}
	private void deathCountdown8()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 7 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown9();}}, 1000);
	}
	private void deathCountdown9()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 6 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown10();}}, 1000);
	}
	private void deathCountdown10()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 5 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown11();}}, 1000);
	}
	private void deathCountdown11()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 4 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown12();}}, 1000);
	}
	private void deathCountdown12()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 3 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown13();}}, 1000);
	}
	private void deathCountdown13()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 2 seconds.");
		t.schedule(new TimerTask(){public void run() {deathCountdown14();}}, 1000);
	}
	private void deathCountdown14()
	{
		Bukkit.broadcastMessage(var.defaultCol() + "Death match will start in 1 seconds.");
		t.schedule(new TimerTask(){public void run() {startDeath();}}, 1000);
	}
}