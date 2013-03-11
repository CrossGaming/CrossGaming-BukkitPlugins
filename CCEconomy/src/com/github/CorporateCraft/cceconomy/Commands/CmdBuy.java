package com.github.CorporateCraft.cceconomy.Commands;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.github.CorporateCraft.cceconomy.*;

public class CmdBuy extends Cmd
{
	Formatter form = new Formatter();
	BalChecks balc = new BalChecks();
	ArrayLists arl = new ArrayLists();
	Materials mat = new Materials();
	Prices pr = new Prices();
	public CmdBuy()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 2 || args.length == 0)
				return false;
			PlayerInventory inventory = player.getInventory();
			String balance = balc.bal(player.getName());
			double intbal = Double.parseDouble(balance);
			int amount = 0;					
		    String itemName = "";
		    String temp = "";
		    short data = 0;
			if(args.length == 2)
			{
				temp = args[0].replaceAll(":", " ");
				itemName = temp.split(" ")[0];
				if(form.isLegal(itemName))
				{
					itemName = mat.idToName(Integer.parseInt(itemName));
					try
					{
						data = Short.parseShort(temp.split(" ")[1]);
					}
					catch(Exception e)
					{
						data = 0;
					}
				}
				if(!form.isLegal(args[1]))
					return false;
				amount = Integer.parseInt(args[1]);
			}
			else
			{
				itemName = Integer.toString(player.getItemInHand().getTypeId());
				if(form.isLegal(itemName))
					itemName = mat.idToName(Integer.parseInt(itemName));
				if(!form.isLegal(args[0]))
					return false;
				data = player.getItemInHand().getDurability();
				amount = Integer.parseInt(args[0]);
			}
			itemName = mat.findItem(itemName);
			if(!mat.itemExists(itemName))
			{
				player.sendMessage(arl.getMessages() + "That item does not exist");
				return true;
			}
			itemName = itemName.toUpperCase();
			double cost = 0.00;
			cost = pr.getCost(arl.getBuyFile(), itemName, amount);
			if(cost == -1.00)
			{
				itemName = itemName.replaceAll("_ITEM", "");
				itemName = form.capFirst(itemName);
				player.sendMessage(arl.getMessages() + itemName + " cannot be bought from the server.");
				return true;
			}
			else
			{
				if (intbal < cost)
				{
					player.sendMessage(arl.getMessages() + "You dont have enough money to buy that item.");
					return true;
				}
				ItemStack itemstack = new ItemStack(Material.matchMaterial(mat.findItem(itemName)), amount, data);
				HashMap<Integer, ItemStack> noFit = inventory.addItem(itemstack);
				if(!noFit.isEmpty())
				{
					amount = amount - noFit.get(0).getAmount();
				    cost = pr.getCost(arl.getBuyFile(), itemName, amount);
				    player.sendMessage(arl.getMessages() + "You do not have enough inventory space to buy that much of that item,"
				    										+ " buying the amount you have inventory space for.");
				}
				balc.removeMoney(player.getName(), cost);
				itemName = itemName.replaceAll("_ITEM", "");
				itemName = form.capFirst(itemName);
				player.sendMessage(arl.getMessages() + "You bought " + Integer.toString(amount) + " of " + itemName + ".");
				player.sendMessage(arl.getMoney() + "$" + form.roundTwoDecimals(cost) + arl.getMessages() + " was removed from your acount.");
				return true;
			}
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "Log in to use this command");
			return true;
		}
	}
}