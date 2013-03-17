package com.crossge.cceconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.cceconomy.*;

public class CmdTDeny extends Cmd
{
	ArrayLists arl = new ArrayLists();
	Trade tr = new Trade();
	public CmdTDeny()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 1)
				return false;
			Player player = (Player) sender;
			Player target = sender.getServer().getPlayer(args[0]);
			String pname = player.getName();
			String offerpname = target.getName();
			if(tr.hasTrade(pname, offerpname))
			{
				player.sendMessage(arl.getMessages() + "You have denied the trade from " + offerpname);
				target.sendMessage(arl.getMessages() + "Your trade to " + pname + " has been denied");
				tr.denyTrade(pname, offerpname);
				return true;
			}
			else
			{
				player.sendMessage(arl.getMessages() + "You do not have a trade offer from " + offerpname);
				return true;
			}
		}
		else
		{
			sender.sendMessage(arl.getMessages() + "You don't have an inventory. Please log in to trade.");
			return true;
		}
	}
}