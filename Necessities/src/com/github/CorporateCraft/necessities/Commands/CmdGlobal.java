package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.CCBot.CCBotIRC;

public class CmdGlobal extends Cmd
{
	public CmdGlobal()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length == 0)
			return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String message = "";
			for(int i = 0; i < args.length; i++)
			{
				message += args[i] + " ";
			}
			message = message.trim();
			CCBotIRC.bot.sendGlobal(player.getName(), message);
			return true;
		}
		else
		{
			String message = "";
			for(int i = 0; i < args.length; i++)
			{
				message += args[i] + " ";
			}
			message = message.trim();
			CCBotIRC.bot.sendGlobal("Console" + "[" + Bukkit.getPluginManager().getPlugin("Necessities").getConfig().getString("CCBot.cState") + "]", message);
			return true;
		}
	}
}