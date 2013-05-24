package com.crossge.hungergames.Commands;

import org.bukkit.command.CommandSender;
import com.crossge.hungergames.*;

public class Cmd
{
	Variables var = new Variables();
	Sponsor sp = new Sponsor();
	Players pl = new Players();
	Kits kit = new Kits();
	Stats s = new Stats();
	Game g = new Game();

	public boolean commandUse(CommandSender sender, String[] args)
	{
		return false;
	}
}