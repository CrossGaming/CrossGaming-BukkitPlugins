package com.github.CrossGaming.ccebridge.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CrossGaming.ccebridge.PlayerInfo;
import com.github.CrossGaming.ccebridge.RankPrices;
import com.github.CorporateCraft.cceconomy.*;

public class CmdRankPrices extends Cmd
{
	Formatter form = new Formatter();
	RankPrices pr = new RankPrices();
	ArrayLists ccearl = new ArrayLists();
	PlayerInfo pInfo = new PlayerInfo();
	public CmdRankPrices()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length > 1)
			return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			int page = 0;
           	if (args.length == 1)
           	{
           		if(!form.isLegal(args[0]))
					return false;
        	   	page = Integer.parseInt(args[0]);
           	}
           	if (args.length == 0)
           		page = 1;
           	if (page == 0)
           		page = 1;
    	   	int time = 0;
    	   	String price;
    	   	int totalpages = pr.priceListPages();
    	   	if (page > totalpages)
    	   	{
    	   		player.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	player.sendMessage(ChatColor.GOLD + "Rank Prices Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = pr.priceLists(page, time);
    	   	String rank = pInfo.curRank(player.getName()).toUpperCase();
    	   	while(price != null)
    	   	{
    	   		price = formL(form.capFirst(price.split(" ")[0]),
    	   									price.split(" ")[1],
    	   									Integer.toString((page*10) + time + 1) + ".",
    	   									rank);
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
					return false;
        	   	page = Integer.parseInt(args[0]);
           	}
           	if (args.length == 0)
           		page = 1;
           	if (page == 0)
           		page = 1;
    	   	int time = 0;
    	   	String price;
    	   	int totalpages = pr.priceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		sender.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	sender.sendMessage(ChatColor.GOLD + "Rank Prices Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = pr.priceLists(page, time);
    	   	while(price != null)
    	   	{
    	   		price = formL(form.capFirst(price.split(" ")[0]),
    	   									price.split(" ")[1],
    	   									Integer.toString((page*10) + time + 1) + ".",
    	   									"CONSOLE");
    	   		sender.sendMessage(price);
    	   		time++;
    	   		price = pr.priceLists(page, time);
    	   	}
			return true;
		}
	}
	private String formL(String rank, String cost, String numb, String curRank)
	{
		String price = "";
		if(!numb.equalsIgnoreCase("10."))
			numb += " ";
		numb += " ";
		if(curRank.equals("CONSOLE"))
		{
			cost = "$" + form.roundTwoDecimals(Double.parseDouble(cost));
			price = ChatColor.GOLD + numb + ccearl.getMessages() + rank + " can be bought for " + ccearl.getMoney() + cost;
		}
		else
		{
			cost = "$" + form.roundTwoDecimals(Double.parseDouble(cost));
			price = ChatColor.GOLD + numb + ccearl.getMessages() + rank + " can be bought for " + ccearl.getMoney() + cost;
			if(pr.hasRank(curRank, rank))
				price += ccearl.getMessages() + "   Already Aquired.";
		}
   		return price;
	}
}