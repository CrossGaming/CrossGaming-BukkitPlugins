/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/util/AbstractWebHTTPTranslator.java,v $
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
 *  V1.00	08/05/04  BRB	Initial Version.								*
 *  V1.01   09/17/04  BRB   Fixed bug to allow unknown countries to still	*
 * 							 work with just the language code               *
 * **************************************************************************
 */
package com.javanetworkframework.rb.util;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import com.javanetworkframework.rb.cache.*;
import com.javanetworkframework.utils.html.HTMLDecoder;



/** Abstract Translator HTTP Resource Bundle.  This is the super class
 * for all HTTP Web Translators.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public abstract class AbstractWebHTTPTranslator extends AbstractWebTranslator  {
	/* -------------------- ABSTRACT SECTION --------------- */

	/** Returns a list of supported languages 
	 * key is 2 letter language ISO code
	 * value is Text bases translation of 2 letter ISO code
	 */
	@SuppressWarnings("rawtypes")
	abstract public Map getSupportedLanguages();

	/** Returns a list of supported translations 
	 * Each member should be in the form:
	 * 		 srcLocale.toString() + "2" + dstLocale.toString()
	 */
	@SuppressWarnings("rawtypes")
	abstract public Set getSupportedTranslations();

	/** Returns URL to web resrouce that will perform translation */
	abstract protected String getURL(String translation);
	
	/** Returns POST parameters to web site to do translation */
	abstract protected String getParams(String srcLanguage, String dstLanguage, String text);

	/** HTML Open Tag that contains translation */
	abstract protected String getStartSearchText();

	/** HTML Close Tag that contains translation */
	abstract protected String getEndSearchText();
	
	/** Unicode character set for input (ie UTF-8) */
	abstract protected String getInputCharSet();
	
	/** Unicode character set for output (ie UTF-8) */
	abstract protected String getOutputCharSet();

	/** Error message displayed by server when it is busy */
	abstract protected String getServerBusyError();
	
	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(AbstractWebHTTPTranslator.class.getName());
	
	/** Controls if website should be used for translation */
	private static Boolean webEnabled = Boolean.TRUE;

	/** Determines if web site will be used for translations */
	public static boolean getWebEnabled() {
		logger.entering(AbstractWebHTTPTranslator.class.getName(),
				"getWebEnabled");
		synchronized(webEnabled) {
			logger.exiting(AbstractWebHTTPTranslator.class.getName(),
				"getWebEnabled", webEnabled);
			return webEnabled.booleanValue();
		}
	}
	
	/** Sets if web site should be used for translations */
	public static void setWebEnabled(boolean enabled) {
		logger.entering(AbstractWebHTTPTranslator.class.getName(),
			"setWebEnabled", Boolean.valueOf(enabled));
		synchronized(webEnabled) {
			webEnabled = Boolean.valueOf(enabled);
		}
		logger.exiting(AbstractWebHTTPTranslator.class.getName(),
			"setWebEnabled");
	}
	
	/* -------------------- INSTANCE SECTION --------------- */

	/** Get's translation from either memory cache, otherwise from website if
	 *  not found in memory
	 * 
	 * @param srcText Text in Source Language to translate
	 * @param srcLocale Locale of srcText
	 * @return Translated text
	 */
	public Object handleGetObject(String srcText, Locale srcLocale) 
	{
		logger.entering(AbstractWebHTTPTranslator.class.getName(),
				"handleGetObject", new Object[] {srcText, srcLocale} );
		TranslatorCacheInterface cache = getCache(srcLocale);
		String retVal = srcText;
		retVal = cache.getTranslation(srcText);
		if(retVal == null) {
			retVal = webTranslate(srcLocale, getDstLocale(), srcText);
			if(retVal != null) {
				cache.saveTranslation(srcText, retVal);
			}
			// If this is production mode, don't return the computed value
			if(getCacheType() == AbstractWebTranslator.CACHE_JDBCPROD) {
				retVal = null;
			}
		}
		logger.exiting(AbstractWebHTTPTranslator.class.getName(),
				"handleGetObject", retVal);
		return retVal;
	}
	
	/** Returns current cached values
	 * 
	 * @return Enumeration of text in cache that was alreay translated
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getKeys() {
		logger.entering(AbstractWebHTTPTranslator.class.getName(),
			"getKeys");
		TranslatorCacheInterface cache = getCache();
		logger.exiting(AbstractWebHTTPTranslator.class.getName(),
			"getKeys", cache.getKeys());
		return cache.getKeys();
	}

	/** Translates text to destination language using web
	 *  language translator
	 * 
	 * @param srcLocal Source locale to translate from
	 * @param dstLocale Destination locale to translate to
	 * @param text Text in srcLocale to translate
	 * @return Translated text or null if translation was not possible
	 */
	@SuppressWarnings("rawtypes")
	public final String webTranslate(Locale srcLocale, Locale dstLocale, String text) {
		logger.entering(AbstractWebHTTPTranslator.class.getName(),
			"webTranslate", new Object[] {srcLocale, dstLocale, text});

		synchronized(webEnabled) {
			if(webEnabled.booleanValue() == false) {
				logger.exiting(AbstractWebHTTPTranslator.class.getName(),
						"webTranslate", "[null because webEnabled==false]");
				return null;
			}
		}
		
		if((text==null) || (text.equals(""))) {
			logger.exiting(AbstractWebHTTPTranslator.class.getName(),
					"webTranslate", "[null because text==null||text=='']");
			return null;
		}
		
		// Input string is assumed to be english, so if destination is also
		// english, just return the input string
		String srcLanguage = srcLocale.toString().toLowerCase();
		String dstLanguage = dstLocale.toString().toLowerCase();

		if(dstLanguage.equals(srcLanguage)) {
			logger.exiting(AbstractWebHTTPTranslator.class.getName(),
					"webTranslate", "[null because dstLanguage==srcLanguage]");
			return text;
		}
		
		/* -- BRB - 9/17/04 -- Fix to allow unknown countries to still work with known languages */
		Set supportedTranslations = getSupportedTranslations();
		if(! supportedTranslations.contains(srcLanguage+"2"+dstLanguage)) {
			srcLanguage = srcLocale.getLanguage().toString().toLowerCase();
			dstLanguage = dstLocale.toString().toLowerCase();
			
			if(! supportedTranslations.contains(srcLanguage+"2"+dstLanguage)) {
				srcLanguage = srcLocale.toString().toLowerCase();
				dstLanguage = dstLocale.getLanguage().toString().toLowerCase();
				
				if(! supportedTranslations.contains(srcLanguage+"2"+dstLanguage)) {
					logger.exiting(AbstractWebHTTPTranslator.class.getName(),
							"webTranslate", "[null because !supportedTranslation]");
					return null;
				}
			}
		}
		/* -- BRB - 9/17/04 -- End of Fix */

		// On server busy, retry will be true
		boolean retry;
		int maxRetry = 10;
		
		// String to hold translation
		String translation=null;
		
		do {
			retry = false;
			
			// HTTP Connection to Web Translator
			HttpURLConnection connection = null;
			
			// Returned translated document from Web Site
			String htmlDoc = "";
	
			try {
				// Get form parameters
				String params = getParams(srcLanguage, dstLanguage, text);
				// Open connection to server
				URL server = new URL(getURL(srcLocale.toString() + "2" + dstLocale.toString()));
				connection = (HttpURLConnection)server.openConnection();
	
				//	post the parameters
				connection.setDoOutput(true);
				connection.setDoInput(true);
				byte[] parameterAsBytes = params.getBytes();
				// Some web services refuses connection from Java Client, so pretend we are IE
				connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322)");
				OutputStream wr =connection.getOutputStream(); 
				wr.write(parameterAsBytes);
				wr.flush();
				wr.close();
	
				// now let's get the results
				connection.connect();
				int responseCode = connection.getResponseCode();
				if(responseCode == 200) {
					InputStream in = connection.getInputStream();
					BufferedReader inReader = new BufferedReader(new InputStreamReader(in, getOutputCharSet()));
					String line;
					while((line=inReader.readLine())!=null) {
						htmlDoc += line + "\n";
					}
				} else {
					System.err.println("Invalid response code: " + responseCode +
							", " + this.getClass().getName());
				}
			}
			catch (IOException e) {
				htmlDoc = getServerBusyError();
			}
			catch (Exception e) {
				e.printStackTrace();
				// System.exit(1);
				htmlDoc = "";
			}
			finally {
				// Close connection
				if(connection != null) {
					connection.disconnect();
				}
			}
	

			// If HTML Document is empty, translation didn't work
			if(!htmlDoc.equals("")) {
				// Find the translated string in the HTML Document
				int startIndex = htmlDoc.indexOf(getStartSearchText());
				int endIndex = htmlDoc.indexOf(getEndSearchText());
				if((startIndex == -1) || (endIndex == -1)) {
					// Is the server busy?
					if((maxRetry > 0) && 
					   (htmlDoc.indexOf(getServerBusyError()) != -1)) {
						retry = true;
						maxRetry--;
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							
						}
					}/* else {
						new Exception("Index out of range, unknown error.").printStackTrace();
					}*/
				} else {
					translation = htmlDoc.substring(
							htmlDoc.indexOf(getStartSearchText()), 
							htmlDoc.indexOf(getEndSearchText()));
					translation = translation.substring(translation.indexOf(">") + 1);

					// Decode HTML Tag Codes  (ie. &lt; is "<")
					translation = HTMLDecoder.decode(translation);
				}
			}
		} while(retry);

		logger.exiting(AbstractWebHTTPTranslator.class.getName(),
				"webTranslate", translation);

		return translation;
	}
}
