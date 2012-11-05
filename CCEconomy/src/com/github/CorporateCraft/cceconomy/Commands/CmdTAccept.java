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
					if(!yourinventory.contains(Material.matchMaterial(Materials.FindItem(itemgetting)), Integer.parseInt(amountgetting)))
					{
						player.sendMessage(CCEconomy.messages + "You do not have that much " + itemgetting);
						return true;
					}
					if(!thereinventory.contains(Material.matchMaterial(Materials.FindItem(itemoffering)), Integer.parseInt(amountoffering)))
					{
						player.sendMessage(CCEconomy.messages + "They do not have that much " + itemoffering);
						return true;
					}
					ItemStack itemstack = new ItemStack(Material.matchMaterial(Materials.FindItem(itemgetting)), Integer.parseInt(amountgetting));
					ItemStack is = new ItemStack(Material.matchMaterial(Materials.FindItem(itemoffering)), Integer.parseInt(amountoffering));
					yourinventory.addItem(is);
					yourinventory.removeItem(itemstack);
					thereinventory.addItem(itemstack);
					thereinventory.removeItem(is);
				}
				if(ToWhom.equalsIgnoreCase(pname))
				{
					if(Double.parseDouble(BalChecks.Bal(offerpname)) - Double.parseDouble(price) < 0)
					{
						player.sendMessage(CCEconomy.messages + "They do not have " + CCEconomy.money + "$" + price);
						return true;
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					if(!yourinventory.contains(Material.matchMaterial(Materials.FindItem(item)), Integer.parseInt(amount)))
					{
						player.sendMessage(CCEconomy.messages + "You do not have that much " + item);
						return true;
					}
					EditPlayerMoney.RemoveMoney(offerpname, Double.parseDouble(price));
					EditPlayerMoney.AddMoney(pname, Double.parseDouble(price));
					ItemStack itemstack = new ItemStack(Material.matchMaterial(Materials.FindItem(item)), Integer.parseInt(amount));
					thereinventory.addItem(itemstack);
					yourinventory.removeItem(itemstack);
				}
				if(ToWhom.equalsIgnoreCase(offerpname))
				{
					if(Double.parseDouble(BalChecks.Bal(pname)) - Double.parseDouble(price) < 0)
					{
						player.sendMessage(CCEconomy.messages + "You do not have " + CCEconomy.money + "$" + price);
						return true;
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					if(!thereinventory.contains(Material.matchMaterial(Materials.FindItem(item)), Integer.parseInt(amount)))
					{
						player.sendMessage(CCEconomy.messages + "They do not have that much " + item);
						return true;
					}
					EditPlayerMoney.RemoveMoney(pname, Double.parseDouble(price));
					EditPlayerMoney.AddMoney(offerpname, Double.parseDouble(price));
					ItemStack itemstack = new ItemStack(Material.matchMaterial(Materials.FindItem(item)), Integer.parseInt(amount));
					yourinventory.addItem(itemstack);
					thereinventory.removeItem(itemstack);
				}
				player.sendMessage(CCEconomy.messages + "You have accepted the trade from " + offerpname);
				target.sendMessage(CCEconomy.messages + "Your trade to " + pname + " has been accepted");
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