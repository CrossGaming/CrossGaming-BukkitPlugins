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
				{
					String message = s.get(p.getName());
					message = parseStats(message);
					p.sendMessage(var.defaultCol() + message);
				}
				else
				{
					String message = s.get(args[0]);
					if(message == null)
					{
						p.sendMessage(var.errorCol() + "Unkown player");
						return false;
					}
					message = parseStats(message);
					p.sendMessage(var.defaultCol() + message);
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
			String message = s.get(args[0]);
			if(message == null)
			{
				sender.sendMessage(var.errorCol() + "Unkown player");
				return false;
			}
			message = parseStats(message);
			sender.sendMessage(var.defaultCol() + message);
		}
		return true;
	}
	private String parseStats(String stats)
	{//Name, Points, Wins, Kills, Deaths, Games
		String[] info = stats.split(" ");
		String statsNew = "";
		statsNew = info[0] + " has " + info[1] + " points, and has won " + info[2] + " games, making a total of " + info[3] + " kills. They have also died "
					+ info[4] + " times, and have played a total of " + info[5] + " games.";
		return statsNew;
	}
}