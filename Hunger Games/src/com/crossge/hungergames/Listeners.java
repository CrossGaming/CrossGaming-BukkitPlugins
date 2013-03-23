package com.crossge.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener
{
	Players pl = new Players();
	Variables var = new Variables();
	Game g = new Game();
	Stats s = new Stats();
	public Listeners()
	{
		
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
		if(pl.isAlive(event.getPlayer().getName()) || pl.isSpectating(event.getPlayer().getName()))
		{
			if(!event.getBlock().getType().equals(Material.LEAVES) || !event.getBlock().getType().isEdible())
				event.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(pl.isAlive(event.getPlayer().getName()) || pl.isSpectating(event.getPlayer().getName()))
		{
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
		if(event.getDamager() instanceof Player )
		{
			if(pl.gameGoing())
			{
				if(!pl.isAlive(((Player)event.getDamager()).getName()))
					event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
    	Player p = (Player) event.getEntity();
    	if(pl.isAlive(p.getName()))
    	{
    		if(event.getDeathMessage().equals(p.getName() + " died"))
    			event.setDeathMessage(var.defaultCol() + p.getName() + " died while trying to win the hunger games.");
    		else
    			event.setDeathMessage(var.defaultCol() + event.getDeathMessage());
    		Player killer = p.getKiller();
    		if(killer != null)
    		{
    			s.addKill(killer.getName());
    			s.addPoints(killer.getName(), 7);
    		}
    		s.addDeath(p.getName());
    		pl.addDead(p.getName());
    		s.addPoints(p.getName(), -7);
    	}
    	if(pl.onePlayerLeft())
    	{
    		Bukkit.broadcastMessage(var.defaultCol() + pl.winner() + " won the Hunger Games.");
    		pl.endGame();
    	}
	}
}