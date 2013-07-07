/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/utils/html/HTMLDecoder.java,v $
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
package com.javanetworkframework.utils.html;

import java.util.logging.Logger;

/** Translates the codes in an HTMLString to it's value (ie. "&lt;" becomes "<") 
 *  This class should be replaced with an XML parser...
 */
public class HTMLDecoder {

	private static final Logger logger = 
		Logger.getLogger(HTMLDecoder.class.getName());

	/** Decodes HTML String (ie. "&lt;" becomes "<")
	 * 
	 * @param htmlString html string
	 * @return decoded values
	 */
	public final static String decode(String htmlString) {
		logger.entering(HTMLDecoder.class.getName(), "decode", htmlString);

		if(htmlString!=null) {
			htmlString = htmlString.replaceAll("&lt;", "<");
			htmlString = htmlString.replaceAll("&gt;", ">");
			htmlString = htmlString.replaceAll("&quot;", "\"");
			htmlString = htmlString.replaceAll("&amp;", "&");
			htmlString = htmlString.replaceAll("&lsquo;", "�");
	
			htmlString = htmlString.replaceAll("&#39;", "'");
		}
		
		logger.exiting(HTMLDecoder.class.getName(), "decode", htmlString);

		return htmlString;
	}

}
