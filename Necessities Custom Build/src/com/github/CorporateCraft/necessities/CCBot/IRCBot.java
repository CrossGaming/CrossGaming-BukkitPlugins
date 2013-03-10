package com.github.CorporateCraft.necessities.CCBot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jibble.pircbot.*;

public class IRCBot extends PircBot
{
	private String irc = "";
    CCBotLog log = new CCBotLog();
    public IRCBot(String name, String irc)
    {
        this.setName(name);
        this.irc = irc;
    }
    public void sendIRC(String sender, String message)
    {
    	sendMessage(irc, sender + ": " + message);
    }
    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
    	String m = "[IRC] " + sender + ": " + message;
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[IRC] " + sender + ": " + ChatColor.RESET + message);
        log.log(m);
    }
    public void onPrivateMessage(String sender, String login, String hostname, String message)
    {
    	String m = "[IRCPM] " + sender + ": " + message;
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[IRCPM] " + sender + ": " + ChatColor.RESET + message);
        log.log(m);
    }
}