package com.crossge.hungergames;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener
{
	Players pl = new Players();
	Variables var = new Variables();
	Game g = new Game();
	Stats s = new Stats();
	private File customConfigFile = new File("plugins/Hunger Games", "config.yml");
	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	private File customConfigFileBreakable = new File("plugins/Hunger Games", "breakable.yml");
	private YamlConfiguration customConfigBreakable = YamlConfiguration.loadConfiguration(customConfigFileBreakable);
	private File customConfigFilePlaceable = new File("plugins/Hunger Games", "placeable.yml");
	private YamlConfiguration customConfigPlaceable = YamlConfiguration.loadConfiguration(customConfigFilePlaceable);
	private File customConfigFileCommands = new File("plugins/Hunger Games", "commands.yml");
	private YamlConfiguration customConfigCommands = YamlConfiguration.loadConfiguration(customConfigFileCommands);
	public Listeners()
	{
		
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player p = event.getPlayer();
		if(pl.deathstarted() && pl.isAlive(p.getName()))
			pl.escaping(p);
		else if(pl.isAlive(p.getName()))//Player is alive but deathmatch has not started
			pl.escapingArena(p);
	}
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event)
	{
		Player p = event.getPlayer();
		String com = "";
		if(event.getMessage().length() > 1)
			com = event.getMessage().substring(1).split(" ")[0];
		if(pl.isAlive(p.getName()) || pl.isSpectating(p.getName()))
		{
			boolean cancel = true;
			for(String path : customConfigCommands.getKeys(false))
			{
				if(com.equalsIgnoreCase(path) && customConfigCommands.getBoolean(path))
				{
					cancel = false;
					break;
				}
			}
			if(cancel)
				p.sendMessage(var.errorCol() + "Error: You may not perform commands while in the hunger games.");
			event.setCancelled(cancel);
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
    	Player p = event.getPlayer();
    	if(s.get(p.getName()) == null)
    		s.write(p.getName(), 0, 0, 0, 0, 0);
    	pl.hideSpectators(p);
	}
	@EventHandler
	public void onBlockDamage(BlockDamageEvent event)
	{
		if(pl.gameGoing())
    	{
			if(pl.isAlive(event.getPlayer().getName()))
			{
				boolean cancel = true;
				for(String path : customConfigBreakable.getKeys(false))
				{
					if(event.getBlock().getType().equals(Material.getMaterial(Integer.parseInt(path))) && customConfigBreakable.getBoolean(path))
					{
						cancel = false;
						break;
					}
				}
				event.setCancelled(cancel);
			}
			else if(pl.isSpectating(event.getPlayer().getName()))
				event.setCancelled(true);
    	}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(pl.gameGoing())
		{
			if(pl.isAlive(event.getPlayer().getName()))
			{
				if(!customConfig.getBoolean("placeBlocks"))
				{
					boolean cancel = true;
					for(String path : customConfigPlaceable.getKeys(false))
					{
						if(event.getBlock().getType().equals(Material.getMaterial(Integer.parseInt(path))) && customConfigPlaceable.getBoolean(path))
						{
							cancel = false;
							break;
						}
					}
					event.setCancelled(cancel);
				}
			}
			else if(pl.isSpectating(event.getPlayer().getName()))
				event.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
    	Player p = event.getPlayer();
    	pl.removeFromQueue(p.getName());
    	g.delVote(p.getName());
    	if(pl.isAlive(p.getName()))
    		p.setHealth(0);
    	pl.unhideSpectators(p);
	}
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
    	Player p = event.getPlayer();
    	if(p.getName().equalsIgnoreCase("pupnewfster") || p.getName().equalsIgnoreCase("Mod_Chris") || p.getName().equalsIgnoreCase("CrusaderDeleters"))
    		event.setFormat(ChatColor.GREEN + "[" + ChatColor.GOLD + "HG Dev" + ChatColor.GREEN + "] " + ChatColor.RESET + event.getFormat());
    	if(pl.isAlive(p.getName()))
    	{
    		String district = pl.district(p.getName());
    		if(district != null)
    		{
    			district = var.districtCol() + district + " " + ChatColor.RESET;
    			event.setFormat(district + event.getFormat());
    		}
    	}
    	else
    	{
    		String points = s.getPoints(p.getName());
    		if(points != null)
    		{
    			points = var.defaultCol() + "(" + var.pointCol() + points + var.defaultCol() + ") " + ChatColor.RESET;
    			event.setFormat(points + event.getFormat());
    		}
    	}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
			if(pl.gameGoing() && !pl.isAlive(((Player)event.getDamager()).getName()))
				event.setCancelled(true);
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
    	Player p = (Player) event.getEntity();
    	if(pl.gameGoing())
    	{
    		if(pl.isAlive(p.getName()))
	    	{
	    		if(event.getDeathMessage().equals(p.getName() + " died"))
	    			event.setDeathMessage(var.defaultCol() + p.getName() + " died while trying to win the hunger games.");
	    		else
	    			event.setDeathMessage(var.defaultCol() + event.getDeathMessage());
	    		Player killer = p.getKiller();
	    		if(killer != null)
	    		{
	    			s.addKill(killer.getName(), 1);
	    			s.addPoints(killer.getName(), 7);
	    		}
	    		s.addDeath(p.getName(), 1);
	    		pl.addDead(p.getName());
	    		s.addPoints(p.getName(), -7);
	    		if(pl.sponsorStart())
		    		pl.startSponsor();
		    	if(pl.deathMatch())
		    		pl.deathCountdown();
		    	if(pl.onePlayerLeft())
		    	{
		    		Bukkit.broadcastMessage(var.defaultCol() + pl.winner() + " won the Hunger Games.");
		    		pl.endTimer();
		    		pl.endGame();
		    	}
	    	}
    	}
	}
}