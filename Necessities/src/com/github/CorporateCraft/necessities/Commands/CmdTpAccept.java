package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdTpAccept
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
				String ToWhom = Teleports.AcceptTp(pname, rname);
				if(ToWhom.equals("tothem"))
				{
					target.teleport(player);
				}
				if(ToWhom.equals("tome"))
				{
					player.teleport(target);
				}
				player.sendMessage(Necessities.messages + "Teleport Accepted.");
				target.sendMessage(Necessities.messages + "Your teleport offer has been accepted");
				return true;
			}
			else
			{
				if(rname.equalsIgnoreCase(pname))
				{
					player.sendMessage(Necessities.messages + "You do not have a teleport request pending");
					return true;
				}
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