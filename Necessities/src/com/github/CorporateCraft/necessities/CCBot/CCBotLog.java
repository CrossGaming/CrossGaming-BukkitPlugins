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
	public void log(String message)
	{
		Calendar c = Calendar.getInstance();
		String second = Integer.toString(c.get(Calendar.SECOND));
		String minute = Integer.toString(c.get(Calendar.MINUTE));
		String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String day = Integer.toString(c.get(Calendar.DATE));
		String month = Integer.toString(c.get(Calendar.MONTH)+1);
		String year = Integer.toString(c.get(Calendar.YEAR));
		String date = month + "-" + day + "-" + year;
		hour = corTime(hour);
		minute = corTime(minute);
		second = corTime(second);
		String time = "(" + hour + ":" + minute + ":" + second + ")";
		String file = "plugins/Necessities/Logs/" + date + ".txt";
		File f = new File(file);
		if(!f.exists())
			fileCreate(file);
		try
		{
			FileWriter writer = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(time + " " + message);
			bw.newLine();
			bw.close();
		}
		catch (Exception e){}
	}
	private String corTime(String time)
	{
		if(time.length() == 1)
			return "0" + time;
		return time;
	}
	private void fileCreate(String file)
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