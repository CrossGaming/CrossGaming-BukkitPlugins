/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/NoTranslatorCache.java,v $
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

import java.util.Vector;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Logger;

/** Cache that doesn't cache transatlations.  
 * Very fast for one time only translations.
 * 
 * @author Brian Blank
 * @version 1.0
 * @see com.javanetworkframework.rb.cache.TranslatorCacheInterface
 */
public class NoTranslatorCache implements TranslatorCacheInterface {
	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(NoTranslatorCache.class.getName());

	/* -------------------- INSTANCE SECTION --------------- */

	/** Source locale */
	private final Locale srcLocale;
	
	/** Destination locale */
	private final Locale dstLocale;
	
	/** Constructs a cache that doesn't cache
	 * 
	 * @param prefix Name to describe cache (ie, Google, AltaVista, FreeTranslation, WebTranslator)
	 * @param srcLocale Locale of source text (text to be translated)
	 * @param dstLocale Locale of destination text (translated text)
	 */
	public NoTranslatorCache(String prefix, Locale srcLocale, Locale dstLocale) {
		logger.entering(this.getClass().getName(), 
				"NoTranslatorCache", 
				new Object[] {prefix, srcLocale, dstLocale});

		this.srcLocale = srcLocale;
		this.dstLocale = dstLocale;

		logger.exiting(this.getClass().getName(), "NoTranslatorCache");
	}

	/** Doesn't do anything (throws away translation)
	 * 
	 * @param srcText Text before translation
	 * @param dstText Text after translation
	 */
	public void saveTranslation(String srcText, String dstText) {
		logger.entering(this.getClass().getName(), "saveTranslation", 
				new Object[] {srcText, dstText});
		
		logger.exiting(this.getClass().getName(), "saveTranslation");
	}

	/** Doesn't do anything (always returns null)
	 * 
	 * @param srcText Text before translation
	 * @return Text after translation or null if not found in cache
	 */
	public String getTranslation(String srcText) {
		logger.entering(this.getClass().getName(), "getTranslation", srcText);
		
		logger.exiting(this.getClass().getName(), "getTranslation", null);
		return null;
	}

	/** Returns an empty enumeration  */
	@SuppressWarnings("rawtypes")
	public Enumeration getKeys() {
		logger.entering(this.getClass().getName(), "getKeys");
		
		Enumeration retVal = new Vector().elements();
		
		logger.exiting(this.getClass().getName(), "getKeys", retVal);
		return retVal;
	}

	/** Doesn't do anything */
	public void resetCache() {
		logger.entering(this.getClass().getName(), "resetCache");
		logger.exiting(this.getClass().getName(), "resetCache");
	}

	/** Returns locale of source text (text to be translated) */
	public Locale getSrcLocale() {
		logger.entering(this.getClass().getName(), "getSrcLocale");

		logger.exiting(this.getClass().getName(), "getSrcLocale", srcLocale);
		return srcLocale;
	}

	/** Returns locale of destination text (translated text) */
	public Locale getDstLocale() {
		logger.entering(this.getClass().getName(), "getDstLocale");

		logger.exiting(this.getClass().getName(), "getDstLocale", dstLocale);
		return dstLocale;
	}
}
