package com.github.CorporateCraft.necessities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CorporateCraft.necessities.Commands.*;

public class Necessities extends JavaPlugin
{	
	@Override
    public void onEnable()
	{	
		getLogger().info("The necessities your server has been needing are enabled.");
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Initialization init = new Initialization();
		init.initiateFiles();
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Cmd com = new Cmd();
		if(cmd.getName().equalsIgnoreCase("ops"))
		{	
			com = new CmdOps();
		}
		else if(cmd.getName().equalsIgnoreCase("banned"))
		{
			com = new CmdBanned();
		} 
		else if(cmd.getName().equalsIgnoreCase("bannedips"))
		{
			com = new CmdBannedips();
		}
		else if(cmd.getName().equalsIgnoreCase("kick"))
		{
			com = new CmdKick();
		}
		else if(cmd.getName().equalsIgnoreCase("ban"))
		{
			com = new CmdBan();
		}
		else if(cmd.getName().equalsIgnoreCase("banip"))
		{
			com = new CmdBanIp();
		}
		else if(cmd.getName().equalsIgnoreCase("gamemode"))
		{
			com = new CmdGameMode();
		}
		else if(cmd.getName().equalsIgnoreCase("reload"))
		{
			com = new CmdReload();
		}
		else if(cmd.getName().equalsIgnoreCase("warn"))
		{
			com = new CmdWarn();
		}
		else if(cmd.getName().equalsIgnoreCase("enchant"))
		{
			com = new CmdEnchant();
		}
		else if(cmd.getName().equalsIgnoreCase("kill"))
		{	
			com = new CmdKill();
		}
		else if(cmd.getName().equalsIgnoreCase("suicide"))
		{	
			com = new CmdSuicide();
		}
		else if(cmd.getName().equalsIgnoreCase("tpaccept"))
		{
			com = new CmdTpAccept();
		}
		else if(cmd.getName().equalsIgnoreCase("tpdeny"))
		{
			com = new CmdTpDeny();
		}
		else if(cmd.getName().equalsIgnoreCase("tpa"))
		{
			com = new CmdTpa();
		}
		else if(cmd.getName().equalsIgnoreCase("tpahere"))
		{
			com = new CmdTpahere();
		}
		else if(cmd.getName().equalsIgnoreCase("tp"))
		{
			com = new CmdTp();
		}
		else if(cmd.getName().equalsIgnoreCase("tphere"))
		{
			com = new CmdTphere();
		}
		else if(cmd.getName().equalsIgnoreCase("motd"))
		{
			com = new CmdMotd();
		}
		else if(cmd.getName().equalsIgnoreCase("rules"))
		{
			com = new CmdRules();
		}
		else if(cmd.getName().equalsIgnoreCase("permissions"))
		{
			com = new CmdPermissions();
		}
		else if(cmd.getName().equalsIgnoreCase("ragequit"))
		{
			com = new CmdRagequit();
		}
		else if(cmd.getName().equalsIgnoreCase("fly"))
		{
			com = new CmdFly();
		}
		else if(cmd.getName().equalsIgnoreCase("god"))
		{
			com = new CmdGod();
		}
		else if(cmd.getName().equalsIgnoreCase("feed"))
		{
			com = new CmdFeed();
		}
		else if(cmd.getName().equalsIgnoreCase("heal"))
		{
			com = new CmdHeal();
		}
		return com.commandUse(sender, args);
	}	
	
    @Override
    public void onDisable()
    {
    	getLogger().info("The necessities your server needs are now missing.");
    }
}