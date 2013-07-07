/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/MemoryOnlyTranslatorCache.java,v $
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

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;

/** Memory Translator Cache - Caches translations to memory. 
 * Cache is lost when JVM is recycled.
 * 
 * @author Brian Blank
 * @version 1.0
 * @see com.javanetworkframework.rb.cache.TranslatorCacheInterface
 */
public class MemoryOnlyTranslatorCache implements TranslatorCacheInterface {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(MemoryOnlyTranslatorCache.class.getName());

	/** Serialized Map cache
	 * map				- key=prefix (ie Google, AltaVista, etc,,,)	value=map1
	 * map1				- key=srcLocale									value=map2
	 * map2 			- key=dstLocale									value=map3
	 * map3	(serialized)- key=srcString									value=dstString
	 */
	@SuppressWarnings("rawtypes")
	private static final Map cache = new TreeMap();
	
	/* -------------------- INSTANCE SECTION --------------- */

	/** Cache for current instance */
	@SuppressWarnings("rawtypes")
	protected Map localCache;
	
	/** Source locale */
	private final Locale srcLocale;
	
	/** Destination locale */
	private final Locale dstLocale;
	
	/** Constructs a memory only translator cache
	 * 
	 * @param prefix Name to describe cache (ie, Google, AltaVista, FreeTranslation, WebTranslator)
	 * @param srcLocale Locale of source text (text to be translated)
	 * @param dstLocale Locale of destination text (translated text)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MemoryOnlyTranslatorCache(String prefix, Locale srcLocale, Locale dstLocale) {
		logger.entering(this.getClass().getName(), 
				"MemoryOnlyTranslatorCache", 
				new Object[] {prefix, srcLocale, dstLocale});

		Map prefixMap, srcLocaleMap;
		
		this.srcLocale = srcLocale;
		this.dstLocale = dstLocale;
		
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
	        	// Create empty cache 
		        localCache = new TreeMap();
				srcLocaleMap.put(dstLocale.toString(), localCache);
			}
		}

		logger.exiting(this.getClass().getName(), "MemoryOnlyTranslatorCache");
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
	public final void saveTranslation(String srcText, String dstText) {
		logger.entering(this.getClass().getName(), "saveTranslation",
				new Object[] {srcText, dstText});
		if(dstText!=null) {
			synchronized(localCache) {
				localCache.put(srcText, dstText);
			}
		}
		logger.entering(this.getClass().getName(), "saveTranslation");
	}

	/** Fetches a translation from cache
	 * 
	 * @param srcText Text before translation
	 * @return Text after translation or null if not found in cache
	 */
	public final String getTranslation(String srcText) {
		logger.entering(this.getClass().getName(), "getTranslation",
				srcText);

		String retVal = (String) localCache.get(srcText);
		
		logger.exiting(this.getClass().getName(), "getTranslation",
				retVal);
		return retVal;
	}

	/** Resets the cache in memory and on disk to be empty */
	public final void resetCache() {
		logger.entering(this.getClass().getName(), "resetCache");

		synchronized(localCache) {
			localCache.clear();
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
		
		logger.exiting(this.getClass().getName(), "getKeys", retVal.elements());
		return retVal.elements();
	}
}
