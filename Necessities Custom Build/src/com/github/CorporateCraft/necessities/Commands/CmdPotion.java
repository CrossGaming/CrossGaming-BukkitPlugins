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
		if (args.length < 3 || args.length > 4)
      	   return false;
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (args.length == 3)
	        {
				PotionEffectType potType = PotionEffectType.getByName(potionFinder(args[0]));
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
				player.sendMessage(arl.getCol() + "Added potion effect of " + trueName(potType.getName()) + ".");
	        }
			else if (args.length == 4)
			{
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null)
					return false;
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
				player.sendMessage(arl.getCol() + "Added potion effect of " + trueName(potType.getName()) + ".");
				target.sendMessage(arl.getCol() + "Recieved potion effect of " + trueName(potType.getName()) + ".");
			}
			return true;
		}
		else
		{
			if (args.length == 4)
			{
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null)
					return false;
				PotionEffectType potType = PotionEffectType.getByName(potionFinder(args[1]));
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
				sender.sendMessage(arl.getCol() + "Added potion effect of " + trueName(potType.getName()) + ".");
				return true;
			}
			return false;
		}
	}
	private String potionFinder(String potion)
	{
		potion = potion.toUpperCase();
		if(potion.equals("NAUSEA"))
			potion = "CONFUSION";
		else if(potion.equals("RESISTANCE"))
			potion = "DAMAGE_RESISTANCE";
		else if(potion.equals("HASTE"))
			potion = "FAST_DIGGING";
		else if(potion.equals("FIRERESISTANCE"))
			potion = "FIRE_RESISTANCE";
		else if(potion.equals("DAMAGE"))
			potion = "HARM";
		else if(potion.equals("INSTANTDAMAGE"))
			potion = "HARM";
		else if(potion.equals("HEALTH"))
			potion = "HEAL";
		else if(potion.equals("INSTANTHEALTH"))
			potion = "HEAL";
		else if(potion.equals("STRENGTH"))
			potion = "INCREASE_DAMAGE";
		else if(potion.equals("JUMPBOOST"))
			potion = "JUMP";
		else if(potion.equals("NIGHTVISION"))
			potion = "NIGHT_VISION";
		else if(potion.equals("NIGHT"))
			potion = "NIGHT_VISION";
		else if(potion.equals("SLOWNESS"))
			potion = "SLOW";
		else if(potion.equals("MININGFATIGUE"))
			potion = "SLOW_DIGGING";
		else if(potion.equals("FATIGUE"))
			potion = "SLOW_DIGGING";
		else if(potion.equals("SWIFTNESS"))
			potion = "SPEED";
		else if(potion.equals("WATER"))
			potion = "WATER_BREATHING";
		else if(potion.equals("WATERBREATHING"))
			potion = "WATER_BREATHING";
		return potion;
	}
	private String trueName(String potion)
	{
		if(potion.equals("CONFUSION"))
			potion = "nausea";
		else if(potion.equals("DAMAGE_RESISTANCE"))
			potion = "resistance";
		else if(potion.equals("FAST_DIGGING"))
			potion = "haste";
		else if(potion.equals("FIRE_RESISTANCE"))
			potion = "fire resistance";
		else if(potion.equals("HARM"))
			potion = "instant damage";
		else if(potion.equals("HEAL"))
			potion = "instant health";
		else if(potion.equals("INCREASE_DAMAGE"))
			potion = "strength";
		else if(potion.equals("JUMP"))
			potion = "jump boost";
		else if(potion.equals("NIGHT_VISION"))
			potion = "night vision";
		else if(potion.equals("SLOW"))
			potion = "slowness";
		else if(potion.equals("SLOW_DIGGING"))
			potion = "mining fatigue";
		else if(potion.equals("SPEED"))
			potion = "swiftness";
		else if(potion.equals("WATER_BREATHING"))
			potion = "water breathing";
		return potion.toLowerCase();
	}
}