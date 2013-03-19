package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.Variables;

public class CmdStats extends Cmd
{
	Variables var = new Variables();
	public CmdStats()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.stats"))
			{
				p.sendMessage("Coming soon.");
			}
			else
			{
				p.sendMessage(var.errorCol() + "Error you may not view your stats.");
			}
		}
		else
		{
			sender.sendMessage("Coming soon.");	
		}
		return true;
	}
}