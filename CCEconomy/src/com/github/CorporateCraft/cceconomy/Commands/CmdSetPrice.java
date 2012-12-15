package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdSetPrice
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 3)
			{
				return false;
			}
			if (args.length == 0)
			{
				return false;
			}
			if (args.length == 1)
			{
				return false;
			}
			if(player.hasPermission("CCEconomy.setprice"))
			{
				String ItemName = "";					
				if(args.length == 3)
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
					String file;
					if(args[2].equalsIgnoreCase("buy"))
					{
						file = CCEconomy.buyfile;
					}
					else if(args[2].equalsIgnoreCase("sell"))
					{
						file = CCEconomy.sellfile;
					}
					else
					{
						player.sendMessage(CCEconomy.messages + "Input either sell or buy");
						return false;
					}
					if(args[1].equalsIgnoreCase("null"))
					{
						Prices.SetCost(file, ItemName, args[1]);
						ItemName = Formatter.CapFirst(ItemName);
						if(args[2].equalsIgnoreCase("buy"))
						{
							player.sendMessage(CCEconomy.messages + ItemName + " can no longer be bought");
						}
						else
						{
							player.sendMessage(CCEconomy.messages + ItemName + " can no longer be sold");
						}
						return true;
					}
					else
					{
						Prices.SetCost(file, ItemName, Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
						ItemName = Formatter.CapFirst(ItemName);
						if(args[2].equalsIgnoreCase("buy"))
						{
							player.sendMessage(CCEconomy.messages + ItemName + "'s buy price was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
						}
						else
						{
							player.sendMessage(CCEconomy.messages + ItemName + "'s sell price was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
						}
						return true;
					}
				}
				if(args.length == 2)
				{
					if(!args[1].equalsIgnoreCase("buy"))
					{
						if(!args[1].equalsIgnoreCase("sell"))
						{
							player.sendMessage(CCEconomy.messages + "Input either sell or buy");
							return false;
						}
					}
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
						player.sendMessage(CCEconomy.messages + "Input either sell or buy");
						return false;
					}
					if(args[0].equalsIgnoreCase("null"))
					{
						Prices.SetCost(file, ItemName, args[0]);
						ItemName = Formatter.CapFirst(ItemName);
						if(args[1].equalsIgnoreCase("buy"))
						{
							player.sendMessage(CCEconomy.messages + ItemName + " can no longer be bought");
						}
						else
						{
							player.sendMessage(CCEconomy.messages + ItemName + " can no longer be sold");
						}
						return true;
					}
					else
					{
						Prices.SetCost(file, ItemName, Formatter.roundTwoDecimals(Double.parseDouble(args[0])));
						ItemName = Formatter.CapFirst(ItemName);
						if(args[1].equalsIgnoreCase("buy"))
						{
							player.sendMessage(CCEconomy.messages + ItemName + "'s buy price was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[0])));
						}
						else
						{
							player.sendMessage(CCEconomy.messages + ItemName + "'s sell price was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[0])));
						}
						return true;
					}
				}
			}
		}
		else
		{
			if (args.length != 3)
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
			String file;
			if(args[2].equalsIgnoreCase("buy"))
			{
				file = CCEconomy.buyfile;
			}
			else if(args[2].equalsIgnoreCase("sell"))
			{
				file = CCEconomy.sellfile;
			}
			else
			{
				sender.sendMessage(CCEconomy.messages + "Input either sell or buy");
				return false;
			}
			if(args[1].equalsIgnoreCase("null"))
			{
				Prices.SetCost(file, ItemName, args[1]);
				ItemName = Formatter.CapFirst(ItemName);
				if(args[2].equalsIgnoreCase("buy"))
				{
					sender.sendMessage(CCEconomy.messages + ItemName + " can no longer be bought");
				}
				else
				{
					sender.sendMessage(CCEconomy.messages + ItemName + " can no longer be sold");
				}
				return true;
			}
			else
			{
				Prices.SetCost(file, ItemName, Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
				ItemName = Formatter.CapFirst(ItemName);
				if(args[2].equalsIgnoreCase("buy"))
				{
					sender.sendMessage(CCEconomy.messages + ItemName + "'s buy price was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
				}
				else
				{
					sender.sendMessage(CCEconomy.messages + ItemName + "'s sell price was set to " + CCEconomy.money + "$" + Formatter.roundTwoDecimals(Double.parseDouble(args[1])));
				}
				return true;
			}
		}
		return false;
	}
}