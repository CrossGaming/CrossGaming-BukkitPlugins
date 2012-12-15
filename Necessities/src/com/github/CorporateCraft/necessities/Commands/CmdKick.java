package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;
import com.github.CorporateCraft.necessities.CCBot.*;

public class CmdKick extends Cmd
{
	CCBotWarn warns = new CCBotWarn();
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
			String reason = "";
			if(args.length == 1)
			{
				reason = "Was kicked by " + p.getName() + ".";
				reason = reason.trim();
			}
			else
			{
				for(int i = 1; i < args.length; i++)
				{
					reason += args[i] + " ";
				}
				reason = reason.trim();
			}
			warns.kick(target.getName(), reason);
			target.kickPlayer(reason);
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
			String reason = "";
			if(args.length == 1)
			{
				reason = "Was kicked by the Console.";
				reason = reason.trim();
			}
			else
			{
				for(int i = 1; i < args.length; i++)
				{
					reason += args[i] + " ";
				}
				reason = reason.trim();
			}
			warns.kick(target.getName(), reason);
			target.kickPlayer(reason);
			return true;
	    }
	}
}