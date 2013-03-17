package com.crossge.cceconomy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.crossge.cceconomy.Commands.*;

public class CCEconomy extends JavaPlugin
{	
	@Override
    public void onEnable()
	{	
		getLogger().info("CCEconomy has been enabled. You now have an advanced economy system.");
		getServer().getPluginManager().registerEvents(new LoginListener(), this);
		Initialization init = new Initialization();
		init.initiateFiles();
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Cmd com = new Cmd();
		if(cmd.getName().equalsIgnoreCase("bal"))
			com = new CmdBalance();
		else if(cmd.getName().equalsIgnoreCase("baltop"))		
			com = new CmdBaltop();
		else if(cmd.getName().equalsIgnoreCase("pricelist"))		
			com = new CmdPriceList();
		else if(cmd.getName().equalsIgnoreCase("pay"))		
			com = new CmdPay();
		else if(cmd.getName().equalsIgnoreCase("cce"))
			com = new CmdCCE();
		else if(cmd.getName().equalsIgnoreCase("price"))
			com = new CmdPrice();
		else if(cmd.getName().equalsIgnoreCase("setprice"))
			com = new CmdSetPrice();
		else if(cmd.getName().equalsIgnoreCase("buy"))
			com = new CmdBuy();
		else if(cmd.getName().equalsIgnoreCase("sell"))
			com = new CmdSell();
		else if(cmd.getName().equalsIgnoreCase("taccept"))
			com = new CmdTAccept();
		else if(cmd.getName().equalsIgnoreCase("tdeny"))
			com = new CmdTDeny();
		else if(cmd.getName().equalsIgnoreCase("trade"))
			com = new CmdTrade();
		else if(cmd.getName().equalsIgnoreCase("tradeitems"))
			com = new CmdTradeItems();
		else if(cmd.getName().equalsIgnoreCase("players"))
			com = new CmdPlayers();
		return com.commandUse(sender, args); 
	}	
    @Override
    public void onDisable()
    {
    	getLogger().info("CCEconomy has been disabled.");
    }
}