package com.github.CorporateCraft.necessities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
//import com.github.CorporateCraft.necessities.CCBot.*;
import com.github.CorporateCraft.necessities.CCBot.CCBotIRC;
import com.github.CorporateCraft.necessities.Commands.*;

public class Necessities extends JavaPlugin
{	
	public Necessities()
	{
		
	}
	CCBotIRC irc = new CCBotIRC();
	@Override
    public void onEnable()
	{	
		this.saveDefaultConfig();
		irc.joinIRC();
		getLogger().info("The necessities your server has been needing are enabled.");
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		Initialization init = new Initialization();
		init.initiateFiles();
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Cmd com = new Cmd();
		if(cmd.getName().equalsIgnoreCase("ops"))
			com = new CmdOps();
		else if(cmd.getName().equalsIgnoreCase("slap"))
			com = new CmdSlap();
		else if(cmd.getName().equalsIgnoreCase("warn"))
			com = new CmdWarn();
		else if(cmd.getName().equalsIgnoreCase("enchant"))
			com = new CmdEnchant();
		else if(cmd.getName().equalsIgnoreCase("permissions"))
			com = new CmdPermissions();
		else if(cmd.getName().equalsIgnoreCase("ragequit"))
			com = new CmdRagequit();
		else if(cmd.getName().equalsIgnoreCase("global"))
			com = new CmdGlobal();
		else if(cmd.getName().equalsIgnoreCase("potion"))
			com = new CmdPotion();
		else if(cmd.getName().equalsIgnoreCase("imp"))
			com = new CmdImp();
		else if(cmd.getName().equalsIgnoreCase("pants"))
			com = new CmdPants();
		else if(cmd.getName().equalsIgnoreCase("boots"))
			com = new CmdBoots();
		else if(cmd.getName().equalsIgnoreCase("chest"))
			com = new CmdChest();
		else if(cmd.getName().equalsIgnoreCase("hide"))
			com = new CmdHide();
		return com.commandUse(sender, args);
	}	
    @Override
    public void onDisable()
    {
    	CCBotIRC.bot.quitServer("Server reloading.");
    	CCBotIRC.irc.quitServer("Server reloading.");
    	getLogger().info("The necessities your server needs are now missing.");
    }
}