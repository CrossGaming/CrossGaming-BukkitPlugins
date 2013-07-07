package com.crossge.hungergames.Commands;

import java.io.File;
import java.io.IOException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdSetSpawn extends Cmd
{
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
   	private File customConfFile = new File("plugins/Hunger Games", "config.yml");
   	private YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 1)
				return false;
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.setspawn"))
			{
				int number = 0;
				try
				{
					number = Integer.parseInt(args[0]);
				}
				catch(Exception e)
				{
					return false;
				}
				int maxSpawns = customConf.getInt("maxPlayers");
				if(number > maxSpawns || number < 0)
				{
					p.sendMessage(var.errorCol() + lang.translate("Error: Max spawns are") +
									" " + Integer.toString(maxSpawns) + " " + lang.translate("with the 0 being the spectator spawn"));
					return false;
				}
				String pathx = p.getWorld().getName() + ".s" + Integer.toString(number) + ".x";
				String pathy = p.getWorld().getName() + ".s" + Integer.toString(number) + ".y";
				String pathz = p.getWorld().getName() + ".s" + Integer.toString(number) + ".z";
				customConfig.set(pathx, p.getLocation().getBlockX());
				customConfig.set(pathy, p.getLocation().getBlockY());
				customConfig.set(pathz, p.getLocation().getBlockZ());
				try
			   	{
					customConfig.save(customConfigFile);
				}
			   	catch (IOException e) {}
				p.sendMessage(var.defaultCol() + lang.translate("Spawn set") + ": " + Integer.toString(number) + " " + lang.translate("at") + " " +
						Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY())+ ", "
						+ Integer.toString(p.getLocation().getBlockZ()));
				g.initMaps();
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not set the spawnpoints for Hunger Games."));
		}
		else
			sender.sendMessage(var.errorCol() + lang.translate("Error: You cannot set spawns for the hunger games because you are not an entity, please log in."));
		return true;
	}
}