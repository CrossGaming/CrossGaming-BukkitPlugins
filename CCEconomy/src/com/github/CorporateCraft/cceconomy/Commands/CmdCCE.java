package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdCCE extends Cmd
{
	Formatter form = new Formatter();
	BalChecks balc = new BalChecks();
	ArrayLists arl = new ArrayLists();
	public CmdCCE()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length > 3 || args.length == 0)
			return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length == 1)
				return false;
			String targetsname;
			try
			{
				Player target = sender.getServer().getPlayer(args[1]);
				targetsname = target.getName();
			}
			catch (Exception e)
			{
				targetsname = args[1];
			}
			if(!balc.doesPlayerExist(targetsname))
			{
				player.sendMessage(arl.getMessages() + "Please enter a valid player to change the balance of.");
				return true;
			}
			if (args[0].equalsIgnoreCase("reset"))
			{
				balc.setMoney(targetsname, "0");
				player.sendMessage(arl.getMessages() + "Your successfully reset the balance of " + targetsname + ".");
				return true;
			}
			if (args.length == 3)
			{
				if(!form.isLegal(args[2]))
					return false;
				double amount = Double.parseDouble(args[2]);
				String balance = balc.bal(targetsname);
				double intbal = Double.parseDouble(balance);
				amount = Double.parseDouble(form.roundTwoDecimals(amount));
				String setamount = form.roundTwoDecimals(amount);
				if (args[0].equalsIgnoreCase("give"))
				{
					balc.addMoney(targetsname, amount);
					player.sendMessage(arl.getMessages() + "Your successfully gave " + arl.getMoney() + " $" + setamount + arl.getMessages() + " to "  + targetsname + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("take"))
				{
					if(intbal-amount>=0)
					{
						balc.removeMoney(targetsname, amount);
						player.sendMessage(arl.getMessages() + "Your successfully took "+ arl.getMoney() + " $" + setamount + arl.getMessages() + " from "  + targetsname + ".");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("set"))
				{
					balc.setMoney(targetsname, setamount);
					player.sendMessage(arl.getMessages() + "Your successfully set the balance of " + targetsname + " to " + arl.getMoney() + "$" + form.roundTwoDecimals(amount));
					return true;
				}
			}
			return false;
		} 	
		else
		{
			String targetsname;
			try
			{
				Player target = sender.getServer().getPlayer(args[1]);
				targetsname = target.getName();
			}
			catch (Exception e)
			{
				targetsname = args[1];
			}
			if(!balc.doesPlayerExist(targetsname))
			{
				sender.sendMessage(arl.getMessages() + "Please enter a valid player to change the balance of.");
				return true;
			}
			if (args[0].equalsIgnoreCase("reset"))
			{
				balc.setMoney(targetsname, "0");
				sender.sendMessage(arl.getMessages() + "Your successfully reset the balance of " + targetsname + ".");
				return true;
			}
			if (args.length == 3)
			{
				if(!form.isLegal(args[2]))
					return false;
				double amount = Double.parseDouble(args[2]);
				String balance = balc.bal(targetsname);
				double intbal = Double.parseDouble(balance);
				amount = Double.parseDouble(form.roundTwoDecimals(amount));
				String setamount = form.roundTwoDecimals(amount);
				if (args[0].equalsIgnoreCase("give"))
				{
					balc.addMoney(targetsname, amount);
					sender.sendMessage(arl.getMessages() + "Your successfully gave "+ arl.getMoney() + " $" + setamount + arl.getMessages() + " to "  + targetsname + ".");
					return true;
				}
				if(args[0].equalsIgnoreCase("take") && intbal-amount >= 0)
				{
					balc.removeMoney(targetsname, amount);
					sender.sendMessage(arl.getMessages() + "Your successfully took "+ arl.getMoney() + " $" + setamount + arl.getMessages() + " from "  + targetsname + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("set"))
				{
					balc.setMoney(targetsname, setamount);
					sender.sendMessage(arl.getMessages() + "Your successfully set the balance of " + targetsname + " to " + arl.getMoney() + "$" + form.roundTwoDecimals(amount));
					return true;
				}
			}
			return false;
		}
	}
}