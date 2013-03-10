package com.github.CorporateCraft.necessities.CCBot;

import org.bukkit.Bukkit;
import java.util.Random;

public class CCBotIRC
{
	CCBotLog log = new CCBotLog();
	private String gcNick = "CG" + rand();
	private String ircNick = "";
	private String server = "irc.crossge.com";
	private String gc = "#global";
	private String ircChan = "";
	private String gcPass = "";
	private String ircPass = "";
	public static CorpBot bot;
	public static IRCBot irc;
	
	public CCBotIRC()
	{
		
	}
	private String rand()
	{
		int r = new Random().nextInt();
		if(r<0)
		{
			r = -r;
		}
		return Integer.toString(r);
	}
	public void joinIRC()
	{
		
		String gcTempNick = Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.gcNick");
		if(!gcTempNick.equals(""))
		{
			gcNick = gcTempNick;
		}
		else
		{
			Bukkit.getPluginManager().getPlugin("Necessities").getConfig().set("CCBot.gcNick", gcNick);
			Bukkit.getPluginManager().getPlugin("Necessities").saveConfig();
		}
		ircNick = Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.ircNick");
		ircChan = Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.ircChan");
		gcPass = Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.gcPass");
		ircPass = Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.ircPass");
		bot = new CorpBot(gcNick);
		irc = new IRCBot(ircNick, ircChan);
		try
		{
			bot.connect(server);
			irc.connect(server);
			bot.identify(gcPass);
			irc.identify(ircPass);
			bot.joinChannel(gc);
			irc.joinChannel(ircChan);
		}
		catch(Exception e)
		{
			
		}
	}
}