/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/SerializedTranslatorCache.java,v $
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
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
public final class SerializedTranslatorCache implements TranslatorCacheInterface {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(SerializedTranslatorCache.class.getName());

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
	private static String filePath = "";
	
	/** Returns the current directory where the serialized cache files are saved */
	public static String getFilePath() {
		logger.entering(SerializedTranslatorCache.class.getName(), "getFilePath");
		synchronized(synchronizationObject) {
			logger.exiting(SerializedTranslatorCache.class.getName(), "getFilePath");
			return filePath;
		}
	}
	
	/** Sets the directory where the serialized cache files are saved
	 * 
	 * @param filePath Directory on disk where to store serialized caches (ie. "C:\CacheDir" or "/var/webtranslator")
	 */
	public static void setFilePath(String filePath) {
		logger.entering(SerializedTranslatorCache.class.getName(),
			"setFilePath", filePath);
		synchronized(synchronizationObject) {
			// Don't accept a null filePath
			if(filePath != null) {
				// If the filepath is blank, set it back to the default filepath
				if(filePath.equals("")) {
					SerializedTranslatorCache.filePath = "";
				} else {
					// Remove ending / or \ in directory name
					if(filePath.endsWith(System.getProperty("file.separator"))) {
						filePath = filePath.substring(0, filePath.length()-1);
					}
					// Check if directory exists
					File dir = new File(filePath);
					if(dir.isDirectory()) {
						SerializedTranslatorCache.filePath = filePath + 
													System.getProperty("file.separator");
					}
				}
			}
		}
		logger.exiting(SerializedTranslatorCache.class.getName(),
				"setFilePath");
	}

	/** Determines if serialization is done with XML or native java serialization method, default is to use XML */
	private static Boolean serializeWithXML = Boolean.TRUE;

	/** Returns true if serialization is done using XML, otherwise false */
	public static boolean getSerializeWithXML() {
		logger.entering(SerializedTranslatorCache.class.getName(),
				"getSerializeWithXML");

		synchronized (serializeWithXML) {
			logger.exiting(SerializedTranslatorCache.class.getName(),
				"getSerializeWithXML", serializeWithXML);
			return serializeWithXML.booleanValue();
		}
	}
	
	/** Sets if serialization is done using XML or not */
	public static void setSerializedWithXML(boolean xml) {
		logger.entering(SerializedTranslatorCache.class.getName(),
			"setSerializeWithXML", Boolean.valueOf(xml));

		synchronized (serializeWithXML) {
			serializeWithXML = new Boolean(xml);
		}

		logger.exiting(SerializedTranslatorCache.class.getName(),
				"setSerializeWithXML");
	}
	
	/* -------------------- INSTANCE SECTION --------------- */

	/** Cache for current instance */
	@SuppressWarnings("rawtypes")
	private final Map localCache;
	
	/** Source locale */
	private final Locale srcLocale;
	
	/** Destination locale */
	private final Locale dstLocale;
	
	/** Filename to serialize current cache */
	private final String filename;

