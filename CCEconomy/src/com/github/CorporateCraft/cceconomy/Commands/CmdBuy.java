package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.github.CorporateCraft.cceconomy.*;

public class CmdBuy
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
			if (args.length == 0)
			{
				return false;
			}
			if(player.hasPermission("CCEconomy.buy"))
			{
				PlayerInventory inventory = player.getInventory();
				String balance = BalChecks.Bal(player.getName());
				double intbal = Double.parseDouble(balance);
				int amount = 0;					
			    String ItemName = "";
				if(args.length == 2)
				{
					ItemName = args[0];
					if(Formatter.isLegal(ItemName))
					{
						ItemName = Materials.idToName(Integer.parseInt(ItemName));
					}
					if(!Formatter.isLegal(args[1]))
					{
						return false;
					}
					amount = Integer.parseInt(args[1]);
				}
				else
				{
					ItemName = Integer.toString(player.getItemInHand().getTypeId());
					if(Formatter.isLegal(ItemName))
					{
						ItemName = Materials.idToName(Integer.parseInt(ItemName));
					}
					if(!Formatter.isLegal(args[0]))
					{
						return false;
					}
					amount = Integer.parseInt(args[0]);
				}
				ItemName = ItemName.toUpperCase();
				Double Cost = 0.00;
				Cost = Prices.GetCost(CCEconomy.buyfile, ItemName, amount);
				ItemName = Formatter.CapFirst(ItemName);
				if(Cost == null)
				{
					player.sendMessage(ItemName + " cannot be bought from the server.");
					return true;
				}
				else
				{
					if (intbal < Cost)
					{
						player.sendMessage("You dont have enough money to buy that item.");
						return true;
					}
					EditPlayerMoney.RemoveMoney(player.getName(), Cost);
					ItemStack itemstack = new ItemStack(Material.matchMaterial(ItemName), amount);
					inventory.addItem(itemstack);
					ItemName = ItemName.replaceAll("_", " ");
					player.sendMessage("You bought " + Integer.toString(amount) + " of " + ItemName + ".");
					player.sendMessage("$" + Formatter.roundTwoDecimals(Cost) + " was removed from your acount.");
					return true;
				}
			}
		}
		else
		{
			sender.sendMessage("Log in to use this command");
			return true;
		}
		return false;
	}
}