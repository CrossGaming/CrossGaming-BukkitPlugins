package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdBalance extends Cmd
{
	ArrayLists arl = new ArrayLists();
	BalChecks balc = new BalChecks();
	public CmdBalance()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
	        Player player = (Player) sender;
	        if (args.length > 1)
	        {
	      	   return false;
	        }
	        if (args.length == 1)
	        {
	       	   if(player.hasPermission("CCEconomy.balothers"))
		       {
	       		   String playersname;
	       		   try
	       		   {
	       			   Player target = sender.getServer().getPlayer(args[0]);
	       			   playersname = target.getName();
	       		   }
	       		   catch (Exception e)
	       		   {
	       			   playersname = args[0];
	       		   }
	       		   String balance = balc.bal(playersname);
		       	   if(balance == null)
		       	   {
		       		   player.sendMessage(arl.getMessages() + "That player is not in my records. If the player is offline, please use the full name.");
		       		   return true;
		       	   }
		       	   player.sendMessage(arl.getMessages() + playersname + "'s balance is: " + arl.getMoney() + "$" + balance);
		       	   return true;
		       }
	       }
	       if(player.hasPermission("CCEconomy.bal"))
	       {
	       	   String balance = balc.bal(player.getName());
	       	   if(balance == null)
	       	   {
	       		   player.sendMessage(arl.getMessages() + "You do not seem to exist let me add you now.");
	       		balc.addPlayerToList(player.getName());
	       		   return true;
	       	   }
	       	   player.sendMessage(arl.getMessages() + "Balance: " + arl.getMoney() + "$" + balance);
	       	   return true;
	       }
	    } 
		else
		{
			if (args.length == 1)
		          {
					String playersname;
					try
					{
						Player target = sender.getServer().getPlayer(args[0]);
						playersname = target.getName();
					}
					catch (Exception e)
					{
						playersname = args[0];
					}
					String balance = balc.bal(playersname);
					if(balance == null)
					{
						sender.sendMessage(arl.getMessages() + "That player is not in my records. If the player is offline, please use the full name.");
						return true;
					}
					sender.sendMessage(arl.getMessages() + playersname + "'s balance is: " + arl.getMoney() + "$" + balance);
					return true;
		       }
			else
			{
				sender.sendMessage(arl.getMessages() + "Log in to use this command");
				return true;
			}
		}
		return false;
	}
}