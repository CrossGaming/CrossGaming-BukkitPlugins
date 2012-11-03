package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdCCE
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 3 || args.length == 0 || args.length == 1)
			{
				return false;
			}
			if(player.hasPermission("CCEconomy.editbal"))
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
				if(!PlayerToFile.DoesPlayerExist(targetsname))
				{
					player.sendMessage("Please enter a valid player to change the balance of.");
					return true;
				}
				if (args[0].equalsIgnoreCase("reset"))
				{
					EditPlayerMoney.SetMoney(targetsname, "0");
					player.sendMessage("Your successfully reset the balance of " + targetsname + ".");
					return true;
				}
				if (args.length == 3)
				{
					if(!Formatter.isLegal(args[2]))
					{
						return false;
					}
					double amount = Double.parseDouble(args[2]);
					String balance = BalChecks.Bal(targetsname);
					double intbal = Double.parseDouble(balance);
					amount = Double.parseDouble(Formatter.roundTwoDecimals(amount));
					String setamount = Formatter.roundTwoDecimals(amount);
					if (args[0].equalsIgnoreCase("give"))
					{
						EditPlayerMoney.AddMoney(targetsname, amount);
						player.sendMessage("Your successfully gave "+ " $" + setamount + " to "  + targetsname + ".");
						return true;
					}
					if (args[0].equalsIgnoreCase("take"))
					{
						if(intbal-amount>=0)
						{
							EditPlayerMoney.RemoveMoney(targetsname, amount);
							player.sendMessage("Your successfully took "+ " $" + setamount + " from "  + targetsname + ".");
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("set"))
					{
						EditPlayerMoney.SetMoney(targetsname, setamount);
						player.sendMessage("Your successfully set the balance of " + targetsname + " to $" + Formatter.roundTwoDecimals(amount));
						return true;
					}
					return false;
				}
			}
			return false;
		} 	
		else
		{
			if (args.length > 3 || args.length == 0)
	        {
				return false;
	        }
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
			if(!PlayerToFile.DoesPlayerExist(targetsname))
			{
				sender.sendMessage("Please enter a valid player to change the balance of.");
				return true;
			}
			if (args[0].equalsIgnoreCase("reset"))
			{
				EditPlayerMoney.SetMoney(targetsname, "0");
				sender.sendMessage("Your successfully reset the balance of " + targetsname + ".");
				return true;
			}
			if (args.length == 3)
			{
				if(!Formatter.isLegal(args[2]))
				{
					return false;
				}
				double amount = Double.parseDouble(args[2]);
				String balance = BalChecks.Bal(targetsname);
				double intbal = Double.parseDouble(balance);
				amount = Double.parseDouble(Formatter.roundTwoDecimals(amount));
				String setamount = Formatter.roundTwoDecimals(amount);
				if (args[0].equalsIgnoreCase("give"))
				{
					EditPlayerMoney.AddMoney(targetsname, amount);
					sender.sendMessage("Your successfully gave "+ " $" + setamount + " to "  + targetsname + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("take"))
				{
					if(intbal-amount>=0)
					{
						EditPlayerMoney.RemoveMoney(targetsname, amount);
						sender.sendMessage("Your successfully took "+ " $" + setamount + " from "  + targetsname + ".");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("set"))
				{
					EditPlayerMoney.SetMoney(targetsname, setamount);
					sender.sendMessage("Your successfully set the balance of " + targetsname + " to $" + Formatter.roundTwoDecimals(amount));
					return true;
				}
				return false;
			}
			return false;
		}
	}
}