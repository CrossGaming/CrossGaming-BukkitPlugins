package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.CorporateCraft.cceconomy.*;

public class CmdTAccept
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
				String Info = Trade.AcceptTrade(pname, offerpname);
				String item = Info.split(" ")[0];
				String amount = Info.split(" ")[1];
				String price = Info.split(" ")[2];
				String ToWhom = Info.split(" ")[3];
				if(Formatter.isLegal(price))
				{
					price = Formatter.roundTwoDecimals(Double.parseDouble(price));
				}
				if(Formatter.isLegal(item))
				{
					item = Materials.idToName(Integer.parseInt(item));
				}
				if(!ToWhom.equalsIgnoreCase(pname) && !ToWhom.equalsIgnoreCase(offerpname))
				{
					String amountgetting = amount;
					String itemgetting = item;
					String amountoffering = ToWhom;
					String itemoffering = price;
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					if(!yourinventory.contains(Material.matchMaterial(itemgetting.toUpperCase()), Integer.parseInt(amountgetting)))
					{
						player.sendMessage("You do not have that much " + itemgetting);
						return true;
					}
					if(!thereinventory.contains(Material.matchMaterial(itemoffering.toUpperCase()), Integer.parseInt(amountoffering)))
					{
						player.sendMessage("They do not have that much " + itemoffering);
						return true;
					}
					ItemStack itemstack = new ItemStack(Material.matchMaterial(itemgetting.toUpperCase()), Integer.parseInt(amountgetting));
					ItemStack is = new ItemStack(Material.matchMaterial(itemoffering.toUpperCase()), Integer.parseInt(amountoffering));
					yourinventory.addItem(itemstack);
					yourinventory.removeItem(is);
					thereinventory.addItem(is);
					thereinventory.removeItem(itemstack);
				}
				if(ToWhom.equalsIgnoreCase(pname))
				{
					if(Double.parseDouble(BalChecks.Bal(offerpname)) - Double.parseDouble(price) < 0)
					{
						player.sendMessage("They do not have $" + price);
						return true;
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					if(!yourinventory.contains(Material.matchMaterial(item.toUpperCase()), Integer.parseInt(amount)))
					{
						player.sendMessage("You do not have that much " + item);
						return true;
					}
					EditPlayerMoney.RemoveMoney(offerpname, Double.parseDouble(price));
					EditPlayerMoney.AddMoney(pname, Double.parseDouble(price));
					ItemStack itemstack = new ItemStack(Material.matchMaterial(item.toUpperCase()), Integer.parseInt(amount));
					thereinventory.addItem(itemstack);
					yourinventory.removeItem(itemstack);
				}
				if(ToWhom.equalsIgnoreCase(offerpname))
				{
					if(Double.parseDouble(BalChecks.Bal(pname)) - Double.parseDouble(price) < 0)
					{
						player.sendMessage("You do not have $" + price);
						return true;
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					if(!thereinventory.contains(Material.matchMaterial(item.toUpperCase()), Integer.parseInt(amount)))
					{
						player.sendMessage("They do not have that much " + item);
						return true;
					}
					EditPlayerMoney.RemoveMoney(pname, Double.parseDouble(price));
					EditPlayerMoney.AddMoney(offerpname, Double.parseDouble(price));
					ItemStack itemstack = new ItemStack(Material.matchMaterial(item.toUpperCase()), Integer.parseInt(amount));
					yourinventory.addItem(itemstack);
					thereinventory.removeItem(itemstack);
				}
				player.sendMessage("You have accepted the trade from " + offerpname);
				target.sendMessage("Your trade to " + pname + " has been accepted");
				return true;
			}
			else
			{
				player.sendMessage("You do not have a trade offer from " + offerpname);
				return true;
			}
		}
		else
		{
			sender.sendMessage("You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}