package com.github.CorporateCraft.cceconomy;

public class EditPlayerMoney
{
	public static void SetMoney(String name, String amount)
	{
		int spotinlist = ArrayLists.Balances.indexOf(name + " " + BalChecks.Bal(name));
		String newbal;
		newbal = name + " " + amount;
		ArrayLists.Balances.set(spotinlist, newbal);
		Formatter.WriteFile(CCEconomy.balfile, ArrayLists.Balances);
	}
	
	public static void RemoveMoney(String name, double amount)
	{
		String bal = BalChecks.Bal(name);
		double intbal = Double.parseDouble(bal);
		double newamount = intbal - amount;
		SetMoney(name, Formatter.roundTwoDecimals(newamount));
	}
	
	public static void AddMoney(String name, double amount)
	{
		String bal = BalChecks.Bal(name);
		double intbal = Double.parseDouble(bal);
		double newamount = intbal + amount;
		SetMoney(name, Formatter.roundTwoDecimals(newamount));
	}
}