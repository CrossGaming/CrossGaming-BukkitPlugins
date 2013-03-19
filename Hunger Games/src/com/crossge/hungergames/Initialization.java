package com.crossge.hungergames;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	Game g = new Game();
	Stats s = new Stats();
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/Hunger Games");
		fileCreate("plugins/Hunger Games/options.txt");
		createYaml();
		g.initMaps();
		s.create();
	}
	
	private File customConfigFile = null;
	public void createYaml()
	{
		customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
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