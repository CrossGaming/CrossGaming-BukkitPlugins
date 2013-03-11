package com.github.CrossGaming.ccebridge.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.cceconomy.ArrayLists;
import com.github.CorporateCraft.cceconomy.BalChecks;
import com.github.CorporateCraft.cceconomy.Formatter;
import com.github.CrossGaming.ccebridge.CmdPrices;
import com.github.CrossGaming.ccebridge.PlayerInfo;

public class CmdBuyCmd extends Cmd
{
	ArrayLists ccearl = new ArrayLists();
	BalChecks bal = new BalChecks();
	CmdPrices cmdp = new CmdPrices();
	Formatter form = new Formatter();
	PlayerInfo pInfo = new PlayerInfo();
	public CmdBuyCmd()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length != 1)
			return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String cmd = form.capFirst(args[0]);
			double cost = cmdp.getCost(cmd);
			double balan = Double.parseDouble(bal.bal(player.getName()));
			String rank = pInfo.curRank(player.getName()).toUpperCase();
			if(balan < cost)
			{
				player.sendMessage(ccearl.getMessages() + "Not enough money.");
				return true;
			}
			if(!cmdp.realCommand(cmd))
			{
				player.sendMessage(ccearl.getMessages() + "Unknown or unbuyable command.");
				return true;
			}
			if(!cmdp.canBuy(cmd, rank))
			{
				player.sendMessage(ccearl.getMessages() + "You do not have the rank required to buy this command.");
				return true;
			}
			Command com = player.getServer().getPluginCommand(cmd);
			if(com == null)
			{
				player.sendMessage(ccearl.getMessages() + "The command " + cmd + " is a nonexistant or built in command.");
				return true;
			}
			String permNode = com.getPermission();
			if(permNode == null || permNode.contains("mcmmo"))
				permNode = "essentials." + cmd.toLowerCase();
			if(pInfo.hasCmd(player.getName(), permNode))
			{
				player.sendMessage(ccearl.getMessages() + "Already have command");
				return true;
			}
			bal.removeMoney(player.getName(), cost);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + player.getName() + " " + permNode);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mansave");
			Bukkit.broadcastMessage(ccearl.getMessages() + player.getName() + " bought the command " + cmd.toLowerCase());
			return true;
		}
		else
		{
			sender.sendMessage(ccearl.getMessages() + "You cannot buy a command you are already have all commands.");
			return true;
		}
	}
}