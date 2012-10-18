package com.github.CorporateCraft.cceconomy;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	public static void InitiateFiles()
	{
		File f = new File("plugins/CCEconomy/moneytracker.txt");
		File d = new File("plugins/CCEconomy");
		if(!d.exists())
		{
			boolean success = d.mkdir();
			if (!success){}
		}
		if(!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e){}
		}
	}
}