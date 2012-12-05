package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdEnchant extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdEnchant()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if (args.length > 2)
	        {
	      	   return false;
	        }
			Player player = (Player) sender;
			if (args.length == 0)
	        {
	      	   return false;
	        }
			if (args.length == 1)
	        {
				Enchantment ench = Enchantment.getByName(args[0].toUpperCase());
				if(ench == null)
				{
					player.sendMessage(arl.GetCol() + "Enchantment does not exist.");
					return true;
				}
				int level = 5;
				if(ench.canEnchantItem(player.getInventory().getItemInHand()))
				{
					player.getInventory().getItemInHand().addUnsafeEnchantment(ench, level);
					player.sendMessage(arl.GetCol() + "Added the enchantment " + ench.getName() + " at level " + Integer.toString(level) + ".");
					return true;
				}
				player.sendMessage(arl.GetCol() + "This item can not support given enchantment.");
	        }
			if (args.length == 2)
	        {
				Enchantment ench = Enchantment.getByName(args[0].toUpperCase());
				if(ench == null)
				{
					player.sendMessage(arl.GetCol() + "Enchantment does not exist.");
					return true;
				}
				try
				{
					Integer.parseInt(args[1]);
				}
				catch(Exception e)
				{
					return false;
				}
				int level = Integer.parseInt(args[1]);
				if(level > 10)
				{
					level = 10;
				}
				if(ench.canEnchantItem(player.getInventory().getItemInHand()))
				{
					player.getInventory().getItemInHand().addUnsafeEnchantment(ench, level);
					player.sendMessage(arl.GetCol() + "Added the enchantment " + ench.getName() + " at level " + Integer.toString(level) + ".");
					return true;
				}
				player.sendMessage(arl.GetCol() + "This item can not support given enchantment.");
	        }
			return true;
		}
		else
		{
			sender.sendMessage(arl.GetCol() + "You are not a player  so you do not have an items.");
			return true;
		}
	}
}