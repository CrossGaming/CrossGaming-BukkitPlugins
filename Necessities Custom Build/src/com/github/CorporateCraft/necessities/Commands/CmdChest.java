package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdChest extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdChest()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if (args.length > 0 && (args[0].contains("rem") || args[0].contains("off") || args[0].equalsIgnoreCase("0")))
			{
				final PlayerInventory inv = p.getInventory();
				final ItemStack chest = inv.getChestplate();
				if (chest == null || chest.getType() == Material.AIR)
				{
					p.sendMessage(arl.getCol() + "You do not have chest at the moment.");
				}
				else
				{
					final ItemStack air = new ItemStack(Material.AIR);
					inv.setChestplate(air);
					p.sendMessage(arl.getCol() + "Chestplate removed");
				}
			}
			else
			{
				if (p.getItemInHand().getType() != Material.AIR)
				{
					final ItemStack hand = p.getItemInHand().clone();
					if (hand.getType().getMaxDurability() == 0)
					{
						final PlayerInventory inv = p.getInventory();
						final ItemStack chest = inv.getChestplate();
						inv.setChestplate(hand);
						inv.setItemInHand(chest);
						p.sendMessage(arl.getCol() + "Chestplate equiped");
					}
					else
					{
						p.sendMessage(arl.getCol() + "Armor chestplate equiped");
					}
				}
				else
				{
					p.sendMessage(arl.getCol() + "You can't equip air as a chestplate");
				}
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