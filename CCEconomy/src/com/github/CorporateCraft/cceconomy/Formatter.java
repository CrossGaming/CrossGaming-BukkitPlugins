package com.github.CorporateCraft.cceconomy;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
	
	public static String CapFirst(String ToCap)
	{
		ArrayList<Character> CurrentWord = new ArrayList<Character>();
		ArrayList<Character> NewWord = new ArrayList<Character>();
		for(int i =0; i < ToCap.length(); i++)
		{
			CurrentWord.add(ToCap.charAt(i));
		}
		for(int i = 0; i < CurrentWord.size(); i++)
		{
			if(i == 0)
			{
				NewWord.add(Character.toUpperCase(CurrentWord.get(i)));
			}
			else
			{
				NewWord.add(Character.toLowerCase(CurrentWord.get(i)));
			}
		}
		String New = "";
		for(int i = 0; i < NewWord.size(); i++)
		{
			New = New + NewWord.get(i);
		}
		return New;
	}
}