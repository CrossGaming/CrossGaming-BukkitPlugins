package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdPants extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdPants()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if (args.length > 0 && (args[0].contains("rem") || args[0].contains("off")))
			{
				PlayerInventory inv = p.getInventory();
				ItemStack pant = inv.getLeggings();
				if (pant == null || pant.getType() == Material.AIR)
					p.sendMessage(arl.getCol() + "You do not have pants at the moment.");
				else
				{
					ItemStack air = new ItemStack(Material.AIR);
					inv.setLeggings(air);
					p.sendMessage(arl.getCol() + "Pants removed");
				}
			}
			else
			{
				if (p.getItemInHand().getType() != Material.AIR)
				{
					ItemStack hand = p.getItemInHand().clone();
					if (hand.getType().getMaxDurability() == 0)
					{
						PlayerInventory inv = p.getInventory();
						ItemStack pant = inv.getLeggings();
						inv.setLeggings(hand);
						inv.setItemInHand(pant);
						p.sendMessage(arl.getCol() + "Pants equiped");
					}
					else
						p.sendMessage(arl.getCol() + "Armor pants equiped");
				}
				else
					p.sendMessage(arl.getCol() + "You can't equip air as pants");
			}
			return true;
        } 
		else
		{
			sender.sendMessage(arl.getCol() + "You do not have armor.");
			return true;
	    }
	}
}