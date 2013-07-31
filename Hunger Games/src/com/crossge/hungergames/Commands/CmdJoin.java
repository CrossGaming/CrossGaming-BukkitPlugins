package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdJoin extends Cmd
{	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.join"))
			{
				if(pl.isAlive(p.getName()))
				{
					p.sendMessage(var.errorCol() + lang.translate("Error: You are already in a game."));
					return true;
				}
				int spot = pl.posInQueue(p.getName()); 
				if(spot == 0)
				{
					pl.addToQueue(p.getName());
					spot = pl.posInQueue(p.getName());
					p.sendMessage(var.defaultCol() + lang.translate("Added in line at position") +
									" " + ChatColor.GOLD + "#" + Integer.toString(spot) + ChatColor.BLUE + " " + lang.translate("for the next game."));
					return true;
				}
				if(pl.queueFull())
				{
					p.sendMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("Sorry the next game is full."));
					return true;
				}
				p.sendMessage(ChatColor.GOLD + "#" + Integer.toString(spot) + ChatColor.BLUE + " " + lang.translate("in line for the next game."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not join the Hunger Games."));
		}
		else
		{
			if(args.length == 1)
			{
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null)
					target.performCommand("hg join");
			}
			else
				sender.sendMessage(var.errorCol() + lang.translate("Error: You cannot join the hunger games, please log in."));
		}
		return true;
	}	
}