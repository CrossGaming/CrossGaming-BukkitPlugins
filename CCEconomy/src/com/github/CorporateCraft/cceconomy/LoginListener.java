package com.github.CorporateCraft.cceconomy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
    	Player player = event.getPlayer();
    	String playername = player.getName();
    	if (!PlayerToFile.DoesPlayerExist(playername))
    	{
    		PlayerToFile.AddPlayerToList(playername);
    	}
	}
}