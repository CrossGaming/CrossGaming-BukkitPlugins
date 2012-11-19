package com.github.CorporateCraft.necessities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CorporateCraft.necessities.Commands.*;

public class Necessities extends JavaPlugin
{
	public static final String Motdfile = "plugins/Necessities/MOTD.txt";
	public static final String Rulesfile = "plugins/Necessities/Rules.txt";
	public static ChatColor messages = ChatColor.GREEN;
	
	@Override
    public void onEnable()
	{	
		getLogger().info("The necessities your server has been needing are enabled.");
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Initialization.InitiateFiles();
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ops"))
		{	
			return CmdOps.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("banned"))
		{
			return CmdBanned.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("bannedips"))
		{
			return CmdBannedips.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("kill"))
		{	
			return CmdKill.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("suicide"))
		{	
			return CmdSuicide.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpaccept"))
		{
			return CmdTpAccept.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpdeny"))
		{
			return CmdTpDeny.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpa"))
		{
			return CmdTpa.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tpahere"))
		{
			return CmdTpahere.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tp"))
		{
			return CmdTp.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("tphere"))
		{
			return CmdTphere.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("motd"))
		{
			return CmdMotd.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("rules"))
		{
			return CmdRules.CommandUse(sender, cmd, label, args);
		}
		else if(cmd.getName().equalsIgnoreCase("permissions"))
		{
			return CmdPermissions.CommandUse(sender, cmd, label, args);
		}
		return false; 
	}	
	
    @Override
    public void onDisable()
    {
    	getLogger().info("The necessities your server needs are now missing.");
    }
}