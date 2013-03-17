package com.crossge.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.necessities.ArrayLists;

public class CmdReload extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdReload()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length > 1)
		{
			return false;
		}
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(args.length == 0)
			{
				Bukkit.reload();
		        Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
			}
			else
			{
				p.getServer().getPluginManager().getPlugin(args[0]).reloadConfig();
				Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
			}
			return true;
		}
		else
		{
			if(args.length == 0)
			{
				Bukkit.reload();
		        Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
			}
			else
			{
				sender.getServer().getPluginManager().getPlugin(args[0]).reloadConfig();
				Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
			}
	        return true;
	    }
	}
}