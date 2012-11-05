package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdSetCost
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 2)
			{
				return false;
			}
			if (args.length == 0)
			{
				return false;
			}
			if(player.hasPermission("CCEconomy.setcost"))
			{
				String ItemName = "";					
				if(args.length == 2)
				{
					ItemName = args[0];
					if(Formatter.isLegal(ItemName))
					{
						ItemName = Materials.idToName(Integer.parseInt(ItemName));
					}
					if(!Formatter.isLegal(args[1]) && !args[1].equalsIgnoreCase("null"))
					{
						return false;
					}
					ItemName = Materials.FindItem(ItemName);
					if(!Materials.ItemExists(ItemName))
					{
						player.sendMessage(CCEconomy.messages + "That item does not exist");
						return true;
					}
					if(args[1].equalsIgnoreCase("null"))
					{
						Prices.SetCost(CCEconomy.buyfile, ItemName, args[1]);
						ItemName = Formatter.CapFirst(ItemName);
						player.sendMessage(CCEconomy.messages + ItemName + " can no longer be bought");
						return true;
					}
					else
					{
						Prices.SetCost(CCEconomy.buyfile, ItemName, Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
						ItemName = Formatter.CapFirst(ItemName);
						player.sendMessage(CCEconomy.messages + ItemName + "'s cost was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
						return true;
					}
				}
				else
				{
					ItemName = Integer.toString(player.getItemInHand().getTypeId());
					if(Formatter.isLegal(ItemName))
					{
						ItemName = Materials.idToName(Integer.parseInt(ItemName));
					}
					if(!Formatter.isLegal(args[0]) && !args[0].equalsIgnoreCase("null"))
					{
						return false;
					}
					ItemName = Materials.FindItem(ItemName);
					if(!Materials.ItemExists(ItemName))
					{
						player.sendMessage(CCEconomy.messages + "That item does not exist");
						return true;
					}
					if(args[0].equalsIgnoreCase("null"))
					{
						Prices.SetCost(CCEconomy.buyfile, ItemName, args[0]);
						ItemName = Formatter.CapFirst(ItemName);
						player.sendMessage(CCEconomy.messages + ItemName + " can no longer be bought");
						return true;
					}
					else
					{
						Prices.SetCost(CCEconomy.buyfile, ItemName, Formatter.roundTwoDecimals(Double.parseDouble(args[0])));
						ItemName = Formatter.CapFirst(ItemName);
						player.sendMessage(CCEconomy.messages + ItemName + "'s cost was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[0])));
						return true;
					}
				}
			}
		}
		else
		{
			if (args.length != 2)
			{
				return false;
			}
			String ItemName;
			ItemName = args[0];
			if(Formatter.isLegal(ItemName))
			{
				ItemName = Materials.idToName(Integer.parseInt(ItemName));
			}
			if(!Formatter.isLegal(args[1]) && !args[1].equalsIgnoreCase("null"))
			{
				return false;
			}
			ItemName = Materials.FindItem(ItemName);
			if(!Materials.ItemExists(ItemName))
			{
				sender.sendMessage(CCEconomy.messages + "That item does not exist");
				return true;
			}
			if(args[1].equalsIgnoreCase("null"))
			{
				Prices.SetCost(CCEconomy.buyfile, ItemName, args[1]);
				ItemName = Formatter.CapFirst(ItemName);
				sender.sendMessage(CCEconomy.messages + ItemName + " can no longer be bought");
				return true;
			}
			else
			{
				Prices.SetCost(CCEconomy.buyfile, ItemName, Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
				ItemName = Formatter.CapFirst(ItemName);
				sender.sendMessage(CCEconomy.messages + ItemName + "'s cost was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
				return true;
			}
		}
		return false;
	}
}