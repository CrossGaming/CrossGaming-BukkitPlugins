package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdGameMode extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdGameMode()
	{
		
	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if(args.length == 0)
		{
			return false;
		}
		if(args.length > 2)
		{
			return false;
		}
		if (sender instanceof Player)
		{
			Player target;
			if(args.length == 2)
			{
				target = sender.getServer().getPlayer(args[1]);
			}
			else
			{
				target = (Player) sender;
			}
			int value = -1;
            try
            {
                value = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ex) {}
            GameMode mode = GameMode.getByValue(value);
            if (mode == null)
            {
                if (args[0].equalsIgnoreCase("creative"))
                {
                    mode = GameMode.CREATIVE;
                }
                else if (args[0].equalsIgnoreCase("adventure"))
                {
                    mode = GameMode.ADVENTURE;
                }
                else
                {
                    mode = GameMode.SURVIVAL;
                }
            }
            if (mode != target.getGameMode())
            {
            	target.setGameMode(mode);
                if (target == sender)
                {
                	Command.broadcastCommandMessage(sender, "Set own game mode to " + mode.toString() + " mode");
                }
                else
                {
                	Command.broadcastCommandMessage(sender, "Set " + target.getName() + "'s game mode to " + mode.toString() + " mode");
                }
            }
			return true;
		}
		else
		{
			if(args.length != 2)
			{
				return false;
			}
			Player target = sender.getServer().getPlayer(args[1]);
			int value = -1;
            try
            {
                value = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ex) {}
            GameMode mode = GameMode.getByValue(value);
            if (mode == null)
            {
                if (args[0].equalsIgnoreCase("creative"))
                {
                    mode = GameMode.CREATIVE;
                }
                else if (args[0].equalsIgnoreCase("adventure"))
                {
                    mode = GameMode.ADVENTURE;
                }
                else
                {
                    mode = GameMode.SURVIVAL;
                }
            }
            if (mode != target.getGameMode())
            {
            	target.setGameMode(mode);
                Command.broadcastCommandMessage(sender, "Set " + target.getName() + "'s game mode to " + mode.toString() + " mode");
            }
	        return true;
	    }
	}
}