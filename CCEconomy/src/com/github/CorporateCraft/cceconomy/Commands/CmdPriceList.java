package com.github.CorporateCraft.cceconomy.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.*;

public class CmdPriceList extends Cmd
{
	Formatter form = new Formatter();
	Prices pr = new Prices();
	ArrayLists arl = new ArrayLists();
	Materials mat = new Materials();
	public CmdPriceList()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
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
    	   	String price;
    	   	int totalpages = pr.priceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		player.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	player.sendMessage(ChatColor.GOLD + "Price List Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = pr.priceLists(page, time);
    	   	while(price != null)
    	   	{
    	   		price = formL(form.capFirst(mat.findItem(price.split(" ")[0])),
    	   										price.split(" ")[2],
    	   										price.split(" ")[1],
    	   										Integer.toString((page*10) + time + 1) + ".");
    	   		player.sendMessage(price);
    	   		time++;
    	   		price = pr.priceLists(page, time);
    	   	}
			return true;
		}
		else
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
    	   	String price;
    	   	int totalpages = pr.priceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		sender.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	sender.sendMessage(ChatColor.GOLD + "Price List Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = pr.priceLists(page, time);
    	   	while(price != null)
    	   	{
    	   		price = formL(form.capFirst(mat.findItem(price.split(" ")[0])),
    	   										price.split(" ")[2],
    	   										price.split(" ")[1],
    	   										Integer.toString((page*10) + time + 1) + ".");
    	   		sender.sendMessage(price);
    	   		time++;
    	   		price = pr.priceLists(page, time);
    	   	}
			return true;
		}
	}
	private String formL(String item, String buy, String sell, String numb)
	{
		String selling = "  sell price: ";
	   	String buying = "  buy price: ";
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
	   	arl.getMessages() + item +
	   			buying + arl.getMoney() + buy + arl.getMessages() +
	   			selling + arl.getMoney() + sell;
   		return price;
	}
}