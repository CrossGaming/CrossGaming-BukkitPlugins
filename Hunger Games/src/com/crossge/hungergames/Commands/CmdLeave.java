package com.crossge.hungergames.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdLeave extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	public CmdLeave()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.leave"))
			{
				int spot = pl.posInQueue(p.getName()); 
				if(spot == 0)//In game not in line
				{
					p.setHealth(0);
					p.sendMessage(var.defaultCol() + "You left theHunger Games.");
					Bukkit.broadcastMessage(var.defaultCol() + p.getName() + " left the current hunger games.");
					return true;
				}
				pl.removeFromQueue(p.getName());
				p.sendMessage(var.defaultCol() + "Removed from the line for joining the next game.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not leave the Hunger Games.");//wait wtf
		}
		else
			sender.sendMessage(var.errorCol() + "Error: You cannot leave the hunger games, please log in.");
		return true;
	}
}