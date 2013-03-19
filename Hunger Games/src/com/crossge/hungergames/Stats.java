package com.crossge.hungergames;

import java.sql.*;

public class Stats
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/HungerGames";
	private static final String USER = "username";
	private static final String PASS = "password";
	  
	public Stats()
	{
		
	}	
	
	public void write(String name, int points, int wins, int kills, int deaths, int games)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("INSERT INTO HungerGames " + 
                "SET name='" + name + "', points=" + points + ", wins=" + wins + ", kills=" + kills + ", deaths=" + deaths + ", games=" + games); 
            stmt.close();
            conn.close();
        }
		catch (Exception e){} 
	}
	
	public void addGame(String name)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	stmt.execute("UPDATE HungerGames SET games = games + 1 WHERE name = '" + name + "'");
                	break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
		catch (Exception e){} 
	}
	public void addDeath(String name)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	stmt.execute("UPDATE HungerGames SET deaths = deaths + 1 WHERE name = '" + name + "'");
                	break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
		catch (Exception e){} 
	}
	public void addKill(String name)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	stmt.execute("UPDATE HungerGames SET kills = kills + 1 WHERE name = '" + name + "'"); 
                	break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
		catch (Exception e){} 
	}
	public void addWin(String name)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	stmt.execute("UPDATE HungerGames SET wins = wins + 1 WHERE name = '" + name + "'"); 
                	break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
		catch (Exception e){} 
	}
	public void addPoints(String name, int points)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT name, points, wins, kills, deaths, games FROM HungerGames");
            while(rs.next())
            {
                if(rs.getString("name").equals(name))
                {
                	stmt.execute("UPDATE HungerGames SET points = points + " + Integer.toString(points) + " WHERE name = '" + name + "'");
                	break;
                }
            }
            rs.close();
            stmt.close();
            conn.close(); 
        }
		catch (Exception e){} 
	}
	
	public String get(String name)
	{
		try
		{  
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(); 
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
            conn.close(); 
        }
		catch (Exception e){}
		return null;
	}
	
	public void create()
	{
		Connection conn = null;
		Statement stmt = null;
	    try
	    {
	    	Class.forName(JDBC_DRIVER);
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	stmt = conn.createStatement();//Points, Wins, Kills, Deaths, Games
	    	String sql = "CREATE TABLE HungerGames " +
	                   	"(name VARCHAR(16), " +
	                   	"points INTEGER), " + 
	                   	"wins INTEGER, " +
	                   	"kills INTEGER, " +
	                   	"deaths INTEGER, " + 
	                   	"games INTEGER";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
            conn.close();
	   }
	   catch(Exception e){}
	}
}