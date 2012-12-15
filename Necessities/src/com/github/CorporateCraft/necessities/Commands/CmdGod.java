package com.github.CorporateCraft.necessities.Commands;

import java.util.HashMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdGod extends Cmd
{
	ArrayLists arl = new ArrayLists();
	private HashMap<String,Boolean> gods = new HashMap<String,Boolean>();
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
			p.sendMessage(arl.getCol() + godlist());
			setGod(target);
			p.sendMessage(arl.getCol() + godlist());
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
	public String godlist()
	{
		return gods.toString();
	}
	private void setGod(Player target)
	{
		if(!gods.containsKey(target))
		{
			gods.put(target.getName(), false);
		}
		if(gods.get(target.getName()))
		{
			gods.put(target.getName(), false);
		}
		else
		{
			gods.put(target.getName(), true);
		}
	}
	public void remP(String name)
	{
		gods.remove(name);
	}
	public void addP(String name)
	{
		gods.put(name, false);
	}
	public boolean isGod(Player p)
	{
		if(!gods.containsKey(p))
		{
			gods.put(p.getName(), false);
		}
		return gods.get(p.getName());
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