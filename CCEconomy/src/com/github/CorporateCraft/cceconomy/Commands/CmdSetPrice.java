package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdSetPrice extends Cmd
{
	ArrayLists arl = new ArrayLists();
	Formatter form = new Formatter();
	Prices pr = new Prices();
	Materials mat = new Materials();
	public CmdSetPrice()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 3)
				return false;
			if (args.length == 0)
				return false;
			if (args.length == 1)
				return false;
			String itemName = "";					
			if(args.length == 3)
			{
				itemName = args[0];
				if(form.isLegal(itemName))
					itemName = mat.idToName(Integer.parseInt(itemName));
				if(!form.isLegal(args[1]) && !args[1].equalsIgnoreCase("null"))
					return false;
				itemName = mat.findItem(itemName);
				if(!mat.itemExists(itemName))
				{
					player.sendMessage(arl.getMessages() + "That item does not exist");
					return true;
				}
				String file;
				if(args[2].equalsIgnoreCase("buy"))
					file = arl.getBuyFile();
				else if(args[2].equalsIgnoreCase("sell"))
					file = arl.getSellFile();
				else
				{
					player.sendMessage(arl.getMessages() + "Input either sell or buy");
					return false;
				}
				if(args[1].equalsIgnoreCase("null"))
				{
					pr.setCost(file, itemName, args[1]);
					itemName = itemName.replaceAll("_ITEM", "");
					itemName = form.capFirst(itemName);
					if(args[2].equalsIgnoreCase("buy"))
						player.sendMessage(arl.getMessages() + itemName + " can no longer be bought");
					else
						player.sendMessage(arl.getMessages() + itemName + " can no longer be sold");
					return true;
				}
				else
				{
					pr.setCost(file, itemName, form.roundTwoDecimals(Double.parseDouble(args[1])));
					itemName = itemName.replaceAll("_ITEM", "");
					itemName = form.capFirst(itemName);
					if(args[2].equalsIgnoreCase("buy"))
						player.sendMessage(arl.getMessages() + itemName + "'s buy price was set to " + arl.getMoney() + "$" + form.roundTwoDecimals(Double.parseDouble(args[1])));
					else
						player.sendMessage(arl.getMessages() + itemName + "'s sell price was set to " + arl.getMoney() + "$" + form.roundTwoDecimals(Double.parseDouble(args[1])));
					return true;
				}
			}
			if(args.length == 2)
			{
				if(!args[1].equalsIgnoreCase("buy") && !args[1].equalsIgnoreCase("sell"))
				{
					player.sendMessage(arl.getMessages() + "Input either sell or buy");
					return false;
				}
				itemName = Integer.toString(player.getItemInHand().getTypeId());
				if(form.isLegal(itemName))
					itemName = mat.idToName(Integer.parseInt(itemName));
				if(!form.isLegal(args[0]) && !args[0].equalsIgnoreCase("null"))
					return false;
				itemName = mat.findItem(itemName);
				if(!mat.itemExists(itemName))
				{
					player.sendMessage(arl.getMessages() + "That item does not exist");
					return true;
				}
				String file;
				if(args[1].equalsIgnoreCase("buy"))
					file = arl.getBuyFile();
				else if(args[1].equalsIgnoreCase("sell"))
					file = arl.getSellFile();
				else
				{
					player.sendMessage(arl.getMessages() + "Input either sell or buy");
					return false;
				}
				if(args[0].equalsIgnoreCase("null"))
				{
					pr.setCost(file, itemName, args[0]);
					itemName = itemName.replaceAll("_ITEM", "");
					itemName = form.capFirst(itemName);
					if(args[1].equalsIgnoreCase("buy"))
						player.sendMessage(arl.getMessages() + itemName + " can no longer be bought");
					else
						player.sendMessage(arl.getMessages() + itemName + " can no longer be sold");
					return true;
				}
				else
				{
					pr.setCost(file, itemName, form.roundTwoDecimals(Double.parseDouble(args[0])));
					itemName = itemName.replaceAll("_ITEM", "");
					itemName = form.capFirst(itemName);
					if(args[1].equalsIgnoreCase("buy"))
						player.sendMessage(arl.getMessages() + itemName + "'s buy price was set to " + arl.getMoney() + "$" + form.roundTwoDecimals(Double.parseDouble(args[0])));
					else
						player.sendMessage(arl.getMessages() + itemName + "'s sell price was set to " + arl.getMoney() + "$" + form.roundTwoDecimals(Double.parseDouble(args[0])));
					return true;
				}
			}
			return true;
		}
		else
		{
			if (args.length != 3)
				return false;
			String itemName;
			itemName = args[0];
			if(form.isLegal(itemName))
				itemName = mat.idToName(Integer.parseInt(itemName));
			if(!form.isLegal(args[1]) && !args[1].equalsIgnoreCase("null"))
				return false;
			itemName = mat.findItem(itemName);
			if(!mat.itemExists(itemName))
			{
				sender.sendMessage(arl.getMessages() + "That item does not exist");
				return true;
			}
			String file;
			if(args[2].equalsIgnoreCase("buy"))
				file = arl.getBuyFile();
			else if(args[2].equalsIgnoreCase("sell"))
				file = arl.getSellFile();
			else
			{
				sender.sendMessage(arl.getMessages() + "Input either sell or buy");
				return false;
			}
			if(args[1].equalsIgnoreCase("null"))
			{
				pr.setCost(file, itemName, args[1]);
				itemName = itemName.replaceAll("_ITEM", "");
				itemName = form.capFirst(itemName);
				if(args[2].equalsIgnoreCase("buy"))
					sender.sendMessage(arl.getMessages() + itemName + " can no longer be bought");
				else
					sender.sendMessage(arl.getMessages() + itemName + " can no longer be sold");
				return true;
			}
			else
			{
				pr.setCost(file, itemName, form.roundTwoDecimals(Double.parseDouble(args[1])));
				itemName = itemName.replaceAll("_ITEM", "");
				itemName = form.capFirst(itemName);
				if(args[2].equalsIgnoreCase("buy"))
					sender.sendMessage(arl.getMessages() + itemName + "'s buy price was set to " + arl.getMoney() + "$" + form.roundTwoDecimals(Double.parseDouble(args[1])));
				else
					sender.sendMessage(arl.getMessages() + itemName + "'s sell price was set to " + arl.getMoney() + "$" + form.roundTwoDecimals(Double.parseDouble(args[1])));
				return true;
			}
		}
	}
}