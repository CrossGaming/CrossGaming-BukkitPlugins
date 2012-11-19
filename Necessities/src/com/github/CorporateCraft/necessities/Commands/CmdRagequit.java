package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdRagequit
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.kickPlayer(Necessities.messages + "RAGEQUIT!");
			Bukkit.broadcastMessage(Necessities.messages + player.getName() + " RAGEQUIT the server!");
			return true;
        } 
		else
		{
			sender.sendMessage(Necessities.messages + "You cannot ragequit you would kill the server.");
			return true;
	    }
	}
}