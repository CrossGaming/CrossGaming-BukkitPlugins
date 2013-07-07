/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/webtranslator/WebTranslator.java,v $
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
package com.javanetworkframework.rb.webtranslator;

import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Locale;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.javanetworkframework.patterns.threads.Barrier;
import com.javanetworkframework.rb.cache.TranslatorCacheInterface;
import com.javanetworkframework.rb.util.AbstractWebTranslator;
import com.javanetworkframework.rb.util.AsynchronousPrefetchThread;
import com.javanetworkframework.rb.util.TranslatorThread;


/** This is the main WebTranslator API that you should use.  If you are 
 * unsure which class to use, this is the one.  However, you should use
 * this inconjunction with Sun's ResourceBundle API.  Please see the javadoc
 * for more information.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public class WebTranslator extends AbstractWebTranslator {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(WebTranslator.class.getName());

	/** All available web translators in order of priority */
	private static String[] translators =
	{"com.javanetworkframework.rb.com.google.GoogleTranslatorRB",
	 "com.javanetworkframework.rb.com.altavista.AltaVistaTranslatorRB",
	 "com.javanetworkframework.rb.com.freetranslation.FreeTranslationTranslatorRB"
	};

	/** Returns a list of supported languages.
	 *  Format of Map is: key - 2 digit iso language code; value - Name of language
	 *  example: map.put("en", "English)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map SgetSupportedLanguages()
	{
		logger.entering(WebTranslator.class.getName(), "SgetSupportedLanguages");
		
		Map retVal = new TreeMap();
		retVal.put("de", "German");
		retVal.put("el", "Greek");
		retVal.put("en", "English");
		retVal.put("es", "Spanish");
		retVal.put("fr", "French");
		retVal.put("it", "Italian");
		retVal.put("ja", "Japanese");
		retVal.put("ko", "Korean");
		retVal.put("nl", "Dutch");
		retVal.put("no", "Norwegian");
		retVal.put("pt", "Portuguese");
		retVal.put("ru", "Russian");
		retVal.put("zh", "Chinese (Simplified)");
		retVal.put("zt", "Chinese (Traditional)");
		
		logger.exiting(WebTranslator.class.getName(), "SgetSupportedLanguages", retVal);
		return retVal;
	}

	/** Returns a list of supported tranlations
	 * Format of set is SrcLocale.toString() + "2" + DstLocale.toString() 
	 * example: en2es (English to Spanish)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set SgetSupportedTranslations() {
		logger.entering(WebTranslator.class.getName(), "SgetSupportedTranslations");

		Set retVal = new TreeSet();
		
		for(Iterator outer = SgetSupportedLanguages().keySet().iterator(); outer.hasNext();) {
			String oVal = (String) outer.next();
			for(Iterator inner = SgetSupportedLanguages().keySet().iterator(); inner.hasNext();) {
				String iVal = (String) inner.next();
				if(!oVal.equals(iVal)) {
					retVal.add(oVal + "2" + iVal);
				}
			}
		}
		
		logger.exiting(WebTranslator.class.getName(), "SgetSupportedTranslations");
		return retVal;
	}

	/* -------------------- INSTANCE SECTION --------------- */

	/* Returns name of this API */
	protected final String getPrefix() {
		logger.entering(getClass().getName(), "getPrefix");
		
		String retVal = "WebTranslator";
		
		logger.exiting(getClass().getName(), "getPrefix", retVal);
		return retVal;
	}

	/** Maintains a list of all web resource bundles in order of priority */
	@SuppressWarnings("rawtypes")
	private ArrayList rbal=null;

	/** Builds the list of all web resource bundles in order of priority */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private synchronized final ArrayList getRB() {
		logger.entering(getClass().getName(), "getRB");
		if(rbal == null) {
			rbal = new ArrayList();
			ResourceBundle rb;

			for(int i=0; i<translators.length; i++) {
				rb = null;
				try {
					rb = ResourceBundle.getBundle(translators[i],getDstLocale());
				}
				catch (MissingResourceException e) {
					rb = null;
				}
				if((rb!=null) && 
				   (rb.getLocale().getLanguage().equals(getDstLocale().getLanguage()))) {
					rbal.add(rb);
				}
			}
		}
		logger.entering(getClass().getName(), "getRB", rbal);
		return rbal;
	}
	
	/** Performs web translations
	 * 
	 * @param srcText Text to translate
	 * @param srcLocale locale of text to translate
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public final Object handleGetObject(String srcText, Locale srcLocale) {
		logger.entering(getClass().getName(), "handleGetObject",
				new Object[] {srcText, srcLocale});

		// A source of Norwegian is unsupported (none of the web services
		// support this right now)
		if(srcLocale.getLanguage().equals("no")) {
			return null;
		}
		
		// Storage for return value
		String retVal = null;
		
		// Get web resource bundles to use for translation
		ArrayList rbal = getRB();
		
		// Get cache
		TranslatorCacheInterface cache = getCache(srcLocale);

		// Check to see if translation can be found in cache
		retVal = cache.getTranslation(srcText);
		if((retVal==null) || (retVal.equals(""))) {
			// If there are no web resource bundles, there is nothing to do.
			if(rbal == null) {
				return null;
			}

			// Storage for translated text counters (to count how many
			// translations were the same)
			Map dstText = new TreeMap();
			
			// Loop through each resource bundle and execute in it's own thread
			Iterator iterator = rbal.iterator();
			ResourceBundle rb;
			Barrier b = new Barrier(rbal.size());
			int threadNum=0;
			String results[] = new String[rbal.size()];
			while(iterator.hasNext()) {
				new TranslatorThread(results, 
									(AbstractWebTranslator) iterator.next(),
									srcText, 
									threadNum++, 
									b,
									dstText,
									srcLocale).start();
			}

			// Wait for all resource bundles to complete
			b.barrierWait();
			
			// Loop through each result to determine which one to use based
			// on 2 algorithms:
			//  1) Best 2 out of 3 -- if 2 sources agree, use that source
			//  2) Priority -- if all sources disagree, take from
			//         a) Google
			//         b) AltaVista
			//         c) FreeTranslation
			int retValQuantity = 0;
			for(int i=0; i<results.length; i++) {
				if((results[i]!=null) && (!results[i].equals(""))) {
					int currQuantity = ((Integer)dstText.get(results[i])).intValue();
					if((retVal==null) || (currQuantity > retValQuantity)) {
						retVal = results[i];
						retValQuantity = currQuantity;
					}
				}
			}
			
			// Cache translation for future requests
			if(retVal!=null) {
				cache.saveTranslation(srcText, retVal);
				
				// If current thread is minimum priority, then we are probably
				// being called from a AsynchronousPrefetch thread.... therefore
				// don't start another one.
				if(Thread.currentThread().getPriority() != Thread.MIN_PRIORITY) {
					//Start an AsynchronousPrefetchThread that will automatically
					// populate the cache for the current srcText in all languages.
					// This way, future translations for the same srcText will be faster
					// This thread runs independent of the current thread and runs
					// in minimum priority.
					new AsynchronousPrefetchThread(srcText, srcLocale).run();
				}
			}
		}
		
		/* If we couldn't get a translation and neither the source and
		 * destination are english, let's perform a 2 way translation where
		 * first we translate from srcLocale to English, then we translate
		 * from English to dstLocale.  We pick English because it's the
		 * only language that supports translating to and from all other
		 * languages
		 */
		if((retVal==null) && 
				(!srcLocale.getLanguage().equals("en")) &&
				(!getDstLocale().getLanguage().equals("en"))) {
			Locale englishLocale = new Locale("en");
			
			// Translate from source to english
			AbstractWebTranslator src2english = (AbstractWebTranslator) ResourceBundle.getBundle(
					"com.javanetworkframework.rb.webtranslator.WebTranslator", 
					englishLocale);
			String englishTranslation = src2english.getString(srcText, srcLocale);

			if(englishTranslation!=null) {
				// Translate from english to destination
				AbstractWebTranslator english2dst = (AbstractWebTranslator) ResourceBundle.getBundle(
						"com.javanetworkframework.rb.webtranslator.WebTranslator", 
						getDstLocale());
				retVal = english2dst.getString(englishTranslation, englishLocale);
			}

			// Save results in cache for future requests
			if(retVal!=null) {
				cache.saveTranslation(srcText, retVal);
			}
		}

		logger.exiting(getClass().getName(), "handleGetObject", retVal);

		// Return translation
		return retVal;
	}
}
