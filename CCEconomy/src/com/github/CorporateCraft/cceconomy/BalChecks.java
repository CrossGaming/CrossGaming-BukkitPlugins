package com.github.CorporateCraft.cceconomy;

import java.util.ArrayList;
import java.util.Collections;

public class BalChecks
{
	Formatter form = new Formatter();
	ArrayLists arl = new ArrayLists();
	private static ArrayList<String> balances = new ArrayList<String>();
	public BalChecks()
	{
		
	}
	public void updateB()
	{
		form.readFile(arl.getBalFile(), balances);
		Collections.sort(balances);
	}
	public String bal(String name)
	{
		for(int i = 0; i < balances.size(); i++)
		{
			if(balances.get(i).startsWith(name))
				return balances.get(i).split(" ")[1];
		}
		return null;
	}
	public String balTop(int page, int time)
	{
		ArrayList<Double> balsort = new ArrayList<Double>();
		for(int i = 0; i < balances.size(); i++)
		{
	        balsort.add(Double.parseDouble(balances.get(i).split(" ")[1]));
		}
		Collections.sort(balsort);
		Collections.reverse(balsort);
		page = page * 10;
		if (balances.size() < time + page + 1)
			return null;
		if (time == 10)
			return null;
		int occurrence = 1;
		for (int i = 0; i < page+time; i++)
		{
			if(balsort.get(i).equals(balsort.get(page + time)))
				occurrence++;
		}
		String strBal = form.roundTwoDecimals(balsort.get(page+time));
		int balSpot = baltopCords(strBal, occurrence);
		if (balSpot == -1)
			return null;
		return balances.get(balSpot);
	}
	private int baltopCords(String money, int occurrence)
	{
		int counter = 1;
		for(int i = 0; i < balances.size(); i++)
		{
			if(balances.get(i).contains(" " + money))
			{
				if(counter == occurrence)
					return i;
				counter++;
			}
		}
		return -1;
	}
	public int baltopPages()
	{
		int rounder = 0;
		if (balances.size()%10 != 0)
			rounder = 1;
		return (balances.size()/10) + rounder;
	}
	public boolean doesPlayerExist(String name)
	{
		for(int i = 0; i < balances.size(); i++)
		{
			if(balances.get(i).startsWith(name))
				return true;
		}
		return false;
	}
	public void addPlayerToList(String name)
	{
		balances.add(name + " 0.00");
		Collections.sort(balances);
		form.writeFile(arl.getBalFile(), balances);
	}
	public void setMoney(String name, String amount)
	{
		int spotinlist = balances.indexOf(name + " " + bal(name));
		String newbal;
		newbal = name + " " + amount;
		balances.set(spotinlist, newbal);
		form.writeFile(arl.getBalFile(), balances);
	}
	public void removeMoney(String name, double amount)
	{
		String bal = bal(name);
		double intbal = Double.parseDouble(bal);
		double newamount = intbal - amount;
		setMoney(name, form.roundTwoDecimals(newamount));
	}
	public void addMoney(String name, double amount)
	{
		String bal = bal(name);
		double intbal = Double.parseDouble(bal);
		double newamount = intbal + amount;
		setMoney(name, form.roundTwoDecimals(newamount));
	}
}