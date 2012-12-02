package com.github.CorporateCraft.necessities.CCBot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import com.github.CorporateCraft.necessities.*;

public class CCBotLog
{
	Formatter form = new Formatter();
	public CCBotLog()
	{
		
	}
	public void Log(String Message)
	{
		Calendar c = Calendar.getInstance();
		String Second = Integer.toString(c.get(Calendar.SECOND));
		String Minute = Integer.toString(c.get(Calendar.MINUTE));
		String Hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String Day = Integer.toString(c.get(Calendar.DATE));
		String Month = Integer.toString(c.get(Calendar.MONTH));
		String Year = Integer.toString(c.get(Calendar.YEAR));
		String date = Month + "-" + Day + "-" + Year;
		Hour = CorTime(Hour);
		Minute = CorTime(Minute);
		Second = CorTime(Second);
		String time = "(" + Hour + ":" + Minute + ":" + Second + ")";
		String file = "plugins/Necessities/Logs/" + date + ".txt";
		File f = new File(file);
		if(!f.exists())
		{
			FileCreate(file);
		}
		try
		{
			FileWriter writer = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(time + " " + Message);
			bw.newLine();
			bw.close();
		}
		catch (Exception e){}
	}
	private String CorTime(String Time)
	{
		if(Time.length() == 1)
		{
			return "0" + Time;
		}
		return Time;
	}
	private void FileCreate(String file)
	{
		File f = new File(file);
		try
		{
			f.createNewFile();
		}
		catch (IOException e){}
		try
		{
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("#Log for " + file.replaceAll("plugins/Necessities/Logs/", "").replaceAll(".txt", ""));
			bw.newLine();
			bw.close();
		}
		catch (Exception e){}
	}
}