package com.crossge.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.crossge.necessities.ArrayLists;

public class CmdRename extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdRename()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			ItemStack hand = player.getItemInHand();
			ItemMeta handMeta = hand.getItemMeta();
			String name = "";
			for(int i = 0; i < args.length; i++)
			{
				name += args[i] + " ";
			}
			name = name.trim();
			handMeta.setDisplayName(name);
			hand.setItemMeta(handMeta);
			return true;
		}
		else
		{
			sender.sendMessage(arl.getCol() + "You can not rename your items because you do not have any.");
			return true;
		}
	}
}