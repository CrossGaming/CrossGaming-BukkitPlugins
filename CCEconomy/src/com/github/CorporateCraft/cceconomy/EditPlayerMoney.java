package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditPlayerMoney
{
	public static void SetMoney(String name, String amount)
	{
		ArrayList<String> list = new ArrayList<String>();
		String file = "plugins/CCEconomy/moneytracker.txt";
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        list.add(inputText);
		    }
		}
		catch (IOException ex){}
		int spotinlist = list.indexOf(name + " " + BalChecks.Bal(name));
		String newbal;
		newbal = name + " " + amount;
		list.set(spotinlist, newbal);
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			for (int i = 0; i < list.size(); i++)
			{
				bw.write(list.get(i));
				bw.newLine();
			}
			bw.close();
		}
		catch (Exception e){}
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