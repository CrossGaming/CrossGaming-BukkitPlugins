package com.crossge.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener
{
	Players pl = new Players();
	Variables var = new Variables();
	public Listeners()
	{
		
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
    	Player p = event.getPlayer();
    	pl.removeFromQueue(p.getName());
    	if(pl.isAlive(p.getName()))
    		p.setHealth(0);
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
    		pl.addDead(p.getName());
    	}
    	if(pl.onePlayerLeft())
    	{
    		Bukkit.broadcastMessage(var.defaultCol() + pl.winner() + " won the Hunger Games.");
    		pl.endGame();
    	}
	}
}