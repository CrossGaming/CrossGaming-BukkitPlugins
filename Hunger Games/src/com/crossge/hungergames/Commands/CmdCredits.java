package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import com.crossge.hungergames.Variables;

public class CmdCredits extends Cmd
{
	Variables var = new Variables();
	public CmdCredits()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		sender.sendMessage(var.defaultCol() + "Credits for this plugin go to the Cross Gaming and Entertainment Development team.");
		sender.sendMessage(var.defaultCol() + "You can contact them at www.crossge.com");
		return true;
	}
}