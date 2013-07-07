/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/util/TranslatorThread.java,v $
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

import java.util.Map;
import java.util.Locale;
import java.util.logging.Logger;

import com.javanetworkframework.patterns.threads.Barrier;


/** Performs http translation in a separate thread.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public class TranslatorThread extends Thread {
	
	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(TranslatorThread.class.getName());
	
	/* -------------------- INSTANCE SECTION --------------- */

	/** Storage for translation results */
	private String[] results;
	
	/** Resource Bundle that will perform translation */
	private AbstractWebTranslator rb;
	
	/** Text to translate */
	private String srcText;

	/** Locale of text to translate */
	private Locale srcLocale;
	
	/** Unique number of this thread */
	private int threadNum;
	
	/** Barrier to notify to main thread that this thread is now done */
	private Barrier b;
	
	/** Contains a count of how many translations had the same translation */
	@SuppressWarnings("rawtypes")
	private Map dstText;
	
	/** Contructs translation thread
	 * 
	 * @param results Where to place results
	 * @param rb Resource bundle to use
	 * @param srcText text to translate
	 * @param threadNum number of thread
	 * @param b Barrier to notify main thread when done
	 * @param dstText Another variable to place return results (to count how many results are the same)
	 * @param srcLocale Locale of source text
	 */
	@SuppressWarnings("rawtypes")
	public TranslatorThread(String[] results, AbstractWebTranslator rb, 
							String srcText, int threadNum, Barrier b,
							Map dstText, Locale srcLocale) {
		super();
		
		logger.entering(getClass().getName(), "TranslatorThread",
				new Object[] {results, rb, srcText, 
								new Integer(threadNum), b, dstText, srcLocale});
		this.results = results;
		this.rb = rb;
		this.srcText = srcText;
		this.threadNum = threadNum;
		this.b = b;
		this.dstText = dstText;
		this.srcLocale = srcLocale;

		logger.exiting(getClass().getName(), "TranslatorThread");
	}

	/** Run's the main thread.  Don't call this directly.  Instead call start() */
	@SuppressWarnings("unchecked")
	public void run() {
		logger.entering(getClass().getName(), "run");
		
		try {
			// Translate text
			String result = rb.getString(srcText, srcLocale);
			
			// Save results
			synchronized(results) {
				results[threadNum] = result;
			}
			
			// Save a count of how many translations had the same translation as this one
			if((result!=null) && (!result.equals(""))) {
				synchronized(dstText) {
					if(dstText.containsKey(result)) {
						dstText.put(result, 
								new Integer(((Integer)dstText.get(result)).intValue()+1));
					} else {
						dstText.put(result, new Integer(1));
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			synchronized(results) {
				results[threadNum] = null;
			}
		} finally {
			// Notify main thread that this thread is now complete
			if(b!=null) {
				b.barrierPost();
			}
		}

		logger.exiting(getClass().getName(), "run");
	}
}
