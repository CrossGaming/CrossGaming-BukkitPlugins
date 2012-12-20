package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdPay extends Cmd
{
	Formatter form = new Formatter();
	ArrayLists arl = new ArrayLists();
	BalChecks balc = new BalChecks();
	public CmdPay()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
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
        	   	if(!form.isLegal(args[1]))
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
				if(!balc.doesPlayerExist(targetsname))
				{
					player.sendMessage(arl.getMessages() + "Please enter a valid player to send money to.");
					return true;
				}
				String balance = balc.bal(player.getName());
				double intbal = Double.parseDouble(balance);
				double payamount = Math.abs(Double.parseDouble(args[1]));
				if (intbal < payamount)
				{
					player.sendMessage(arl.getMessages() + "You dont have: " + arl.getMoney() + "$" + args[1]);
					return true;
				}
				payamount = Double.parseDouble(form.roundTwoDecimals(payamount));
				balc.removeMoney(player.getName(), payamount);
				balc.addMoney(targetsname, payamount);
				player.sendMessage(arl.getMessages() + "Your payed " + targetsname + arl.getMoney() + " $" + form.roundTwoDecimals(payamount));
				
				try
				{
					Player target = sender.getServer().getPlayer(args[0]);
					target.sendMessage(arl.getMessages() + "You received " + arl.getMoney() + "$" + form.roundTwoDecimals(payamount) + arl.getMessages() + " from " + player.getName() + ".");
				}
				catch (Exception e){}
				return true;
           }
        } 
		else
		{
			sender.sendMessage(arl.getMessages() + "Log in to use this command or use cce");
			return true;
	    }
		return false;
	}
}