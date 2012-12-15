package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKill extends Cmd
{
	public CmdKill()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length == 0)
			{
				return false;
			}
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				return false;
			}
			if(args.length == 1)
			{
				target.setHealth(0);
				return true;
			}
			int i = 1;
			String deathMessage = target.getName() + " ";
			while (i < args.length)
			{
				deathMessage += args[i] + " ";
				i++;
			}        	   
			deathMessage = deathMessage.trim();
			target.setHealth(0);
			Bukkit.broadcastMessage(deathMessage);
			return true;
        } 
		else
		{
			if(args.length == 0)
			{
				return false;
			}
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				return false;
			}
			if(args.length == 1)
			{
				target.setHealth(0);
				return true;
			}
			int i = 1;
			String deathMessage = target.getName() + " ";
			while (i < args.length)
			{
				deathMessage += args[i] + " ";
				i++;
			}        	   
			deathMessage = deathMessage.trim();
			target.setHealth(0);
			Bukkit.broadcastMessage(deathMessage);
        	return true;
	    }
	}
}