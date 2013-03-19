package com.crossge.hungergames.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdJoin extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	public CmdJoin()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.join"))
			{
				if(pl.isAlive(p.getName()))
				{
					p.sendMessage(var.defaultCol() + "You are already in a game.");
					return true;
				}
				int spot = pl.posInQueue(p.getName()); 
				if(spot == 0)
				{
					pl.addToQueue(p.getName());
					spot = pl.posInQueue(p.getName());
					p.sendMessage(var.defaultCol() + "Added in line position " + ChatColor.GOLD + "#" + Integer.toString(spot) + var.defaultCol() + " for the next game.");
					return true;
				}
				if(pl.queueFull())
				{
					p.sendMessage(var.defaultCol() + " Sorry the next game is full.");
					return true;
				}
				p.sendMessage(ChatColor.GOLD + "#" + Integer.toString(spot) + var.defaultCol() + " in line for the next game.");
			}
			else
			{
				p.sendMessage(var.errorCol() + "Error you may not join the Hunger Games.");
			}
		}
		else
		{
			sender.sendMessage("You cannot join the hunger games, please log in.");
		}
		return true;
	}	
}
