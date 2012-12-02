package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdRagequit extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdRagequit()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 0)
			{
				return false;
			}
			Player player = (Player) sender;
			player.kickPlayer(arl.messages + "RAGEQUIT!");
			Bukkit.broadcastMessage(arl.messages + player.getName() + " RAGEQUIT the server!");
			return true;
        } 
		else
		{
			sender.sendMessage(arl.messages + "You cannot ragequit you would kill the server.");
			return true;
	    }
	}
}