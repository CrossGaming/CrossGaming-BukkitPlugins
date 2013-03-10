package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;
import com.github.CorporateCraft.necessities.CCBot.CCBotLog;

public class CmdRagequit extends Cmd
{
	ArrayLists arl = new ArrayLists();
	CCBotLog log = new CCBotLog();
	public CmdRagequit()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 0)
			{
				return false;
			}
			Player player = (Player) sender;
			player.kickPlayer(arl.getCol() + "RAGEQUIT!");
			log.log(player.getName() + " RAGEQUIT the server!");
			Bukkit.broadcastMessage(arl.getCol() + player.getName() + " RAGEQUIT the server!");
			return true;
        } 
		else
		{
			sender.sendMessage(arl.getCol() + "You cannot ragequit you would kill the server.");
			return true;
	    }
	}
}