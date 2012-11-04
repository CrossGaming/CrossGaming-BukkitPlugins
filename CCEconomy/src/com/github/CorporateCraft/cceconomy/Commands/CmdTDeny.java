package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdTDeny
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
			String pname = player.getName();
			String offerpname = target.getName();
			if(Trade.hasTrade(pname, offerpname))
			{
				player.sendMessage(CCEconomy.messages + "You have denied the trade from " + offerpname);
				target.sendMessage(CCEconomy.messages + "Your trade to " + pname + " has been denied");
				Trade.DenyTrade(pname, offerpname);
				return true;
			}
			else
			{
				player.sendMessage(CCEconomy.messages + "You do not have a trade offer from " + offerpname);
				return true;
			}
		}
		else
		{
			sender.sendMessage(CCEconomy.messages + "You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
	
}