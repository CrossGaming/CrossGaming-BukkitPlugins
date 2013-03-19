package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdVote extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	Game g = new Game();
	public CmdVote()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 1)
				return false;
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.vote"))
			{
				int map = 1;
				try
				{
					map = Integer.parseInt(args[0]);
				}
				catch(Exception e)
				{
					return false;
				}
				if(map == 0 || map > 3)
				{
					p.sendMessage(var.errorCol() + "Please enter a number inbetween 1 and 3.");
					return false;
				}
				if(!pl.gameGoing())
				{
					int spot = pl.posInQueue(p.getName()); 
					if(spot != 0)
					{
						g.addVote(p.getName(), map);
						p.sendMessage(var.defaultCol() + "You voted for map " + args[0]);
					}
					else
					{
						p.sendMessage(var.defaultCol() + "You must join the queue before you can vote.");
					}
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