package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;

public class CmdCredits extends Cmd
{
	public boolean commandUse(CommandSender sender, String[] args)
	{
		sender.sendMessage(var.defaultCol() + "Credits for this plugin go to the Cross GE Development team.");
		sender.sendMessage(var.defaultCol() + "pupnewfster, CrusaderDeleters, and Mod_Chris.");
		sender.sendMessage(var.defaultCol() + "You can contact them at www.crossge.com");
		return true;
	}
}