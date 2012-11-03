package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.github.CorporateCraft.cceconomy.EditPlayerMoney;
import com.github.CorporateCraft.cceconomy.Formatter;
import com.github.CorporateCraft.cceconomy.Materials;
import com.github.CorporateCraft.cceconomy.Prices;

public class CmdSell
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
			if(player.hasPermission("CCEconomy.sell"))
			{
				PlayerInventory inventory = player.getInventory();
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
				Cost = Prices.GetCost("plugins/CCEconomy/sellprices.txt", ItemName, amount);
				ItemName = Formatter.CapFirst(ItemName);
				if(Cost == -1.00)
				{
					player.sendMessage(ItemName + " cannot be sold to the server.");
					return true;
				}
				else
				{
					EditPlayerMoney.AddMoney(player.getName(), Cost);
					ItemStack itemstack = new ItemStack(Material.matchMaterial(ItemName), amount);
					if(inventory.contains(Material.matchMaterial(ItemName), amount))
					{
						inventory.removeItem(itemstack);
						ItemName = ItemName.replaceAll("_", " ");
						player.sendMessage("You sold " + Integer.toString(amount) + " of " + ItemName + ".");
						player.sendMessage("$" + Formatter.roundTwoDecimals(Cost) + " was added to your acount.");
					}
					else
					{
						player.sendMessage("You do not have that many " + ItemName + "s");
					}
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