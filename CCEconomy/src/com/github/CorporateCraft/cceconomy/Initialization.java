package com.github.CorporateCraft.cceconomy;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	public static void InitiateFiles()
	{
		File f1 = new File(CCEconomy.balfile);
		File f2 = new File(CCEconomy.sellfile);
		File f3 = new File(CCEconomy.buyfile);
		File d = new File("plugins/CCEconomy");
		if(!d.exists())
		{
			try
			{
				d.mkdir();
			}
			catch (Exception e){}
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
		Materials.UpdateSell();
		Materials.UpdateBuy();
		ArrayLists.StartLists();
	}
}