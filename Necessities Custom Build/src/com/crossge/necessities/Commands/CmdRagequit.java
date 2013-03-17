package com.crossge.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.necessities.*;
import com.crossge.necessities.CCBot.CCBotLog;

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
				return false;
			Player player = (Player) sender;
			player.kickPlayer(ChatColor.DARK_RED + "RAGEQUIT!");
			log.log(player.getName() + " RAGEQUIT the server!");
			Bukkit.broadcastMessage(arl.getCol() + player.getName() + ChatColor.DARK_RED + " RAGEQUIT the server!");
			return true;
        } 
		else
		{
			sender.sendMessage(arl.getCol() + "You cannot ragequit you would kill the server.");
			return true;
	    }
	}
}