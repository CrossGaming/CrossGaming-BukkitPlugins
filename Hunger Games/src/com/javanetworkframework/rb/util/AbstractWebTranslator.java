/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/util/AbstractWebTranslator.java,v $
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
package com.javanetworkframework.rb.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.javanetworkframework.rb.cache.*;


/** super class for all Web Translators.  This class directly 
 * extends ResourceBundle.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public abstract class AbstractWebTranslator extends ResourceBundle {

	/* -------------------- ABSTRACT SECTION --------------- */

	/** Returns name of current translation provider */
	abstract protected String getPrefix();
	
	/** Performs translation 
	 * 
	 * @param srcText text to translate
	 * @param srcLocale locale of text to translate
	 * @return Returns translated text
	 */
	abstract public Object handleGetObject(String srcText, Locale srcLocale);

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(AbstractWebTranslator.class.getName());
	
	/** Controls access to cache file */
	private static final Object synchronizationObject = new Object();

	/** Source language, default is English (en) */
	private static Locale defaultSrcLocale = new Locale("en", "US");

	/** Returns current source language */
	public static Locale getDefaultSrcLocale() {
		logger.entering(AbstractWebTranslator.class.getName(), 
				"getDefaultSrcLocale");
		synchronized(synchronizationObject) {
			logger.exiting(AbstractWebTranslator.class.getName(), 
				"getDefaultSrcLocale", defaultSrcLocale);
			return defaultSrcLocale;
		}
	}
	
	/** Sets programmer's locale
	 *
	 * @param srcLanguage Locale of the programmer using this API
	 */
	public static void setDefaultSrcLocale(Locale srcLocale) {
		logger.entering(AbstractWebTranslator.class.getName(), 
			"setDefaultSrcLocale", srcLocale);
		synchronized(synchronizationObject) {
			logger.exiting(AbstractWebTranslator.class.getName(), 
					"setDefaultSrcLocale");
			AbstractWebTranslator.defaultSrcLocale = srcLocale;
		}
	}
	
	/** Don't use any cache */
	public final static int CACHE_NOCACHE = 1;
	
	/** Use a memory only cache */
	public final static int CACHE_MEMONLY = 2;
	
	/** Use a serialized memory cache */
	public final static int CACHE_SERIALIZED = 3;
	
	/** Use a JDBC Cache */
	public final static int CACHE_JDBC = 4;
	
	/** Use a Production JDBC Cache */
	public final static int CACHE_JDBCPROD = 5;

	/** Current cache scheme in use */
	private static int cacheType = CACHE_SERIALIZED;
	
	/** Returns cache scheme in use */
	public static int getCacheType() {
		logger.entering(AbstractWebTranslator.class.getName(), 
				"getCacheType");
		synchronized(synchronizationObject) {
			logger.exiting(AbstractWebTranslator.class.getName(), 
				"getCacheType", new Integer(cacheType));
			return cacheType;
		}
	}
	
	/** Sets new cache scheme
	 * 
	 * @param newCacheType one of CACHE_NOCACHE, CACHE_MEMONLY, CACHE_SERIALIZED
	 */
	public static void setCacheType(int newCacheType) {
		logger.entering(AbstractWebTranslator.class.getName(), 
			"setCacheType", new Integer(newCacheType));
		switch(newCacheType) {
			case CACHE_NOCACHE:
			case CACHE_MEMONLY:
			case CACHE_SERIALIZED:
			case CACHE_JDBC:
			case CACHE_JDBCPROD:
				synchronized(synchronizationObject) {
					cacheType = newCacheType;
				}
				break;
			default:
				System.err.println("Invalid cache type");
				break;
		}
		logger.exiting(AbstractWebTranslator.class.getName(), 
			"setCacheType");
	}

	/* -------------------- INSTANCE SECTION --------------- */
	
	/** Saves the programmer's locale to the local instance.  This makes
	 * this class thread safe so we know that the source locale is not 
	 * going to change mid-way through the program.
	 */
	private final Locale srcLocale = getDefaultSrcLocale();

	/** Returns source locale of this instance */
	public Locale getSrcLocale() {
		logger.entering(getClass().getName(), "getSrcLocale");
		Locale retVal = srcLocale;
		logger.exiting(getClass().getName(), "getSrcLocale", retVal);
		return retVal;
	}
	
	/** Performs translation using instance source locale */
	public Object handleGetObject(String srcText) {
		logger.entering(getClass().getName(), "handleGetObject", srcText);
		Object retVal = handleGetObject(srcText, getSrcLocale());
		logger.exiting(getClass().getName(), "handleGetObject", retVal);
		return retVal;
	}
	
	/** Performs translation using locale specified in parameter
	 * 
	 * @param srcText Text to translate
	 * @param srcLocale Locale of text to translate
	 * @return Returns translation
	 */
	public String getString(String srcText, Locale srcLocale) {
		logger.entering(getClass().getName(), "getString", 
				new Object[] {srcText, srcLocale} );
		String retVal = (String) handleGetObject(srcText, srcLocale);
		logger.exiting(getClass().getName(), "getString", retVal);
		return retVal;
	}

	/** Get's the destination locale of this class */
	protected final Locale getDstLocale() {
		logger.entering(getClass().getName(), "getDstLocale");
		Locale retVal;

		/* If super.getLocale() is blank, that means we are using the 
		 * default locale, so just return the source locale
		 */
		if((getLocale() == null) || (getLocale().getLanguage().equals(""))) {
			retVal = getSrcLocale();
		} else {
			retVal = getLocale();
		}

		logger.exiting(getClass().getName(), "getDstLocale", retVal);
		return retVal;
	}

	/** Returns cache using instance source locale */
	protected TranslatorCacheInterface getCache() {
		logger.entering(getClass().getName(), "getCache");
		TranslatorCacheInterface retVal = getCache(getSrcLocale());
		logger.exiting(getClass().getName(), "getCache", retVal);
		return retVal;
	}

	/** Returns cache for source locale specified in parameter list
	 * 
	 * @param srcLocale Source locale to get cache for
	 * @return cache of specified source locale
	 */
	protected TranslatorCacheInterface getCache(Locale srcLocale) {
		logger.entering(getClass().getName(), "getCache", srcLocale);
		TranslatorCacheInterface retVal;
		switch(cacheType) {
			case CACHE_NOCACHE:
				retVal = new NoTranslatorCache(getPrefix(), srcLocale, getDstLocale());
			case CACHE_MEMONLY:
				retVal = new MemoryOnlyTranslatorCache(getPrefix(), srcLocale, getDstLocale());
			case CACHE_JDBC:
				retVal = new JDBCTranslatorCache(getPrefix(), srcLocale, getDstLocale());
			case CACHE_JDBCPROD:
				retVal = new JDBCProductionCache(getPrefix(), srcLocale, getDstLocale());
			case CACHE_SERIALIZED:
			default:
				retVal = new SerializedTranslatorCache(getPrefix(), srcLocale, getDstLocale());
		}
		logger.exiting(getClass().getName(), "getCache", retVal);
		return retVal;
	}

	/** Resets the cache memory */
	public void resetCache() {
		logger.entering(getClass().getName(), "resetCache");

		TranslatorCacheInterface cache = getCache();
		cache.resetCache();

		logger.exiting(getClass().getName(), "resetCache");
	}

	/** Returns an enumeration of all texts that have been translated so far
	 * and saved to cache 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration getKeys() {
		logger.entering(getClass().getName(), "getKeys");
		Enumeration retVal = getCache().getKeys();
		logger.exiting(getClass().getName(), "getKeys", retVal);
		return retVal;
	}
}
