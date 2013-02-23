package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdSlap extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdSlap()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length != 1)
        {
      	   return false;
        }
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				return false;
			}
			Location loc = new Location(target.getWorld(), target.getLocation().getBlockX(), 500, target.getLocation().getBlockZ());
			target.teleport(loc);
			Bukkit.broadcastMessage(arl.getCol() + target.getName() + " was slapped sky high by " + player.getName());
			return true;
		}
		else
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				return false;
			}
			Location loc = new Location(target.getWorld(), target.getLocation().getBlockX(), 500, target.getLocation().getBlockZ());
			target.teleport(loc);
			Bukkit.broadcastMessage(arl.getCol() + target.getName() + " was slapped sky high by the Console");
			return true;
		}
	}
}