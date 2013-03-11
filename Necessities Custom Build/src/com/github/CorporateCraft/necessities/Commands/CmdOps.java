package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdOps extends Cmd
{
	ArrayLists arl = new ArrayLists();
	Formatter form = new Formatter();
	public CmdOps()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length != 0)
			return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String line = form.readFileStr("ops.txt");
			if(line == null)
			{
				player.sendMessage(arl.getCol() + "There are no operators.");
				return true;
			}
			player.sendMessage(arl.getCol() + "The Operators are: " + line);
			return true;
        } 
		else
		{
			String line = form.readFileStr("ops.txt");
			if(line == null)
			{
				sender.sendMessage(arl.getCol() + "There are no operators.");
				return true;
			}
			sender.sendMessage(arl.getCol() + "The Operators are: " + line);
			return true;
	    }
	}
}