	/** Constructs a memory cache that serializes itself to disk
	 * 
	 * @param prefix Name to describe cache (ie, Google, AltaVista, FreeTranslation, WebTranslator)
	 * @param srcLocale Locale of source text (text to be translated)
	 * @param dstLocale Locale of destination text (translated text)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SerializedTranslatorCache(String prefix, Locale srcLocale, Locale dstLocale)
	{
		logger.entering(getClass().getName(), "SerializedTranslatorCache", 
				new Object[] {prefix, srcLocale, dstLocale});

		Map prefixMap, srcLocaleMap, tmpMap;
		
		this.srcLocale = srcLocale;
		this.dstLocale = dstLocale;
		
		filename = prefix + "-" + srcLocale + "2" + dstLocale + ".dat";

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
		        try {
		            // Load object from file
		        	boolean useXML = SerializedTranslatorCache.getSerializeWithXML();
		        	String filePath = SerializedTranslatorCache.getFilePath();
		        	if(!useXML) {
			        	ObjectInputStream in = new  ObjectInputStream(
			        								new FileInputStream(filePath + filename));
			        	tmpMap = (TreeMap) in.readObject();
		        	} else {
			        	XMLDecoder d = new XMLDecoder(
			        			new BufferedInputStream(new FileInputStream(filePath + filename)));
			        	tmpMap = (TreeMap) d.readObject();
			        	d.close();
		        	}
		        }
		        catch (FileNotFoundException e) {
		            // Create empty cache file if one doesn't already exist
		            tmpMap = new TreeMap();
		        }
		        catch (Exception e) {
		        	e.printStackTrace();

		        	// Create empty cache file if one doesn't already exist
		            tmpMap = new TreeMap();
		        }
		        localCache = tmpMap;
				srcLocaleMap.put(dstLocale.toString(), localCache);
			}
		}
		logger.exiting(getClass().getName(), "SerializedTranslatorCache");
	}
	
	/** Returns locale of source text (text to be translated) */
	public final Locale getSrcLocale() {
		logger.entering(getClass().getName(), "getSrcLocale");
		Locale retVal = new Locale(srcLocale.getLanguage(), srcLocale.getCountry());
		logger.exiting(getClass().getName(), "getSrcLocale", retVal);
		return retVal;
	}

	/** Returns locale of destination text (translated text) */
	public final Locale getDstLocale() {
		logger.entering(getClass().getName(), "getDstLocale");
		Locale retVal = new Locale(dstLocale.getLanguage(), dstLocale.getCountry());
		logger.exiting(getClass().getName(), "getDstLocale", retVal);
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
		logger.entering(getClass().getName(), "saveTranslation",
				new Object[] {srcText, dstText});
		if(dstText!=null) {
			synchronized(localCache) {
				localCache.put(srcText, dstText);
				serializeCacheToDisk();
			}
		}
		logger.exiting(getClass().getName(), "saveTranslation");
	}
	
	/** Serializes cache to disk.  You must be in a 
	 *  synchronized(localCache) block before calling this function
	 */
	private final void serializeCacheToDisk()
	{
		logger.entering(getClass().getName(), "serializeCacheToDisk");
		synchronized(localCache) {
	        try {
	            // Write contactList to file
	        	boolean useXML = SerializedTranslatorCache.getSerializeWithXML();
	        	String filePath = SerializedTranslatorCache.getFilePath();

	        	if(!useXML) {
		            ObjectOutputStream out = new ObjectOutputStream(
		                                        new FileOutputStream(filePath + filename));
		            out.writeObject(localCache);
	        	} else {
		        	XMLEncoder e = new XMLEncoder(
		        			new BufferedOutputStream(
		        					new FileOutputStream(filePath + filename)));
		        	e.writeObject(localCache);
					e.close();
	        	}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		}
		logger.exiting(getClass().getName(), "serializeCacheToDisk");
	}

	/** Fetches a translation from cache
	 * 
	 * @param srcText Text before translation
	 * @return Text after translation or null if not found in cache
	 */
	public final String getTranslation(String srcText) {
		logger.entering(getClass().getName(), "getTranslation", srcText);
		String retVal = (String) localCache.get(srcText);
		logger.exiting(getClass().getName(), "getTranslation", retVal);
		return retVal;
	}

	/** Resets the cache in memory and on disk to be empty
	 */
	public final void resetCache()
	{
		logger.entering(getClass().getName(), "resetCache");
		synchronized(localCache) {
			localCache.clear();
			serializeCacheToDisk();
		}
		logger.exiting(getClass().getName(), "resetCache");
	}
	
	/** Returns an enumeration of all the pre-translated text */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final Enumeration getKeys() {
		logger.entering(getClass().getName(), "getKeys");
		Vector retVal = new Vector();
		
		synchronized (localCache) {
			Iterator iterator = localCache.keySet().iterator();
			while(iterator.hasNext()) {
				retVal.add(iterator.next());
			}
		}
		logger.exiting(getClass().getName(), "getKeys", retVal.elements());
		return retVal.elements();
	}
}
