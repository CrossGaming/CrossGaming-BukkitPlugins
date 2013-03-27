package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdSpectate extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
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
						pl.delSpectating(p.getName());
						p.sendMessage(var.defaultCol() + "No longer spectating the Hunger Games.");
						return true;
					}
					pl.addSpectating(p.getName());
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