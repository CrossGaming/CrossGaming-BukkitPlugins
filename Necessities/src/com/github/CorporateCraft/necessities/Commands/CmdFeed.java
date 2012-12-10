package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdFeed extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdFeed()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length > 1)
        {
      	   return false;
        }
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			Player target;
			if (args.length == 0)
	        {
				target = p;
	        }
			else
			{
				target = sender.getServer().getPlayer(args[0]);
			}
			if(target == null)
			{
				target = p;
			}
			target.setFoodLevel(20);
			if(p == target)
			{
				p.sendMessage(arl.getCol() + "Satisfied your hunger.");
			}
			else
			{
				p.sendMessage(arl.getCol() + "Satisfied " + target.getName() + "'s hunger.");
			}
			return true;
		}
		else
		{
			if (args.length == 0)
	        {
				sender.sendMessage(arl.getCol() + "You are not a player you can't be fed.");
	      	   return true;
	        }
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				sender.sendMessage(arl.getCol() + "Nonexistant player.");
				return true;
			}
			target.setFoodLevel(20);
			sender.sendMessage(arl.getCol() + "Satisfied " + target.getName() + "'s hunger.");
			return true;
		}
	}
}