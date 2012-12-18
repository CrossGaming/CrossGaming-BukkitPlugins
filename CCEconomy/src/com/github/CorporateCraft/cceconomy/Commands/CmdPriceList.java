package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdPriceList
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			int page = 0;
           	if (args.length == 1)
           	{
           		if(!Formatter.isLegal(args[0]))
				{
					return false;
				}
        	   	page = Integer.parseInt(args[0]);
           	}
           	if (args.length == 0)
           	{
           		page = 1;
           	}
    	   	int time = 0;
    	   	String price;
    	   	int totalpages = Prices.PriceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		player.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	player.sendMessage(ChatColor.GOLD + "Price List Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = Prices.PriceLists(page, time);
    	   	while(price != null)
    	   	{
    	   		price = Form(Formatter.CapFirst(Materials.FindItem(price.split(" ")[0])),
    	   										price.split(" ")[2],
    	   										price.split(" ")[1],
    	   										Integer.toString((page*10) + time + 1) + ".");
    	   		player.sendMessage(price);
    	   		time++;
    	   		price = Prices.PriceLists(page, time);
    	   	}
			return true;
		}
		else
		{
			int page = 0;
           	if (args.length == 1)
           	{
           		if(!Formatter.isLegal(args[0]))
				{
					return false;
				}
        	   	page = Integer.parseInt(args[0]);
           	}
           	if (args.length == 0)
           	{
           		page = 1;
           	}
    	   	int time = 0;
    	   	String price;
    	   	int totalpages = Prices.PriceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		sender.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	sender.sendMessage(ChatColor.GOLD + "Price List Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = Prices.PriceLists(page, time);
    	   	while(price != null)
    	   	{
    	   		price = Form(Formatter.CapFirst(Materials.FindItem(price.split(" ")[0])),
    	   										price.split(" ")[2],
    	   										price.split(" ")[1],
    	   										Integer.toString((page*10) + time + 1) + ".");
    	   		sender.sendMessage(price);
    	   		time++;
    	   		price = Prices.PriceLists(page, time);
    	   	}
			return true;
		}
	}
	private static String Form(String item, String buy, String sell, String numb)
	{
		String selling = "     sell price: ";
	   	String buying = "     buy price: ";
		String price = "";
		try
		{
			if(item.split(" ")[1].equalsIgnoreCase("Item"))
			{
				item = item.split(" ")[0];
			}
		}
		catch(Exception e)//no spaces
		{
			
		}
		if(!numb.equalsIgnoreCase("10."))
		{
			numb += " ";
		}
		numb += " ";
		sell = "$" + sell;
		buy = "$" + buy;
		if(buy.trim().equalsIgnoreCase("$null"))
   		{
			buying = "";
			buy = "";
   		}
   		if(sell.trim().equalsIgnoreCase("$null"))
   		{
   			selling = "";
   			sell = "";
   		}
	   	price = ChatColor.GOLD + numb +
	   			CCEconomy.messages + item +
	   			buying + CCEconomy.money + buy + CCEconomy.messages +
	   			selling + CCEconomy.money + sell;
   		return price;
	}
}