package com.crossge.hungergames;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.bukkit.configuration.file.YamlConfiguration;

public class Stats
{
	private static Connection conn = null;
	private static File customConfigFile = new File("plugins/Hunger Games", "sql.yml");
   	private static YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
   	private static File customConfFile = new File("plugins/Hunger Games", "config.yml");
   	private static YamlConfiguration customConf = YamlConfiguration.loadConfiguration(customConfFile);
   	private static File customConfigFileStats = new File("plugins/Hunger Games", "stats.yml");
   	private static YamlConfiguration customConfigStats = YamlConfiguration.loadConfiguration(customConfigFileStats);
	private static final String hostname = customConfig.getString("hostname");
	private static final String port = customConfig.getString("port");
	private static final String dbName = customConfig.getString("dbName");
	private static final String DB_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
	private static final String USER = customConfig.getString("username");
	private static final String PASS = customConfig.getString("password");	
	
	public void write(String name, int points, int wins, int kills, int deaths, int games)
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return;
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO HungerGames " + 
		                "SET Name='" + name + "', Points=" + points + ", Wins=" + wins + ", Kills=" + kills + ", Deaths=" + deaths + ", Games=" + games);
	            stmt.executeUpdate();
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			customConfigStats.set(name + ".points", points);
			customConfigStats.set(name + ".wins", wins);
			customConfigStats.set(name + ".kills", kills);
			customConfigStats.set(name + ".deaths", deaths);
			customConfigStats.set(name + ".games", games);
			save();
		}
	}
	private void save()
	{
		try
		{
			customConfigStats.save(customConfigFileStats);
		}
		catch(Exception e){}
	}
	public void addGame(String name, int games)//Should be 1 except for when editing stats
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return;
				PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Games = Games + " + Integer.toString(games) + " WHERE Name = '" + name + "'");
				stmt.executeUpdate();
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			customConfigStats.set(name + ".games", customConfigStats.getInt(name + ".games") + games);
			save();
		}
	}
	public void convertToYML()
	{
		if(!customConf.getBoolean("useMySQL"))
			return;
		try
		{
			if(conn.isClosed())
				return;
	    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Points, Wins, Kills, Deaths, Games
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                	customConfigStats.set(rs.getString("Name") + ".points", rs.getInt("Points"));
            		customConfigStats.set(rs.getString("Name") + ".wins", rs.getInt("Wins"));
            		customConfigStats.set(rs.getString("Name") + ".kills", rs.getInt("Kills"));
            		customConfigStats.set(rs.getString("Name") + ".deaths", rs.getInt("Deaths"));
            		customConfigStats.set(rs.getString("Name") + ".games", rs.getInt("Games"));
            }
            save();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		
	}
	public void addDeath(String name, int deaths)//Should be 1 except for when editing stats
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return;
				PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Deaths = Deaths + " + Integer.toString(deaths) + " WHERE Name = '" + name + "'");
				stmt.executeUpdate();
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			customConfigStats.set(name + ".deaths", customConfigStats.getInt(name + ".deaths") + deaths);
			save();
		}
	}
	public void addKill(String name, int kills)//Should be 1 except for when editing stats
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return;
				PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Kills = Kills + " + Integer.toString(kills) + " WHERE Name = '" + name + "'");
				stmt.executeUpdate();
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			customConfigStats.set(name + ".kills", customConfigStats.getInt(name + ".kills") + kills);
			save();
		}
	}
	public void addWin(String name, int wins)//Should be 1 except for when editing stats
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{ 
				if(conn.isClosed())
					return;
				PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Wins = Wins + " + Integer.toString(wins) + " WHERE Name = '" + name + "'");
				stmt.executeUpdate();
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			customConfigStats.set(name + ".wins", customConfigStats.getInt(name + ".wins") + wins);
			save();
		}
	}
	public void addPoints(String name, int points)
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return;
				PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Points = Points + " + Integer.toString(points) + " WHERE Name = '" + name + "'");
				stmt.executeUpdate();
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			customConfigStats.set(name + ".points", customConfigStats.getInt(name + ".points") + points);
			save();
		}
	}
	
	public String get(String name)
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return null;
		    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Name, Points, Wins, Kills, Deaths, Games
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next())
	            {
	                if(rs.getString("Name").equals(name))
	                {
	                	return name + " " + Integer.toString(rs.getInt("Points")) + " " + Integer.toString(rs.getInt("Wins"))
	                			 + " " + Integer.toString(rs.getInt("Kills")) + " " + Integer.toString(rs.getInt("Deaths"))
	                			+ " " + Integer.toString(rs.getInt("Games"));
	                }
	            }
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			return name + " " + Integer.toString(customConfigStats.getInt(name + ".points")) + " " + Integer.toString(customConfigStats.getInt(name + ".wins"))
			 + " " + Integer.toString(customConfigStats.getInt(name + ".kills")) + " " + Integer.toString(customConfigStats.getInt(name + ".deaths"))
			+ " " + Integer.toString(customConfigStats.getInt(name + ".games"));
		}
		return null;
	}
	public String getPoints(String name)
	{
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return null;
		    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Points, Wins, Kills, Deaths, Games
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next())
	                if(rs.getString("Name").equals(name))
	                	return Integer.toString(rs.getInt("Points"));
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
			return Integer.toString(customConfigStats.getInt(name + ".points"));
		return null;
	}
	public ArrayList<String> leaderboards()
	{
		ArrayList<String> temp = new ArrayList<String>();
		if(customConf.getBoolean("useMySQL"))
		{
			try
			{
				if(conn.isClosed())
					return null;
		    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Name, Points, Wins, Kills, Deaths, Games
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next())
	            {
	            	temp.add(rs.getString("Name") + " " + Integer.toString(rs.getInt("Points")) + " " + Integer.toString(rs.getInt("Wins"))
                			 + " " + Integer.toString(rs.getInt("Kills")) + " " + Integer.toString(rs.getInt("Deaths"))
                			+ " " + Integer.toString(rs.getInt("Games")));
	            }
	        }
			catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
		}
		else
		{
			for(String path : customConfigStats.getKeys(false))
			{
				temp.add(path + " " + Integer.toString(customConfigStats.getInt(path + ".points")) + " " + Integer.toString(customConfigStats.getInt(path + ".wins"))
						+ " " + Integer.toString(customConfigStats.getInt(path + ".kills")) + " " + Integer.toString(customConfigStats.getInt(path + ".deaths"))
						+ " " + Integer.toString(customConfigStats.getInt(path + ".games")));
			}
		}
		Collections.sort(temp);
		return temp;
	}
	public void connect()
	{
		if(customConf.getBoolean("useMySQL"))
		{
		    try
		    {
		    	Class.forName("com.mysql.jdbc.Driver");
		    	Properties connectionProperties = new Properties();
		        connectionProperties.put("user", USER);
		        connectionProperties.put("password", PASS);
		        connectionProperties.put("autoReconnect", "false");
		        connectionProperties.put("maxReconnects", "0");
		    	conn = DriverManager.getConnection(DB_URL, connectionProperties);
		    	String sql = "CREATE TABLE IF NOT EXISTS HungerGames (" +
		                   	"Name CHAR(16), " +
		                   	"Points INTEGER, " + 
		                   	"Wins INTEGER, " +
		                   	"Kills INTEGER, " +
		                   	"Deaths INTEGER, " + 
		                   	"Games INTEGER)";
		    	PreparedStatement stmt = conn.prepareStatement(sql);//Points, Wins, Kills, Deaths, Games
		    	stmt.executeUpdate();
		   }
		   catch(Exception e){System.out.print(e.getCause()); e.printStackTrace();}	  
		}
	}
}