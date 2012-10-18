package com.github.CorporateCraft.cceconomy;

import java.text.DecimalFormat;

public class Formatter
{
	public static boolean isLegal(String input)  
	{  
	   try  
	   {  
	      Double.parseDouble(input);  
	      return true;  
	   }  
	   catch(Exception e)  
	   {  
	      return false;  
	   }  
	}
	
	public static String roundTwoDecimals(double d)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		String newdf = df.format(d);
        return newdf;
	}
}