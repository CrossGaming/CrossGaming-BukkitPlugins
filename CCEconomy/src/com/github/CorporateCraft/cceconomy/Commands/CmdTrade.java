package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.CorporateCraft.cceconomy.*;

public class CmdTrade
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length != 5)
	        {
	      	   return false;
	        }
			Player target = sender.getServer().getPlayer(args[0]);
			String pname = player.getName();
			String offertopname = target.getName();
			if(!Formatter.isLegal(args[3]))
			{
				return false;
			}
			if(!Formatter.isLegal(args[2]))
			{
				return false;
			}
			String price = args[3];
			String amount = args[2];
			String item = args[1];
			if(Formatter.isLegal(item))
			{
				item = Materials.idToName(Integer.parseInt(item));
			}
			price = Formatter.roundTwoDecimals(Double.parseDouble(price));
			if(args[4].equalsIgnoreCase("theypay"))
			{
				if(Double.parseDouble(BalChecks.Bal(offertopname)) - Double.parseDouble(price) < 0)
				{
					player.sendMessage(offertopname + " does not have that much money");
					return true;
				}
				PlayerInventory inventory = player.getInventory();
				if(!inventory.contains(Material.matchMaterial(item.toUpperCase()), Integer.parseInt(amount)))
				{
					player.sendMessage("You do not have that much " + item);
					return true;
				}
				Trade.CreateTrade(offertopname + " " + pname + " " + item + " " + amount + " " + price + " " + pname);
				player.sendMessage("You have offered a trade to " + offertopname);
				target.sendMessage(pname + " has offered to trade you " + amount + " of " + item + " for $" + price);
				target.sendMessage("Type /taccept or /tdeny to accept or deny their trade request");
			}
			if(args[4].equalsIgnoreCase("ipay"))
			{
				if(Double.parseDouble(BalChecks.Bal(pname)) - Double.parseDouble(price) < 0)
				{
					player.sendMessage("You do not have $" + price);
					return true;
				}
				PlayerInventory inventory = target.getInventory();
				if(!inventory.contains(Material.matchMaterial(item.toUpperCase()), Integer.parseInt(amount)))
				{
					player.sendMessage("They do not have that much " + item);
					return true;
				}
				Trade.CreateTrade(offertopname + " " + pname + " " + item + " " + amount + " " + price + " " + offertopname);
				player.sendMessage("You have offered a trade to " + offertopname);
				target.sendMessage(pname + " has offered to trade you $" + price + " for " + amount + " of " + item);
				target.sendMessage("Type /taccept " + pname + " or /tdeny " + pname + " to accept or deny their trade request");
			}
			return true;
		}
		else
		{
			sender.sendMessage("You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}