package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdForceStart extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	public CmdForceStart()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.forcestart"))
			{
				if(!pl.allowStart())
				{
					pl.gameStart();
					p.sendMessage(var.defaultCol() + "You started the game.");
					Bukkit.broadcastMessage(var.defaultCol() + p.getName() + " started the game.");
				}
				else
					p.sendMessage(var.errorCol() + "Error: Game is already started.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not vote to start the Hunger Games.");
		}
		else
		{
			if(pl.gameGoing())
			{
				pl.gameStart();
				sender.sendMessage(var.defaultCol() + "You started the game.");
				Bukkit.broadcastMessage(var.defaultCol() + "The console started the game.");
			}
			else
				sender.sendMessage(var.errorCol() + "Error: Game is already started.");
		}
		return true;
	}
}