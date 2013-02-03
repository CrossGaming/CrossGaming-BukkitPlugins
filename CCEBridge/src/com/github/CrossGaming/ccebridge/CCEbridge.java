package com.github.CrossGaming.ccebridge;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CrossGaming.ccebridge.Commands.*;

public class CCEbridge extends JavaPlugin
{	
	@Override
    public void onEnable()
	{	
		getLogger().info("The bridge between CCEconomy and Essentials Group Manager has been enabled.");
		Initialization init = new Initialization();
		init.initiateFiles();
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Cmd com = new Cmd();
		if(cmd.getName().equalsIgnoreCase("rankprices"))
		{			
			com = new CmdRankPrices();
		}
		else if(cmd.getName().equalsIgnoreCase("setrankprice"))
		{
			com = new CmdSetRankPrice();
		}
		else if(cmd.getName().equalsIgnoreCase("buyrank"))
		{
			com = new CmdBuyRank();
		}
		else if(cmd.getName().equalsIgnoreCase("commandprices"))
		{			
			com = new CmdCmdPrices();
		}
		else if(cmd.getName().equalsIgnoreCase("setcommandprice"))
		{
			com = new CmdSetCmdPrice();
		}
		else if(cmd.getName().equalsIgnoreCase("buycommand"))
		{
			com = new CmdBuyCmd();
		}
		return com.commandUse(sender, args); 
	}	
	
    @Override
    public void onDisable()
    {
    	getLogger().info("Bridge disabled.");
    }
}