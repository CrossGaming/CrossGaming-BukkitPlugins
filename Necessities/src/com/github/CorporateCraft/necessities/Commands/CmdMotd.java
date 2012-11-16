package com.github.CorporateCraft.necessities.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdMotd
{
	public static boolean CommandUse(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			try
			{
			    FileReader reader = new FileReader(Necessities.Motdfile);
			    BufferedReader buff = new BufferedReader(reader);
			    while(true)
			    {
			    	String inputText = buff.readLine();
			        if(inputText == null)
			        {
			         	break;
			        }
			        player.sendMessage(Necessities.messages + inputText);
			    }
			}
			catch (IOException ex){}
			return true;
		}
		else
		{
			try
			{
			    FileReader reader = new FileReader(Necessities.Motdfile);
			    BufferedReader buff = new BufferedReader(reader);
			    while(true)
			    {
			    	String inputText = buff.readLine();
			        if(inputText == null)
			        {
			         	break;
			        }
			        sender.sendMessage(Necessities.messages + inputText);
			    }
			}
			catch (IOException ex){}
			return true;
		}
	}
}