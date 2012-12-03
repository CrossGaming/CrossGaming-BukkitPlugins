package com.github.CorporateCraft.necessities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.CorporateCraft.necessities.CCBot.CCBot;
import com.github.CorporateCraft.necessities.Commands.*;

public class Necessities extends JavaPlugin
{	
	CCBot Bot = new CCBot();
	@Override
    public void onEnable()
	{	
		getLogger().info("The necessities your server has been needing are enabled.");
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Initialization init = new Initialization();
		init.InitiateFiles();
		Bot.StartTimer();
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Cmd Com;
		if(cmd.getName().equalsIgnoreCase("ops"))
		{	
			Com = new CmdOps();
		}
		else if(cmd.getName().equalsIgnoreCase("banned"))
		{
			Com = new CmdBanned();
		} 
		else if(cmd.getName().equalsIgnoreCase("bannedips"))
		{
			Com = new CmdBannedips();
		}
		else if(cmd.getName().equalsIgnoreCase("kill"))
		{	
			Com = new CmdKill();
		}
		else if(cmd.getName().equalsIgnoreCase("suicide"))
		{	
			Com = new CmdSuicide();
		}
		else if(cmd.getName().equalsIgnoreCase("tpaccept"))
		{
			Com = new CmdTpAccept();
		}
		else if(cmd.getName().equalsIgnoreCase("tpdeny"))
		{
			Com = new CmdTpDeny();
		}
		else if(cmd.getName().equalsIgnoreCase("tpa"))
		{
			Com = new CmdTpa();
		}
		else if(cmd.getName().equalsIgnoreCase("tpahere"))
		{
			Com = new CmdTpahere();
		}
		else if(cmd.getName().equalsIgnoreCase("tp"))
		{
			Com = new CmdTp();
		}
		else if(cmd.getName().equalsIgnoreCase("tphere"))
		{
			Com = new CmdTphere();
		}
		else if(cmd.getName().equalsIgnoreCase("motd"))
		{
			Com = new CmdMotd();
		}
		else if(cmd.getName().equalsIgnoreCase("rules"))
		{
			Com = new CmdRules();
		}
		else if(cmd.getName().equalsIgnoreCase("permissions"))
		{
			Com = new CmdPermissions();
		}
		else if(cmd.getName().equalsIgnoreCase("ragequit"))
		{
			Com = new CmdRagequit();
		}
		else
		{
			return false;
		}
		return Com.CommandUse(sender, args);
	}	
	
    @Override
    public void onDisable()
    {
    	Bot.CancelTimer();
    	getLogger().info("The necessities your server needs are now missing.");
    }
}