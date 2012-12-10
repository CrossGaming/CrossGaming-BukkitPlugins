package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;
import com.github.CorporateCraft.necessities.CCBot.*;

public class CmdKick extends Cmd
{
	CCBotWarn Warns = new CCBotWarn();
	ArrayLists arl = new ArrayLists();
	public CmdKick()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				p.sendMessage(arl.getCol() + "Nonexistant player");
				return true;
			}
			if(target.isOp())
			{
				p.sendMessage(arl.getCol() + "You may not kick an op");
				return true;
			}
			String Reason = "";
			if(args.length == 1)
			{
				Reason = "Was kicked by " + p.getName() + ".";
				Reason = Reason.trim();
			}
			else
			{
				for(int i = 1; i < args.length; i++)
				{
					Reason += args[i] + " ";
				}
				Reason = Reason.trim();
			}
			Warns.kick(target.getName(), Reason);
			target.kickPlayer(Reason);
			return true;
		}
		else
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				sender.sendMessage(arl.getCol() + "Nonexistant player");
				return true;
			}
			String Reason = "";
			if(args.length == 1)
			{
				Reason = "Was kicked by the Console.";
				Reason = Reason.trim();
			}
			else
			{
				for(int i = 1; i < args.length; i++)
				{
					Reason += args[i] + " ";
				}
				Reason = Reason.trim();
			}
			Warns.kick(target.getName(), Reason);
			target.kickPlayer(Reason);
			return true;
	    }
	}
}