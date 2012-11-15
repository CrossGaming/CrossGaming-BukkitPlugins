package com.github.CorporateCraft.necessities;

public class Teleports
{
	public static boolean hasTp(String pname, String rname)
	{
		return ArrayLists.Tps.containsKey(pname + " " + rname);
	}
	
	public static void CreateTp(String key, String value)
	{
		ArrayLists.Tps.put(key, value);
		for(int i = 0; i<ArrayLists.LastTp.size(); i++)
		{
			if(ArrayLists.LastTp.get(i).startsWith(key.split(" ")[0] + " "))
			{
				ArrayLists.LastTp.remove(i);
				break;
			}
		}
		ArrayLists.LastTp.add(key);
	}
	
	public static String LastOffer(String pname)
	{
		for(int i = 0; i<ArrayLists.LastTp.size(); i++)
		{
			if(ArrayLists.LastTp.get(i).startsWith(pname + " "))
			{
				return ArrayLists.LastTp.get(i).split(" ")[1];
			}
		}
		return pname;
	}
	
	public static String AcceptTp(String pname, String rname)
	{
		String Info = ArrayLists.Tps.get(pname + " " + rname);
		removeTp(pname, rname);
		return Info;
	}
	
	public static void DenyTp(String pname, String rname)
	{
		removeTp(pname, rname);
	}
	
	public static void removeTp(String pname, String rname)
	{
		ArrayLists.Tps.remove(pname + " " + rname);
		for(int i = 0; i<ArrayLists.LastTp.size(); i++)
		{
			if(ArrayLists.LastTp.get(i).equalsIgnoreCase(pname + " " + rname))
			{
				ArrayLists.LastTp.remove(i);
				break;
			}
		}
	}
}