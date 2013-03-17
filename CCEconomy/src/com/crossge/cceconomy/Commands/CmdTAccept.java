package com.crossge.cceconomy.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import com.crossge.cceconomy.*;;

public class CmdTAccept extends Cmd
{
	Trade tr = new Trade();
	Formatter form = new Formatter();
	Materials mat = new Materials();
	ArrayLists arl = new ArrayLists();
	BalChecks balc = new BalChecks();
	public CmdTAccept()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 1)
				return false;
			Player player = (Player) sender;
			Player target = sender.getServer().getPlayer(args[0]);
			String pname = player.getName();
			String offerpname = target.getName();
			if(tr.hasTrade(pname, offerpname))
			{
				String info = tr.acceptTrade(pname, offerpname);
				String item = "";
				String amount = info.split(" ")[1];
				String price = info.split(" ")[2];
				String toWhom = info.split(" ")[3];
				String temp = "";
			    short data = 0;
			    temp = info.split(" ")[0].replaceAll(":", " ");
				item = temp.split(" ")[0];
				if(form.isLegal(price))
					price = form.roundTwoDecimals(Double.parseDouble(price));
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
				if(!toWhom.equalsIgnoreCase(pname) && !toWhom.equalsIgnoreCase(offerpname))
				{
					String amountgetting = amount;
					String itemgetting = item;
					String amountoffering = toWhom;
					String itemoffering = "";
					short dataget = data;
					short dataoff = 0;
					String temp2 = "";
				    temp2 = price.replaceAll(":", " ");
				    itemoffering = temp.split(" ")[0];
					if(form.isLegal(itemoffering))
					{
						try
						{
							dataoff = Short.parseShort(temp2.split(" ")[1]);
						}
						catch(Exception e)
						{
							dataoff = 0;
						}
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					ItemStack itemstack = new ItemStack(Material.matchMaterial(mat.findItem(itemgetting)), Integer.parseInt(amountgetting), dataget);
					ItemStack is = new ItemStack(Material.matchMaterial(mat.findItem(itemoffering)), Integer.parseInt(amountoffering), dataoff);
					if(!yourinventory.contains(itemstack))
					{
						player.sendMessage(arl.getMessages() + "You do not have that much " + itemgetting);
						return true;
					}
					if(!thereinventory.contains(is))
					{
						player.sendMessage(arl.getMessages() + "They do not have that much " + itemoffering);
						return true;
					}
					yourinventory.addItem(is);
					yourinventory.removeItem(itemstack);
					thereinventory.addItem(itemstack);
					thereinventory.removeItem(is);
				}
				if(toWhom.equalsIgnoreCase(pname))
				{
					if(Double.parseDouble(balc.bal(offerpname)) - Double.parseDouble(price) < 0)
					{
						player.sendMessage(arl.getMessages() + "They do not have " + arl.getMoney() + "$" + price);
						return true;
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					ItemStack itemstack = new ItemStack(Material.matchMaterial(mat.findItem(item)), Integer.parseInt(amount), data);
					if(!yourinventory.contains(itemstack))
					{
						player.sendMessage(arl.getMessages() + "You do not have that much " + item);
						return true;
					}
					balc.removeMoney(offerpname, Double.parseDouble(price));
					balc.addMoney(pname, Double.parseDouble(price));
					thereinventory.addItem(itemstack);
					yourinventory.removeItem(itemstack);
				}
				if(toWhom.equalsIgnoreCase(offerpname))
				{
					if(Double.parseDouble(balc.bal(pname)) - Double.parseDouble(price) < 0)
					{
						player.sendMessage(arl.getMessages() + "You do not have " + arl.getMoney() + "$" + price);
						return true;
					}
					PlayerInventory thereinventory = target.getInventory();
					PlayerInventory yourinventory = player.getInventory();
					ItemStack itemstack = new ItemStack(Material.matchMaterial(mat.findItem(item)), Integer.parseInt(amount), data);
					if(!thereinventory.contains(itemstack))
					{
						player.sendMessage(arl.getMessages() + "They do not have that much " + item);
						return true;
					}
					balc.removeMoney(pname, Double.parseDouble(price));
					balc.addMoney(offerpname, Double.parseDouble(price));
					yourinventory.addItem(itemstack);
					thereinventory.removeItem(itemstack);
				}
				player.sendMessage(arl.getMessages() + "You have accepted the trade from " + offerpname);
				target.sendMessage(arl.getMessages() + "Your trade to " + pname + " has been accepted");
				return true;
			}
			else
			{
				player.sendMessage(arl.getMessages() + "You do not have a trade offer from " + offerpname);
				return true;
			}
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}