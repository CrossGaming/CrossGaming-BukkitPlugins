package com.crossge.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import com.crossge.cceconomy.*;

public class CmdTradeItems extends Cmd
{
	Formatter form = new Formatter();
	Trade tr = new Trade();
	Materials mat = new Materials();
	ArrayLists arl = new ArrayLists();
	public CmdTradeItems()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 5)
				return false;
			Player player = (Player) sender;
			Player target = sender.getServer().getPlayer(args[0]);
			String pname = player.getName();
			String offertopname = target.getName();
			if(!form.isLegal(args[2]) || !form.isLegal(args[4]))
				return false;
			String amountgetting = args[2];
			String itemgetting = "";
			String amountoffering = args[4];
			String itemoffering = "";
			short dataget = 0;
			short dataoff = 0;
			String tempget = "";
			tempget = args[1].replaceAll(":", " ");
			itemgetting = tempget.split(" ")[0];
			String tempoff = "";
			tempoff = args[3].replaceAll(":", " ");
			itemoffering = tempget.split(" ")[0];
			if(form.isLegal(itemgetting))
			{
				itemgetting = mat.idToName(Integer.parseInt(itemgetting));
				try
				{
					dataget = Short.parseShort(tempget.split(" ")[1]);
				}
				catch(Exception e)
				{
					dataget = 0;
				}
			}
			if(form.isLegal(itemoffering))
			{
				itemoffering = mat.idToName(Integer.parseInt(itemoffering));
				try
				{
					dataoff = Short.parseShort(tempoff.split(" ")[1]);
				}
				catch(Exception e)
				{
					dataoff = 0;
				}
			}
			PlayerInventory thereinventory = target.getInventory();
			PlayerInventory yourinventory = player.getInventory();
			itemoffering = mat.findItem(itemoffering);
			itemgetting = mat.findItem(itemgetting);
			if(!mat.itemExists(itemoffering) || !mat.itemExists(itemgetting))
			{
				player.sendMessage(arl.getMessages() + "That item does not exist");
				return true;
			}
			if(!yourinventory.contains(Material.matchMaterial(itemoffering), Integer.parseInt(amountoffering)))
			{
				player.sendMessage(arl.getMessages() + "You do not have that much " + form.capFirst(itemoffering));
				return true;
			}
			if(!thereinventory.contains(Material.matchMaterial(itemgetting), Integer.parseInt(amountgetting)))
			{
				player.sendMessage(arl.getMessages() + "They do not have that much " + form.capFirst(itemgetting));
				return true;
			}
			tr.createTrade(offertopname + " " + pname + " " + itemgetting + ":" + Short.toString(dataget) + " " + amountgetting + " " + itemoffering + ":" + Short.toString(dataoff) + " " + amountoffering);
			itemgetting = form.capFirst(itemgetting);
			itemoffering = form.capFirst(itemoffering);
			player.sendMessage(arl.getMessages() + "You have offered a trade to " + offertopname);
			target.sendMessage(arl.getMessages() + pname + " has offered to trade you " + amountgetting + " of " + itemgetting + " for " + amountoffering + " of " + itemoffering);
			target.sendMessage(arl.getMessages() + "Type /taccept " + pname + " or /tdeny " + pname + " to accept or deny their trade request");
			return true;
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}