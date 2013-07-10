package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.crossge.hungergames.ChestRandomizer;

public class CmdSetChests extends Cmd
{
	ChestRandomizer cr = new ChestRandomizer();
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(args.length != 1)
			{
				p.sendMessage(var.errorCol() + lang.translate("Error: Must give world name."));
				return false;
			}
			if(p.hasPermission("HungerGames.setchests"))
			{
				String world = args[0];
				if(Bukkit.getWorld(world) == null)
				{
					p.sendMessage(var.errorCol() + lang.translate("Error: Must give a valid world name."));
					return false;
				}
				Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("Chests for") + " " + world + " " +
										lang.translate("are being located expect a little lag."));
				cr.chests(world);
				Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("Chests for") + " " + world + " " + lang.translate("have been located."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not set the chest locations for Hunger Games."));
		}
		else
		{
			if(args.length != 1)
			{
				sender.sendMessage(var.errorCol() + lang.translate("Error: Must give world name."));
				return false;
			}
			String world = args[0];
			if(Bukkit.getWorld(world) == null)
			{
				sender.sendMessage(var.errorCol() + lang.translate("Error: Must give a valid world name."));
				return false;
			}
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("Chests for") + " " + world + " " +
									lang.translate("are being located expect a little lag."));
			cr.chests(world);
			Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("Chests for") + " " + world + " " + lang.translate("have been located."));
		}
		return true;
	}
}