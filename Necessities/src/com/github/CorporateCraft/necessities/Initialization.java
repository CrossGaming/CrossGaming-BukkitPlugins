package com.github.CorporateCraft.necessities;

import java.io.File;

public class Initialization
{
	public static void InitiateFiles()
	{
		DirCreate("plugins/Necessities");
		ArrayLists.StartLists();
	}
	
	private static void DirCreate(String directory)
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
}