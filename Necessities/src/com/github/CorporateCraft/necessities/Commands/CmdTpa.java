package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdTpa
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
			String pname = player.getName();
			Player target = sender.getServer().getPlayer(args[0]);
			String rname = target.getName();
			Teleports.CreateTp(rname + " " + pname, "tothem");
			player.sendMessage(Necessities.messages + "You sent a teleport request to " + rname);
			target.sendMessage(Necessities.messages + pname + " is requesting to teleport to you");
			target.sendMessage(Necessities.messages + "Type /tpaccept or /tpdeny to accept or deny their trade request");
			return true;
		}
		else
		{
			sender.sendMessage(Necessities.messages + "You are not a player you can't teleport.");
			return true;
		}
	}
}