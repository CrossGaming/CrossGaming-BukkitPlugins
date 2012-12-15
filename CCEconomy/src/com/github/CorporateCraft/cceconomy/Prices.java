package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;

public class Prices
{
	private static ArrayList<String> Prices = new ArrayList<String>();
	public static String Cost(String file, String ItemName)
	{
		ItemName = ItemName.toUpperCase().replaceAll("_", "");
		if(file.equals(CCEconomy.sellfile))
		{
			for(int i = 0; i < ArrayLists.SellPrices.size(); i++)
			{
				if(ArrayLists.SellPrices.get(i).split(" ")[0].equalsIgnoreCase(ItemName))
				{
					return ArrayLists.SellPrices.get(i).split(" ")[1];
				}
			}
		}
		if(file.equals(CCEconomy.buyfile))
		{
			for(int i = 0; i < ArrayLists.BuyPrices.size(); i++)
			{
				if(ArrayLists.BuyPrices.get(i).split(" ")[0].equalsIgnoreCase(ItemName))
				{
					return ArrayLists.BuyPrices.get(i).split(" ")[1];
				}
			}
		}
		return null;
	}
	
	public static Double GetCost(String file, String ItemName, int Amount)
	{
		String CostPerUnit = Cost(file, ItemName);
		if(CostPerUnit == null)
		{
			return -1.00;
		}
		if(CostPerUnit.equalsIgnoreCase("null"))
		{
			return -1.00;
		}
		Double Cost = Double.parseDouble(CostPerUnit) * Amount;
		return Cost;
	}
	
	public static void SetCost(String file, String itemname, String amount)
	{
		itemname = itemname.toUpperCase().replaceAll("_", "");
		String newcost = itemname + " " + amount;
		if(file.equals(CCEconomy.sellfile))
		{
			int spotinlist = ArrayLists.SellPrices.indexOf(itemname + " " + Cost(file, itemname));
			ArrayLists.SellPrices.set(spotinlist, newcost);
			Formatter.WriteFile(file, ArrayLists.SellPrices);
			updateList();
		}
		if(file.equals(CCEconomy.buyfile))
		{
			int spotinlist = ArrayLists.BuyPrices.indexOf(itemname + " " + Cost(file, itemname));
			ArrayLists.BuyPrices.set(spotinlist, newcost);
			Formatter.WriteFile(file, ArrayLists.BuyPrices);
			updateList();
		}
	}
	public static void updateList()
	{
		Prices.clear();
		boolean temp = false;
		for(int i = 0; i<ArrayLists.SellPrices.size(); i++)
		{
			if(ArrayLists.BuyPrices.get(i).split(" ")[1].equalsIgnoreCase("null"))
			{
				if(ArrayLists.SellPrices.get(i).split(" ")[1].equalsIgnoreCase("null"))
				{
					temp = true;
				}
			}
			if(!temp)
			{
				Prices.add(ArrayLists.SellPrices.get(i) + " " + ArrayLists.BuyPrices.get(i).split(" ")[1]);
			}
			temp = false;
		}
	}
	public static int PriceListPages()
	{
		int rounder = 0;
		if (Prices.size()%10 != 0)
		{
			rounder = 1;
		}
		return (Prices.size()/10) + rounder;
	}
	public static String PriceLists(int page, int time)
	{
		page = page * 10;
		if (Prices.size() < time + page + 1)
		{
			return null;
		}
		if (time == 10)
		{
			return null;
		}
		return Prices.get(page+time);
	}
}