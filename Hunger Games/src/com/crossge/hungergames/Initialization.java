package com.crossge.hungergames;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Initialization
{
	Game g = new Game();
	Stats s = new Stats();
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/Hunger Games");
		fileCreate("plugins/Hunger Games/options.txt");
		createYaml();
		g.initMaps();
		s.connect();
	}
	
	private File customConfigFile = null;
	private File customConfigFileSQL = null;
	public void createYaml()
	{
		customConfigFile = new File("plugins/Hunger Games", "spawns.yml");
		if(!customConfigFile.exists())
		{
			try
			{
				customConfigFile.createNewFile();
			}
			catch (IOException e){}
		}
		customConfigFileSQL = new File("plugins/Hunger Games", "sql.yml");
		if(!customConfigFileSQL.exists())
		{
			try
			{//hostname, port, dbName, username, password
				customConfigFileSQL.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFileSQL);
				customConfig.set("hostname", "localhost");
				customConfig.set("port", "3306");
				customConfig.set("dbName", "HungerGames");
				customConfig.set("username", "username");
				customConfig.set("password", "password");
				customConfig.save(customConfigFileSQL);
			}
			catch (IOException e){}
		}
	}
	
	private void dirCreate(String directory)
	{
		File d = new File(directory);
		if(!d.exists())
		{
			try
			{
				d.mkdir();
			}
			catch (Exception e){}
		}
	}
	
	private void fileCreate(String file)
	{
		File f = new File(file);
		if(!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e){}
		}
	}
}