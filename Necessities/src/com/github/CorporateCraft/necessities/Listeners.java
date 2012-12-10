package com.github.CorporateCraft.necessities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.github.CorporateCraft.necessities.CCBot.*;
import com.github.CorporateCraft.necessities.Commands.CmdGod;

public class Listeners implements Listener
{
	ArrayLists arl = new ArrayLists();
	CCBot Bot = new CCBot();
	CmdGod God = new CmdGod();
	public Listeners()
	{
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		Bot.logIn(player.getName());
		God.addP(player.getName());
    	if(player.hasPermission("Necessities.motd"))
    	{
    		try
    		{
    			FileReader reader = new FileReader(arl.getMotd());
    			BufferedReader buff = new BufferedReader(reader);
    			while(true)
    			{
    				String inputText = buff.readLine();
    				if(inputText == null)
		        	{
    					break;
		        	}
    				player.sendMessage(arl.getCol() + inputText);
    			}
    		}
    		catch (IOException ex){}
    	}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
    	Player player = event.getPlayer();
    	Bot.logOut(player.getName());
    	God.remP(player.getName());
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		Entity entity = event.getEntity();
		if (entity instanceof Player)
		{
			event.setCancelled(God.isGod((Player) entity));
		}
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		String Message = event.getMessage();
		Bot.logChat(player.getName(), Message);
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String Message = event.getMessage();
		Bot.logCom(player.getName(), Message);
	}
	@EventHandler
	public void onCommand(ServerCommandEvent event)
	{
		String Message = event.getCommand();
		Bot.logConsole(Message);
	}
}