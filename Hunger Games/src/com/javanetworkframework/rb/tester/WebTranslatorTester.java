/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/rb/tester/WebTranslatorTester.java,v $
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
package com.javanetworkframework.rb.tester;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import com.javanetworkframework.rb.util.AbstractWebTranslator;


public class WebTranslatorTester {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
//		JDBCTranslatorCache.setJdbcPropertiesFilename(
//				"D:\\work\\java\\WebTranslator\\META-INF\\webtranslator_jdbc.properties");
//		AbstractWebTranslator.setCacheType(AbstractWebTranslator.CACHE_JDBCPROD);
		
		// Initialize test arguments
		Map srcText = new TreeMap();
		srcText.put("en", "Hello world!");
		srcText.put("es", "�Hola mundo!");
		srcText.put("fr", "Bonjour le monde!");
		srcText.put("de", "Hallo Welt!");
		srcText.put("nl", "Zeg aan hallo wereld!");
		srcText.put("it", "Ciao il mondo!");
		srcText.put("pt", "Oi mundo!");
		srcText.put("no", "Hei verden!");
		srcText.put("ru", "\u0417\u0434\u0440\u0430\u0432\u0441\u0442\u0432\u0443\u043b\u0442\u0435\u0021\u0020\u043c\u0438\u0440\u0021");
		srcText.put("zt", "\u4f60\u597d\u4e16\u754c\u0021");
		srcText.put("zh", "\u4f60\u597d\u4e16\u754c\u0021");
		srcText.put("el", "\u0393\u03b5\u03b9\u03ac\u03c3\u03bf\u03c5\u0020\u03ba\u03cc\u03c3\u03bc\u03bf\u03c2\u0021");
		srcText.put("ja", "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\u0021");
		srcText.put("ko", "\uc5ec\ubcf4\uc138\uc694\u0020\uc138\uacc4\u0021");
		
		Map srcText2 = new TreeMap(srcText);

		for(Iterator outer=srcText.keySet().iterator(); outer.hasNext();) {
			Locale srcLoc = new Locale((String) outer.next());
			String output = "";
			for(Iterator inner=srcText2.keySet().iterator(); inner.hasNext();) {
				Locale dstLoc = new Locale((String) inner.next());

				if(!srcLoc.getLanguage().equals(dstLoc.getLanguage())) {
					// Get resource bundle
					AbstractWebTranslator res = (AbstractWebTranslator) ResourceBundle.getBundle(
							"com.javanetworkframework.rb.webtranslator.WebTranslator",
							dstLoc);
					
					System.out.println("Converting " + srcLoc + " to " + dstLoc + "...");
					
					output += srcLoc + " to " + dstLoc + "\n";
		
					// Translate text
					String translation = res.getString((String)srcText.get(srcLoc.toString()), srcLoc);
					
					output += srcText.get(srcLoc.toString()) + " --> " + 
								translation +
								"\n";
				}
			}
			// Uses dialog box because a ms-dos prompt does a bad job showing unicode
			JOptionPane.showMessageDialog(null, output, "WebTranslatorTester", JOptionPane.INFORMATION_MESSAGE);
		}
		
		System.exit(2);
	}
}
