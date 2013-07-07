package com.crossge.hungergames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.crossge.hungergames.Commands.*;

public class HungerGames extends JavaPlugin
{
	ChestRandomizer cr = new ChestRandomizer();
	Language lang = new Language();
	Players pl = new Players();
	Game g = new Game();
	@Override
    public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Initialization init = new Initialization();
		init.initiateFiles();
		try
		{
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    getLogger().info(lang.translate("Metrics enabled."));
		}
		catch (Exception e){}
		g.start();
		getLogger().info(lang.translate("Hunger Games brought to you by") + " Cross GE " + lang.translate("has been enabled."));
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Cmd com = new Cmd();
		if(cmd.getName().equalsIgnoreCase("hungergames"))		
			com = new CmdHungerGames();
		return com.commandUse(sender, args); 
	}
	
    @Override
    public void onDisable()
    {
    	pl.endTimer();
    	pl.sendToWSpawn();
    	pl.unhideSpec();
    	cr.emptyChests();
    	getLogger().info(lang.translate("Hunger Games disabled."));
    }
}