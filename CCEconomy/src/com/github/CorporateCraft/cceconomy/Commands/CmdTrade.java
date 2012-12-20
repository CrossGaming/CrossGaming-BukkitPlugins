package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import com.github.CorporateCraft.cceconomy.*;

public class CmdTrade extends Cmd
{
	Formatter form = new Formatter();
	Materials mat = new Materials();
	ArrayLists arl = new ArrayLists();
	BalChecks balc = new BalChecks();
	Trade tr = new Trade();
	public CmdTrade()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
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
			if(!form.isLegal(args[3]))
			{
				return false;
			}
			if(!form.isLegal(args[2]))
			{
				return false;
			}
			String price = args[3];
			String amount = args[2];
			String item = "";
			String temp = "";
		    short data = 0;
		    temp = args[1].replaceAll(":", " ");
			item = temp.split(" ")[0];
			if(form.isLegal(item))
			{
				item = mat.idToName(Integer.parseInt(item));
				try
				{
					data = Short.parseShort(temp.split(" ")[1]);
				}
				catch(Exception e)
				{
					data = 0;
				}
			}
			price = form.roundTwoDecimals(Double.parseDouble(price));
			item = mat.findItem(item);
			if(!mat.itemExists(item))
			{
				player.sendMessage(arl.getMessages() + "That item does not exist");
				return true;
			}
			if(args[4].equalsIgnoreCase("theypay"))
			{
				if(Double.parseDouble(balc.bal(offertopname)) - Double.parseDouble(price) < 0)
				{
					player.sendMessage(arl.getMessages() + offertopname + " does not have that much money");
					return true;
				}
				PlayerInventory inventory = player.getInventory();
				if(!inventory.contains(Material.matchMaterial(item), Integer.parseInt(amount)))
				{
					player.sendMessage(arl.getMessages() + "You do not have that much " + form.capFirst(item));
					return true;
				}
				tr.createTrade(offertopname + " " + pname + " " + item + ":" + Short.toString(data) + " " + amount + " " + price + " " + pname);
				item = form.capFirst(item);
				player.sendMessage(arl.getMessages() + "You have offered a trade to " + offertopname);
				target.sendMessage(arl.getMessages() + pname + " has offered to trade you " + amount + " of " + item + " for " + arl.getMoney() + "$" + price);
				target.sendMessage(arl.getMessages() + "Type /taccept or /tdeny to accept or deny their trade request");
			}
			if(args[4].equalsIgnoreCase("ipay"))
			{
				if(Double.parseDouble(balc.bal(pname)) - Double.parseDouble(price) < 0)
				{
					player.sendMessage(arl.getMessages() + "You do not have " + arl.getMoney() + "$" + price);
					return true;
				}
				PlayerInventory inventory = target.getInventory();
				if(!inventory.contains(Material.matchMaterial(item), Integer.parseInt(amount)))
				{
					player.sendMessage(arl.getMessages() + "They do not have that much " + form.capFirst(item));
					return true;
				}
				tr.createTrade(offertopname + " " + pname + " " + item + ":" + Short.toString(data) + " " + amount + " " + price + " " + offertopname);
				item = form.capFirst(item);
				player.sendMessage(arl.getMessages() + "You have offered a trade to " + offertopname);
				target.sendMessage(arl.getMessages() + pname + " has offered to trade you " + arl.getMoney() + "$" + price + arl.getMessages() + " for " + amount + " of " + item);
				target.sendMessage(arl.getMessages() + "Type /taccept " + pname + " or /tdeny " + pname + " to accept or deny their trade request");
			}
			return true;
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}