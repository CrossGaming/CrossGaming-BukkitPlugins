package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdForceStart extends Cmd
{	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.forcestart"))
			{
				if(!pl.allowStart())
				{
					g.start();
					p.sendMessage(var.defaultCol() + lang.translate("You started the game."));
					Bukkit.broadcastMessage(var.defaultCol() + p.getName() + " " + lang.translate("started the game."));
				}
				else
					p.sendMessage(var.errorCol() + lang.translate("Error: Game is already started."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not start the Hunger Games."));
		}
		else
		{
			if(pl.gameGoing())
			{
				g.start();
				sender.sendMessage(var.defaultCol() + lang.translate("You started the game."));
				Bukkit.broadcastMessage(var.defaultCol() + lang.translate("The console started the game."));
			}
			else
				sender.sendMessage(var.errorCol() + lang.translate("Error: Game is already started."));
		}
		return true;
	}
}