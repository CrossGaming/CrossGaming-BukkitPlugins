package com.crossge.hungergames.Commands;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.crossge.hungergames.Kits;
import com.crossge.hungergames.Players;
import com.crossge.hungergames.Variables;

public class CmdKit extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	Kits kit = new Kits();
	private File customConfigFile = new File("plugins/Hunger Games", "config.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public CmdKit()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.kit"))
			{
				if(customConfig.getBoolean("useKits"))
				{
					if(pl.gameGoing())
					{
						if(pl.isAlive(p.getName()))
						{
							if(args.length == 0)
								kit.listKits(p);
							else
							{
								//add check if already chose kit
								kit.giveKit(p, args[0]);
							}
						}
						else
							p.sendMessage(var.errorCol() + "Error: You are not in the game.");
					}
					else
						p.sendMessage(var.errorCol() + "Error: Game not going.");
				}
				else
					p.sendMessage(var.errorCol() + "Error: This server has kits disabled.");
			}
			else
				p.sendMessage(var.errorCol() + "Error: you may not use kits for the Hunger Games.");
		}
		else
			sender.sendMessage(var.errorCol() + "Error: You may not use any kits.");
		return true;
	}
}