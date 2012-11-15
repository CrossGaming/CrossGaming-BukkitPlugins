package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdTpDeny
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 1)
	        {
	      	   return false;
	        }
			String pname = player.getName();
			String rname;
			Player target;
			if(args.length == 0)
			{
				target = sender.getServer().getPlayer(Teleports.LastOffer(pname));
			}
			else
			{
				target = sender.getServer().getPlayer(args[0]);
			}
			rname = target.getName();
			if(Teleports.hasTp(pname, rname))
			{
				player.sendMessage(Necessities.messages + "Teleport Denied.");
				target.sendMessage(Necessities.messages + "Your teleport offer has been denied");
				Teleports.DenyTp(pname, rname);
				return true;
			}
			else
			{
				player.sendMessage(Necessities.messages + "You do not have a teleport request from " + rname);
				return true;
			}
		}
		else
		{
			sender.sendMessage(Necessities.messages + "You are not a player you can't teleport.");
			return true;
		}
	}
	
}