package com.github.CorporateCraft.necessities;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	ArrayLists arl = new ArrayLists();
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/Necessities");
		dirCreate("plugins/Necessities/Logs");
		fileCreate(arl.getMotd());
		fileCreate(arl.getRules());
		fileCreate(arl.getProf());
		fileCreate(arl.getAl());
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