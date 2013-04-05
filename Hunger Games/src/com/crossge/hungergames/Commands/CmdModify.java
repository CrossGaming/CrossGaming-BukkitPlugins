package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdModify extends Cmd
{
	public CmdModify()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length != 3)
			return false;
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.modify"))
			{
				String targetName = args[0];
				if(s.get(targetName) != null)
				{
					int temp = 0;
					try
					{
						temp = Integer.parseInt(args[2]);
					}
					catch(Exception e)
					{
						p.sendMessage(var.errorCol() + "Error: Must enter a number to modify by.");
						return false;
					}
					if(args[1].equalsIgnoreCase("deaths"))
					{
						s.addDeath(targetName, temp);
						p.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " deaths to " + targetName + ".");
					}
					else if(args[1].equalsIgnoreCase("games"))
					{
						s.addGame(targetName, temp);
						p.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " games to " + targetName + ".");
					}
					else if(args[1].equalsIgnoreCase("kills"))
					{
						s.addKill(targetName, temp);
						p.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " kills to " + targetName + ".");
					}
					else if(args[1].equalsIgnoreCase("points"))
					{
						s.addPoints(targetName, temp);
						p.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " points to " + targetName + ".");
					}
					else if(args[1].equalsIgnoreCase("wins"))
					{
						s.addWin(targetName, temp);
						p.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " wins to " + targetName + ".");
					}
					else
						p.sendMessage(var.errorCol() + "Error: Unknown stat section.");
				}
				else
					p.sendMessage(var.errorCol() + "Error: Player does not exist.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not modify a players stats for the Hunger Games.");
		}
		else
		{
			String targetName = args[0];
			if(s.get(targetName) != null)
			{
				int temp = 0;
				try
				{
					temp = Integer.parseInt(args[2]);
				}
				catch(Exception e)
				{
					sender.sendMessage(var.errorCol() + "Error: Must enter a number to modify by.");
					return false;
				}
				if(args[1].equalsIgnoreCase("deaths"))
				{
					s.addDeath(targetName, temp);
					sender.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " deaths to " + targetName + ".");
				}
				else if(args[1].equalsIgnoreCase("games"))
				{
					s.addGame(targetName, temp);
					sender.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " games to " + targetName + ".");
				}
				else if(args[1].equalsIgnoreCase("kills"))
				{
					s.addKill(targetName, temp);
					sender.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " kills to " + targetName + ".");
				}
				else if(args[1].equalsIgnoreCase("points"))
				{
					s.addPoints(targetName, temp);
					sender.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " points to " + targetName + ".");
				}
				else if(args[1].equalsIgnoreCase("wins"))
				{
					s.addWin(targetName, temp);
					sender.sendMessage(var.defaultCol() + "Added " + Integer.toString(temp) + " wins to " + targetName + ".");
				}
				else
					sender.sendMessage(var.errorCol() + "Error: Unknown stat section.");
			}
			else
				sender.sendMessage(var.errorCol() + "Error: Player does not exist.");
		}
		return true;
	}
}