package com.github.CorporateCraft.ragequit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Ragequit extends JavaPlugin
{
	@Override
    public void onEnable()
	{	
		getLogger().info("The ability to ragequit has been enabled.");
    }
 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ragequit"))
		{			
			if (sender instanceof Player)
			{
	           Player player = (Player) sender;
	           if(player.hasPermission("Ragequit.rage"))
	           {
	        	   player.kickPlayer("RAGEQUIT!");
	        	   Bukkit.broadcastMessage(player.getName() + " RAGEQUIT the server!");
	        	   return true;
	           }
	           else
	           {
	        	   player.sendMessage("No you can't ragequit");
	           }
	        } 
			else
			{
				sender.sendMessage("Command can only be used from ingame");
				return true;
		    }
		}
		return false; 
	}
	
    @Override
    public void onDisable()
    {
    	getLogger().info("The ability to ragequit has been disabled.");
    }
}
