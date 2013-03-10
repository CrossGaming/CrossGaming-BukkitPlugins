package com.github.CorporateCraft.necessities.CCBot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jibble.pircbot.*;

public class CorpBot extends PircBot
{
	private String gc = "#global";
    CCBotLog log = new CCBotLog();
    public CorpBot(String name)
    {
        this.setName(name);
    }
    public void sendGlobal(String sender, String message)
    {
    	sendMessage(gc, sender + ": " + message);
    	Bukkit.broadcastMessage(ChatColor.GOLD + "[Global] " + sender + ": "+ ChatColor.RESET + message);
		log.log("[Global] " + message);
    }
    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
    	if(!message.startsWith("!") && !message.startsWith("^"))
    	{
    		String m = "[Global] " + sender + ": " + message;
        	Bukkit.broadcastMessage(ChatColor.GOLD + "[Global] " + sender + ": " + ChatColor.RESET + message);
        	log.log(m);
    	}
    }
    public void onPrivateMessage(String sender, String login, String hostname, String message)
    {
    	String m = "[GlobalPM] " + sender + ": " + message;
        Bukkit.broadcastMessage(ChatColor.GOLD + "[GlobalPM] " + sender + ": " + ChatColor.RESET + message);
        log.log(m);
    }
}