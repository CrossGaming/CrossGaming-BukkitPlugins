package com.crossge.hungergames.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdWorldSpawn extends Cmd
{
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
   	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 0)
				return false;
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.setworldspawn"))
			{
				String pathWorld = "worldS.world";
				String pathx = "worldS.x";
				String pathy = "worldS.y";
				String pathz = "worldS.z";
				customConfig.set(pathWorld, p.getWorld().getName());
				customConfig.set(pathx, p.getLocation().getBlockX());
				customConfig.set(pathy, p.getLocation().getBlockY());
				customConfig.set(pathz, p.getLocation().getBlockZ());
				try
			   	{
					customConfig.save(customConfigFile);
				}
			   	catch (IOException e) {}
				p.sendMessage(var.defaultCol() + lang.translate("World spawn set at") + ": " +
						Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY())+ ", "
						+ Integer.toString(p.getLocation().getBlockZ()));
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not set the world spawn for Hunger Games."));
		}
		else
			sender.sendMessage(var.errorCol() + lang.translate("Error: You cannot set spawns for the hunger games because you are not an entity, please log in."));
		return true;
	}
}