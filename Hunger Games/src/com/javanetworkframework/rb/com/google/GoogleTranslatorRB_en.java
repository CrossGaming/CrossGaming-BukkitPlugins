/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/com/google/GoogleTranslatorRB_en.java,v $
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

/** Google Translator Resource Bundle for English (en).
 *  This class is necessary because if it didn't exist, the parent class GoogleTranslatorRB would be used
 *  and getLocale().getLanguage() would return a blank string resulting in an English only interface
 *  no matter what you set the Locale to.
 * 
 * @author Brian Blank
 * @version 1.0
 */
public final class GoogleTranslatorRB_en extends GoogleTranslatorRB {

}
