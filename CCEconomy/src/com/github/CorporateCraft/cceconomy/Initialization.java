package com.github.CorporateCraft.cceconomy;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	public static void InitiateFiles()
	{
		File f1 = new File("plugins/CCEconomy/moneytracker.txt");
		File f2 = new File("plugins/CCEconomy/sellprices.txt");
		File f3 = new File("plugins/CCEconomy/buyprices.txt");
		File f4 = new File("plugins/CCEconomy/itemids.txt");
		File d = new File("plugins/CCEconomy");
		if(!d.exists())
		{
			boolean success = d.mkdir();
			if (!success){}
		}
		if(!f1.exists())
		{
			try
			{
				f1.createNewFile();
			}
			catch (IOException e){}
		}
		if(!f2.exists())
		{
			try
			{
				f2.createNewFile();
			}
			catch (IOException e){}
		}
		if(!f3.exists())
		{
			try
			{
				f3.createNewFile();
			}
			catch (IOException e){}
		}
		if(!f4.exists())
		{
			try
			{
				f4.createNewFile();
			}
			catch (IOException e){}
		}
	}
}