package com.crossge.hungergames.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdStats extends Cmd
{
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
						p.sendMessage(var.errorCol() + lang.translate("Error: Unkown player"));
						return false;
					}
					message = parseStats(message);
					p.sendMessage(var.defaultCol() + message);
				}
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not view your stats."));
		}
		else
		{
			if(args.length != 1)
				return false;
			String message = s.get(args[0]);
			if(message == null)
			{
				sender.sendMessage(var.errorCol() + lang.translate("Error: Unkown player"));
				return false;
			}
			message = parseStats(message);
			sender.sendMessage(var.defaultCol() + ChatColor.YELLOW + message);
		}
		return true;
	}
	private String parseStats(String stats)
	{//Name, Points, Wins, Kills, Deaths, Games
		String[] info = stats.split(" ");
		String statsNew = "";
		statsNew = info[0] + " " + lang.translate("has") + " " + info[1] + " " + lang.translate("points, and has won") + " " +
					info[2] + " " + lang.translate("games, making a total of") + " " + info[3] + " " + lang.translate("kills. They have also died" +
					" " + info[4] + " " + lang.translate("times, and have played a total of") + " " + info[5] + " " + lang.translate("games."));
		return statsNew;
	}
}