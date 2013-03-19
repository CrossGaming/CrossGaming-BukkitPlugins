package com.crossge.hungergames.Commands;

import java.io.File;
import java.io.IOException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.crossge.hungergames.*;

public class CmdSetSpawn extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	Game g = new Game();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public CmdSetSpawn()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 1)
				return false;
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.setspawn"))
			{
				int number = 1;
				try
				{
					number = Integer.parseInt(args[0]);
				}
				catch(Exception e)
				{
					return false;
				}
				if(number > 25)
				{
					p.sendMessage(var.defaultCol() + "Max spawns are 25 with the 25th being the spectator spawn");
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
				p.sendMessage(var.defaultCol() + "Spawn set: " + Integer.toString(number) + " at " +
						Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY())+ ", "
						+ Integer.toString(p.getLocation().getBlockZ()));
				g.initMaps();
			}
			else
			{
				p.sendMessage(var.errorCol() + "Error you may not set the spawnpoints for Hunger Games.");
			}
		}
		else
		{
			sender.sendMessage(var.errorCol() + "You cannot setspawns for the hunger games because you are not an entity, please log in.");
		}
		return true;
	}
}