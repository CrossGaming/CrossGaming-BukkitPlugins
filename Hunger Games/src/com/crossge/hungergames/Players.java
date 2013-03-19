package com.crossge.hungergames;

import java.util.ArrayList;

public class Players
{
	private static ArrayList<String> alive = new ArrayList<String>();
	private static ArrayList<String> dead = new ArrayList<String>();
	private static ArrayList<String> queued = new ArrayList<String>();
	private static ArrayList<String> spectating = new ArrayList<String>();
	public Players()
	{
		
	}
	
	public String leftAlive()
	{
		String temp = "";
		for(int i = 0; i < alive.size(); i++)
		{
			temp += alive.get(i) + ", ";
		}
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 2);
		temp += ".";
		return temp;
	}
	public boolean isAlive(String name)
	{
		return alive.contains(name);
	}
	public boolean isSpectating(String name)
	{
		return spectating.contains(name);
	}
	public void addSpectating(String name)
	{
		spectating.add(name);
	}
	public void delSpectating(String name)
	{
		spectating.remove(name);
	}
	public void addDead(String name)
	{
		alive.remove(name);
		dead.add(name);
	}
	public String deceased()
	{
		String temp = "";
		for(int i = 0; i < dead.size(); i++)
		{
			temp += dead.get(i) + ", ";
		}
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 1);
		temp += ".";
		return temp;
	}
	public String breathing()
	{
		String temp = "";
		for(int i = 0; i < alive.size(); i++)
		{
			temp += alive.get(i) + ", ";
		}
		temp = temp.trim();
		temp = temp.substring(0, temp.length() - 1);
		temp += ".";
		return temp;
	}
	public String amount()
	{
		try
		{
			return Integer.toString(alive.size()) + " alive out of " + Integer.toString(alive.size() + dead.size()) + " total.";
		}
		catch(Exception e)
		{
			return "Game not started";
		}
	}
	public boolean onePlayerLeft()
	{
		return alive.size() == 1;
	}
	public String winner()
	{
		return alive.get(0);
	}
	public void endGame()
	{
		alive.clear();
		dead.clear();
		spectating.clear();
	}
	public void addToQueue(String name)
	{
		queued.add(name);
	}
	public void removeFromQueue(String name)
	{
		queued.remove(name);
	}
	public boolean queueFull()
	{
		return queued.size() == 24;
	}
	public int posInQueue(String name)
	{
		return queued.indexOf(name) + 1;
	}	
	public void gameStart()
	{
		alive.clear();
		dead.clear();
		spectating.clear();
		for(int i = 0; i < queued.size(); i++)
		{
			alive.add(queued.get(i));
		}
		queued.clear();
	}
}