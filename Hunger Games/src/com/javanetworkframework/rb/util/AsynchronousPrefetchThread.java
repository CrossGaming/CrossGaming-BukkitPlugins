/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/util/AsynchronousPrefetchThread.java,v $
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

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.javanetworkframework.rb.webtranslator.WebTranslator;


public class AsynchronousPrefetchThread extends Thread {

	/* -------------------- STATIC SECTION --------------- */
	
	private static final Logger logger = 
		Logger.getLogger(AsynchronousPrefetchThread.class.getName());

	private static Boolean enabled = Boolean.TRUE;
	
	public static void setEnabled(boolean enabled) {
		logger.entering(AsynchronousPrefetchThread.class.getName(),
				"setEnabled", Boolean.valueOf(enabled));
		
		synchronized(AsynchronousPrefetchThread.enabled) {
			AsynchronousPrefetchThread.enabled = new Boolean(enabled);
		}
		
		logger.exiting(AsynchronousPrefetchThread.class.getName(), "setEnabled");
	}
	
	public static boolean getEnabled() {
		logger.entering(AsynchronousPrefetchThread.class.getName(),
				"getEnabled");
		
		synchronized(AsynchronousPrefetchThread.enabled) {
			logger.exiting(AsynchronousPrefetchThread.class.getName(), 
					"getEnabled", enabled);
			return enabled.booleanValue();
		}
	}
	
	/* -------------------- INSTANCE SECTION --------------- */
	
	private final Locale srcLoc;
	private final String srcText;

	public AsynchronousPrefetchThread(String srcText, Locale srcLoc) {
		super();
		logger.entering(getClass().getName(), "AsynchronousPrefetchThread",
				new Object[] {srcText, srcLoc});
		
		this.srcLoc = srcLoc;
		this.srcText = srcText;
		
		logger.exiting(getClass().getName(), "AsynchronousPrefetchThread");
	}

	@SuppressWarnings("rawtypes")
	public void run() {
		logger.entering(getClass().getName(), "run");

		//		setPriority(Thread.MIN_PRIORITY);
		
		if(AsynchronousPrefetchThread.getEnabled() == true) {
			Map supportedLan = WebTranslator.SgetSupportedLanguages();
			for(Iterator iterator = supportedLan.keySet().iterator(); iterator.hasNext();) {
				Locale dstLoc = new Locale((String) iterator.next());
				
				if(!srcLoc.getLanguage().equals(dstLoc.getLanguage())) {
					WebTranslator  res = (WebTranslator) ResourceBundle.getBundle(
							"com.javanetworkframework.rb.webtranslator.WebTranslator",
							dstLoc);
					
					Thread t = new TranslatorThread(new String[1], res, srcText, 0, null, new TreeMap(), srcLoc);
					t.setPriority(Thread.MIN_PRIORITY);
					t.start();
				}
			}
		}
		logger.exiting(getClass().getName(), "run");
	}
}
