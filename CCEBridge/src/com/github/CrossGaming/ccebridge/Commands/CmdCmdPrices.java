package com.github.CrossGaming.ccebridge.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.ArrayLists;
import com.github.CorporateCraft.cceconomy.Formatter;
import com.github.CrossGaming.ccebridge.CmdPrices;
import com.github.CrossGaming.ccebridge.PlayerInfo;

public class CmdCmdPrices extends Cmd
{
	Formatter form = new Formatter();
	CmdPrices cmdp = new CmdPrices();
	ArrayLists ccearl = new ArrayLists();
	PlayerInfo pInfo = new PlayerInfo();
	public CmdCmdPrices()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length > 1)
		{
			return false;
		}
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
    	   	int totalpages = cmdp.priceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		player.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	player.sendMessage(ChatColor.GOLD + "Command Prices Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = cmdp.priceLists(page, time);
    	   	String rank = pInfo.curRank(player.getName()).toUpperCase();
    	   	Command com;
    	   	boolean hasNode = false;
    	   	while(price != null)
    	   	{
    			com = player.getServer().getPluginCommand(price.split(" ")[0]);
    			if(com == null)
    			{
    				hasNode = false;
    			}
    			else
    			{
    				String tempn = com.getPermission();
    				if(tempn == null)
    				{
    					tempn = "essentials." + price.split(" ")[0].toLowerCase();
    				}
    				if(tempn.contains("mcmmo"))
    				{
    					tempn = "essentials." + price.split(" ")[0].toLowerCase();
    				}
    				hasNode = pInfo.hasCmd(player.getName(), tempn);	
    			}
    	   		price = formL(form.capFirst(price.split(" ")[0]),
    	   									price.split(" ")[1],
    	   									price.split(" ")[2],
    	   									Integer.toString((page*10) + time + 1) + ".",
    	   									rank,
    	   									hasNode);
    	   		player.sendMessage(price);
    	   		time++;
    	   		price = cmdp.priceLists(page, time);
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
    	   	int totalpages = cmdp.priceListPages();
    	   	if (page>totalpages)
    	   	{
    	   		sender.sendMessage(ChatColor.GOLD + "Input a number from 1 to " + Integer.toString(totalpages));
    	   		return true;
    	   	}
    	   	sender.sendMessage(ChatColor.GOLD + "Command Prices Page [" + Integer.toString(page) + "/" + Integer.toString(totalpages) + "]");
    	   	page = page - 1;
    	   	price = cmdp.priceLists(page, time);
    	   	while(price != null)
    	   	{
    	   		price = formL(form.capFirst(price.split(" ")[0]),
    	   									price.split(" ")[1],
    	   									price.split(" ")[2],
    	   									Integer.toString((page*10) + time + 1) + ".",
    	   									"CONSOLE",
    	   									true);
    	   		sender.sendMessage(price);
    	   		time++;
    	   		price = cmdp.priceLists(page, time);
    	   	}
			return true;
		}
	}
	private String formL(String cmd, String rank, String cost, String numb, String curRank, boolean hasCmd)
	{
		String price = "";
		rank = rank.toLowerCase();
		if(!numb.equalsIgnoreCase("10."))
		{
			numb += " ";
		}
		numb += " ";
		if(curRank.equals("CONSOLE"))
		{
			cost = "$" + form.roundTwoDecimals(Double.parseDouble(cost));
			price = ChatColor.GOLD + numb + ccearl.getMessages() + cmd + " can be bought for " + ccearl.getMoney() + cost + ccearl.getMessages() + " by rank " + rank;
		}
		else
		{
			cost = "$" + form.roundTwoDecimals(Double.parseDouble(cost));
			price = ChatColor.GOLD + numb + ccearl.getMessages() + cmd + " can be bought for " + ccearl.getMoney() + cost;
			if(hasCmd)
			{
				price += ccearl.getMessages() + "   Already Aquired.";
			}
			else
			{
				price +=  ccearl.getMessages() + " by rank " + rank;
			}
		}
   		return price;
	}
}