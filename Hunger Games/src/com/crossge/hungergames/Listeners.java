package com.crossge.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
    	Player p = event.getPlayer();
    	pl.removeFromQueue(p.getName());
    	g.delVote(p.getName());
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
    		Player killer = p.getKiller();
    		if(killer != null)
    		{
    			s.addKill(killer.getName());
    			s.addPoints(killer.getName(), 7);
    		}
    		s.addDeath(p.getName());
    		pl.addDead(p.getName());
    	}
    	if(pl.onePlayerLeft())
    	{
    		Bukkit.broadcastMessage(var.defaultCol() + pl.winner() + " won the Hunger Games.");
    		pl.endGame();
    	}
	}
}