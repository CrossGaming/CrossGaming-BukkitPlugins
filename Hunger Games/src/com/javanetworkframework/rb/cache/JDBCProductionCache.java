/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/cache/JDBCProductionCache.java,v $
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

import java.util.Locale;

/** Class for a JDBC Production class.  Only approved translations will be
 *  found in the database with this cache.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public class JDBCProductionCache extends JDBCTranslatorCache {

	/** Constructs a JDBC Production Class
	 * 
	 * @param prefix Translator using this cache
	 * @param srcLocale Source Locale (programmer's locale)
	 * @param dstLocale Destination Locale (client's locale)
	 */
	public JDBCProductionCache(String prefix, Locale srcLocale, Locale dstLocale)
	{
		super(prefix, srcLocale, dstLocale);
	}

}
