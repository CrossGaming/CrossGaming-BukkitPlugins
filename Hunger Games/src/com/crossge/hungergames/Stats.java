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
	                "SET Name='" + name + "', Points=" + points + ", Wins=" + wins + ", Kills=" + kills + ", Deaths=" + deaths + ", Games=" + games);
            stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
	}
	public void addGame(String name)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Games = Games + 1 WHERE Name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
	}
	public void addDeath(String name)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Deaths = Deaths + 1 WHERE Name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
	}
	public void addKill(String name)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Kills = Kills + 1 WHERE Name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
	}
	public void addWin(String name)
	{
		try
		{ 
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Wins = Wins + 1 WHERE Name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
	}
	public void addPoints(String name, int points)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET Points = Points + " + Integer.toString(points) + " WHERE Name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
	}
	
	public String get(String name)
	{
		try
		{
	    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Points, Wins, Kills, Deaths, Games
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
		return null;
	}
	public String getPoints(String name)
	{
		try
		{
	    	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HungerGames");//Points, Wins, Kills, Deaths, Games
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                if(rs.getString("Name").equals(name))
                {
                	return Integer.toString(rs.getInt("Points"));
                }
            }
        }
		catch (Exception e){System.out.print(e.getCause()); e.printStackTrace();}
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