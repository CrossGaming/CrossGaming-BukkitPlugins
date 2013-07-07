/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/tester/TestProgram.java,v $
 * **************************************************************************
 * Java Web Translator Project												*
 * http://sourceforge.net/projects/webtranslator/							*
 * **************************************************************************
 * CVS INFORMATION															*
 * Current revision $Revision: 1.1 $
 * On branch $Name: A0-2 $
 * Latest change by $Author: xyombie $ on $Date: 2004/09/18 00:44:18 $
 * **************************************************************************
 * Modification History:													*
 * VERSION    DATE	 AUTHOR	DESCRIPTION OF CHANGE	    				    *
 * -------	-------- ------	------------------------------------------------*
 *  V1.00	09/17/04  BRB	Initial Version.								*
 * **************************************************************************
 */
package com.javanetworkframework.rb.tester;

import java.util.*;

public class TestProgram {

	public static void main(String[] args) {

		Locale loc = new Locale(System.getProperty("user.language"), 
								System.getProperty("user.country"));

		ResourceBundle res = ResourceBundle.getBundle(
				"com.javanetworkframework.rb.webtranslator.WebTranslator", 
				loc);

		System.out.println(res.getString("Hello world!"));
	}
}
