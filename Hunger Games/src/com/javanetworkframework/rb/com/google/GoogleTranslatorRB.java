/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/com/google/GoogleTranslatorRB.java,v $
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
package com.javanetworkframework.rb.com.google;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import com.javanetworkframework.rb.util.AbstractWebHTTPTranslator;


/** Google Translator Resource Bundle.  Uses Google's web translator
 *  to translate text at run-time.  Translations are stored in
 *  a cache that can be serialized to disk or sent to a database via JDBC.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public class GoogleTranslatorRB extends AbstractWebHTTPTranslator {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = Logger.getLogger(GoogleTranslatorRB.class.getName());

	/** Returns a list of supported languages.
	 *  Format of Map is: key - 2 digit iso language code; value - Name of language
	 *  example: map.put("en", "English)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map SgetSupportedLanguages()
	{
		logger.entering(GoogleTranslatorRB.class.getName(), "SgetSupportedLanguages");
		Map retVal = new TreeMap();
		retVal.put("de", "Deutsch");
		retVal.put("es", "Espa�ol");
		retVal.put("fr", "Fran�ais");
		retVal.put("it", "Italiano");
		retVal.put("pt", "Portugu�s");
		retVal.put("en", "English");
		logger.exiting(GoogleTranslatorRB.class.getName(), "SgetSupportedLanguages", retVal);
		return retVal;
	}

	/** Returns a list of supported tranlations
	 * Format of set is SrcLocale.toString() + "2" + DstLocale.toString() 
	 * example: en2es (English to Spanish)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set SgetSupportedTranslations() {
		logger.entering(GoogleTranslatorRB.class.getName(), "SgetSupportedTranslations");
		Set retVal = new TreeSet();
		retVal.add("en2de");
		retVal.add("en2es");
		retVal.add("en2fr");
		retVal.add("en2it");
		retVal.add("en2pt");
		retVal.add("de2en");
		retVal.add("de2fr");
		retVal.add("es2en");
		retVal.add("fr2en");
		retVal.add("fr2de");
		retVal.add("it2en");
		retVal.add("pt2en");
		logger.exiting(GoogleTranslatorRB.class.getName(), "SgetSupportedTranslations", retVal);
		return retVal;
	}
	
	/* -------------------- INSTANCE SECTION --------------- */
	
	/** Name of this translator */
	protected String getPrefix() {
		logger.entering(getClass().getName(), "getPrefix");
		String retVal = "Google";
		logger.exiting(getClass().getName(), "getPrefix", retVal);
		return retVal;
	}
	
	/** Character encoding required by provider for input */
	protected String getInputCharSet() {
		logger.entering(getClass().getName(), "getInputCharSet");
		String retVal = "UTF-8";
		logger.exiting(getClass().getName(), "getInputCharSet", retVal);
		return retVal;
	}

	/** Character encoding used by provider for output */
	protected String getOutputCharSet() {
		logger.entering(getClass().getName(), "getOutputCharSet");
		String retVal = "UTF-8";
		logger.exiting(getClass().getName(), "getOutputCharSet", retVal);
		return retVal;
	}

	/** Returns a list of supported languages.
	 *  Format of Map is: key - 2 digit iso language code; value - Name of language
	 *  example: map.put("en", "English)
	 */
	@SuppressWarnings("rawtypes")
	public Map getSupportedLanguages()
	{
		logger.entering(getClass().getName(), "getSupportedLanguages");
		Map retVal = SgetSupportedLanguages();
		logger.exiting(getClass().getName(), "getSupportedLanguages", retVal);
		return retVal;
	}
	
	/** Returns a list of supported tranlations
	 * Format of set is SrcLocale.toString() + "2" + DstLocale.toString() 
	 * example: en2es (English to Spanish)
	 */
	@SuppressWarnings("rawtypes")
	public Set getSupportedTranslations() {
		logger.entering(getClass().getName(), "getSupportedTranslations");
		Set retVal = SgetSupportedTranslations();
		logger.exiting(getClass().getName(), "getSupportedTranslations", retVal);
		return retVal;
	}
	
	/** Returns URL to web translator */
	protected String getURL(String translation)
	{
		logger.entering(getClass().getName(), "getURL", translation);
		String retVal = "http://translate.google.com/translate_t";
		logger.exiting(getClass().getName(), "getURL", retVal);
		return retVal;
	}
	
	/** Returns parameters passed to web translator */
	protected String getParams(String srcLanguage, String dstLanguage, String text)
	{
		logger.entering(getClass().getName(), "getParams", 
				new Object[] { srcLanguage, dstLanguage, text });
		String retVal = null;
		try {
			retVal = URLEncoder.encode( "text", getInputCharSet()) + "=" + URLEncoder.encode( text, getInputCharSet())  + "&" +
					URLEncoder.encode( "langpair", getInputCharSet()) + "=" + URLEncoder.encode( srcLanguage + "|" + dstLanguage, getInputCharSet()) + "&" +
					URLEncoder.encode( "hl", getInputCharSet()) + "=" + URLEncoder.encode("en", getInputCharSet()) + "&" +
					URLEncoder.encode( "ie", getInputCharSet()) + "=" + URLEncoder.encode( getInputCharSet(), getInputCharSet()) + "&" + 
					URLEncoder.encode( "oe", getInputCharSet()) + "=" + URLEncoder.encode( getInputCharSet(), getInputCharSet()) + "&" +
					URLEncoder.encode( "submit", getInputCharSet()) + "=" + URLEncoder.encode("translate", getInputCharSet());
		} catch (UnsupportedEncodingException e) {
			System.err.println(e);
		}
		logger.exiting(getClass().getName(), "getParams", retVal);
		return retVal;
	}
	
	/** Returns tag name where translation can be found. */
	protected String getStartSearchText()
	{
		logger.entering(getClass().getName(), "getStartSearchText");
		String retVal = "<textarea name=q";
		logger.exiting(getClass().getName(), "getStartSearchText", retVal);
		return retVal;
	}

	/** Text that indicates the end of the translation */
	protected String getEndSearchText()
	{
		logger.entering(getClass().getName(), "getEndSearchText");
		String retVal = "</textarea>";
		logger.exiting(getClass().getName(), "getEndSearchText", retVal);
		return retVal;
	}
	
	/** Text that indicates server is busy */
	protected String getServerBusyError() {
		logger.entering(getClass().getName(), "getServerBusyError");
		String retVal = "Server doesn't return server busy error";
		logger.exiting(getClass().getName(), "getServerBusyError", retVal);
		return retVal;
	}

}
