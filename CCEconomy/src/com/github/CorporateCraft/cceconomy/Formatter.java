package com.github.CorporateCraft.cceconomy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Formatter
{
	public Formatter()
	{
		
	}
	public boolean isLegal(String input)  
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
	
	public String roundTwoDecimals(double d)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		String newdf = df.format(d);
        return newdf;
	}
	
	public String capFirst(String toCap)
	{
		ArrayList<Character> currentWord = new ArrayList<Character>();
		ArrayList<Character> newWord = new ArrayList<Character>();
		for(int i =0; i < toCap.length(); i++)
		{
			currentWord.add(toCap.charAt(i));
		}
		for(int i = 0; i < currentWord.size(); i++)
		{
			if(i == 0)
			{
				newWord.add(Character.toUpperCase(currentWord.get(i)));
			}
			else
			{
				if(currentWord.get(i).equals('_'))
				{
					newWord.add(' ');
				}
				else if(currentWord.get(i-1).equals('_'))
				{
					newWord.add(Character.toUpperCase(currentWord.get(i)));
				}
				else
				{
					newWord.add(Character.toLowerCase(currentWord.get(i)));
				}
			}
		}
		String neww = "";
		for(int i = 0; i < newWord.size(); i++)
		{
			neww = neww + newWord.get(i);
		}
		return neww;
	}
	
	public void readFile(String file, ArrayList<String> info)
	{
		info.clear();
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null)
		        {
		         	break;
		        }
		        info.add(inputText);
		    }
		}
		catch (IOException ex){}
	}
	
	public void writeFile(String file, ArrayList<String> info)
	{
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			for (int i = 0; i < info.size(); i++)
			{
				bw.write(info.get(i));
				bw.newLine();
			}
			bw.close();
		}
		catch (Exception e){}
	}
	
	public boolean fileEmpty(String file)
	{
		try
		{
			FileInputStream fi = new FileInputStream(new File(file));
			if (fi.read() == -1)  
			{  
				return true;
			} 
		}
		catch (FileNotFoundException e){}
		catch(IOException e){}  
		return false;
	}
}