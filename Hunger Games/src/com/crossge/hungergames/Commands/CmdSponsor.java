package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSponsor extends Cmd
{
	public CmdSponsor()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.sponsor"))
			{
				if(args.length != 0)
					return false;
				if(pl.gameGoing())
				{
					if(pl.alreadySponsored(p.getName()))
					{
						p.sendMessage(var.errorCol() + "Error: You already sponsored yourself this round.");
						return true;
					}
					sp.giveItems(p);
					pl.addSponsored(p.getName());
					p.sendMessage(var.defaultCol() + "You sponsored yourself.");
				}
				else
					p.sendMessage(var.errorCol() + "Error: Game not going.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not view the help for Hunger Games.");
		}
		else
			sender.sendMessage(var.defaultCol() + "You may not sponsor yourself.");
		return true;
	}
}