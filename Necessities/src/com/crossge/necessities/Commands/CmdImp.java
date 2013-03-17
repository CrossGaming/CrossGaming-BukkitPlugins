package com.crossge.necessities.Commands;

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
			return false;
		if (sender instanceof Player)
		{
			if(args.length > 1)
			{
				Player p = Bukkit.getPlayer(args[0]);
				if(p != null)
				{
					String message = "";
					for(String a : args)
					{
						message = message + " " + a;
					}
					message = ChatColor.translateAlternateColorCodes('&', message.replaceFirst(args[0], "").trim());
					p.chat(message);
					return true;
				}
			}
			String message = "";
			for(String a : args)
			{
				message = message + " " + a;
			}
			message = ChatColor.translateAlternateColorCodes('&', message.trim());
			Bukkit.broadcastMessage(message);
		}
		else
		{
			if(args.length > 1)
			{
				Player p = Bukkit.getPlayer(args[0]);
				if(p != null)
				{
					String message = "";
					for(String a : args)
					{
						message = message + " " + a;
					}
					message = ChatColor.translateAlternateColorCodes('&', message.replaceFirst(args[0], "").trim());
					p.chat(message);
					return true;
				}
			}
			String message = "";
			for(String a : args)
			{
				message = message + " " + a;
			}
			message = ChatColor.translateAlternateColorCodes('&', message.trim());
			Bukkit.broadcastMessage(message);
		}
		return true;
	}
}