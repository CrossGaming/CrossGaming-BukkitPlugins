package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.*;

public class CmdBanned extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdBanned()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		Formatter form = new Formatter();
		if (sender instanceof Player)
		{
			if(args.length != 0)
			{
				return false;
			}
			Player player = (Player) sender;
			String line = form.readFileStr("banned-players");
			if(line == null)
			{
				player.sendMessage(arl.getCol() + "There are no banned players.");
				return true;
			}
			player.sendMessage(arl.getCol() + "Banned Players: " + line);
			return true;
		}
		else
		{
			if(args.length != 0)
			{
				return false;
			}
			String line = form.readFileStr("banned-players");
			if(line == null)
			{
				sender.sendMessage(arl.getCol() + "There are no banned players.");
				return true;
			}
			sender.sendMessage(arl.getCol() + "Banned Players: " + line);
	        return true;
	    }
	}
}