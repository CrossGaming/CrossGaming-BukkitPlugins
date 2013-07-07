package com.crossge.hungergames.Commands;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdKit extends Cmd
{
	private File customConfigFile = new File("plugins/Hunger Games", "config.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

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
								if(!kit.chose(p.getName()))
								{
									if(kit.exists(args[0]))
									{
										kit.giveKit(p, args[0]);
										p.sendMessage(var.defaultCol() + lang.translate("You chose the kit") + " " + args[0] + ".");
									}
									else
										p.sendMessage(var.errorCol() + lang.translate("Error: That kit does not exist."));	
								}
								else
									p.sendMessage(var.errorCol() + lang.translate("Error: Already chose a kit."));
							}
						}
						else
							p.sendMessage(var.errorCol() + lang.translate("Error: You are not in the game."));
					}
					else
						p.sendMessage(var.errorCol() + lang.translate("Error: Game not going."));
				}
				else
					p.sendMessage(var.errorCol() + lang.translate("Error: This server has kits disabled."));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: you may not use kits for the Hunger Games."));
		}
		else
			sender.sendMessage(var.errorCol() + lang.translate("Error: You may not use any kits."));
		return true;
	}
}