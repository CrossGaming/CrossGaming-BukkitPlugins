package com.crossge.hungergames.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.crossge.hungergames.Game;
import com.crossge.hungergames.Players;
import com.crossge.hungergames.Variables;

public class CmdSetCorner extends Cmd
{
	Variables var = new Variables();
	Players pl = new Players();
	Game g = new Game();
	private File customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public CmdSetCorner()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 1)
				return false;
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.setcorner"))
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
				if(number > 2)
				{
					p.sendMessage(var.errorCol() + "Error: Only 2 corners are needed.");
					return false;
				}
				String pathx = p.getWorld().getName() + ".corner" + Integer.toString(number) + ".x";
				String pathy = p.getWorld().getName() + ".corner" + Integer.toString(number) + ".y";
				String pathz = p.getWorld().getName() + ".corner" + Integer.toString(number) + ".z";
				customConfig.set(pathx, p.getLocation().getBlockX());
				customConfig.set(pathy, p.getLocation().getBlockY());
				customConfig.set(pathz, p.getLocation().getBlockZ());
				try
			   	{
					customConfig.save(customConfigFile);
				}
			   	catch (IOException e) {}
				p.sendMessage(var.defaultCol() + "Corner set: " + Integer.toString(number) + " at " +
						Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY())+ ", "
						+ Integer.toString(p.getLocation().getBlockZ()));
				g.initMaps();
			}
			else
				p.sendMessage(var.errorCol() + "Error: You may not set the corners for Hunger Games.");
		}
		else
			sender.sendMessage(var.errorCol() + "Error: You cannot set corners for the hunger games because you are not an entity, please log in.");
		return true;
	}
}