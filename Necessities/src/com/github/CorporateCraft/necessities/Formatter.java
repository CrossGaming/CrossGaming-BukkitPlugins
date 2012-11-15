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
				if(CurrentWord.get(i).equals('_'))
				{
					NewWord.add(' ');
				}
				else if(CurrentWord.get(i-1).equals('_'))
				{
					NewWord.add(Character.toUpperCase(CurrentWord.get(i)));
				}
				else
				{
					NewWord.add(Character.toLowerCase(CurrentWord.get(i)));
				}
			}
		}
		String New = "";
		for(int i = 0; i < NewWord.size(); i++)
		{
			New = New + NewWord.get(i);
		}
		return New;
	}
	
	public static void ReadFile(String file, ArrayList<String> Info)
	{
		Info.clear();
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
		        Info.add(inputText);
		    }
		}
		catch (IOException ex){}
	}
	
	public static void WriteFile(String file, ArrayList<String> Info)
	{
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			for (int i = 0; i < Info.size(); i++)
			{
				bw.write(Info.get(i));
				bw.newLine();
			}
			bw.close();
		}
		catch (Exception e){}
	}
	
	public static Boolean FileEmpty(String file)
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