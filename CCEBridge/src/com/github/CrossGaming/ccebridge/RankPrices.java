package com.github.CrossGaming.ccebridge;

import java.util.ArrayList;
import java.util.Collections;

import com.github.CorporateCraft.cceconomy.Formatter;

public class RankPrices
{
	RankArl arl = new RankArl();
	Formatter form = new Formatter();
	private static ArrayList<String> rankPrices = new ArrayList<String>();
	private static ArrayList<String> rankOrder = new ArrayList<String>();
	public RankPrices()
	{
		
	}
	public void updateL()
	{
		form.readFile(arl.getRankPriceFile(), rankPrices);
		orderRanks();
	}
	private void orderRanks()
	{
		rankOrder.clear();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<String> temp2 = new ArrayList<String>();
		for(int i = 0; i < rankPrices.size(); i++)
		{
			temp.add(Integer.parseInt(rankPrices.get(i).split(" ")[1]));
		}
		Collections.sort(temp);//lowest to highest
		for(int i = 0; i < temp.size(); i++)
		{
			for(int j = 0; j < rankPrices.size(); j++)
			{
				if(temp.get(i) == Integer.parseInt(rankPrices.get(j).split(" ")[1]))
				{
					rankOrder.add(rankPrices.get(j).split(" ")[0]);//Just rank names
					temp2.add(rankPrices.get(j));
				}
			}
		}
		rankPrices = temp2;
	}
	public boolean rankBuyable(String rankName)
	{
		rankName = rankName.toUpperCase();
		for(int i = 0; i < rankPrices.size(); i++)
		{
			if(rankPrices.get(i).split(" ")[0].equalsIgnoreCase(rankName))
			{
				return true;
			}
		}
		return false;
	}
	public String cost(String rankName)
	{
		rankName = rankName.toUpperCase();
		for(int i = 0; i < rankPrices.size(); i++)
		{
			if(rankPrices.get(i).split(" ")[0].equalsIgnoreCase(rankName))
			{
				return rankPrices.get(i).split(" ")[1];
			}
		}
		return null;
	}
	public double getCost(String rankName)
	{
		rankName = rankName.toUpperCase();
		String costPerUnit = cost(rankName);
		if(costPerUnit == null)
		{
			return -1.00;
		}
		if(costPerUnit.equalsIgnoreCase("null"))
		{
			return -1.00;
		}
		double cost = Double.parseDouble(costPerUnit);
		return cost;
	}
	public boolean nextRank(String rankName, String checkRank)
	{
		rankName = rankName.toUpperCase();
		checkRank = checkRank.toUpperCase();
		int rLoc = -1;
		int cLoc = -1;
		for(int i = 0; i < rankOrder.size(); i++)
		{
			if(rankOrder.get(i).equals(rankName))
			{
				rLoc = i;
			}
			if(rankOrder.get(i).equals(checkRank))
			{
				cLoc = i;
			}
		}
		if(rankName.equals("GUEST"))
		{
			if(cLoc == 0)
			{
				return true;
			}
		}
		if(rLoc == -1)
		{
			return false; //Has a rank that can't be bought so shouldn't buy any new ranks
		}
		if(cLoc == -1)
		{
			return false; //Rank to be bought does not exist
		}
		if(cLoc - rLoc == 1)//Is next rank
		{
			return true;
		}
		return false;
	}
	public boolean hasRank(String rankName, String checkRank)
	{
		rankName = rankName.toUpperCase();
		checkRank = checkRank.toUpperCase();
		if(rankName.equals(checkRank))
		{
			return true;
		}
		else if(rankName.equals("GUEST"))
		{
			return false;
		}
		int rLoc = -1;
		int cLoc = -1;
		for(int i = 0; i < rankOrder.size(); i++)
		{
			if(rankOrder.get(i).equals(rankName))
			{
				rLoc = i;
			}
			if(rankOrder.get(i).equals(checkRank))
			{
				cLoc = i;
			}
		}
		if(rLoc == -1)
		{
			return true; //Has a rank that can't be bought but so can see prices
		}
		if(cLoc == -1)
		{
			return true; //Rank checking against cannot be bought (DAFUQ all the checkRanks are self entered)
		}
		if(rLoc > cLoc)
		{
			return true;
		}
		return false;
	}
	public ArrayList<String> rOrd()
	{
		return rankOrder;
	}
	public void rCost(String rankName)
	{
		rankName = rankName.toUpperCase();
		rankPrices.remove(rankName + " " + cost(rankName));
		form.writeFile(arl.getRankPriceFile(), rankPrices);
		orderRanks();
	}
	public void setCost(String rankName, String amount)
	{
		rankName = rankName.toUpperCase();
		String newcost = rankName + " " + amount;
		int spotinlist = rankPrices.indexOf(rankName + " " + cost(rankName));
		if(spotinlist == -1)
		{
			rankPrices.add(newcost);
		}
		else
		{
			rankPrices.set(spotinlist, newcost);
		}
		form.writeFile(arl.getRankPriceFile(), rankPrices);
		orderRanks();
	}
	public int priceListPages()
	{
		int rounder = 0;
		if (rankPrices.size()%10 != 0)
		{
			rounder = 1;
		}
		return (rankPrices.size()/10) + rounder;
	}
	public String priceLists(int page, int time)
	{
		page = page * 10;
		if (rankPrices.size() < time + page + 1)
		{
			return null;
		}
		if (time == 10)
		{
			return null;
		}
		return rankPrices.get(page+time);
	}
}