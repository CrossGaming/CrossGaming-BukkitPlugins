package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdSetSpawn extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	public CmdSetSpawn()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.setspawn"))
			{//temporarily starts game logic
				pl.gameStart();
				p.sendMessage("Coming soon.");
			}
			else
			{
				p.sendMessage(var.errorCol() + "Error you may not set the spawnpoints for Hunger Games.");
			}
		}
		else
		{
			sender.sendMessage("You cannot setspawns for the hunger games because you are not an entity, please log in.");
		}
		return true;
	}
}