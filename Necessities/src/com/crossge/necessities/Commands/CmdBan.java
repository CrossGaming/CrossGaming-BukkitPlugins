package com.crossge.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.necessities.ArrayLists;
import com.crossge.necessities.CCBot.*;

public class CmdBan extends Cmd
{
	CCBotWarn warns = new CCBotWarn();
	ArrayLists arl = new ArrayLists();
	public CmdBan()
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
				if(Bukkit.getOfflinePlayer(args[0]).isOp())
				{
					p.sendMessage(arl.getCol() + "You may not ipban an op");
					return true;
				}
				Bukkit.getOfflinePlayer(args[0]).setBanned(true);
				warns.ban(args[0], "Was banned by " + p.getName() + ".");
				return true;
			}
			if(target.isOp())
			{
				p.sendMessage(arl.getCol() + "You may not ban an op");
				return true;
			}
			String reason = "";
			if(args.length == 1)
			{
				reason = "Was banned by " + p.getName() + ".";
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
			warns.ban(target.getName(), reason);
			target.kickPlayer(reason);
			Bukkit.getOfflinePlayer(args[0]).setBanned(true);
			return true;
		}
		else
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				Bukkit.getOfflinePlayer(args[0]).setBanned(true);
				warns.ban(args[0], "Was banned by the Console.");
				return true;
			}
			String reason = "";
			if(args.length == 1)
			{
				reason = "Was banned by the Console.";
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
			warns.ban(target.getName(), reason);
			target.kickPlayer(reason);
			Bukkit.getOfflinePlayer(args[0]).setBanned(true);
			return true;
	    }
	}
}