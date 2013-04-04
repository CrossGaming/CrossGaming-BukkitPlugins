package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.Variables;

public class CmdLeaderboards extends Cmd
{
	Variables var = new Variables();
	public CmdLeaderboards()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.leaderboards"))
			{
					
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not view the leaderboards for the Hunger Games.");
		}
		else
		{
			
		}
		return true;
	}
}