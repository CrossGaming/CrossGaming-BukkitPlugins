package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.github.CorporateCraft.necessities.*;

import org.bukkit.entity.Player;

public class CmdTphere
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length != 1)
	        {
	      	   return false;
	        }
			Player target = sender.getServer().getPlayer(args[0]);
			player.sendMessage(Necessities.messages + "Teleporting...");
			target.sendMessage(Necessities.messages + "Teleporting...");
			target.teleport(player);
			return true;
		}
		else
		{
			sender.sendMessage(Necessities.messages + "You are not a player you can't teleport.");
			return true;
		}
	}
}