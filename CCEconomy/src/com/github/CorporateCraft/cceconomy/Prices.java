package com.github.CorporateCraft.cceconomy;

public class Prices
{
	public static String Cost(String file, String ItemName)
	{
		ItemName.toUpperCase();
		if(file.equals(CCEconomy.sellfile))
		{
			for(int i = 0; i < ArrayLists.SellPrices.size(); i++)
			{
				if(ArrayLists.SellPrices.get(i).startsWith(ItemName))
				{
					return ArrayLists.SellPrices.get(i).split(" ")[1];
				}
			}
		}
		if(file.equals(CCEconomy.buyfile))
		{
			for(int i = 0; i < ArrayLists.BuyPrices.size(); i++)
			{
				if(ArrayLists.BuyPrices.get(i).startsWith(ItemName))
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
		itemname = itemname.toUpperCase();
		itemname = itemname.replaceAll("_", "");
		if(file.equals(CCEconomy.sellfile))
		{
			int spotinlist = ArrayLists.SellPrices.indexOf(itemname + " " + Cost(file, itemname));
			String newcost;
			newcost = itemname + " " + amount;
			ArrayLists.SellPrices.set(spotinlist, newcost);
			Formatter.WriteFile(file, ArrayLists.SellPrices);
		}
		if(file.equals(CCEconomy.buyfile))
		{
			int spotinlist = ArrayLists.BuyPrices.indexOf(itemname + " " + Cost(file, itemname));
			String newcost;
			newcost = itemname + " " + amount;
			ArrayLists.BuyPrices.set(spotinlist, newcost);
			Formatter.WriteFile(file, ArrayLists.BuyPrices);
		}
	}
}