package com.github.CorporateCraft.opsandbanned;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class OpsAndBanned extends JavaPlugin
{
	@Override
    public void onEnable()
	{	
		getLogger().info("OpsAndBanned has been enabled.");
    }
 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ops"))
		{			
			if (sender instanceof Player)
			{
	           Player player = (Player) sender;
	           if(player.hasPermission("OpsAndBanned.ops"))
	           {
	        	   String line = readFile("ops.txt");
	        	   if(line == null)
	        	   {
	        		   player.sendMessage("There are no operators.");
	        		   return true;
	        	   }
	        	   player.sendMessage("The Operators are:");
	        	   player.sendMessage(line);
	        	   return true;
	           }
	           else
	           {
	        	   player.sendMessage("No you cant use this sorry");
	           }
	        } 
			else
			{
				String line = readFile("ops.txt");
				if(line == null)
				{
					sender.sendMessage("There are no operators.");
					return true;
				}
				sender.sendMessage("The Operators are:");
				sender.sendMessage(line);
				return true;
		    }
		}
		if(cmd.getName().equalsIgnoreCase("banned"))
		{
			if (sender instanceof Player)
			{
		       Player player = (Player) sender;
		       if(player.hasPermission("OpsAndBanned.banned"))
	           {
		    	   String line = readFile("banned-players");
		       		if(line == null)
					{
						player.sendMessage("There are no banned players.");
						return true;
					}
		       		player.sendMessage("Banned Players:");
		       		player.sendMessage(line);
		       		return true;
	           }
	           else
	           {
	        	   player.sendMessage("No you cant use this sorry");
	        	   return true;
	           }
			}
			else
			{
				String line = readFile("banned-players");
				if(line == null)
				{
					sender.sendMessage("There are no banned players.");
					return true;
				}
				sender.sendMessage("Banned Players:");
		        sender.sendMessage(line);
		        return true;
		    }
		}
		if(cmd.getName().equalsIgnoreCase("bannedips"))
		{
			if (sender instanceof Player)
			{
		       Player player = (Player) sender;
		       if(player.hasPermission("OpsAndBanned.banned"))
	           {
		    	   String line = readFile("banned-ips");
		       		if(line == null)
					{
						player.sendMessage("There are no banned ips.");
						return true;
					}
		       		player.sendMessage("Banned Ips:");
		       		player.sendMessage(line);
		       		return true;
	           }
	           else
	           {
	        	   player.sendMessage("No you cant use this sorry");
	        	   return true;
	           }
			}
			else
			{
				String line = readFile("banned-ips");
				if(line == null)
				{
					sender.sendMessage("There are no banned ips.");
					return true;
				}
				sender.sendMessage("Banned Ips:");
				sender.sendMessage(line);
		        return true;
		    }
		}
		return false; 
	}
	
	public String readFile(String file)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	if (sb.length()>1)
		          	{
		          		sb.deleteCharAt(sb.length()-1);
		           		sb.deleteCharAt(sb.length()-1);
		           		sb.append(".");
		           		break;
		           	}
		        }
		        if(!inputText.startsWith("#"))
		        {
		        	sb.append(inputText).append(", ");
		        }
		    }
		}
		catch (IOException ex)
		{
		    return null;
		}
		String allFile = sb.toString();
		return allFile;
	}
	
    @Override
    public void onDisable()
    {
    	getLogger().info("OpsAndBanned has been disabled.");
    }
}
