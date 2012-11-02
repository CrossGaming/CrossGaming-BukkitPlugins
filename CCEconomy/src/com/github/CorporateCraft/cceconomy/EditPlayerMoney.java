package com.github.CorporateCraft.cceconomy;

public class EditPlayerMoney
{
	public static void SetMoney(String name, String amount)
	{
		String file = "plugins/CCEconomy/moneytracker.txt";
		int spotinlist = ArrayLists.Balances.indexOf(name + " " + BalChecks.Bal(name));
		String newbal;
		newbal = name + " " + amount;
		ArrayLists.Balances.set(spotinlist, newbal);
		Formatter.WriteFile(file, ArrayLists.Balances);
	}
	
	public static void RemoveMoney(String name, double amount)
	{
		String bal = BalChecks.Bal(name);
		double intbal = Double.parseDouble(bal);
		double newamount;
		newamount = intbal - amount;
		String news = Formatter.roundTwoDecimals(newamount);
		SetMoney(name, news);
	}
	
	public static void AddMoney(String name, double amount)
	{
		String bal = BalChecks.Bal(name);
		double intbal = Double.parseDouble(bal);
		double newamount;
		newamount = intbal + amount;
		String news = Formatter.roundTwoDecimals(newamount);
		SetMoney(name, news);
	}
}