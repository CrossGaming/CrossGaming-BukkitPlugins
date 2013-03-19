package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdStats extends Cmd
{
	Variables var = new Variables();
	Stats s = new Stats();
	public CmdStats()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.stats"))
			{
				if(args.length == 0)
					p.sendMessage(var.defaultCol() + s.get(p.getName()));
				else
				{
					p.sendMessage(var.defaultCol() + s.get(args[0]));
				}
			}
			else
			{
				p.sendMessage(var.errorCol() + "Error you may not view your stats.");
			}
		}
		else
		{
			if(args.length != 1)
				return false;
			sender.sendMessage(var.defaultCol() + s.get(args[0]));
		}
		return true;
	}
}