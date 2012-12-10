package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.ArrayLists;
import com.github.CorporateCraft.necessities.CCBot.*;

public class CmdBanIp extends Cmd
{
	CCBotWarn Warns = new CCBotWarn();
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
				Warns.banIp(args[0], "Was ipbanned by " + p.getName() + ".");
				return true;
			}
			if(target.isOp())
			{
				p.sendMessage(arl.getCol() + "You may not ipban an op");
				return true;
			}
			String Reason = "";
			if(args.length == 1)
			{
				Reason = "Was ipbanned by " + p.getName() + ".";
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
			Warns.banIp(target.getName(), Reason);
			Bukkit.banIP(target.getAddress().getAddress().getHostAddress());
			target.kickPlayer(Reason);
			return true;
		}
		else
		{
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				Bukkit.banIP(args[0]);
				Warns.banIp(args[0], "Was ipbanned by the Console.");
				return true;
			}
			String Reason = "";
			if(args.length == 1)
			{
				Reason = "Was ipbanned by the Console.";
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
			Warns.banIp(target.getName(), Reason);
			Bukkit.banIP(target.getAddress().getAddress().getHostAddress());
			target.kickPlayer(Reason);
			return true;
	    }
	}
}