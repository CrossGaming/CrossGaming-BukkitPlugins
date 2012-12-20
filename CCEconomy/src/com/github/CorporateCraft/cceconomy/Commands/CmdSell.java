package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.github.CorporateCraft.cceconomy.*;

public class CmdSell extends Cmd
{
	BalChecks balc = new BalChecks();
	Formatter form = new Formatter();
	Materials mat = new Materials();
	ArrayLists arl = new ArrayLists();
	Prices pr = new Prices();
	public CmdSell()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
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
			if(player.hasPermission("CCEconomy.sell"))
			{
				PlayerInventory inventory = player.getInventory();
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
					{
						return false;
					}
					amount = Integer.parseInt(args[1]);
				}
				else
				{
					itemName = Integer.toString(player.getItemInHand().getTypeId());
					data = player.getItemInHand().getDurability();
					if(form.isLegal(itemName))
					{
						itemName = mat.idToName(Integer.parseInt(itemName));
					}
					if(!form.isLegal(args[0]))
					{
						return false;
					}
					amount = Integer.parseInt(args[0]);
				}
				itemName = mat.findItem(itemName);
				if(!mat.itemExists(itemName))
				{
					player.sendMessage(arl.getMessages() + "That item does not exist");
					return true;
				}
				double cost = 0.00;
				cost = pr.getCost(arl.getSellFile(), itemName, amount);
				if(cost == -1.00)
				{
					itemName = itemName.replaceAll("_ITEM", "");
					itemName = form.capFirst(itemName);
					player.sendMessage(arl.getMessages() + itemName + " cannot be sold to the server.");
					return true;
				}
				else
				{
					ItemStack itemstack = new ItemStack(Material.matchMaterial(itemName), amount, data);
					if(inventory.contains(Material.matchMaterial(mat.findItem(itemName)), amount))
					{
						balc.addMoney(player.getName(), cost);
						inventory.removeItem(itemstack);
						itemName = itemName.replaceAll("_ITEM", "");
						itemName = form.capFirst(itemName);
						player.sendMessage(arl.getMessages() + "You sold " + Integer.toString(amount) + " of " + itemName + ".");
						player.sendMessage(arl.getMoney() + "$" + form.roundTwoDecimals(cost) + arl.getMessages() + " was added to your acount.");
					}
					else
					{
						itemName = itemName.replaceAll("_ITEM", "");
						itemName = form.capFirst(itemName);
						player.sendMessage(arl.getMessages() + "You do not have that many " + itemName + "s");
					}
					return true;
				}
			}
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "Log in to use this command");
			return true;
		}
		return false;
	}
}