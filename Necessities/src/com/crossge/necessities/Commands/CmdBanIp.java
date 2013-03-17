package com.crossge.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.necessities.ArrayLists;
import com.crossge.necessities.CCBot.*;

public class CmdBanIp extends Cmd
{
	CCBotWarn warns = new CCBotWarn();
	ArrayLists arl = new ArrayLists();
	public CmdBanIp()
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
				Bukkit.banIP(args[0]);
				warns.banIp(args[0], "Was ipbanned by " + p.getName() + ".");
				return true;
			}
			if(target.isOp())
			{
				p.sendMessage(arl.getCol() + "You may not ipban an op");
				return true;
			}
			String reason = "";
			if(args.length == 1)
			{
				reason = "Was ipbanned by " + p.getName() + ".";
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
			warns.banIp(target.getName(), reason);
			Bukkit.banIP(target.getAddress().getAddress().getHostAddress());
			target.kickPlayer(reason);
			return true;
		}
		else
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				Bukkit.banIP(args[0]);
				warns.banIp(args[0], "Was ipbanned by the Console.");
				return true;
			}
			String reason = "";
			if(args.length == 1)
			{
				reason = "Was ipbanned by the Console.";
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
			warns.banIp(target.getName(), reason);
			Bukkit.banIP(target.getAddress().getAddress().getHostAddress());
			target.kickPlayer(reason);
			return true;
	    }
	}
}