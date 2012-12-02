package com.github.CorporateCraft.necessities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.github.CorporateCraft.necessities.CCBot.*;

public class Listeners implements Listener
{
	ArrayLists arl = new ArrayLists();
	CCBot Bot = new CCBot();
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
    	Player player = event.getPlayer();
    	if(player.hasPermission("Necessities.motd"))
    	{
    		try
    		{
    			FileReader reader = new FileReader(arl.GetMotd());
    			BufferedReader buff = new BufferedReader(reader);
    			while(true)
    			{
    				String inputText = buff.readLine();
    				if(inputText == null)
		        	{
    					break;
		        	}
    				player.sendMessage(arl.GetCol() + inputText);
    			}
    		}
    		catch (IOException ex){}
    	}
    	Bot.LogIn(player.getName());
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
    	Player player = event.getPlayer();
    	Bot.LogOut(player.getName());
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		String Message = event.getMessage();
		Bot.LogChat(player.getName(), Message);
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String Message = event.getMessage();
		Bot.LogCom(player.getName(), Message);
	}
	@EventHandler
	public void onCommand(ServerCommandEvent event)
	{
		String Message = event.getCommand();
		Bot.LogConsole(Message);
	}
}