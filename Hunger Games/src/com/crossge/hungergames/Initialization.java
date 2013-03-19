package com.crossge.hungergames;

import java.io.File;

public class Initialization
{
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/Hunger Games");
		
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
	
	/*private void fileCreate(String file)
	{//No instances yet
		File f = new File(file);
		if(!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e){}
		}
	}*/
}