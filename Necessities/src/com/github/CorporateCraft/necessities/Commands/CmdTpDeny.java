package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdTpDeny extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdTpDeny()
	{

	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length > 1)
	        {
	      	   return false;
	        }
			String pname = player.getName();
			String rname;
			Player target;
			Teleports telp = new Teleports();
			if(args.length == 0)
			{
				target = sender.getServer().getPlayer(telp.LastOffer(pname));
			}
			else
			{
				target = sender.getServer().getPlayer(args[0]);
			}
			rname = target.getName();
			if(telp.hasTp(pname, rname))
			{
				player.sendMessage(arl.messages + "Teleport Denied.");
				target.sendMessage(arl.messages + "Your teleport offer has been denied");
				telp.DenyTp(pname, rname);
				return true;
			}
			else
			{
				player.sendMessage(arl.messages + "You do not have a teleport request from " + rname);
				return true;
			}
		}
		else
		{
			sender.sendMessage(arl.messages + "You are not a player you can't teleport.");
			return true;
		}
	}
	
}