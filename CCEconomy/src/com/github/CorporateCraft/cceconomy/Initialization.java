package com.github.CorporateCraft.cceconomy;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	public static void InitiateFiles()
	{
		DirCreate("plugins/CCEconomy");
		FileCreate(CCEconomy.balfile);
		FileCreate(CCEconomy.sellfile);
		FileCreate(CCEconomy.buyfile);
		Materials.UpdateMats();
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