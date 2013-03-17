package com.crossge.necessities.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.crossge.necessities.*;

public class CmdMotd extends Cmd
{
	ArrayLists arl = new ArrayLists();
	public CmdMotd()
	{

	}
	public boolean commandUse(CommandSender sender, String[] args)
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
			    FileReader reader = new FileReader(arl.getMotd());
			    BufferedReader buff = new BufferedReader(reader);
			    while(true)
			    {
			    	String inputText = buff.readLine();
			        if(inputText == null)
			        {
			         	break;
			        }
			        player.sendMessage(arl.getCol() + inputText);
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
			    FileReader reader = new FileReader(arl.getMotd());
			    BufferedReader buff = new BufferedReader(reader);
			    while(true)
			    {
			    	String inputText = buff.readLine();
			        if(inputText == null)
			        {
			         	break;
			        }
			        sender.sendMessage(arl.getCol() + inputText);
			    }
			}
			catch (IOException ex){}
			return true;
		}
	}
}