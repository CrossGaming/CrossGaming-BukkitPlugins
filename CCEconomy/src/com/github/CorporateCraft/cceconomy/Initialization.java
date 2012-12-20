package com.github.CorporateCraft.cceconomy;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	ArrayLists arl = new ArrayLists();
	Materials mat = new Materials();
	BalChecks balc = new BalChecks();
	Prices pr = new Prices();
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/CCEconomy");
		fileCreate(arl.getBalFile());
		fileCreate(arl.getSellFile());
		fileCreate(arl.getBuyFile());
		mat.updateMats();
		pr.updateL();
		balc.updateB();
	}
	
	private void dirCreate(String directory)
	{
		File d = new File(directory);
		if(!d.exists())
		{
			try
			{
				d.mkdir();
			}
			catch (Exception e){}
		}
	}
	
	private void fileCreate(String file)
	{
		File f = new File(file);
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