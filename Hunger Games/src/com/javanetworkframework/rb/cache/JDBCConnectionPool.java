/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/JDBCConnectionPool.java,v $
 * **************************************************************************
 * Java Web Translator Project												*
 * http://sourceforge.net/projects/webtranslator/							*
 * **************************************************************************
 * CVS INFORMATION															*
 * Current revision $Revision: 1.2 $
 * On branch $Name: A0-2 $
 * Latest change by $Author: xyombie $ on $Date: 2004/09/18 00:44:18 $
 * **************************************************************************
 * Modification History:													*
 * VERSION    DATE	 AUTHOR	DESCRIPTION OF CHANGE	    				    *
 * -------	-------- ------	------------------------------------------------*
 *  V1.00	09/17/04  BRB	Initial Version.								*
 * **************************************************************************
 */
package com.javanetworkframework.rb.cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/** Impliments a simple JDBC Connection pool
 * 
 * @author Brian Blank
 * @version 1.0
 */
public class JDBCConnectionPool implements IJDBCConnectionPool {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(JDBCConnectionPool.class.getName());

	/* -------------------- INSTANCE SECTION --------------- */

	@SuppressWarnings("unused")
	private final String jdbcDriver, jdbcURL, jdbcUser, jdbcPass;
	private final Connection connections[];
	private int connInUse = 0;
	private int numWaitingProcs = 0;
	
	/** Constructor for JDBCConnectionPool
	 * 
	 * @param jdbcDriver Name of JDBC Driver
	 * @param jdbcURL URL to JDBC Database
	 * @param jdbcUser Username of Database
	 * @param jdbcPass Password of Database
	 * @param maxConn Max number of connections
	 * @throws ClassNotFoundException JDBC Driver not found
	 */
	public JDBCConnectionPool(String jdbcDriver, String jdbcURL, 
			String jdbcUser, String jdbcPass, int maxConn) 
	throws ClassNotFoundException
	{
		logger.entering(this.getClass().getName(), "JDBCConnectionPool",
				new Object[] {jdbcDriver, jdbcURL, jdbcUser, jdbcPass, 
								new Integer(maxConn)});

		this.jdbcDriver = jdbcDriver;
		this.jdbcURL = jdbcURL;
		this.jdbcUser = jdbcUser;
		this.jdbcPass = jdbcPass;
		
		Class.forName(jdbcDriver);
		
		connections = new Connection[maxConn];
		for(int i = 0; i < maxConn; i++) {
			connections[i] = null;
		}
		
		logger.exiting(this.getClass().getName(), "JDBCConnectionPool");
	}

	/** Get's a JDBC Connection from the connection pool.  If
	 *  no connection is available and we didn't reach max connections,
	 *  a new connection is created.  Otherwise, this call will become a
	 *  blocking wait.
	 */
	public synchronized Connection getConnection() 
	throws SQLException
	{
		logger.entering(this.getClass().getName(), "getConnection");

		while(connInUse >= connections.length) {
			try {
				numWaitingProcs++;
				System.err.println("Max DB Connections reached.  " + 
						numWaitingProcs + " processes in wait() state.  You" +
						" might want to consider increasing maxDBConnections");
				wait();
				numWaitingProcs--;
			}
			catch(InterruptedException e) {
				// Do nothing
			}
		}
		connInUse++;
		if(connections[connInUse-1] == null) {
			connections[connInUse-1] = DriverManager.getConnection(jdbcURL);
		}
		
		logger.exiting(this.getClass().getName(), "getConnection", 
					connections[connInUse-1]);
		return connections[connInUse-1];
	}
	
	/** Returns a JDBC Connection to the connection pool.
	 * 
	 * @param c JDBC Connection to return to the pool
	 */
	public synchronized void returnConnection(Connection c) {
		logger.entering(this.getClass().getName(), "returnConnection", c);

		connections[connInUse-1] = c;
		connInUse--;
		notifyAll();
		
		logger.exiting(this.getClass().getName(), "returnConnection");
	}
}
