package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdPay
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
           Player player = (Player) sender;
           if (args.length > 2 || args.length == 0)
           {
        	   return false;
           }
           if(player.hasPermission("CCEconomy.pay"))
           {
        	   	if(!Formatter.isLegal(args[1]))
				{
					return false;
				}
        	   	String targetsname;
        	   	try
				{
					Player target = sender.getServer().getPlayer(args[0]);
					targetsname = target.getName();
				}
				catch (Exception e)
				{
					targetsname = args[0];
				}
				if(!PlayerToFile.DoesPlayerExist(targetsname))
				{
					player.sendMessage("Please enter a valid player to send money to.");
					return true;
				}
				String balance = BalChecks.Bal(player.getName());
				double intbal = Double.parseDouble(balance);
				double payamount = Math.abs(Double.parseDouble(args[1]));
				if (intbal < payamount)
				{
					player.sendMessage("You dont have: $" + args[1]);
					return true;
				}
				payamount = Double.parseDouble(Formatter.roundTwoDecimals(payamount));
				EditPlayerMoney.RemoveMoney(player.getName(), payamount);
				EditPlayerMoney.AddMoney(targetsname, payamount);
				player.sendMessage("Your payed " + targetsname + " $" + Formatter.roundTwoDecimals(payamount));
				
				try
				{
					Player target = sender.getServer().getPlayer(args[0]);
					target.sendMessage("You received $" + Formatter.roundTwoDecimals(payamount) + " from " + player.getName() + ".");
				}
				catch (Exception e){}
				return true;
           }
        } 
		else
		{
			sender.sendMessage("Log in to use this command or use cce");
			return true;
	    }
		return false;
	}
}