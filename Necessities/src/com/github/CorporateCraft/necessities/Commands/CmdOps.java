package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdOps extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdOps()
	{

	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		Formatter form = new Formatter();
		if (sender instanceof Player)
		{
			if(args.length != 0)
			{
				return false;
			}
			Player player = (Player) sender;
			String line = form.readFileStr("ops.txt");
			if(line == null)
			{
				player.sendMessage(arl.GetCol() + "There are no operators.");
				return true;
			}
			player.sendMessage(arl.GetCol() + "The Operators are: " + line);
			return true;
        } 
		else
		{
			if(args.length != 0)
			{
				return false;
			}
			String line = form.readFileStr("ops.txt");
			if(line == null)
			{
				sender.sendMessage(arl.GetCol() + "There are no operators.");
				return true;
			}
			sender.sendMessage(arl.GetCol() + "The Operators are: " + line);
			return true;
	    }
	}
}