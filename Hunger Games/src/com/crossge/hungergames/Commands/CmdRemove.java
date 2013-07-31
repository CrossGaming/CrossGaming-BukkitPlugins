package com.crossge.hungergames.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdRemove extends Cmd
{
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
   	private File customConfigFileChest = new File("plugins/Hunger Games", "chestlocs.yml");
   	private YamlConfiguration customConfigChest = YamlConfiguration.loadConfiguration(customConfigFileChest);
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 2)
				return false;
			Player p = (Player) sender;
			if(args[0].equalsIgnoreCase("arena"))
			{
				if(p.hasPermission("HungerGames.removeArena"))
				{
					customConfig.set(args[1], null);
					customConfigChest.set(args[1], null);
					try
				   	{
						customConfig.save(customConfigFile);
					}
				   	catch (IOException e) {}
					p.sendMessage(var.defaultCol() + lang.translate("Arena removed: ") + " " + args[1]);
					g.initMaps();
				}
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not remove arenas from the Hunger Games."));
		}
		else
		{
			if(args[0].equalsIgnoreCase("arena"))
			{
				customConfig.set(args[1], null);
				customConfigChest.set(args[1], null);
				try
				  	{
					customConfig.save(customConfigFile);
				}
				  	catch (IOException e) {}
				sender.sendMessage(var.defaultCol() + lang.translate("Arena removed: ") + " " + args[1]);
				g.initMaps();
			}
		}
		return true;
	}
}