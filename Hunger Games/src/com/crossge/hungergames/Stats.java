package com.crossge.hungergames;

import java.sql.*;

public class Stats
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://CrossHG.db.10520830.hostedresource.com/CrossHG";//"CrossHG.db.10520830.hostedresource.com";
	private static final String USER = "CrossHG";
	private static final String PASS = "iu#32H@Ggb0";
	private static Connection conn = null;
	private static Statement stmt = null;
	public Stats()
	{
		
	}	
	
	public void write(String name, int points, int wins, int kills, int deaths, int games)
	{
		try
		{  
            stmt.executeUpdate("INSERT INTO HungerGames " + 
                "SET name='" + name + "', points=" + points + ", wins=" + wins + ", kills=" + kills + ", deaths=" + deaths + ", games=" + games); 
        }
		catch (Exception e){} 
	}
	public void addGame(String name)
	{
		try
		{  
			stmt.executeUpdate("UPDATE HungerGames SET games = games + 1 WHERE name = '" + name + "'");
        }
		catch (Exception e){} 
	}
	public void addDeath(String name)
	{
		try
		{  
			stmt.executeUpdate("UPDATE HungerGames SET deaths = deaths + 1 WHERE name = '" + name + "'");
        }
		catch (Exception e){} 
	}
	public void addKill(String name)
	{
		try
		{  
			stmt.executeUpdate("UPDATE HungerGames SET kills = kills + 1 WHERE name = '" + name + "'");
        }
		catch (Exception e){} 
	}
	public void addWin(String name)
	{
		try
		{  
			stmt.executeUpdate("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
        }
		catch (Exception e){} 
	}
	public void addPoints(String name, int points)
	{
		try
		{  
			stmt.executeUpdate("UPDATE HungerGames SET points = points + " + Integer.toString(points) + " WHERE name = '" + name + "'");
        }
		catch (Exception e){} 
	}
	
	public String get(String name)
	{
		try
		{   
            ResultSet rs = stmt.executeQuery("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	return rs.getString("name") + " " + Integer.toString(rs.getInt("points")) + " " + Integer.toString(rs.getInt("wins"))
                			 + " " + Integer.toString(rs.getInt("kills")) + " " + Integer.toString(rs.getInt("deaths"))
                			+ " " + Integer.toString(rs.getInt("games"));
                }
            }
        }
		catch (Exception e){}
		return null;
	}
	
	public void connect()
	{
	    try
	    {
	    	Class.forName(JDBC_DRIVER);
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();//Points, Wins, Kills, Deaths, Games
	    	String sql = "CREATE TABLE IF NOT EXISTS HungerGames (" +
	                   	"name VARCHAR(16) NOT NULL, " +
	                   	"points INTEGER) NOT NULL, " + 
	                   	"wins INTEGER NOT NULL, " +
	                   	"kills INTEGER NOT NULL, " +
	                   	"deaths INTEGER NOT NULL, " + 
	                   	"games INTEGER NOT NULL)";
	    	stmt.executeUpdate(sql);
	   }
	   catch(Exception e){}
	}
	public void stop()
	{
		try
		{
			stmt.close();
			conn.close();
		}
		catch(Exception e){}
	}
}