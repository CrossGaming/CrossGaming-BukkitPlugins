/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/JDBCTranslatorCache.java,v $
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

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;

/** Serialized Translator Cache - Serializes cache out to disk so the
 *  next time the program is restarted, the cache will remain intact.
 * 
 * @author Brian Blank
 * @version 1.0
 * @see com.javanetworkframework.rb.cache.TranslatorCacheInterface
 */
public class JDBCTranslatorCache implements TranslatorCacheInterface {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(JDBCTranslatorCache.class.getName());

	/** Serialized Map cache
	 * map				- key=prefix (ie. Google, AltaVista, etc...)	value=map1
	 * map1				- key=srcLocale									value=map2
	 * map2 			- key=dstLocale									value=map3
	 * map3	(serialized)- key=srcString									value=dstString
	 */
	@SuppressWarnings("rawtypes")
	private static final Map cache = new TreeMap();

	/** Directory where to save the serialized cache files */
	private static final Object synchronizationObject = new Object();

	/** JDBC Properties from properties file */
	private static Properties jdbcProperties = null;
	
	/** Filename of JDBC Properties file */
	private static String jdbcPropertiesFilename = null;

	/** Returns the current JDBC Properties Filename */
	public static String getJdbcPropertiesFilename() {
		logger.entering(JDBCTranslatorCache.class.getName(), "getJdbcPropertiesFilename");
		synchronized(synchronizationObject) {
			logger.exiting(JDBCTranslatorCache.class.getName(), 
					"getJdbcPropertiesFilename", jdbcPropertiesFilename);
			return jdbcPropertiesFilename;
		}
	}

