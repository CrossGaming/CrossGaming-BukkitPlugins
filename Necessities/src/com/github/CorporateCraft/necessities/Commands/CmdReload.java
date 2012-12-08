package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdReload extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdReload()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if(args.length > 1)
		{
			return false;
		}
		if (sender instanceof Player)
		{
			if(args.length == 0)
			{
				Bukkit.reload();
		        Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
			}
			else
			{
				sender.getServer().getPluginManager().getPlugin(args[0]).reloadConfig();
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
			}
	        return true;
	    }
	}
}