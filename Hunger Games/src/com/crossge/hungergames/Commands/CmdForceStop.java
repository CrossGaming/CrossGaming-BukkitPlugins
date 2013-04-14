package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdForceStop extends Cmd
{
	public CmdForceStop()
	{
		
	}
	
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
					p.sendMessage(var.defaultCol() + "You stopped the game.");
					Bukkit.broadcastMessage(var.defaultCol() + p.getName() + " stopped the game.");
				}
				else
					p.sendMessage(var.errorCol() + "Error: Game is already stopped.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not stop the Hunger Games.");
		}
		else
		{
			if(pl.gameGoing())
			{
				pl.endGame();
				sender.sendMessage(var.defaultCol() + "You stopped the game.");
				Bukkit.broadcastMessage(var.defaultCol() + "The console stopped the game.");
			}
			else
				sender.sendMessage(var.errorCol() + "Error: Game is already started.");
		}
		return true;
	}
}