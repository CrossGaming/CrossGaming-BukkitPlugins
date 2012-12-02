package com.github.CorporateCraft.necessities;

public class Teleports
{
	ArrayLists arl = new ArrayLists();
	public Teleports()
	{
	
	}
	public boolean hasTp(String pname, String rname)
	{
		return arl.Tps.containsKey(pname + " " + rname);
	}
	
	public void CreateTp(String key, String value)
	{
		
		arl.Tps.put(key, value);
		for(int i = 0; i<arl.LastTp.size(); i++)
		{
			if(arl.LastTp.get(i).startsWith(key.split(" ")[0] + " "))
			{
				arl.LastTp.remove(i);
				break;
			}
		}
		arl.LastTp.add(key);
	}
	
	public String LastOffer(String pname)
	{
		for(int i = 0; i<arl.LastTp.size(); i++)
		{
			if(arl.LastTp.get(i).startsWith(pname + " "))
			{
				return arl.LastTp.get(i).split(" ")[1];
			}
		}
		return pname;
	}
	
	public String AcceptTp(String pname, String rname)
	{
		String Info = arl.Tps.get(pname + " " + rname);
		removeTp(pname, rname);
		return Info;
	}
	
	public void DenyTp(String pname, String rname)
	{
		removeTp(pname, rname);
	}
	
	public void removeTp(String pname, String rname)
	{
		arl.Tps.remove(pname + " " + rname);
		for(int i = 0; i<arl.LastTp.size(); i++)
		{
			if(arl.LastTp.get(i).equalsIgnoreCase(pname + " " + rname))
			{
				arl.LastTp.remove(i);
				break;
			}
		}
	}
}