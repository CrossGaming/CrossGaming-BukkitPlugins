package com.github.CrossGaming.ccebridge.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CrossGaming.ccebridge.*;
import com.github.CorporateCraft.cceconomy.*;

public class CmdSetRankPrice extends Cmd
{
	ArrayLists ccearl = new ArrayLists();
	Formatter form = new Formatter();
	RankPrices pr = new RankPrices();
	public CmdSetRankPrice()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String rankName = form.capFirst(args[0]);
			if(!form.isLegal(args[1]))
			{
				if(args[1].equalsIgnoreCase("null"))
				{
					pr.rCost(rankName);
					player.sendMessage(ccearl.getMessages() + rankName + " can no longer be bought.");
					return true;
				}
				return false;
			}
			String cost = args[1];
			pr.setCost(rankName, cost);
			player.sendMessage(ccearl.getMessages() + "Added " + rankName + " at the price of " + ccearl.getMoney() + "$" + cost);
			return true;
		}
		else
		{
			String rankName = form.capFirst(args[0]);
			if(!form.isLegal(args[1]))
			{
				if(args[1].equalsIgnoreCase("null"))
				{
					pr.rCost(rankName);
					sender.sendMessage(ccearl.getMessages() + rankName + " can no longer be bought.");
					return true;
				}
				return false;
			}
			String cost = args[1];
			pr.setCost(rankName, cost);
			sender.sendMessage(ccearl.getMessages() + "Added " + rankName + " at the price of " + ccearl.getMoney() + "$" + cost);
			return true;
		}
	}
}