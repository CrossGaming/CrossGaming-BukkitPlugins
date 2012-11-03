package com.github.CorporateCraft.cceconomy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.CorporateCraft.cceconomy.Commands.CmdBalance;
import com.github.CorporateCraft.cceconomy.Commands.CmdBaltop;
import com.github.CorporateCraft.cceconomy.Commands.CmdBuy;
import com.github.CorporateCraft.cceconomy.Commands.CmdCCE;
import com.github.CorporateCraft.cceconomy.Commands.CmdCost;
import com.github.CorporateCraft.cceconomy.Commands.CmdPay;
import com.github.CorporateCraft.cceconomy.Commands.CmdPrice;
import com.github.CorporateCraft.cceconomy.Commands.CmdSell;
import com.github.CorporateCraft.cceconomy.Commands.CmdSetCost;
import com.github.CorporateCraft.cceconomy.Commands.CmdSetPrice;


public class CCEconomy extends JavaPlugin
{
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
		return false; 
	}	
	
	public class LoginListener implements Listener
	{
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event)
		{
        	Player player = event.getPlayer();
        	String playername = player.getName();
        	if (!PlayerToFile.DoesPlayerExist(playername))
        	{
        		PlayerToFile.AddPlayerToList(playername);
        	}
		}
	}
	
    @Override
    public void onDisable()
    {
    	getLogger().info("CCEconomy has been disabled.");
    }
}