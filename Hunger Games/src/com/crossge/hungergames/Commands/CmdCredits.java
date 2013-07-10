package com.crossge.hungergames.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdCredits extends Cmd
{
	public boolean commandUse(CommandSender sender, String[] args)
	{
		sender.sendMessage(var.defaultCol() + lang.translate("Credits for this plugin go to the") + " Cross GE Dev Team.");
		sender.sendMessage(var.defaultCol() + "pupnewfster, CrusaderDeleters, and Mod_Chris.");
		sender.sendMessage(ChatColor.DARK_RED + lang.translate("You can contact them at") + " www.crossge.com");
		return true;
	}
}