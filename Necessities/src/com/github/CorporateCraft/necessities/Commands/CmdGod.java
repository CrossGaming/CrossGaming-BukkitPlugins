package com.github.CorporateCraft.necessities.Commands;

import java.util.HashMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdGod extends Cmd
{
	private HashMap<String,Boolean> Gods = new HashMap<String,Boolean>();
	ArrayLists arl = new ArrayLists();
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
			p.sendMessage(arl.getCol() + Gods.toString());
			setGod(target);
			p.sendMessage(arl.getCol() + Gods.toString());
			if(p == target)
			{
				p.sendMessage(arl.getCol() + "God mode " + enabled(target).toLowerCase() + ".");
			}
			else
			{
				p.sendMessage(arl.getCol() + enabled(target) + " god mode for player " + target.getName() + ".");
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
			sender.sendMessage(arl.getCol() + enabled(target) + " god mode for player " + target.getName() + ".");
			return true;
		}
	}
	private void setGod(Player target)
	{
		if(!Gods.containsKey(target))
		{
			Gods.put(target.getName(), false);
		}
		if(Gods.get(target.getName()))
		{
			Gods.put(target.getName(), false);
		}
		else
		{
			Gods.put(target.getName(), true);
		}
	}
	public void remP(String name)
	{
		Gods.remove(name);
	}
	public void addP(String name)
	{
		Gods.put(name, false);
	}
	public Boolean isGod(Player p)
	{
		return Gods.containsKey(p.getName());
	}
	private String enabled(Player target)
	{
		if(Gods.get(target.getName()))
		{
			return "Enabled";
		}
		return "Disabled";
	}
}