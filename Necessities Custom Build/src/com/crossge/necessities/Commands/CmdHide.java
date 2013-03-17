package com.crossge.necessities.Commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.necessities.ArrayLists;

public class CmdHide extends Cmd
{
	ArrayLists arl = new ArrayLists();
	private static ArrayList<String> hidden = new ArrayList<String>();
	public CmdHide()
	{
		
	}
	public void removeP(String name)
	{
		if(hidden.contains(name))
			hidden.remove(name);
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length != 0)
      	   return false;
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(hidden.contains(p.getName()))
			{
				Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " joined the game.");
				hidden.remove(p.getName());
			}
			else
			{
				Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " left the game.");
				hidden.add(p.getName());
			}
			p.performCommand("vanish");
			return true;
		}
		else
		{
			sender.sendMessage(arl.getCol() + "The console cannot hide.");
			return true;
		}
	}
}