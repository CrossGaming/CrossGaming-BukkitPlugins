package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdFly extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdFly()
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
			target.setAllowFlight(!target.getAllowFlight());
			if(p == target)
			{
				p.sendMessage(arl.getCol() + enabled(target) + " own fly mode.");
			}
			else
			{
				p.sendMessage(arl.getCol() + enabled(target) + " fly mode for player " + target.getName() + ".");
			}
			return true;
		}
		else
		{
			if (args.length == 0)
	        {
				sender.sendMessage(arl.getCol() + "You are not a player you can't fly.");
	      	   return true;
	        }
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				sender.sendMessage(arl.getCol() + "Nonexistant player.");
				return true;
			}
			target.setAllowFlight(!target.getAllowFlight());
			sender.sendMessage(arl.getCol() + enabled(target) + " fly mode for player " + target.getName() + ".");
			return true;
		}
	}
	private String enabled(Player target)
	{
		if(target.getAllowFlight())
		{
			return "Enabled";
		}
		return "Disabled";
	}
}