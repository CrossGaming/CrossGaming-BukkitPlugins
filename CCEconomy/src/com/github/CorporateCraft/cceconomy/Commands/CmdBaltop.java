package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdBaltop extends Cmd
{
	Formatter form = new Formatter();
	BalChecks balc = new BalChecks();
	ArrayLists arl = new ArrayLists();
	public CmdBaltop()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
           Player player = (Player) sender;
           if(player.hasPermission("CCEconomy.baltop"))
	       {
	           	int page = 0;
	           	if (args.length == 1)
	           	{
	           		if(!form.isLegal(args[0]))
					{
						return false;
					}
	        	   	page = Integer.parseInt(args[0]);
	           	}
	           	if (args.length == 0)
	           	{
	           		page = 1;
	           	}
	           	if (page == 0)
	           	{
	           		page = 1;
	           	}
        	   	int time = 0;
        	   	String bal;
        	   	int totalpages = balc.baltopPages();
        	   	if (page>totalpages)
        	   	{
        	   		player.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
        	   		return true;
        	   	}
        	   	player.sendMessage(ChatColor.GOLD + "Balanace Top Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
        	   	page = page - 1;
        	   	bal = balc.balTop(page, time);
        	   	while(bal != null)
        	   	{
        	   		bal = ChatColor.GOLD + Integer.toString((page*10) + time + 1) + ". " + arl.getMessages() +
        	   			  bal.split(" ")[0] + " has: " + arl.getMoney() +
        	   			  "$" + bal.split(" ")[1];
        	   		player.sendMessage(bal);
        	   		time++;
        	   		bal = balc.balTop(page, time);
        	   	}
        	   	return true;
	       }
        } 
		else
		{
			int page = 0;
           	if (args.length == 1)
           	{
        	   	page = Integer.parseInt(args[0]);
           	}
           	if (args.length == 0)
           	{
           		page = 1;
           	}
           	if (page == 0)
           	{
           		page = 1;
           	}
    	   	int time = 0;
    	   	String bal;
    	   	int totalpages = balc.baltopPages();
    	   	if (page>totalpages)
    	   	{
    	   		sender.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	sender.sendMessage(ChatColor.GOLD + "Balanace Top Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	bal = balc.balTop(page, time);
    	   	while(bal != null)
    	   	{
    	   		bal = ChatColor.GOLD + Integer.toString((page*10) + time + 1) + ". " + arl.getMessages() +
    	   			  bal.split(" ")[0] + " has: " + arl.getMoney() +
    	   			  "$" + bal.split(" ")[1];
    	   		sender.sendMessage(bal);
    	   		time++;
    	   		bal = balc.balTop(page, time);
    	   	}
    	   	return true;
	    }
		return false;
	}
}