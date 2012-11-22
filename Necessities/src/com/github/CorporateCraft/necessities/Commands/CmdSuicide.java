package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdSuicide
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if(args.length != 0)
			{
				return false;
			}
			String DeathMessage = player.getName() + " committed suicide";
			DeathMessage = DeathMessage.trim();
			player.setHealth(0);
			Bukkit.broadcastMessage(DeathMessage);
			return true;
        } 
		else
		{
			sender.sendMessage(Necessities.messages + "You are not a player you can not commit suicide");
        	return true;
	    }
	}
}