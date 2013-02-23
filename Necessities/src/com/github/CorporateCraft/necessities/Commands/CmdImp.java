package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdImp extends Cmd
{
	public CmdImp()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length == 0)
		{
			return false;
		}
		String message = "";
		for(String a : args)
		{
			message = message + " " + a;
		}
		message = message.trim();
		message = ChatColor.translateAlternateColorCodes('&', message);
		if (sender instanceof Player)
		{
			Bukkit.broadcastMessage(message);
		}
		else
		{
			Bukkit.broadcastMessage(message);
		}
		return true;
	}
}