package com.github.CorporateCraft.necessities;

public class Teleports
{
	ArrayLists arl = new ArrayLists();
	public Teleports()
	{
	
	}
	public boolean hasTp(String pname, String rname)
	{
		return arl.GetTps().containsKey(pname + " " + rname);
	}
	
	public void CreateTp(String key, String value)
	{
		arl.AddTps(key, value);
		for(int i = 0; i<arl.GetLastTp().size(); i++)
		{
			if(arl.GetLastTp().get(i).startsWith(key.split(" ")[0] + " "))
			{
				arl.RemoveLastTp(i);
				break;
			}
		}
		arl.AddLastTp(key);
	}
	
	public String LastOffer(String pname)
	{
		for(int i = 0; i<arl.GetLastTp().size(); i++)
		{
			if(arl.GetLastTp().get(i).startsWith(pname + " "))
			{
				return arl.GetLastTp().get(i).split(" ")[1];
			}
		}
		return pname;
	}
	
	public String AcceptTp(String pname, String rname)
	{
		String Info = arl.GetTps().get(pname + " " + rname);
		removeTp(pname, rname);
		return Info;
	}
	
	public void DenyTp(String pname, String rname)
	{
		removeTp(pname, rname);
	}
	
	public void removeTp(String pname, String rname)
	{
		arl.RemoveTps(pname + " " + rname);
		for(int i = 0; i<arl.GetLastTp().size(); i++)
		{
			if(arl.GetLastTp().get(i).equalsIgnoreCase(pname + " " + rname))
			{
				arl.RemoveLastTp(i);
				break;
			}
		}
	}
}