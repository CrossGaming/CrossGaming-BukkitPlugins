package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdWarn extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdWarn()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 2)
			{
				return false;
			}
			
			return true;
		}
		else
		{
			if(args.length != 2)
			{
				return false;
			}
			
	        return true;
	    }
	}
}