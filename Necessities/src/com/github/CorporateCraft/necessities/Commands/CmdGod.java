package com.github.CorporateCraft.necessities.Commands;

import java.util.HashMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;
import com.github.CorporateCraft.necessities.CCBot.CCBotLog;

public class CmdGod extends Cmd
{
	ArrayLists arl = new ArrayLists();
	CCBotLog log = new CCBotLog();
	private static HashMap<String,Boolean> gods = new HashMap<String,Boolean>();
	public CmdGod()
	{
		
	}
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length > 1)
        {
      	   return false;
        }
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			Player target;
			if (args.length == 0)
	        {
				target = p;
	        }
			else
			{
				target = sender.getServer().getPlayer(args[0]);
			}
			if(target == null)
			{
				target = p;
			}
			setGod(target);
			String enab = enabled(target);
			if(p == target)
			{
				p.sendMessage(arl.getCol() + "God mode " + enab.toLowerCase() + ".");
				log.log(enab + " god mode for player " + target.getName() + ".");
			}
			else
			{
				p.sendMessage(arl.getCol() + enab + " god mode for player " + target.getName() + ".");
				log.log(enab + " god mode for player " + target.getName() + ".");
				target.sendMessage(arl.getCol() + "God mode " + enab.toLowerCase() + ".");
			}
			return true;
		}
		else
		{
			if (args.length == 0)
	        {
				sender.sendMessage(arl.getCol() + "You are not a player you can't be a god.");
	      	   	return true;
	        }
			Player target = sender.getServer().getPlayer(args[0]);
			if(target == null)
			{
				sender.sendMessage(arl.getCol() + "Nonexistant player.");
				return true;
			}
			setGod(target);
			String enab = enabled(target);
			sender.sendMessage(arl.getCol() + enab + " god mode for player " + target.getName() + ".");
			log.log(enabled(target) + " god mode for player " + target.getName() + ".");
			target.sendMessage(arl.getCol() + "God mode " + enab.toLowerCase() + ".");
			return true;
		}
	}
	private void setGod(Player target)
	{
		String name = target.getName();
		addP(name);
		gods.put(name, !gods.get(name));
	}
	public void remP(String name)
	{
		gods.remove(name);
	}
	public void addP(String name)
	{
		if(!gods.containsKey(name))
		{
			gods.put(name, false);
		}
	}
	public boolean isGod(Player p)
	{
		String name = p.getName();
		if(!gods.containsKey(name))
		{
			gods.put(name, false);
		}
		return gods.get(name);
	}
	private String enabled(Player target)
	{
		if(gods.get(target.getName()))
		{
			return "Enabled";
		}
		return "Disabled";
	}
}