package com.github.CorporateCraft.killwithmessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KillWithMessage extends JavaPlugin
{
	@Override
    public void onEnable()
	{	
		getLogger().info("KillWithMessage has been enabled.");
    }
 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("killwithmessage"))
		{			
			if (sender instanceof Player)
			{
	           Player player = (Player) sender;
	           if(player.hasPermission("KillWithMessage.kill"))
	           {
	        	   Player target = sender.getServer().getPlayer(args[0]);
	        	   int i = 1;
	        	   String DeathMessage = target.getName() + " ";
	        	   while (i < args.length)
	        	   {
	        		   DeathMessage += args[i] + " ";
	        		   i++;
	        	   }        	   
	        	   DeathMessage = DeathMessage.trim();
	        	   target.setHealth(0);
	        	   Bukkit.broadcastMessage(DeathMessage);
	        	   return true;
	           }
	           else
	           {
	        	   player.sendMessage("No you cant kill people, I shal not allow it.");
	           }
	        } 
			else
			{
				Player target = sender.getServer().getPlayer(args[0]);
				String DeathMessage = args.toString().replace(args[0], ""); 
	        	Bukkit.broadcastMessage(ChatColor.WHITE + DeathMessage);
	        	target.setHealth(0);
	        	return true;
		    }
		}
		return false; 
	}
	
    @Override
    public void onDisable()
    {
    	getLogger().info("KillWithMessage has been disabled.");
    }
}
