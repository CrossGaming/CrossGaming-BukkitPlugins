package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdTpa extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdTpa()
	{

	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length != 1)
	        {
	      	   return false;
	        }
			String pname = player.getName();
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				return false;
			}
			String rname = target.getName();
			Teleports telp = new Teleports();
			telp.CreateTp(rname + " " + pname, "tothem");
			player.sendMessage(arl.GetCol() + "You sent a teleport request to " + rname);
			target.sendMessage(arl.GetCol() + pname + " is requesting to teleport to you");
			target.sendMessage(arl.GetCol() + "Type /tpaccept or /tpdeny to accept or deny their teleport request");
			return true;
		}
		else
		{
			sender.sendMessage(arl.GetCol() + "You are not a player you can't teleport.");
			return true;
		}
	}
}