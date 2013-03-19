package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdVote extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	public CmdVote()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.vote"))
			{
				if(pl.gameGoing())
				{
					pl.addVote(p.getName());
					p.sendMessage(var.defaultCol() + "You voted to start the game.");
					Bukkit.broadcastMessage(var.defaultCol() + p.getName() + " voted to start the game.");
				}
				else
				{
					p.sendMessage(var.defaultCol() + "Game is already started.");
				}
			}
			else
			{
				p.sendMessage(var.errorCol() + "Error you may not vote to start the Hunger Games.");
			}
		}
		else
		{
			sender.sendMessage(var.errorCol() + "You cannot vote for starting the hunger games, please log in.");
		}
		return true;
	}
}