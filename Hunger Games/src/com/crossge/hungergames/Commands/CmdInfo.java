package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdInfo extends Cmd
{	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("HungerGames.info"))
			{
				p.sendMessage(var.defaultCol() + lang.translate("Map") + ": " + g.getNext());
				p.sendMessage(var.defaultCol() + pl.amount());
				p.sendMessage(var.defaultCol() + lang.translate("Alive") + ": " + pl.breathing());
				p.sendMessage(var.defaultCol() + lang.translate("Dead") + ": " + pl.deceased());
			}
			else
				p.sendMessage(var.errorCol() + lang.translate("Error: You may not view the info for the current round."));
		}
		else
		{
			sender.sendMessage(var.defaultCol() + lang.translate("Map") + ": " + g.getNext());
			sender.sendMessage(var.defaultCol() + pl.amount());
			sender.sendMessage(var.defaultCol() + lang.translate("Alive") + ": " + pl.breathing());
			sender.sendMessage(var.defaultCol() + lang.translate("Dead") + ": " + pl.deceased());
		}
		return true;
	}
}