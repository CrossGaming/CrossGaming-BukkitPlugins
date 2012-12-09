package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;
import com.github.CorporateCraft.necessities.CCBot.*;

public class CmdBan extends Cmd
{
	CCBotWarn Warns = new CCBotWarn();
	ArrayLists arl = new ArrayLists();
	public CmdBan()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				if(Bukkit.getOfflinePlayer(args[0]).isOp())
				{
					p.sendMessage(arl.GetCol() + "You may not ipban an op");
					return true;
				}
				Bukkit.getOfflinePlayer(args[0]).setBanned(true);
				Warns.Ban(args[0], "Was banned by " + p.getName() + ".");
				return true;
			}
			if(target.isOp())
			{
				p.sendMessage(arl.GetCol() + "You may not ban an op");
				return true;
			}
			String Reason = "";
			if(args.length == 1)
			{
				Reason = "Was banned by " + p.getName() + ".";
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
			Warns.Ban(target.getName(), Reason);
			target.kickPlayer(Reason);
			Bukkit.getOfflinePlayer(args[0]).setBanned(true);
			return true;
		}
		else
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				Bukkit.getOfflinePlayer(args[0]).setBanned(true);
				Warns.Ban(args[0], "Was banned by the Console.");
				return true;
			}
			String Reason = "";
			if(args.length == 1)
			{
				Reason = "Was banned by the Console.";
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
			Warns.Ban(target.getName(), Reason);
			target.kickPlayer(Reason);
			Bukkit.getOfflinePlayer(args[0]).setBanned(true);
			return true;
	    }
	}
}