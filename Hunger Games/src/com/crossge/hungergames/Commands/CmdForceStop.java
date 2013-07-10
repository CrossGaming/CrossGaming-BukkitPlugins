package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdForceStop extends Cmd
{	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.forcestop"))
			{
				if(pl.allowStart())
				{
					pl.endGame();
					p.sendMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("You stopped the game."));
					Bukkit.broadcastMessage(var.defaultCol() + ChatColor.DARK_RED + p.getName() + " " + lang.translate("stopped the game."));
				}
				else
					p.sendMessage(var.errorCol() + lang.translate("Error: Game is already stopped."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not stop the Hunger Games."));
		}
		else
		{
			if(pl.gameGoing())
			{
				pl.endGame();
				sender.sendMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("You stopped the game."));
				Bukkit.broadcastMessage(var.defaultCol() + ChatColor.DARK_RED + lang.translate("The console stopped the game."));
			}
			else
				sender.sendMessage(var.errorCol() + lang.translate("Error: Game is already started."));
		}
		return true;
	}
}