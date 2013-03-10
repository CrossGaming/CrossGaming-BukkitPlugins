package com.github.CorporateCraft.necessities;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import com.github.CorporateCraft.necessities.CCBot.*;
import com.github.CorporateCraft.necessities.Commands.CmdHide;

public class Listeners implements Listener
{
	ArrayLists arl = new ArrayLists();
	CCBot bot = new CCBot();
	CmdHide hide = new CmdHide();
	public Listeners()
	{
		
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		bot.logIn(player.getName());
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
    	Player player = event.getPlayer();
    	bot.logOut(player.getName());
    	hide.removeP(player.getName());
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