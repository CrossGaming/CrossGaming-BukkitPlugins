package com.github.CrossGaming.ccebridge.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.ArrayLists;
import com.github.CorporateCraft.cceconomy.Formatter;
import com.github.CrossGaming.ccebridge.CmdPrices;

public class CmdSetCmdPrice extends Cmd
{
	ArrayLists ccearl = new ArrayLists();
	Formatter form = new Formatter();
	CmdPrices cmdp = new CmdPrices();
	public CmdSetCmdPrice()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length != 3)
		{
			return false;
		}
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String cmd = form.capFirst(args[0]);
			if(!form.isLegal(args[1]))
			{
				if(args[1].equalsIgnoreCase("null"))
				{
					cmdp.removeCommand(cmd);
					player.sendMessage(ccearl.getMessages() + cmd + " can no longer be bought.");
					return true;
				}
				return false;
			}
			String price = args[1];
			String rank = args[2];
			cmdp.addCommand(rank, cmd, price);
			price = form.roundTwoDecimals(Double.parseDouble(price));
			player.sendMessage(ccearl.getMessages() + "Added " + cmd + " to the commands of rank " + rank + " at the price of " + ccearl.getMoney() + "$" + price);
			return true;
		}
		else
		{
			String cmd = form.capFirst(args[0]);
			if(!form.isLegal(args[1]))
			{
				if(args[1].equalsIgnoreCase("null"))
				{
					cmdp.removeCommand(cmd);
					sender.sendMessage(ccearl.getMessages() + cmd + " can no longer be bought.");
					return true;
				}
				return false;
			}
			String price = args[1];
			String rank = args[2];
			cmdp.addCommand(rank, cmd, price);
			price = form.roundTwoDecimals(Double.parseDouble(price));
			sender.sendMessage(ccearl.getMessages() + "Added " + cmd + " to the commands of rank " + rank + " at the price of " + ccearl.getMoney() + "$" + price);
			return true;
		}
	}
}