package com.github.CorporateCraft.necessities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CorporateCraft.necessities.Commands.*;

public class Necessities extends JavaPlugin
{
	public static ChatColor messages = ChatColor.GREEN;
	
	@Override
    public void onEnable()
	{	
		getLogger().info("The necessities your server has been needing are enabled.");
		Initialization.InitiateFiles();
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("tpaccept"))
		{
			return CmdTpAccept.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tpdeny"))
		{
			return CmdTpDeny.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tpa"))
		{
			return CmdTpa.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tpahere"))
		{
			return CmdTpahere.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tp"))
		{
			return CmdTp.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tphere"))
		{
			return CmdTphere.CommandUse(sender, cmd, label, args);
		}
		return false; 
	}	
	
    @Override
    public void onDisable()
    {
    	getLogger().info("The necessities your server needs are now missing.");
    }
}