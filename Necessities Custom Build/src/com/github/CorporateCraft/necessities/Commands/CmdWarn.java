package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.ArrayLists;
import com.github.CorporateCraft.necessities.CCBot.*;

public class CmdWarn extends Cmd
{
	CCBot warns = new CCBot();
	ArrayLists arl = new ArrayLists();
	public CmdWarn()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if(args.length < 2)
		{
			return false;
		}
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
				p.sendMessage(arl.getCol() + "You may not warn an op");
				return true;
			}
			String reason = "";
			for(int i = 1; i < args.length; i++)
			{
				reason += args[i] + " ";
			}
			reason = reason.trim();
			warns.warnP(target.getName(), reason, p.getName());	
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
			for(int i = 1; i < args.length; i++)
			{
				reason += args[i] + " ";
			}
			reason = reason.trim();
			warns.warnP(target.getName(), reason, "Console");
			return true;
	    }
	}
}