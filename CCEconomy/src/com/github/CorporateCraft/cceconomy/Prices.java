package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;

public class Prices
{
	ArrayLists arl = new ArrayLists();
	Formatter form = new Formatter();
	private static ArrayList<String> sellPrices = new ArrayList<String>();
	private static ArrayList<String> buyPrices = new ArrayList<String>();
	private static ArrayList<String> price = new ArrayList<String>();
	public Prices()
	{
		
	}
	public void updateL()
	{
		form.readFile(arl.getSellFile(), sellPrices);
		form.readFile(arl.getBuyFile(), buyPrices);
		updateList();
	}
	public String cost(String file, String itemName)
	{
		itemName = itemName.toUpperCase().replaceAll("_", "");
		if(file.equals(arl.getSellFile()))
		{
			for(int i = 0; i < sellPrices.size(); i++)
			{
				if(sellPrices.get(i).split(" ")[0].equalsIgnoreCase(itemName))
				{
					return sellPrices.get(i).split(" ")[1];
				}
			}
		}
		if(file.equals(arl.getBuyFile()))
		{
			for(int i = 0; i < buyPrices.size(); i++)
			{
				if(buyPrices.get(i).split(" ")[0].equalsIgnoreCase(itemName))
				{
					return buyPrices.get(i).split(" ")[1];
				}
			}
		}
		return null;
	}
	public double getCost(String file, String itemName, int amount)
	{
		String costPerUnit = cost(file, itemName);
		if(costPerUnit == null)
		{
			return -1.00;
		}
		if(costPerUnit.equalsIgnoreCase("null"))
		{
			return -1.00;
		}
		double cost = Double.parseDouble(costPerUnit) * amount;
		return cost;
	}
	
	public void setCost(String file, String itemName, String amount)
	{
		itemName = itemName.toUpperCase().replaceAll("_", "");
		String newcost = itemName + " " + amount;
		if(file.equals(arl.getSellFile()))
		{
			int spotinlist = sellPrices.indexOf(itemName + " " + cost(file, itemName));
			sellPrices.set(spotinlist, newcost);
			form.writeFile(file, sellPrices);
			updateList();
		}
		if(file.equals(arl.getBuyFile()))
		{
			int spotinlist = buyPrices.indexOf(itemName + " " + cost(file, itemName));
			buyPrices.set(spotinlist, newcost);
			form.writeFile(file, buyPrices);
			updateList();
		}
	}
	public void updateList()
	{
		price.clear();
		boolean temp = false;
		for(int i = 0; i < sellPrices.size(); i++)
		{
			if(buyPrices.get(i).split(" ")[1].equalsIgnoreCase("null"))
			{
				if(sellPrices.get(i).split(" ")[1].equalsIgnoreCase("null"))
				{
					temp = true;
				}
			}
			if(!temp)
			{
				price.add(sellPrices.get(i) + " " + buyPrices.get(i).split(" ")[1]);
			}
			temp = false;
		}
	}
	public int priceListPages()
	{
		int rounder = 0;
		if (price.size()%10 != 0)
		{
			rounder = 1;
		}
		return (price.size()/10) + rounder;
	}
	public String priceLists(int page, int time)
	{
		page = page * 10;
		if (price.size() < time + page + 1)
		{
			return null;
		}
		if (time == 10)
		{
			return null;
		}
		return price.get(page+time);
	}
}