package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;

public class CmdHungerGames extends Cmd
{
	public CmdHungerGames()
	{
		
	}
	
	public boolean commandUse(CommandSender sender, String[] args)
	{
		Cmd com = new Cmd();
		if(args.length == 0 || args[0].equalsIgnoreCase("help"))
			com = new CmdHelp();
		else if(args[0].equalsIgnoreCase("join"))
			com = new CmdJoin();
		else if(args[0].equalsIgnoreCase("leave"))
			com = new CmdLeave();
		else if(args[0].equalsIgnoreCase("spectate"))
			com = new CmdSpectate();
		else if(args[0].equalsIgnoreCase("setspawn"))
			com = new CmdSetSpawn();
		else if(args[0].equalsIgnoreCase("info"))
			com = new CmdInfo();
		else if(args[0].equalsIgnoreCase("stats"))
			com = new CmdStats();
		return com.commandUse(sender, args);	
	}	
}