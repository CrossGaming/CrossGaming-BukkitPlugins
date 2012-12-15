package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdPrice
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
			if(player.hasPermission("CCEconomy.price"))
			{
				String ItemName = "";
				String Oper = "";
				if (args.length == 1)
				{
					if(!args[0].equalsIgnoreCase("buy"))
					{
						if(!args[0].equalsIgnoreCase("sell"))
						{
							player.sendMessage(CCEconomy.messages + "Input either sell or buy");
							return false;
						}
					}
					ItemName = Integer.toString(player.getItemInHand().getTypeId());
					Oper = args[0];
				}
				if (args.length == 2)
				{
					if(!args[1].equalsIgnoreCase("buy"))
					{
						if(!args[1].equalsIgnoreCase("sell"))
						{
							player.sendMessage(CCEconomy.messages + "Input either sell or buy");
							return false;
						}
					}
					ItemName = args[0];
					Oper = args[1];
				}
				if(Formatter.isLegal(ItemName))
				{
					ItemName = Materials.idToName(Integer.parseInt(ItemName));
				}
				ItemName = Materials.FindItem(ItemName);
				if(!Materials.ItemExists(ItemName))
				{
					player.sendMessage(CCEconomy.messages + "That item does not exist");
					return true;
				}
				String file = "";
				if(Oper.equalsIgnoreCase("buy"))
				{
					file = CCEconomy.buyfile;
				}
				else if(Oper.equalsIgnoreCase("sell"))
				{
					file = CCEconomy.sellfile;
				}
				else
				{
					player.sendMessage(CCEconomy.messages + "Input either sell or buy");
					return false;
				}
				String cost = Prices.Cost(file, ItemName);
				ItemName = Formatter.CapFirst(ItemName);
				if(cost == null)
				{
					if(Oper.equalsIgnoreCase("buy"))
					{
						player.sendMessage(CCEconomy.messages + ItemName + " cannot be bought from the server");
					}
					else
					{
						player.sendMessage(CCEconomy.messages + ItemName + " cannot be sold to the server");
					}
					return true;
				}
				if(cost.equalsIgnoreCase("null"))
				{
					if(Oper.equalsIgnoreCase("buy"))
					{
						player.sendMessage(CCEconomy.messages + ItemName + " cannot be bought from the server");
					}
					else
					{
						player.sendMessage(CCEconomy.messages + ItemName + " cannot be sold to the server");
					}
					return true;
				}
				player.sendMessage(CCEconomy.messages + ItemName + " can be sold for " + CCEconomy.money + "$" + cost);
				return true;
			}
		}
		else
		{
			if (args.length != 2)
			{
				return false;
			}
			String ItemName = args[0];
			if(Formatter.isLegal(ItemName))
			{
				ItemName = Materials.idToName(Integer.parseInt(ItemName));
			}
			ItemName = Materials.FindItem(ItemName);
			if(!Materials.ItemExists(ItemName))
			{
				sender.sendMessage(CCEconomy.messages + "That item does not exist");
				return true;
			}
			String file;
			if(args[1].equalsIgnoreCase("buy"))
			{
				file = CCEconomy.buyfile;
			}
			else if(args[1].equalsIgnoreCase("sell"))
			{
				file = CCEconomy.sellfile;
			}
			else
			{
				sender.sendMessage(CCEconomy.messages + "Input either sell or buy");
				return false;
			}
			String cost = Prices.Cost(file, ItemName);
			ItemName = Formatter.CapFirst(ItemName);
			if(cost == null)
			{
				if(args[1].equalsIgnoreCase("buy"))
				{
					sender.sendMessage(CCEconomy.messages + ItemName + " cannot be bought from the server");
				}
				else
				{
					sender.sendMessage(CCEconomy.messages + ItemName + " cannot be sold to the server");
				}
				return true;
			}
			if(cost.equalsIgnoreCase("null"))
			{
				if(args[1].equalsIgnoreCase("buy"))
				{
					sender.sendMessage(CCEconomy.messages + ItemName + " cannot be bought from the server");
				}
				else
				{
					sender.sendMessage(CCEconomy.messages + ItemName + " cannot be sold to the server");
				}
				return true;
			}
			sender.sendMessage(CCEconomy.messages + ItemName + " can be sold for " + CCEconomy.money + "$" + cost);
			return true;
		}
		return false;
	}
}