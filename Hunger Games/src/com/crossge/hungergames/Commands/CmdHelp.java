package com.crossge.hungergames.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHelp extends Cmd
{
	public boolean commandUse(CommandSender sender, String[] args)
	{
		int maxPages = 5;
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.help"))
			{
				int page = 1;
				if(args.length > 0)
				{
					try
					{
						page = Integer.parseInt(args[0]);
					}
					catch(Exception e){}
				}
				if(page == 0)
					page = 1;
				if(page > maxPages)
				{
					p.sendMessage(var.errorCol() + lang.translate("Error: Please enter a page inbetween ") + " 1 " +
								lang.translate("and") + " " + Integer.toString(maxPages));
					return false;
				}
				p.sendMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("Hunger Games Help Page") + " " + Integer.toString(page) + " " +
								lang.translate("of") + " " + Integer.toString(maxPages));
				if(page == 1)
					page1(sender);
				else if(page == 2)
					page2(sender);
				else if(page == 3)
					page3(sender);
				else if(page == 4)
					page4(sender);
				else if(page == 5)
					page5(sender);
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not view the help for Hunger Games."));
		}
		else
		{
			int page = 1;
			if(args.length > 0)
			{
				try
				{
					page = Integer.parseInt(args[0]);
				}
				catch(Exception e){}
			}
			if(page == 0)
				page = 1;
			if(page > maxPages)
			{
				sender.sendMessage(var.errorCol() + lang.translate("Error: Please enter a page inbetween") + " 1 " +
									lang.translate("and") + " " + Integer.toString(maxPages));
				return false;
			}
			sender.sendMessage(var.defaultCol() + ChatColor.GREEN + lang.translate("Hunger Games Help Page") + " " +
								Integer.toString(page) + lang.translate("of") + " " + Integer.toString(maxPages));
			if(page == 1)
				page1(sender);
			else if(page == 2)
				page2(sender);
			else if(page == 3)
				page3(sender);
			else if(page == 4)
				page4(sender);
			else if(page == 5)
				page5(sender);
		}
		return true;
	}
	private void page1(CommandSender sender)
	{
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg credits ~ " + lang.translate("Shows the credits of the team who brought you this plugin."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg help [page] ~ " + lang.translate("Shows the help page [page] for hunger games."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg join ~ " + lang.translate("Gets in line for next game."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg spectate [player] ~ " + lang.translate("Spectates the current game or [player]."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg leave ~ " + lang.translate("Leaves the current game or if in line, the line."));
	}
	private void page2(CommandSender sender)
	{
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg setspawn [number] ~ " + lang.translate("Sets the [number] spawnpoint" +
							"(max set in config.yml bye maxPlayers) 0 is the specator spawnpoint."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg info ~ " + lang.translate("Views info about the current round."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg vote [map number] ~ " + lang.translate("Votes for map [map number]."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg stats [player] ~ " + lang.translate("Shows the stats of [player]."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg forcestart ~ " + lang.translate("Forces the game to start."));
	}
	private void page3(CommandSender sender)
	{
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg sponsor ~ " + lang.translate("Sponsors yourself."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg kit [kitname] ~ " + lang.translate("Chooses a kit to use (disabled by default)."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg setkitprice [kitname] [price] ~ " + lang.translate("Sets the price for a kit."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg buykit [kitname] ~ " + lang.translate("Will buy the kit if you have enough points."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg kitprices ~ " + lang.translate("View the prices for the buyable kits."));
	}
	private void page4(CommandSender sender)
	{
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg convert [mysql:yml] ~ " + lang.translate("Converts stats from one database to specified one."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg setworldspawn ~ " + lang.translate("Sets the spawn players will go to when they die or game ends."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg modify [player] [stats class] [amount] ~ " + lang.translate("Modifies [player]'s [stats class] by [amount]."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg leaderboard [stats class] [page] ~ " + lang.translate("Leaderboard of each stat class."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + lang.translate("Stat Classes:") + " deaths, games, kills, points, wins.");
	}
	private void page5(CommandSender sender)
	{
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg setcorner [number] ~ " + lang.translate("Sets the corners for the hunger games either 1 or 2."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg forcestop ~ " + lang.translate("Forces the current game to stop."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + "/hg setchests [world] ~ " + lang.translate("Locates the chest locations for given world."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + lang.translate("Coming soon."));
		sender.sendMessage(var.defaultCol() + ChatColor.AQUA + lang.translate("Coming soon."));
	}
}
