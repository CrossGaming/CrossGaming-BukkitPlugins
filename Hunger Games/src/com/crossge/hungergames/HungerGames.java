package com.crossge.hungergames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.crossge.hungergames.Commands.*;

public class HungerGames extends JavaPlugin
{
	Players pl = new Players();
	Game g = new Game();
	@Override
    public void onEnable()
	{	
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		this.saveDefaultConfig();
		Initialization init = new Initialization();
		init.initiateFiles();
		getLogger().info("Hunger Games brought to you by Cross GE has been enabled.");
		g.start();//comment to stop automation
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
    	pl.unhideSpec();
    	getLogger().info("Hunger Games disabled.");
    }
}