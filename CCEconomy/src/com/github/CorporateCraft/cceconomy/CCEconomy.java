package com.github.CorporateCraft.cceconomy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CorporateCraft.cceconomy.Commands.*;

public class CCEconomy extends JavaPlugin
{
	public static final String balfile = "plugins/CCEconomy/moneytracker.txt";
	public static final String sellfile = "plugins/CCEconomy/sellprices.txt";
	public static final String buyfile = "plugins/CCEconomy/buyprices.txt";
	public static ChatColor messages = ChatColor.GREEN;
	public static ChatColor money = ChatColor.AQUA;
	
	@Override
    public void onEnable()
	{	
		getLogger().info("CCEconomy has been enabled. You now have an advanced economy system.");
		getServer().getPluginManager().registerEvents(new LoginListener(), this);
		Initialization.InitiateFiles();
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("bal"))
		{
			return CmdBalance.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("baltop"))
		{			
			return CmdBaltop.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("pay"))
		{			
			return CmdPay.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("cce"))
		{			
			return CmdCCE.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("price"))
		{
			return CmdPrice.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("cost"))
		{
			return CmdCost.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("setprice"))
		{
			return CmdSetPrice.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("setcost"))
		{
			return CmdSetCost.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("buy"))
		{
			return CmdBuy.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("sell"))
		{
			return CmdSell.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("taccept"))
		{
			return CmdTAccept.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tdeny"))
		{
			return CmdTDeny.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("trade"))
		{
			return CmdTrade.CommandUse(sender, cmd, label, args);
		}
		if(cmd.getName().equalsIgnoreCase("tradeitems"))
		{
			return CmdTradeItems.CommandUse(sender, cmd, label, args);
		}
		return false; 
	}	
	
    @Override
    public void onDisable()
    {
    	getLogger().info("CCEconomy has been disabled.");
    }
}