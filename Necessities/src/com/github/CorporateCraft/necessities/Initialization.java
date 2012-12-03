package com.github.CorporateCraft.necessities;

import java.io.File;
import java.io.IOException;

public class Initialization
{
	ArrayLists arl = new ArrayLists();
	public Initialization()
	{
		
	}
	public void InitiateFiles()
	{
		DirCreate("plugins/Necessities");
		DirCreate("plugins/Necessities/Logs");
		FileCreate(arl.GetMotd());
		FileCreate(arl.GetRules());
		FileCreate(arl.GetProf());
		ArrayLists ar = new ArrayLists();
		ar.StartLists();
	}
	
	private void DirCreate(String directory)
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
	
	private void FileCreate(String file)
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