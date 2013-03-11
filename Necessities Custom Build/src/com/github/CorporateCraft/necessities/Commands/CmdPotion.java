package com.github.CorporateCraft.necessities.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.CorporateCraft.necessities.ArrayLists;

public class CmdPotion extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdPotion()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		if (args.length > 4)
      	   return false;
		if (args.length < 3)
      	   return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length == 3)
	        {
				PotionEffectType potType = PotionEffectType.getByName(args[0]);
				if(potType == null)
				{
					player.sendMessage(arl.getCol() + "Potion effect does not exist.");
					return true;
				}
				try
				{
					Integer.parseInt(args[1]);
					Integer.parseInt(args[2]);
				}
				catch(Exception e)
				{
					return false;
				}
				PotionEffect pot = new PotionEffect(potType, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				pot.apply(player);
				player.sendMessage(arl.getCol() + "Added potion effect of " + potType.getName() + ".");
	        }
			else if (args.length == 4)
			{
				Player target = Bukkit.getPlayer(args[0]);
				PotionEffectType potType = PotionEffectType.getByName(args[1]);
				if(potType == null)
				{
					player.sendMessage(arl.getCol() + "Potion effect does not exist.");
					return true;
				}
				try
				{
					Integer.parseInt(args[2]);
					Integer.parseInt(args[3]);
				}
				catch(Exception e)
				{
					return false;
				}
				PotionEffect pot = new PotionEffect(potType, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				pot.apply(target);
				player.sendMessage(arl.getCol() + "Added potion effect of " + potType.getName() + ".");
				target.sendMessage(arl.getCol() + "Recieved potion effect of " + potType.getName() + ".");
			}
			return true;
		}
		else
		{
			if (args.length == 4)
			{
				Player target = Bukkit.getPlayer(args[0]);
				PotionEffectType potType = PotionEffectType.getByName(args[1]);
				if(potType == null)
				{
					sender.sendMessage(arl.getCol() + "Potion effect does not exist.");
					return true;
				}
				try
				{
					Integer.parseInt(args[2]);
					Integer.parseInt(args[3]);
				}
				catch(Exception e)
				{
					return false;
				}
				PotionEffect pot = new PotionEffect(potType, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				pot.apply(target);
				sender.sendMessage(arl.getCol() + "Added potion effect of " + potType.getName() + ".");
				return true;
			}
			return false;
		}
	}
}