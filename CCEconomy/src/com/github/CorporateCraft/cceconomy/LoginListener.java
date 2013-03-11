package com.github.CorporateCraft.cceconomy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener
{
	BalChecks balc = new BalChecks();
	public LoginListener()
	{
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
    	Player player = event.getPlayer();
    	String playername = player.getName();
    	if (!balc.doesPlayerExist(playername))
    		balc.addPlayerToList(playername);
	}
}