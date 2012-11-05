package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.CorporateCraft.cceconomy.CCEconomy;
import com.github.CorporateCraft.cceconomy.Formatter;
import com.github.CorporateCraft.cceconomy.Materials;
import com.github.CorporateCraft.cceconomy.Trade;

public class CmdTradeItems
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
			if(!Formatter.isLegal(args[4]))
			{
				return false;
			}
			if(!Formatter.isLegal(args[2]))
			{
				return false;
			}
			String amountgetting = args[2];
			String itemgetting = args[1];
			String amountoffering = args[4];
			String itemoffering = args[3];
			if(Formatter.isLegal(itemgetting))
			{
				itemgetting = Materials.idToName(Integer.parseInt(itemgetting));
			}
			if(Formatter.isLegal(itemoffering))
			{
				itemoffering = Materials.idToName(Integer.parseInt(itemoffering));
			}
			PlayerInventory thereinventory = target.getInventory();
			PlayerInventory yourinventory = player.getInventory();
			itemoffering = Materials.FindItem(itemoffering);
			itemgetting = Materials.FindItem(itemgetting);
			if(!Materials.ItemExists(itemoffering))
			{
				player.sendMessage(CCEconomy.messages + "That item does not exist");
				return true;
			}
			if(!Materials.ItemExists(itemgetting))
			{
				player.sendMessage(CCEconomy.messages + "That item does not exist");
				return true;
			}
			if(!yourinventory.contains(Material.matchMaterial(itemoffering), Integer.parseInt(amountoffering)))
			{
				player.sendMessage(CCEconomy.messages + "You do not have that much " + Formatter.CapFirst(itemoffering));
				return true;
			}
			if(!thereinventory.contains(Material.matchMaterial(itemgetting), Integer.parseInt(amountgetting)))
			{
				player.sendMessage(CCEconomy.messages + "They do not have that much " + Formatter.CapFirst(itemgetting));
				return true;
			}
			Trade.CreateTrade(offertopname + " " + pname + " " + itemgetting + " " + amountgetting + " " + itemoffering + " " + amountoffering);
			itemgetting = Formatter.CapFirst(itemgetting);
			itemoffering = Formatter.CapFirst(itemoffering);
			player.sendMessage(CCEconomy.messages + "You have offered a trade to " + offertopname);
			target.sendMessage(CCEconomy.messages + pname + " has offered to trade you " + amountgetting + " of " + itemgetting + " for " + amountoffering + " of " + itemoffering);
			target.sendMessage(CCEconomy.messages + "Type /taccept " + pname + " or /tdeny " + pname + " to accept or deny their trade request");
			return true;
		}
		else
		{
			sender.sendMessage(CCEconomy.messages + "You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}