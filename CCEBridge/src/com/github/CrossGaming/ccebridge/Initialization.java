package com.github.CrossGaming.ccebridge;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	RankArl arl = new RankArl();
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/CCEBridge");
		fileCreate(arl.getRankPriceFile());
		createYaml();
		new RankPrices().updateL();
	}
   	private File customConfigFile = null;
	public void createYaml()
	{
		customConfigFile = new File("plugins/CCEBridge", "commands.yml");
		if(!customConfigFile.exists())
		{
			try
			{
				customConfigFile.createNewFile();
			}
			catch (IOException e){}
		}
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