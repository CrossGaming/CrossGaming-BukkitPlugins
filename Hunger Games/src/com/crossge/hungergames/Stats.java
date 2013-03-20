package com.crossge.hungergames;

import java.io.File;
import java.sql.*;
import java.util.Properties;

import org.bukkit.configuration.file.YamlConfiguration;

public class Stats
{
	private static Connection conn = null;
	private static File customConfigFile = new File("plugins/Hunger Games", "sql.yml");
   	private static YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	private static final String hostname = customConfig.getString("hostname");
	private static final String port = customConfig.getString("port");
	private static final String dbName = customConfig.getString("dbName");
	private static final String DB_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
	private static final String USER = customConfig.getString("username");
	private static final String PASS = customConfig.getString("password");
	public Stats()
	{
		
	}	
	
	public void write(String name, int points, int wins, int kills, int deaths, int games)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO HungerGames " + 
	                "SET name='" + name + "', points=" + points + ", wins=" + wins + ", kills=" + kills + ", deaths=" + deaths + ", games=" + games);
            stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
	}
	public void addGame(String name)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET games = games + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
	}
	public void addDeath(String name)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET deaths = deaths + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
	}
	public void addKill(String name)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET kills = kills + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
	}
	public void addWin(String name)
	{
		try
		{ 
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET wins = wins + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
	}
	public void addPoints(String name, int points)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET points = points + " + Integer.toString(points) + " WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
	}
	
	public String get(String name)
	{
		try
		{
	    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Points, Wins, Kills, Deaths, Games
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	return name + " " + Integer.toString(rs.getInt("points")) + " " + Integer.toString(rs.getInt("wins"))
                			 + " " + Integer.toString(rs.getInt("kills")) + " " + Integer.toString(rs.getInt("deaths"))
                			+ " " + Integer.toString(rs.getInt("games"));
                }
            }
        }
		catch (Exception e){System.out.print(e.getCause());}
		return null;
	}
	
	public void connect()
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
	                   	"name CHAR(16), " +
	                   	"points INTEGER), " + 
	                   	"wins INTEGER, " +
	                   	"kills INTEGER, " +
	                   	"deaths INTEGER, " + 
	                   	"games INTEGER)";
	    	PreparedStatement stmt = conn.prepareStatement(sql);//Points, Wins, Kills, Deaths, Games
	    	stmt.executeUpdate(sql);
	   }
	   catch(Exception e){System.out.print(e.getCause()); System.out.print("104");}	   
	}
}