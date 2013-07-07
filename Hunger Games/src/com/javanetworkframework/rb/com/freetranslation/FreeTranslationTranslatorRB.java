/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/com/freetranslation/FreeTranslationTranslatorRB.java,v $
 * **************************************************************************
 * Java Web Translator Project												*
 * http://sourceforge.net/projects/webtranslator/							*
 * **************************************************************************
 * CVS INFORMATION															*
 * Current revision $Revision: 1.2 $
 * On branch $Name: A0-2 $
 * Latest change by $Author: xyombie $ on $Date: 2004/09/18 00:44:17 $
 * **************************************************************************
 * Modification History:													*
 * VERSION    DATE	 AUTHOR	DESCRIPTION OF CHANGE	    				    *
 * -------	-------- ------	------------------------------------------------*
 *  V1.00	09/17/04  BRB	Initial Version.								*
 * **************************************************************************
 */
package com.javanetworkframework.rb.com.freetranslation;

import java.net.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.io.*;

import com.javanetworkframework.rb.util.AbstractWebHTTPTranslator;


/** FreeTranslation.com Translator Resource Bundle.  Uses FreeTranslation's web translator
 *  to translate text at run-time.  Translations are stored in
 *  a cache that can be serialized to disk or sent to a database via JDBC.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public class FreeTranslationTranslatorRB extends AbstractWebHTTPTranslator {

	/* -------------------- STATIC SECTION --------------- */
	
	private static final Logger logger = Logger.getLogger(FreeTranslationTranslatorRB.class.getName());

	/** Returns a list of supported languages.
	 *  Format of Map is: key - 2 digit iso language code; value - Name of language
	 *  example: map.put("en", "English)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map SgetSupportedLanguages()
	{
		logger.entering(FreeTranslationTranslatorRB.class.getName(), "SgetSupportedLanguages");
		Map retVal = new TreeMap();
		retVal.put("de", "German");
		retVal.put("es", "Spanish");
		retVal.put("fr", "French");
		retVal.put("it", "Italian");
		retVal.put("pt", "Portuguese");
		retVal.put("en", "English");
		retVal.put("nl", "Dutch");
		retVal.put("ru", "Russian");
		retVal.put("no", "Norwegian");
		retVal.put("zt", "TraditionalChinese");
		retVal.put("zh_TW", "TraditionalChinese");
		retVal.put("zh", "SimplifiedChinese");
		logger.exiting(FreeTranslationTranslatorRB.class.getName(), "SgetSupportedLanguages", retVal);
		return retVal;
	}
	
	/** Returns a list of supported tranlations
	 * Format of set is SrcLocale.toString() + "2" + DstLocale.toString() 
	 * example: en2es (English to Spanish)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set SgetSupportedTranslations() {
		logger.entering(FreeTranslationTranslatorRB.class.getName(), "SgetSupportedTranslations");
		Set retVal = new TreeSet(); 
		retVal.add("en2ru");
		retVal.add("ru2en");
		retVal.add("en2es");
		retVal.add("en2fr");
		retVal.add("en2de");
		retVal.add("en2it");
		retVal.add("en2nl");
		retVal.add("en2pt");
		retVal.add("en2no");
		retVal.add("es2en");
		retVal.add("fr2en");
		retVal.add("de2en");
		retVal.add("it2en");
		retVal.add("nl2en");
		retVal.add("pt2en");
		retVal.add("en2zh");
		retVal.add("en2zt");
		retVal.add("en2zh_TW");
		logger.exiting(FreeTranslationTranslatorRB.class.getName(), "SgetSupportedTranslations", retVal);
		return retVal;
	}
	
	/* -------------------- INSTANCE SECTION --------------- */
	
	/** Name of this translator */
	protected String getPrefix() {
		logger.entering(getClass().getName(), "getPrefix");
		String retVal = "FreeTranslation";
		logger.exiting(getClass().getName(), "getPrefix", retVal);
		return retVal;
	}
	
	/** Character encoding required by provider for input */
	protected String getInputCharSet() {
		logger.entering(getClass().getName(), "getInputCharSet");
		String retVal = "windows-1252";
		logger.exiting(getClass().getName(), "getInputCharSet", retVal);
		return retVal;
	}

	/** Character encoding used by provider for output */
	protected String getOutputCharSet() {
		logger.entering(getClass().getName(), "getOutputCharSet");
		String retVal = "windows-1252";
		logger.exiting(getClass().getName(), "getOutputCharSet", retVal);
		return retVal;
	}

	/** Returns a list of supported languages.
	 *  Format of Map is: key - 2 digit iso language code; value - Name of language
	 *  example: map.put("en", "English)
	 */
	@SuppressWarnings("rawtypes")
	public Map getSupportedLanguages() {
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
		String retVal;
		if(translation.equals("en2ru") ||
		   translation.equals("ru2en") ||
		   translation.equals("en2zh") ||
		   translation.equals("en2zh_TW") ||
		   translation.equals("en2zt")) {
			retVal = "http://ets6.freetranslation.com";
		} else {
			retVal = "http://ets.freetranslation.com";			
		}
		logger.exiting(getClass().getName(), "getURL", retVal);
		return retVal;
	}
	
	/** Returns parameters passed to web translator */
	@SuppressWarnings("rawtypes")
	protected String getParams(String srcLanguage, String dstLanguage, String text)
	{
		logger.entering(getClass().getName(), "getParams", 
				new Object[] { srcLanguage, dstLanguage, text });
		if(srcLanguage.equals("zh_TW")) {
			srcLanguage = "zt";
		}
		if(dstLanguage.equals("zh_TW")) {
			dstLanguage = "zt";
		}

		String retVal = null;
		Map languages = getSupportedLanguages();
		srcLanguage = (String) languages.get(srcLanguage);
		dstLanguage = (String) languages.get(dstLanguage);
		String language = srcLanguage + "/" + dstLanguage;

		try {
			retVal = URLEncoder.encode( "sequence", getInputCharSet()) + "=" + URLEncoder.encode( "core", getInputCharSet())  + "&" +
					URLEncoder.encode( "mode", getInputCharSet()) + "=" + URLEncoder.encode( "html", getInputCharSet()) + "&" +
					URLEncoder.encode( "template", getInputCharSet()) + "=" + URLEncoder.encode("results_en-us.htm", getInputCharSet()) + "&" +
					URLEncoder.encode( "language", getInputCharSet()) + "=" + URLEncoder.encode( language, getInputCharSet()) + "&" + 
					URLEncoder.encode( "srctext", getInputCharSet()) + "=" + URLEncoder.encode( text, getInputCharSet());
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
		String retVal = "<textarea name=\"dsttext\"";
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
