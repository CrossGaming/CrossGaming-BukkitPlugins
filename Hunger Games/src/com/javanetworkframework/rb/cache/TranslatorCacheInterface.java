/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/TranslatorCacheInterface.java,v $
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
import java.util.Locale;

/**  Translator Cache Interface - Interface used by all cache schemes
 * 
 * @author Brian Blank
 * @version 1.0
 */
public interface TranslatorCacheInterface {
	/** Save a translation to cache
	 * 
	 * @param srcText Text before translation
	 * @param dstText Text after translation
	 */
	public void saveTranslation(String srcText, String dstText);
	
	/** Fetches a translation from cache
	 * 
	 * @param srcText Text before translation
	 * @return Text after translation or null if not found in cache
	 */
	public String getTranslation(String srcText);
	
	/** Returns an enumeration of all the pre-translated text */
	@SuppressWarnings("rawtypes")
	public Enumeration getKeys();
	
	/** Resets the cache to be empty.
	 */
	public void resetCache();
	
	/** Returns locale of source text (text to be translated) */
	public Locale getSrcLocale();
	
	/** Returns locale of destination text (translated text) */
	public Locale getDstLocale();
}
