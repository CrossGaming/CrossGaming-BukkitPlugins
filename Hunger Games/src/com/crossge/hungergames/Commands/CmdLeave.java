package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdLeave extends Cmd
{	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.leave"))
			{
				int spot = pl.posInQueue(p.getName()); 
				if(spot == 0)//In game not in line
				{
					p.setHealth(0);
					p.sendMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("You left the Hunger Games."));
					Bukkit.broadcastMessage(var.defaultCol() + ChatColor.GRAY + p.getName() + " " + lang.translate("left the current hunger games."));
					return true;
				}
				pl.removeFromQueue(p.getName());
				p.sendMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("Removed from the line for joining the next game."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not leave the Hunger Games."));//wait wtf
		}
		else
			sender.sendMessage(var.errorCol() + lang.translate("Error: You cannot leave the hunger games, please log in."));
		return true;
	}
}