package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdTpAccept extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdTpAccept()
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
				String ToWhom = telp.AcceptTp(pname, rname);
				if(ToWhom.equals("tothem"))
				{
					target.teleport(player);
				}
				if(ToWhom.equals("tome"))
				{
					player.teleport(target);
				}
				player.sendMessage(arl.GetCol() + "Teleport Accepted.");
				target.sendMessage(arl.GetCol() + "Your teleport offer has been accepted");
				return true;
			}
			else
			{
				if(rname.equalsIgnoreCase(pname))
				{
					player.sendMessage(arl.GetCol() + "You do not have a teleport request pending");
					return true;
				}
				player.sendMessage(arl.GetCol() + "You do not have a teleport request from " + rname);
				return true;
			}
		}
		else
		{
			sender.sendMessage(arl.GetCol() + "You are not a player you can't teleport.");
			return true;
		}
	}
}