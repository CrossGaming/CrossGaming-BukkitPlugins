package com.crossge.hungergames;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Players
{
	Variables var = new Variables();
	Language lang = new Language();
	Sponsor spons = new Sponsor();
	Stats s = new Stats();
	Game g = new Game();
	ChestRandomizer cr = new ChestRandomizer();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	private File customConfFile = new File("plugins/Hunger Games", "config.yml");
   	private YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
   	private static ArrayList<String> spectating = new ArrayList<String>();
	private static ArrayList<String> sponsored = new ArrayList<String>();
	private static ArrayList<String> origalive = new ArrayList<String>();
	private static ArrayList<String> queued = new ArrayList<String>();
	private static ArrayList<String> alive = new ArrayList<String>();
	private static ArrayList<String> dead = new ArrayList<String>();
	private static boolean alreadySponsor = false;
	private static boolean deathStarted = false;
	private static boolean gameStarted = false;
	private static boolean invincible = true;
	private static boolean moveDeny = true;
	private static boolean death = false;
	private static String motd = "Voting";
	private static String worldName = "";
	private static int xCornMin = 0;
	private static int xCornMax = 0;
	private static int zCornMin = 0;
	private static int zCornMax = 0;
	private static int tptime = 0;
	private static int stime = 0;
	private static int dtime = 0;
	private static int time = 0;
	private static int xmin = 0;
	private static int xmax = 0;
	private static int zmin = 0;
	private static int zmax = 0;
	private static Timer invinc = new Timer();
	private static Timer noMove = new Timer();
	private static Timer count = new Timer();
	private static Timer day = new Timer();
	private static Timer t = new Timer();
	
	public boolean alreadySponsored(String name)
	{
		return sponsored.contains(name);
	}
	public String getMotd()
	{
		return motd;
	}
	public void sendToWSpawn()
	{
		Player p;
		for(int i = 0; i < alive.size(); i++)
		{
			p = Bukkit.getPlayer(alive.get(i));
			p.setFoodLevel(20);
			p.setHealth(20);
			p.setFlying(false);
			p.setCanPickupItems(true);
			PlayerInventory inv = p.getInventory();
			for(int j = 0; j < 36; j++)
				inv.setItem(j, new ItemStack(Material.AIR));
			inv.setBoots(new ItemStack(Material.AIR));
			inv.setLeggings(new ItemStack(Material.AIR));
			inv.setChestplate(new ItemStack(Material.AIR));
			inv.setHelmet(new ItemStack(Material.AIR));
			p.setExp(-p.getExp());
			p.getActivePotionEffects().clear();
			p.teleport(spawnLoc(), TeleportCause.PLUGIN);
		}
		for(int i = 0; i < spectating.size(); i++)
		{
			p = Bukkit.getPlayer(spectating.get(i));
			p.setFoodLevel(20);
			p.setHealth(20);
			p.setFlying(false);
			p.setCanPickupItems(true);
			PlayerInventory inv = p.getInventory();
			for(int j = 0; j < 36; j++)
				inv.setItem(j, new ItemStack(Material.AIR));
			inv.setBoots(new ItemStack(Material.AIR));
			inv.setLeggings(new ItemStack(Material.AIR));
			inv.setChestplate(new ItemStack(Material.AIR));
			inv.setHelmet(new ItemStack(Material.AIR));
			p.setExp(-p.getExp());
			p.getActivePotionEffects().clear();
			p.teleport(spawnLoc(), TeleportCause.PLUGIN);
		}
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
		invinc.cancel();
		invinc.purge();
		invinc = new Timer();
		noMove.cancel();
		noMove.purge();
		noMove = new Timer();
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
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("The players left now recieve sponsorships."));
		for(int i = 0; i < alive.size(); i++)
			spons.giveItems(Bukkit.getPlayer(alive.get(i)));
	}
	public void escaping(Player p)
	{
		int x = p.getLocation().getBlockX();
		int z = p.getLocation().getBlockZ();
		if(x >= xmax || x <= xmin || z >= zmax || z <= zmin)
		{
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.DARK_RED + p.getName() + " " + lang.translate("is trying to escape the death match."));
			Player temp;
			for(int i = 0; i < alive.size(); i++)
			{
				temp = Bukkit.getPlayer(alive.get(i));
				temp.teleport(loc(i + 1), TeleportCause.PLUGIN);
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
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("Death match started."));
		motd = lang.translate("Deathmatch");
		Player temp;
		for(int i = 0; i < alive.size(); i++)
		{
			temp = Bukkit.getPlayer(alive.get(i));
			temp.teleport(loc(i + 1), TeleportCause.PLUGIN);
		}
	}
	public boolean deathMatch()
	{
		return alive.size() <= customConf.getInt("playersDeathMatch");
	}
	public boolean sponsorStart()
	{
		if(((origalive.size() / 5) >= alive.size() || alive.size() == customConf.getInt("playersDeathMatch") + 1) && !alreadySponsor)
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
	public boolean safeTime()
	{
		return invincible;
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
		p.teleport(target, TeleportCause.PLUGIN);
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
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setCanPickupItems(false);
		p.teleport(new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz)), TeleportCause.PLUGIN);
		p.getActivePotionEffects().clear();
		p.setGameMode(GameMode.CREATIVE);
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
			p.sendMessage(var.defaultCol() + ChatColor.RED + lang.translate("You may not leave the arena."));
			Location l = new Location(p.getWorld(), x - 1, p.getLocation().getBlockY(), z);
			p.teleport(l, TeleportCause.PLUGIN);
		}
		else if(x <= xCornMin)
		{
			p.sendMessage(var.defaultCol() + ChatColor.RED + lang.translate("You may not leave the arena."));
			Location l = new Location(p.getWorld(), x + 1, p.getLocation().getBlockY(), z);
			p.teleport(l, TeleportCause.PLUGIN);
		}
		else if(z >= zCornMax)
		{
			p.sendMessage(var.defaultCol() + ChatColor.RED + lang.translate("You may not leave the arena."));
			Location l = new Location(p.getWorld(), x, p.getLocation().getBlockY(), z - 1);
			p.teleport(l, TeleportCause.PLUGIN);
		}
		else if(z <= zCornMin)
		{
			p.sendMessage(var.defaultCol() + ChatColor.RED + lang.translate("You may not leave the arena."));
			Location l = new Location(p.getWorld(), x, p.getLocation().getBlockY(), z + 1);
			p.teleport(l, TeleportCause.PLUGIN);
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
		p.setGameMode(GameMode.SURVIVAL);
		p.setFlying(false);
		p.setCanPickupItems(true);
		PlayerInventory inv = p.getInventory();
		for(int j = 0; j < 36; j++)
			inv.setItem(j, new ItemStack(Material.AIR));
		inv.setBoots(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setHelmet(new ItemStack(Material.AIR));
		p.setExp(-p.getExp());
		p.getActivePotionEffects().clear();
		p.teleport(spawnLoc(), TeleportCause.PLUGIN);
	}
	public void addDead(String name)
	{
		alive.remove(name);
		dead.add(name);
		Player p = Bukkit.getPlayer(name);
		p.setGameMode(GameMode.SURVIVAL);
		p.setFlying(false);
		p.setCanPickupItems(true);
		PlayerInventory inv = p.getInventory();
		for(int j = 0; j < 36; j++)
			inv.setItem(j, new ItemStack(Material.AIR));
		inv.setBoots(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setHelmet(new ItemStack(Material.AIR));
		p.setExp(-p.getExp());
		p.getActivePotionEffects().clear();
		p.teleport(spawnLoc(), TeleportCause.PLUGIN);
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
			return Integer.toString(alive.size()) + " " + lang.translate("alive out of") + " " +
					Integer.toString(alive.size() + dead.size()) + " " + lang.translate("total.");
		}
		catch(Exception e)
		{
			return lang.translate("Game not started");
		}
	}
	public boolean onePlayerLeft()
	{
		return (alive.size() == 1 || alive.size() == 0);
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
		PlayerInventory inv = p.getInventory();
		for(int j = 0; j < 36; j++)
			inv.setItem(j, new ItemStack(Material.AIR));
		inv.setBoots(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setHelmet(new ItemStack(Material.AIR));
		p.setExp(-p.getExp());
		p.getActivePotionEffects().clear();
		p.teleport(spawnLoc(), TeleportCause.PLUGIN);
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
		motd = "Game over";
		sendToWSpawn();
		alive.clear();
		origalive.clear();
		dead.clear();
		unhideSpec();
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.setCanPickupItems(true);
			if(customConf.getBoolean("kickOnEnd"))
				p.kickPlayer("Game Over.");
		}
		death = false;
		alreadySponsor = false;
		deathStarted = false;
		gameStarted = false;
		cr.emptyChests();
		spectating.clear();
		sponsored.clear();
		g.end();
	}
	public void safeEnd()
	{
		Bukkit.broadcastMessage(var.defaultCol() + lang.translate("Players are no longer invincible."));
		invincible = false;
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
		motd = lang.translate("Voting");
		alive.clear();
		origalive.clear();
		dead.clear();
		spectating.clear();
		gameStarted = true;
		invincible = true;
		moveDeny = true;
		vote();
	}
	public Location pSpawnPoint(Player p)
	{
		return loc(alive.indexOf(p.getName()) + 1);
	}
	public void checkPlayers()
	{
		if(queued.size() < customConf.getInt("minPlayers"))
		{
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("Not enough players to start, need at least") + " " +
							Integer.toString(customConf.getInt("minPlayers")) + ". " + lang.translate("Restarting countdown."));
			vote();
		}
		else
			finishGameStart();
	}
	public boolean denyMoving()
	{
		return moveDeny;
	}
	private void finishGameStart()
	{
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Game will now start."));
		cr.emptyChests();
		cr.randomizeChests();
		for(int i = 0; i < queued.size(); i++)
		{
			alive.add(queued.get(i));
			origalive.add(queued.get(i));
		}
		queued.clear();
		joinGame();
		motd = g.getNext() + " " + lang.translate("Pre-game");
		tpCool();
	}
	public void finishGameStart2()
	{
		motd = g.getNext() + " " + lang.translate("Game in progress");
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Players may now move."));
		moveDeny = false;
		safeTimer();
		if(deathMatch())
			deathCountdown();
		Bukkit.getWorld(g.getNext()).setTime(70584000);//70584000: sunrise. 70620000: evening
		TimerTask timerTask = new TimerTask()
		{
            @Override
            public void run()
            {
            	new Players().refillChests();
            }
        };
        try
        {
        	day.schedule(timerTask, 660000);//11 minutes
        }
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void refillChests()
	{
		cr.randomizeChests();
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("The chests have been refilled."));
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
			temp.teleport(loc(i + 1), TeleportCause.PLUGIN);
			temp.getActivePotionEffects().clear();
			PlayerInventory inv = temp.getInventory();
			for(int j = 0; j < 36; j++)
				inv.setItem(j, new ItemStack(Material.AIR));
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
		String pathx = world + ".s" + Integer.toString(number) + ".x";
		String pathy = world + ".s" + Integer.toString(number) + ".y";
		String pathz = world + ".s" + Integer.toString(number) + ".z";
		return new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz));
	}
	public Location spawnLoc()
	{
		String world = customConfig.getString("worldS.world");
		String pathx = "worldS.x";
		String pathy = "worldS.y";
		String pathz = "worldS.z";
		return new Location(Bukkit.getWorld(world), customConfig.getInt(pathx), customConfig.getInt(pathy), customConfig.getInt(pathz));
	}
	public void safeTimer()
	{		
		if(stime == 0)
			stime = customConf.getInt("safeTime");
		int freq = customConf.getInt("messageFrequency");
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Players are now invincible for") + " " + getTime(stime));
		if(freq >= stime)
		{
			int temp = stime;
			stime = 0;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().safeEnd();
	            }
	        };
	        try
	        {
	        	invinc.schedule(timerTask, temp * 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			stime -= freq;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().safeTimer();
	            }
	        };
	        try
	        {
	        	invinc.schedule(timerTask, freq * 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void tpCool()
	{		
		if(tptime == 0)
			tptime = customConf.getInt("tpCoolDown");
		int freq = customConf.getInt("messageFrequency");
		Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Players may move in") + " " + getTime(tptime));
		if(tptime == 1 || tptime == 0)
		{
			tptime = 0;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().finishGameStart2();
	            }
	        };
	        try
	        {
	        	noMove.schedule(timerTask, 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(tptime > 1 && tptime <=10)
		{
			tptime -= 1;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().tpCool();
	            }
	        };
	        try
	        {
	        	noMove.schedule(timerTask, 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(freq >= tptime)
		{
			int temp = tptime - 10;
			tptime = 10;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().tpCool();
	            }
	        };
	        try
	        {
	        	noMove.schedule(timerTask, temp * 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(tptime - freq >= 10)
		{
			
			tptime -= freq;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().tpCool();
	            }
	        };
	        try
	        {
	        	noMove.schedule(timerTask, freq * 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Bukkit.broadcastMessage("If this shows up there is an error.");
		}
	}
	public void deathCountdown()
	{
		if(!deathStarted)
		{			
			if(dtime == 0)
				dtime = customConf.getInt("deathTime");
			int freq = customConf.getInt("messageFrequency");
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("Death match will start in") + " " + getTime(dtime));	
			if(dtime == 1 || dtime == 0)
			{
				dtime = 0;
				TimerTask timerTask = new TimerTask()
				{
		            @Override
		            public void run()
		            {
		            	new Players().startDeath();
		            }
		        };
		        try
		        {
		        	t.schedule(timerTask, 1000);
		        }
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(dtime > 1 && dtime <=10)
			{
				dtime -= 1;
				TimerTask timerTask = new TimerTask()
				{
		            @Override
		            public void run()
		            {
		            	new Players().deathCountdown();
		            }
		        };
		        try
		        {
		        	t.schedule(timerTask, 1000);
		        }
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(freq >= dtime)
			{
				int temp = dtime - 10;
				dtime = 10;
				TimerTask timerTask = new TimerTask()
				{
		            @Override
		            public void run()
		            {
		            	new Players().deathCountdown();
		            }
		        };
		        try
		        {
		        	t.schedule(timerTask, temp * 1000);
		        }
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(dtime - freq >= 10)
			{
				
				dtime -= freq;
				TimerTask timerTask = new TimerTask()
				{
		            @Override
		            public void run()
		            {
		            	new Players().deathCountdown();
		            }
		        };
		        try
		        {
		        	t.schedule(timerTask, freq * 1000);
		        }
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				Bukkit.broadcastMessage("If this shows up there is an error.");
			}
		}
	}
	public void vote()
	{
		if(time == 0)
			time = customConf.getInt("votingTime");
		int freq = customConf.getInt("messageFrequency");
		if(customConf.getBoolean("showJoin"))
		{
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Game will start in") + " " + getTime(time) + " " +
					lang.translate("please use /hg join to join."));
		}
		else
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Game will start in") + " " + getTime(time));	
		g.holdVote();
		if(freq >= time)
		{
			int temp = time;
			time = 0;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {
	            	new Players().checkPlayers();
	            }
	        };
	        try
	        {
	        	count.schedule(timerTask, temp * 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			time -= freq;
			TimerTask timerTask = new TimerTask()
			{
	            @Override
	            public void run()
	            {	      
	            	new Players().vote();
	            }
	        };
	        try
	        {
	        	count.schedule(timerTask, freq * 1000);
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	private String getTime(int time)
	{
		int seconds = time % 60;
		int minutes = time / 60;
		String message = "";
		if(minutes == 1)
			message = "1 " + lang.translate("minute");
		else if(minutes > 1)
			message = Integer.toString(minutes) + " " + lang.translate("minutes");
		if(seconds == 1)
			message += " 1 " + lang.translate("second");
		else if(seconds > 1)
			message += " " + Integer.toString(seconds) + " " + lang.translate("seconds");
		message = message.trim();
		return message;
	}
}