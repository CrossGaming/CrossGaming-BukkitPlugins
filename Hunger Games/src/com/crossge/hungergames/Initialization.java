package com.crossge.hungergames;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Initialization
{
	Game g = new Game();
	Stats s = new Stats();
	UpdateCheck up = new UpdateCheck();
	private File customConfigFile = new File("plugins/Hunger Games", "config.yml");
	private File customConfigFileSpawns = new File("plugins/Hunger Games", "spawns.yml");
	private File customConfigFileSQL = new File("plugins/Hunger Games", "sql.yml");
	private File customConfigFileChest = new File("plugins/Hunger Games", "chests.yml");
	private File customConfigFileSponsor = new File("plugins/Hunger Games", "sponsors.yml");
	private File customConfigFileKits = new File("plugins/Hunger Games", "kits.yml");
	private File customConfigFileKitPrices = new File("plugins/Hunger Games", "kitprices.yml");
	private File customConfigFileStats = new File("plugins/Hunger Games", "stats.yml");
	private File customConfigFileBreakable = new File("plugins/Hunger Games", "breakable.yml");
	private File customConfigFilePlaceable = new File("plugins/Hunger Games", "placeable.yml");
	private File customConfigFileCommands = new File("plugins/Hunger Games", "commands.yml");
	private File customConfigFileChestLocations = new File("plugins/Hunger Games", "chestlocs.yml");

	public void initiateFiles()
	{
		dirCreate("plugins/Hunger Games");
		createYaml();
		g.initMaps();
		s.connect();
		YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		if(customConfig.getBoolean("checkForUpdates"))
			up.checkForUpdate();
	}
	
	public void createYaml()
	{
		if(!customConfigFile.exists())
		{
			try
			{
				customConfigFile.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
				customConfig.set("minPerChest", 3);
				customConfig.set("maxPerChest", 7);
				customConfig.set("itemsPerSponsor", 3);
				customConfig.set("useKits", false);
				customConfig.set("useMySQL", false);
				customConfig.set("maxPlayers", 24);
				customConfig.set("placeBlocks", false);
				customConfig.set("safeTime", 15);
				customConfig.set("tpCoolDown", 30);
				customConfig.set("minPlayers", 2);
				customConfig.set("playersDeathMatch", 3);
				customConfig.set("deathTime", 60);
				customConfig.set("updateMOTD", false);
				customConfig.set("language", "en");
				customConfig.set("votingTime", 180);
				customConfig.set("messageFrequency", 30);
				customConfig.set("checkForUpdates", true);
				customConfig.save(customConfigFile);
			}
			catch (IOException e){}
		}
		else
		{
			YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
			try
			{
				if(!customConfig.contains("minPerChest"))
					customConfig.set("minPerChest", 3);
				if(!customConfig.contains("maxPerChest"))
					customConfig.set("maxPerChest", 7);
				if(!customConfig.contains("itemsPerSponsor"))
					customConfig.set("itemsPerSponsor", 3);
				if(!customConfig.contains("useKits"))
					customConfig.set("useKits", false);
				if(!customConfig.contains("useMySQL"))
					customConfig.set("useMySQL", false);
				if(!customConfig.contains("maxPlayers"))
					customConfig.set("maxPlayers", 24);
				if(!customConfig.contains("placeBlocks"))
					customConfig.set("placeBlocks", false);
				if(!customConfig.contains("safeTime"))
					customConfig.set("safeTime", 15);
				if(!customConfig.contains("tpCoolDown"))
					customConfig.set("tpCoolDown", 30);
				if(!customConfig.contains("minPlayers"))
					customConfig.set("minPlayers", 2);
				if(!customConfig.contains("deathTime"))
					customConfig.set("deathTime", 60);
				if(!customConfig.contains("playersDeathMatch"))
					customConfig.set("playersDeathMatch", 3);
				if(!customConfig.contains("updateMOTD"))
					customConfig.set("updateMOTD", false);
				if(!customConfig.contains("language"))
					customConfig.set("language", "en");
				if(!customConfig.contains("votingTime"))
					customConfig.set("votingTime", 180);
				if(!customConfig.contains("messageFrequency"))
					customConfig.set("messageFrequency", 30);
				if(!customConfig.contains("checkForUpdates"))
					customConfig.set("checkForUpdates", true);
				customConfig.save(customConfigFile);
			}
			catch (IOException e){}
		}
		if(!customConfigFileChestLocations.exists())
		{
			try
			{
				customConfigFileChestLocations.createNewFile();
			}
			catch (IOException e){}
		}
		if(!customConfigFileSpawns.exists())
		{
			try
			{
				customConfigFileSpawns.createNewFile();
			}
			catch (IOException e){}
		}
		if(!customConfigFileKitPrices.exists())
		{
			try
			{
				customConfigFileKitPrices.createNewFile();
			}
			catch (IOException e){}
		}
		if(!customConfigFileCommands.exists())
		{
			try
			{
				customConfigFileCommands.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFileBreakable);
				customConfig.set("hungergames", true);
				customConfig.set("survivalgames", true);
				customConfig.set("hg", true);
				customConfig.set("sg", true);
				customConfig.set("spawn", true);
				customConfig.save(customConfigFileCommands);
			}
			catch (IOException e){}
		}
		if(!customConfigFileBreakable.exists())
		{
			try
			{
				customConfigFileBreakable.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFileBreakable);
				customConfig.set("18", true);
				customConfig.set("51", true);
				customConfig.save(customConfigFileBreakable);
			}
			catch (IOException e){}
		}
		if(!customConfigFilePlaceable.exists())
		{
			try
			{
				customConfigFilePlaceable.createNewFile();
				YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFilePlaceable);
				customConfig.set("18", true);
				customConfig.set("259", true);
				customConfig.save(customConfigFilePlaceable);
			}
			catch (IOException e){}
		}
		if(!customConfigFileStats.exists())
		{
			try
			{
				customConfigFileStats.createNewFile();
			}
			catch (IOException e){}
		}	
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
				customConfig.set("Survivor.271", 1);//wood axe
				customConfig.set("Survivor.261", 1);//bow
				customConfig.set("Survivor.262", 20);//arrow
				customConfig.set("Survivor.363", 3);//raw steak
				customConfig.save(customConfigFileKits);
			}
			catch (IOException e){}
		}
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
				customConfig.set("276", 0.15);//diamond sword
				customConfig.set("279", 0.15);//diamond axe
				customConfig.set("280", 5.14);//stick
				customConfig.set("282", 0.86);//stew
				customConfig.set("283", 0.18);//gold sword
				customConfig.set("286", 0.18);//gold axe
				customConfig.set("297", 0.68);//bread
				customConfig.set("298", 0.68);//leather hat
				customConfig.set("299", 0.43);//leather chest
				customConfig.set("300", 0.43);//leather pants
				customConfig.set("301", 0.86);//leather boots
				customConfig.set("302", 0.18);//chain hat
				customConfig.set("303", 0.14);//chain chest
				customConfig.set("304", 0.14);//chain pants
				customConfig.set("305", 0.18);//chain boots
				customConfig.set("306", 0.18);//iron hat
				customConfig.set("307", 0.14);//iron chest
				customConfig.set("308", 0.14);//iron pants
				customConfig.set("309", 0.18);//iron boots
				customConfig.set("310", 0.14);//diamond hat
				customConfig.set("311", 0.12);//diamond chest
				customConfig.set("312", 0.12);//diamond pants
				customConfig.set("313", 0.14);//diamond boots
				customConfig.set("314", 0.18);//gold hat
				customConfig.set("315", 0.14);//gold chest
				customConfig.set("316", 0.14);//gold pants
				customConfig.set("317", 0.18);//gold boots
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