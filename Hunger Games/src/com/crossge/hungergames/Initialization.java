package com.crossge.hungergames;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Initialization
{
	Game g = new Game();
	Stats s = new Stats();
	private File customConfigFile = null;
	private File customConfigFileSQL = null;
	private File customConfigFileChest = null;
	private File customConfigFileSponsor = null;
	private File customConfigFileKits = null;
	public Initialization()
	{
		
	}
	public void initiateFiles()
	{
		dirCreate("plugins/Hunger Games");
		createYaml();
		g.initMaps();
		s.connect();
	}
	
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
		customConfigFileKits = new File("plugins/Hunger Games", "kits.yml");
		if(!customConfigFileKits.exists())
		{
			try
			{
				customConfigFileKits.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFileKits);
				customConfig.set("Tribute.345", 1);//compass
				customConfig.set("Tribute.346", 1);//fishing rod
				customConfig.set("Tribute.272", 1);//stone sword
				customConfig.set("Tribute.297", 2);//bread
				customConfig.save(customConfigFileKits);
			}
			catch (IOException e){}
		}
		customConfigFileChest = new File("plugins/Hunger Games", "chests.yml");
		if(!customConfigFileChest.exists())
		{
			try
			{
				customConfigFileChest.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFileChest);
				customConfig.set("39", 0.86);//brown mushroom
				customConfig.set("40", 0.86);//red mushroom
				customConfig.set("258", 0.08);//iron axe
				customConfig.set("259", 0.25);//flint and steel
				customConfig.set("260", 4.71);//apple
				customConfig.set("261", 0.86);//bow
				customConfig.set("262", 0.86);//arrow
				customConfig.set("263", 0.86);//coal
				customConfig.set("264", 0.04);//diamond
				customConfig.set("265", 0.43);//iron ingot
				customConfig.set("266", 2.57);//gold ingot
				customConfig.set("267", 0.16);//iron sword
				customConfig.set("268", 0.86);//wood sword
				customConfig.set("271", 0.86);//wood axe
				customConfig.set("272", 0.08);//stone sword
				customConfig.set("275", 0.08);//stone axe
				customConfig.set("280", 5.14);//stick
				customConfig.set("281", 0.86);//bowl
				customConfig.set("282", 0.86);//stew
				customConfig.set("283", 0.08);//gold sword
				customConfig.set("286", 0.08);//gold axe
				customConfig.set("287", 0.43);//string
				customConfig.set("288", 0.86);//feather
				customConfig.set("296", 2.06);//wheat
				customConfig.set("297", 0.68);//bread
				customConfig.set("298", 0.68);//leather hat
				customConfig.set("299", 0.43);//leather chest
				customConfig.set("300", 0.43);//leather pants
				customConfig.set("301", 0.86);//leather boots
				customConfig.set("302", 0.08);//chain hat
				customConfig.set("303", 0.04);//chain chest
				customConfig.set("304", 0.04);//chain pants
				customConfig.set("305", 0.08);//chain boots
				customConfig.set("306", 0.08);//iron hat
				customConfig.set("309", 0.08);//iron boots
				customConfig.set("314", 0.08);//gold hat
				customConfig.set("315", 0.04);//gold chest
				customConfig.set("316", 0.04);//gold pants
				customConfig.set("317", 0.08);//gold boots
				customConfig.set("318", 0.86);//flint
				customConfig.set("319", 3.00);//raw pork
				customConfig.set("320", 0.43);//cooked pork
				customConfig.set("322", 0.08);//gold apple
				customConfig.set("332", 2.57);//snowball
				customConfig.set("333", 0.43);//boat
				customConfig.set("334", 5.14);//leather
				customConfig.set("344", 7.71);//egg
				customConfig.set("345", 6.00);//compass
				customConfig.set("346", 0.43);//fishing rod
				customConfig.set("347", 6.00);//clock
				customConfig.set("349", 5.14);//raw fish
				customConfig.set("350", 0.68);//cooked fish
				customConfig.set("357", 6.00);//cookie
				customConfig.set("359", 2.57);//shears
				customConfig.set("360", 6.00);//melon
				customConfig.set("363", 3.00);//raw steak
				customConfig.set("364", 0.43);//steak
				customConfig.set("365", 3.85);//raw chicken
				customConfig.set("366", 0.68);//chicken
				customConfig.set("368", 0.34);//ender pearl
				customConfig.set("391", 4.71);//carrot
				customConfig.set("392", 7.71);//potato
				customConfig.set("393", 0.68);//cooked potato
				customConfig.set("395", 6.00);//map
				customConfig.set("396", 0.08);//gold carrot
				customConfig.set("400", 0.43);//pie
				customConfig.save(customConfigFileChest);
			}
			catch (IOException e){}
		}
		customConfigFileSponsor = new File("plugins/Hunger Games", "sponsors.yml");
		if(!customConfigFileSponsor.exists())
		{
			try
			{
				customConfigFileSponsor.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFileSponsor);
				customConfig.set("258", 0.08);//iron axe
				customConfig.set("259", 0.25);//flint and steel
				customConfig.set("260", 4.71);//apple
				customConfig.set("261", 0.86);//bow
				customConfig.set("262", 0.86);//arrow
				customConfig.set("263", 0.86);//coal
				customConfig.set("264", 0.06);//diamond
				customConfig.set("265", 0.43);//iron ingot
				customConfig.set("266", 2.57);//gold ingot
				customConfig.set("267", 0.16);//iron sword
				customConfig.set("268", 0.86);//wood sword
				customConfig.set("271", 0.86);//wood axe
				customConfig.set("272", 0.08);//stone sword
				customConfig.set("275", 0.08);//stone axe
				customConfig.set("276", 0.05);//diamond sword
				customConfig.set("279", 0.05);//diamond axe
				customConfig.set("280", 5.14);//stick
				customConfig.set("282", 0.86);//stew
				customConfig.set("283", 0.08);//gold sword
				customConfig.set("286", 0.08);//gold axe
				customConfig.set("297", 0.68);//bread
				customConfig.set("298", 0.68);//leather hat
				customConfig.set("299", 0.43);//leather chest
				customConfig.set("300", 0.43);//leather pants
				customConfig.set("301", 0.86);//leather boots
				customConfig.set("302", 0.08);//chain hat
				customConfig.set("303", 0.04);//chain chest
				customConfig.set("304", 0.04);//chain pants
				customConfig.set("305", 0.08);//chain boots
				customConfig.set("306", 0.08);//iron hat
				customConfig.set("307", 0.04);//iron chest
				customConfig.set("308", 0.04);//iron pants
				customConfig.set("309", 0.08);//iron boots
				customConfig.set("310", 0.04);//diamond hat
				customConfig.set("311", 0.02);//diamond chest
				customConfig.set("312", 0.02);//diamond pants
				customConfig.set("313", 0.04);//diamond boots
				customConfig.set("314", 0.08);//gold hat
				customConfig.set("315", 0.04);//gold chest
				customConfig.set("316", 0.04);//gold pants
				customConfig.set("317", 0.08);//gold boots
				customConfig.set("319", 3.00);//raw pork
				customConfig.set("320", 0.43);//cooked pork
				customConfig.set("322", 0.08);//gold apple
				customConfig.set("333", 0.43);//boat
				customConfig.set("345", 6.00);//compass
				customConfig.set("346", 0.43);//fishing rod
				customConfig.set("347", 6.00);//clock
				customConfig.set("349", 5.14);//raw fish
				customConfig.set("350", 0.68);//cooked fish
				customConfig.set("357", 6.00);//cookie
				customConfig.set("359", 2.57);//shears
				customConfig.set("360", 6.00);//melon
				customConfig.set("363", 3.00);//raw steak
				customConfig.set("364", 0.43);//steak
				customConfig.set("365", 3.85);//raw chicken
				customConfig.set("366", 0.68);//chicken
				customConfig.set("368", 0.34);//ender pearl
				customConfig.set("391", 4.71);//carrot
				customConfig.set("392", 7.71);//potato
				customConfig.set("393", 0.68);//cooked potato
				customConfig.set("395", 6.00);//map
				customConfig.set("396", 0.08);//gold carrot
				customConfig.set("400", 0.43);//pie
				customConfig.save(customConfigFileSponsor);
			}
			catch(Exception e){}
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
}