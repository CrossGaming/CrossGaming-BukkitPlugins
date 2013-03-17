package com.crossge.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.crossge.cceconomy.*;

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
			if (args.length > 2 || args.length == 0)
				return false;
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
					if(!args[1].equalsIgnoreCase("all"))
						return false;
					amount = itemAmount(inventory, Material.matchMaterial(itemName));
				}
				else
					amount = Integer.parseInt(args[1]);
			}
			else
			{
				itemName = Integer.toString(player.getItemInHand().getTypeId());
				data = player.getItemInHand().getDurability();
				if(form.isLegal(itemName))
					itemName = mat.idToName(Integer.parseInt(itemName));
				if(!form.isLegal(args[0]))
				{
					if(!args[0].equalsIgnoreCase("all"))
						return false;
					amount = itemAmount(inventory, Material.matchMaterial(mat.findItem(itemName)));
				}
				else
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
				if(!itemName.equalsIgnoreCase("NETHER_BRICK_ITEM"))
					itemName = itemName.replaceAll("_ITEM", "");
				itemName = form.capFirst(itemName);
				player.sendMessage(arl.getMessages() + itemName + " cannot be sold to the server.");
				return true;
			}
			else
			{
				ItemStack itemstack = new ItemStack(Material.matchMaterial(itemName), amount, data);
				if(inventory.containsAtLeast(new ItemStack(Material.matchMaterial(itemName), 1, data), amount) )
				{
					balc.addMoney(player.getName(), cost);
					inventory.removeItem(itemstack);
					if(!itemName.equalsIgnoreCase("NETHER_BRICK_ITEM"))
						itemName = itemName.replaceAll("_ITEM", "");
					itemName = form.capFirst(itemName);
					player.sendMessage(arl.getMessages() + "You sold " + Integer.toString(amount) + " " + itemName + ".");
					player.sendMessage(arl.getMoney() + "$" + form.roundTwoDecimals(cost) + arl.getMessages() + " was added to your acount.");
				}
				else
				{
					if(inventory.contains(Material.matchMaterial(itemName), amount) && mat.isTool(itemstack))
					{
						if(sell(inventory,amount,Material.matchMaterial(itemName)))
						{
							if(!itemName.equalsIgnoreCase("NETHER_BRICK_ITEM"))
								itemName = itemName.replaceAll("_ITEM", "");
							itemName = form.capFirst(itemName);
							player.sendMessage(arl.getMessages() + "You sold " + Integer.toString(amount) + " of " + itemName + ".");
							player.sendMessage(arl.getMoney() + "$" + form.roundTwoDecimals(cost) + arl.getMessages() + " was added to your acount.");
						}
						else
						{
							if(!itemName.equalsIgnoreCase("NETHER_BRICK_ITEM"))
								itemName = itemName.replaceAll("_ITEM", "");
							itemName = form.capFirst(itemName);
							player.sendMessage(arl.getMessages() + "You do not have " + Integer.toString(amount)  + " " + plural(itemName) + ".");
						}
					}
					else
					{
						if(!itemName.equalsIgnoreCase("NETHER_BRICK_ITEM"))
							itemName = itemName.replaceAll("_ITEM", "");
						itemName = form.capFirst(itemName);
						player.sendMessage(arl.getMessages() + "You do not have " + Integer.toString(amount)  + " " + plural(itemName) + ".");
					}
				}
				return true;
			}
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "Log in to use this command");
			return true;
		}
	}
	private String plural(String s)
	{
		if(s.endsWith("s") || s.endsWith("S"))
			return s;
		return s + "s";
	}
	private boolean sell(PlayerInventory inv, int cAmount, Material matType)
	{
		for(ItemStack s : inv.getContents())
		{
			if(s == null)
				continue;
			if(cAmount > 0 && s.getType() == matType && s.getEnchantments().size() == 0)
			{
				inv.removeItem(new ItemStack(matType, 1, s.getDurability()));
				cAmount = cAmount - 1;
				sell(inv,cAmount,matType);
			}
			if(cAmount == 0)
				return true;
		}
		return false;
	}
	private int itemAmount(PlayerInventory inv, Material matType)
	{
		int amount = 0;
		for(ItemStack s : inv.getContents())
		{
			if(s == null || s.getType() != matType)
				continue;
			if(s.getEnchantments().size() == 0)
				amount += s.getAmount();
		}
		return amount;
	}
}