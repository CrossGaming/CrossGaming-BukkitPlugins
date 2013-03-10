package com.github.CorporateCraft.necessities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import com.github.CorporateCraft.necessities.CCBot.*;
import com.github.CorporateCraft.necessities.Commands.*;

public class Listeners implements Listener
{
	ArrayLists arl = new ArrayLists();
	CCBot bot = new CCBot();
	CmdGod god = new CmdGod();
	CmdHide hide = new CmdHide();
	public Listeners()
	{
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		bot.logIn(player.getName());
		god.addP(player.getName());
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
    	bot.logOut(player.getName());
    	god.remP(player.getName());
    	hide.removeP(player.getName());
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		Entity entity = event.getEntity();
		if (entity instanceof Player)
		{
			event.setCancelled(god.isGod((Player) entity));
		}
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		String message = event.getMessage();
		bot.logChat(player.getName(), message);
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String message = event.getMessage();
		bot.logCom(player.getName(), message);
	}
	@EventHandler
	public void onCommand(ServerCommandEvent event)
	{
		String message = event.getCommand();
		bot.logConsole(message);
	}
}