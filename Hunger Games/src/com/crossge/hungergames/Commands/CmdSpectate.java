package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpectate extends Cmd
{
	public CmdSpectate()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.spectate"))
			{
				if(pl.isAlive(p.getName()))
				{
					p.sendMessage(var.errorCol() + "Error: You are already in a game.");
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
								p.sendMessage(var.defaultCol() + "Now spectating " + target.getName() + ".");
							}
							else
								p.sendMessage(var.errorCol() + "Error: That player is not playing.");
						}
						else
						{
							pl.delSpectating(p.getName());
							p.sendMessage(var.defaultCol() + "No longer spectating the Hunger Games.");
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
							p.sendMessage(var.defaultCol() + "Now spectating " + target.getName() + ".");
						}
						else
							p.sendMessage(var.errorCol() + "Error: That player is not playing.");
					}
					else
						p.sendMessage(var.defaultCol() + "Now spectating the Hunger Games.");
				}
				else
					p.sendMessage(var.errorCol() + "Error: There is no current game.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not spectate the Hunger Games.");
		}
		else
			sender.sendMessage(var.errorCol() + "Error: You cannot spectate the hunger games, please log in.");
		return true;
	}
}