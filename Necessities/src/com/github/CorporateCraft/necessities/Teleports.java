package com.github.CorporateCraft.necessities;

import java.util.ArrayList;
import java.util.HashMap;

public class Teleports
{
	private HashMap<String,String> Tps = new HashMap<String,String>();
	private ArrayList<String> LastTp = new ArrayList<String>();
	public Teleports()
	{
	
	}
	public boolean hasTp(String pname, String rname)
	{
		return Tps.containsKey(pname + " " + rname);
	}
	
	public void CreateTp(String key, String value)
	{
		Tps.put(key, value);
		for(int i = 0; i<LastTp.size(); i++)
		{
			if(LastTp.get(i).startsWith(key.split(" ")[0] + " "))
			{
				LastTp.remove(i);
				break;
			}
		}
		LastTp.add(key);
	}
	
	public String LastOffer(String pname)
	{
		for(int i = 0; i<LastTp.size(); i++)
		{
			if(LastTp.get(i).startsWith(pname + " "))
			{
				return LastTp.get(i).split(" ")[1];
			}
		}
		return pname;
	}
	
	public String AcceptTp(String pname, String rname)
	{
		String Info = Tps.get(pname + " " + rname);
		removeTp(pname, rname);
		return Info;
	}
	
	public void DenyTp(String pname, String rname)
	{
		removeTp(pname, rname);
	}
	
	public void removeTp(String pname, String rname)
	{
		Tps.remove(pname + " " + rname);
		for(int i = 0; i<LastTp.size(); i++)
		{
			if(LastTp.get(i).equalsIgnoreCase(pname + " " + rname))
			{
				LastTp.remove(i);
				break;
			}
		}
	}
}