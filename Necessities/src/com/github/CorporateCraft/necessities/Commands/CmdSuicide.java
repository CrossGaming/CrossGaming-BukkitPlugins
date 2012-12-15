package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdSuicide extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdSuicide()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if(args.length != 0)
			{
				return false;
			}
			String deathMessage = player.getName() + " committed suicide";
			deathMessage = deathMessage.trim();
			player.setHealth(0);
			Bukkit.broadcastMessage(deathMessage);
			return true;
        } 
		else
		{
			sender.sendMessage(arl.getCol() + "You are not a player you can not commit suicide");
        	return true;
	    }
	}
}