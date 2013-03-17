package com.crossge.cceconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.cceconomy.*;

public class CmdPlayers  extends Cmd
{
	BalChecks balc = new BalChecks();
	ArrayLists arl = new ArrayLists();
	public CmdPlayers()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length != 0)
			return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
           	player.sendMessage(arl.getMessages() + balc.players() + " players have joined the server.");
    	   	return true;
        } 
		else
		{
    	   	sender.sendMessage(arl.getMessages() + balc.players() + " players have joined the server.");
    	   	return true;
	    }
	}
}