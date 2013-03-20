package com.crossge.hungergames;

import java.sql.*;

public class Stats
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://CrossHG.db.10520830.hostedresource.com";///CrossHG";//"CrossHG.db.10520830.hostedresource.com";
	private static final String USER = "CrossHG";
	private static final String PASS = "iu#32H@Ggb0";
	public Stats()
	{
		
	}	
	
	public void write(String name, int points, int wins, int kills, int deaths, int games)
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO HungerGames " + 
	                "SET name='" + name + "', points=" + points + ", wins=" + wins + ", kills=" + kills + ", deaths=" + deaths + ", games=" + games);
            stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
	}
	public void addGame(String name)
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET games = games + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
	}
	public void addDeath(String name)
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET deaths = deaths + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
	}
	public void addKill(String name)
	{
		Connection conn = null;
		try
		{  
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET kills = kills + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
	}
	public void addWin(String name)
	{
		Connection conn = null;
		try
		{ 
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET wins = wins + 1 WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
	}
	public void addPoints(String name, int points)
	{
		Connection conn = null;
		try
		{  
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement("UPDATE HungerGames SET points = points + " + Integer.toString(points) + " WHERE name = '" + name + "'");
			stmt.executeUpdate();
        }
		catch (Exception e){System.out.print(e.getCause());}
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
	}
	
	public String get(String name)
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
		finally
		{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e){System.out.print(e.getCause());}
		}
		return null;
	}
	
	public void connect()
	{
		
		try
		{
			Class.forName(JDBC_DRIVER);
		}
		catch (ClassNotFoundException e){System.out.print(e.getCause());  System.out.print("169");}
		Connection conn = null;
	    try
	    {
	    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
	   catch(Exception e){System.out.print(e.getCause()); System.out.print("184");}
	   finally
	   {
		   try
		   {
			   if(conn != null)
				   conn.close();
		   }
		   catch(Exception e){System.out.print(e.getCause()); System.out.print("191");}
	   }	   
	}
}