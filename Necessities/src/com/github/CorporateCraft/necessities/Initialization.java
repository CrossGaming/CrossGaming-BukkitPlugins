package com.github.CorporateCraft.necessities;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	public static void InitiateFiles()
	{
		DirCreate("plugins/Necessities");
		FileCreate(Necessities.Motdfile);
		FileCreate(Necessities.Rulesfile);
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
	
	private static void FileCreate(String file)
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