	/** Set the JDBC Properties file and load file into memory
	 * 
	 * @param filename Filename of properties file
	 */
	public static void setJdbcPropertiesFilename(String filename) 
	{
		logger.entering(JDBCTranslatorCache.class.getName(), 
				"setJdbcPropertiesFilename", filename);

		synchronized(synchronizationObject) {
			try {
				jdbcPropertiesFilename = filename;
		        InputStream iStream;
		        jdbcProperties = new Properties();
	
	            iStream = new FileInputStream(filename);
	           	jdbcProperties.load(iStream);
	           	iStream.close();

	           	if( ! jdbcProperties.getProperty("jdbcDriver").equals("")) {
	           		connectionPool = new JDBCConnectionPool(
	           				jdbcProperties.getProperty("jdbcDriver"),
							jdbcProperties.getProperty("jdbcURL"),
							jdbcProperties.getProperty("jdbcUser"),
							jdbcProperties.getProperty("jdbcPass"),
							Integer.parseInt(jdbcProperties.getProperty("maxDBConnections")));
	           	}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.exiting(JDBCTranslatorCache.class.getName(), "setJdbcPropertiesFilename");
	}

	/** JDBC Connection pool */
	private static IJDBCConnectionPool connectionPool;

	/** Set the JDBC Connection Pool */
	public static void setConnectionPool(IJDBCConnectionPool pool) {
		logger.entering(JDBCTranslatorCache.class.getName(), 
				"setConnectionPool", pool);

		synchronized(synchronizationObject) {
			connectionPool = pool;
		}
		
		logger.exiting(JDBCTranslatorCache.class.getName(), 
				"setConnectionPool");
	}
	
	/* -------------------- INSTANCE SECTION --------------- */

	/** Cache for current instance */
	@SuppressWarnings("rawtypes")
	private final Map localCache;
	
	/** Source locale */
	private final Locale srcLocale;
	
	/** Destination locale */
	private final Locale dstLocale;
	
	private final String prefix;
	
	private final String getApproved() {
		logger.entering(this.getClass().getName(), "getApproved");
		
		String retVal = "";
		
		if(getClass().getName().endsWith("JDBCProductionCache")) {
			retVal = " " + jdbcProperties.getProperty(jdbcProperties.getProperty("dbType") + "_approved");
		}
		
		logger.exiting(this.getClass().getName(), "getApproved", retVal);
		return retVal;
	}
	
	/** Constructs a memory cache that serializes itself to disk
	 * 
	 * @param prefix Name to describe cache (ie, Google, AltaVista, FreeTranslation, WebTranslator)
	 * @param srcLocale Locale of source text (text to be translated)
	 * @param dstLocale Locale of destination text (translated text)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JDBCTranslatorCache(String prefix, Locale srcLocale, Locale dstLocale)
	{
		logger.entering(this.getClass().getName(), "JDBCTranslatorCache",
				new Object[] {prefix, srcLocale, dstLocale});

		Map prefixMap, srcLocaleMap;
		
		this.srcLocale = srcLocale;
		this.dstLocale = dstLocale;
		this.prefix = prefix;
		
		synchronized(cache) {
			if(cache.containsKey(prefix)) {
				prefixMap = (Map) cache.get(prefix);
			} else {
				prefixMap = new TreeMap();
				cache.put(prefix, prefixMap);
			}
			if(prefixMap.containsKey(srcLocale.toString())) {
				srcLocaleMap = (Map) prefixMap.get(srcLocale.toString());
			} else {
				srcLocaleMap = new TreeMap();
				prefixMap.put(srcLocale.toString(), srcLocaleMap);
			}
			if(srcLocaleMap.containsKey(dstLocale.toString())) {
				localCache = (Map) srcLocaleMap.get(dstLocale.toString());
			} else {
	            // Create empty memory cache 
		        localCache = new TreeMap();
				srcLocaleMap.put(dstLocale.toString(), localCache);
				
				// If preload is true, preload the cache by selecting all the data in the database for this
				// src/dst locale and prefix
				if(jdbcProperties.getProperty("preloadCache").equals("true")) {
					Connection c = null;
					try {
						c = connectionPool.getConnection();
						String sql = jdbcProperties.getProperty(jdbcProperties.getProperty("dbType") +
								"_preload_select") +
								getApproved();
						PreparedStatement selectAll = c.prepareStatement(sql);

						selectAll.setString(1, getSrcLocale().toString().toLowerCase());
						selectAll.setString(2, getDstLocale().toString().toLowerCase());
						selectAll.setString(3, prefix);
						ResultSet results = selectAll.executeQuery();
						while(results.next()) {
							 localCache.put(results.getString("src_text"), results.getString("dst_text"));
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						if(c!=null) connectionPool.returnConnection(c);
					}
				}
			}
		}
		logger.exiting(this.getClass().getName(), "JDBCTranslatorCache");
	}
	
	/** Returns locale of source text (text to be translated) */
	public final Locale getSrcLocale() {
		logger.entering(this.getClass().getName(), "getSrcLocale");
		Locale retVal = new Locale(srcLocale.getLanguage(), srcLocale.getCountry());
		logger.exiting(this.getClass().getName(), "getSrcLocale", retVal);
		return retVal;
	}

	/** Returns locale of destination text (translated text) */
	public final Locale getDstLocale() {
		logger.entering(this.getClass().getName(), "getDstLocale");
		Locale retVal = new Locale(dstLocale.getLanguage(), dstLocale.getCountry());
		logger.exiting(this.getClass().getName(), "getDstLocale", retVal);
		return retVal;
	}

	/** Save a translation to cache
	 * 
	 * @param srcText Text before translation
	 * @param dstText Text after translation
	 */
	@SuppressWarnings("unchecked")
	public final void saveTranslation(String srcText, String dstText)
	{
		logger.entering(this.getClass().getName(), "saveTranslation",
				new Object[] {srcText, dstText});

		if(dstText!=null) {
			synchronized(localCache) {
				
				String tempDstText = null;
				
				if(jdbcProperties.getProperty("safeSaves").equals("true")) {
					tempDstText = getTranslation(srcText);
				}
				
				if(tempDstText == null) {
					localCache.put(srcText, dstText);

					Connection c = null;
					try {
						c = connectionPool.getConnection();
						PreparedStatement insert = c.prepareStatement(
								jdbcProperties.getProperty(jdbcProperties.getProperty("dbType") + "_insert"));

						insert.setInt(1, srcText.hashCode());
						insert.setInt(2, dstText.hashCode());
						insert.setString(3, srcLocale.toString().toLowerCase());
						insert.setString(4, dstLocale.toString().toLowerCase());
						insert.setString(5, prefix);
						insert.setString(6, srcText);
						insert.setString(7, dstText);
						insert.executeUpdate();
					}
					catch(SQLException e) {
						e.printStackTrace();
					}
					finally {
						if(c!=null) connectionPool.returnConnection(c);
					}

				}
			}
		}
		logger.exiting(this.getClass().getName(), "saveTranslation");
	}
	
	/** Fetches a translation from cache
	 * 
	 * @param srcText Text before translation
	 * @return Text after translation or null if not found in cache
	 */
	@SuppressWarnings("unchecked")
	public final String getTranslation(String srcText) 
	{
		logger.entering(this.getClass().getName(), "getTranslation",
				srcText);

		String retVal = (String) localCache.get(srcText);
		
		if(retVal == null) {
			if(jdbcProperties.getProperty("safeSaves").equals("true")) {
				Connection c = null;
				try {
					c = connectionPool.getConnection();
					PreparedStatement select = c.prepareStatement(
							jdbcProperties.getProperty(jdbcProperties.getProperty("dbType") + "_select") +
							getApproved());

					select.setInt(1, srcText.hashCode());
					select.setString(2, srcText);
					select.setString(3, getSrcLocale().toString().toLowerCase());
					select.setString(4, getDstLocale().toString().toLowerCase());
					select.setString(5, prefix);
					ResultSet results = select.executeQuery();
					while(results.next()) {
						 localCache.put(srcText, results.getString("dst_text"));
						 retVal = results.getString("dst_text");
					}
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				finally {
					if(c!=null) connectionPool.returnConnection(c);
				}
			}
		}
		
		logger.exiting(this.getClass().getName(), "getTranslation",
				retVal);
		return retVal;
	}

	/** Resets the cache in memory and in the database to be empty
	 */
	public final void resetCache()
	{
		logger.entering(this.getClass().getName(), "resetCache");

		synchronized(localCache) {
			localCache.clear();
			Connection c = null;
			try {
				c = connectionPool.getConnection();
				PreparedStatement reset = c.prepareStatement(
						jdbcProperties.getProperty(jdbcProperties.getProperty("dbType") + "_reset"));

				reset.setString(1, getSrcLocale().toString().toLowerCase());
				reset.setString(2, getDstLocale().toString().toLowerCase());
				reset.setString(3, prefix);
				reset.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(c!=null) connectionPool.returnConnection(c);
			}
		}

		logger.exiting(this.getClass().getName(), "resetCache");
	}
	
	/** Returns an enumeration of all the pre-translated text */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final Enumeration getKeys() {
		logger.entering(this.getClass().getName(), "getKeys");

		Vector retVal = new Vector();
		
		synchronized (localCache) {
			Iterator iterator = localCache.keySet().iterator();
			while(iterator.hasNext()) {
				retVal.add(iterator.next());
			}
		}
		logger.exiting(this.getClass().getName(), "getKeys",
				retVal.elements());
		return retVal.elements();
	}
}
