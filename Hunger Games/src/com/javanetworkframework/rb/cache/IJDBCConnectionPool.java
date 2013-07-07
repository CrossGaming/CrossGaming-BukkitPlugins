/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/IJDBCConnectionPool.java,v $
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
import java.sql.SQLException;

/** This interface allows you to create a customized JDBC Connection Pool
 *  for the JDBCTranslatorCache & JDBCProductionCache classes.  This will
 *  be useful if you want to utilize an existing jdbc connection pool from
 *  and LDAP J2EE environment.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public interface IJDBCConnectionPool {
	/** Get a JDBC Connection from the pool */
	public Connection getConnection() throws SQLException;
	
	/** Return a JDBC Connection back to the pool */
	public void returnConnection(Connection c);
}
