package com.github.CorporateCraft.necessities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Formatter
{
	public Formatter()
	{
		
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
		         	break;
		        info.add(inputText);
		    }
		}
		catch (IOException ex){}
	}
	public String readFileStr(String file)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
		    FileReader reader = new FileReader(file);
		    BufferedReader buff = new BufferedReader(reader);
		    while(true)
		    {
		    	String inputText = buff.readLine();
		        if(inputText == null && sb.length() > 1)
		        {
	          		sb.deleteCharAt(sb.length()-1);
	           		sb.deleteCharAt(sb.length()-1);
	           		sb.append(".");
	           		break;
		        }
		        if(!inputText.startsWith("#"))
		        	sb.append(inputText).append(", ");
		    }
		}
		catch (IOException ex)
		{
		    return null;
		}
		String allFile = sb.toString();
		return allFile;
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
				return true;
		}
		catch (FileNotFoundException e){}
		catch(IOException e){}  
		return false;
	}
}