package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.*;

public class CmdBanned
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String line = Formatter.readFileStr("banned-players");
			if(line == null)
			{
				player.sendMessage(Necessities.messages + "There are no banned players.");
				return true;
			}
			player.sendMessage(Necessities.messages + "Banned Players: " + line);
			return true;
		}
		else
		{
			String line = Formatter.readFileStr("banned-players");
			if(line == null)
			{
				sender.sendMessage(Necessities.messages + "There are no banned players.");
				return true;
			}
			sender.sendMessage(Necessities.messages + "Banned Players: " + line);
	        return true;
	    }
	}
}