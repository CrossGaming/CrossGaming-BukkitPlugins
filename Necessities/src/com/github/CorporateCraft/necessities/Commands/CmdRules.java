package com.github.CorporateCraft.necessities.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.CorporateCraft.necessities.*;

public class CmdRules extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdRules()
	{

	}
	public boolean CommandUse(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			if(args.length != 0)
			{
				return false;
			}
			Player player = (Player) sender;
			try
			{
			    FileReader reader = new FileReader(arl.Rulesfile);
			    BufferedReader buff = new BufferedReader(reader);
			    while(true)
			    {
			    	String inputText = buff.readLine();
			        if(inputText == null)
			        {
			         	break;
			        }
			        player.sendMessage(arl.messages + inputText);
			    }
			}
			catch (IOException ex){}
			return true;
		}
		else
		{
			if(args.length != 0)
			{
				return false;
			}
			try
			{
			    FileReader reader = new FileReader(arl.Rulesfile);
			    BufferedReader buff = new BufferedReader(reader);
			    while(true)
			    {
			    	String inputText = buff.readLine();
			        if(inputText == null)
			        {
			         	break;
			        }
			        sender.sendMessage(arl.messages + inputText);
			    }
			}
			catch (IOException ex){}
			return true;
		}
	}
}