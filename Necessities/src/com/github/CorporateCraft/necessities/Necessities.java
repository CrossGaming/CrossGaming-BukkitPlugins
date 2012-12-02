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
		init.InitiateFiles();
    }
	Boolean IsCmd = false;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ops"))
		{	
			CmdOps Com = new CmdOps();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("banned"))
		{
			CmdBanned Com = new CmdBanned();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("bannedips"))
		{
			CmdBannedips Com = new CmdBannedips();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("kill"))
		{	
			CmdKill Com = new CmdKill();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("suicide"))
		{	
			CmdSuicide Com = new CmdSuicide();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpaccept"))
		{
			CmdTpAccept Com = new CmdTpAccept();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpdeny"))
		{
			CmdTpDeny Com = new CmdTpDeny();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpa"))
		{
			CmdTpa Com = new CmdTpa();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpahere"))
		{
			CmdTpahere Com = new CmdTpahere();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tp"))
		{
			CmdTp Com = new CmdTp();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tphere"))
		{
			CmdTphere Com = new CmdTphere();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("motd"))
		{
			CmdMotd Com = new CmdMotd();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("rules"))
		{
			CmdRules Com = new CmdRules();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("permissions"))
		{
			CmdPermissions Com = new CmdPermissions();
			IsCmd = Com.CommandUse(sender, args);
		}
		else if(cmd.getName().equalsIgnoreCase("ragequit"))
		{
			CmdRagequit Com = new CmdRagequit();
			IsCmd = Com.CommandUse(sender, args);
		}
		return IsCmd; 
	}	
	
    @Override
    public void onDisable()
    {
    	getLogger().info("The necessities your server needs are now missing.");
    }
}