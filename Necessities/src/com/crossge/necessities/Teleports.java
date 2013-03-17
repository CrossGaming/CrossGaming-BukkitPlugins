package com.crossge.necessities;

import java.util.ArrayList;
import java.util.HashMap;

public class Teleports
{
	private static HashMap<String,String> tps = new HashMap<String,String>();
	private ArrayList<String> lastTp = new ArrayList<String>();
	public Teleports()
	{

	}
	public boolean hasTp(String pname, String rname)
	{
		return tps.containsKey(pname + " " + rname);
	}
	public void createTp(String key, String value)
	{
		tps.put(key, value);
		for(int i = 0; i<lastTp.size(); i++)
		{
			if(lastTp.get(i).startsWith(key.split(" ")[0] + " "))
			{
				lastTp.remove(i);
				break;
			}
		}
		lastTp.add(key);
	}
	
	public String lastOffer(String pname)
	{
		for(int i = 0; i<lastTp.size(); i++)
		{
			if(lastTp.get(i).startsWith(pname + " "))
				return lastTp.get(i).split(" ")[1];
		}
		return pname;
	}
	public String acceptTp(String pname, String rname)
	{
		String Info = tps.get(pname + " " + rname);
		removeTp(pname, rname);
		return Info;
	}
	public void denyTp(String pname, String rname)
	{
		removeTp(pname, rname);
	}
	public void removeTp(String pname, String rname)
	{
		tps.remove(pname + " " + rname);
		for(int i = 0; i<lastTp.size(); i++)
		{
			if(lastTp.get(i).equalsIgnoreCase(pname + " " + rname))
			{
				lastTp.remove(i);
				break;
			}
		}
	}
}