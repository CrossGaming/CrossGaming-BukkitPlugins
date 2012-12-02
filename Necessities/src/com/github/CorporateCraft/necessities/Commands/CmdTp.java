package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import com.github.CorporateCraft.necessities.*;

import org.bukkit.entity.Player;

public class CmdTp extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdTp()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length != 1)
	        {
	      	   return false;
	        }
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				return false;
			}
			player.sendMessage(arl.GetCol() + "Teleporting...");
			target.sendMessage(arl.GetCol() + "Teleporting...");
			player.teleport(target);
			return true;
		}
		else
		{
			sender.sendMessage(arl.GetCol() + "You are not a player you can't teleport.");
			return true;
		}
	}
}