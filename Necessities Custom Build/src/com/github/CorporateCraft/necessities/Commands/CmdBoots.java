package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdBoots extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdBoots()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if (args.length > 0 && (args[0].contains("rem") || args[0].contains("off") || args[0].equalsIgnoreCase("0")))
			{
				PlayerInventory inv = p.getInventory();
				ItemStack boot = inv.getLeggings();
				if (boot == null || boot.getType() == Material.AIR)
					p.sendMessage(arl.getCol() + "You do not have boots at the moment.");
				else
				{
					ItemStack air = new ItemStack(Material.AIR);
					inv.setBoots(air);
					p.sendMessage(arl.getCol() + "Boots removed");
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
						ItemStack boot = inv.getBoots();
						inv.setBoots(hand);
						inv.setItemInHand(boot);
						p.sendMessage(arl.getCol() + "Boots equiped");
					}
					else
						p.sendMessage(arl.getCol() + "Armor boots equiped");
				}
				else
					p.sendMessage(arl.getCol() + "You can't equip air as boots");
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