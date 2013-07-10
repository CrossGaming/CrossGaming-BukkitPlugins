package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpectate extends Cmd
{
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.spectate"))
			{
				if(pl.isAlive(p.getName()))
				{
					p.sendMessage(var.errorCol() + lang.translate("Error: You are already in a game."));
					return true;
				}
				if(pl.gameGoing())
				{
					if(pl.isSpectating(p.getName()))
					{
						if(args.length == 1)
						{
							Player target = Bukkit.getPlayer(args[0]);
							if(pl.isAlive(target.getName()))
							{
								pl.spectate(p, target);
								p.sendMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Now spectating") + " " + target.getName() + ".");
							}
							else
								p.sendMessage(var.errorCol() + lang.translate("Error: That player is not playing."));
						}
						else
						{
							pl.delSpectating(p.getName());
							p.sendMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("No longer spectating the Hunger Games."));
							return true;
						}
					}
					pl.addSpectating(p.getName());
					if(args.length == 1)
					{
						Player target = Bukkit.getPlayer(args[0]);
						if(pl.isAlive(target.getName()))
						{
							pl.spectate(p, target);
							p.sendMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Now spectating") + " " + target.getName() + ".");
						}
						else
							p.sendMessage(var.errorCol() + lang.translate("Error: That player is not playing."));
					}
					else
						p.sendMessage(var.defaultCol() + ChatColor.WHITE + lang.translate("Now spectating the Hunger Games."));
				}
				else
					p.sendMessage(var.errorCol() + lang.translate("Error: There is no current game."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not spectate the Hunger Games."));
		}
		else
			sender.sendMessage(var.errorCol() + lang.translate("Error: You cannot spectate the hunger games, please log in."));
		return true;
	}
}