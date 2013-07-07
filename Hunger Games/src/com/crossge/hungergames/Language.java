package com.crossge.hungergames;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import org.bukkit.configuration.file.YamlConfiguration;
import com.javanetworkframework.rb.util.AsynchronousPrefetchThread;

public class Language
{
	private File customConfigFile = new File("plugins/Hunger Games", "config.yml");
   	private YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	public String translate(String message)
	{
		String lang = locale();
		if(lang.equalsIgnoreCase("en"))
			return message;
		Locale loc = new Locale(lang);
		AsynchronousPrefetchThread.setEnabled(false);
	    ResourceBundle res = ResourceBundle.getBundle("com.javanetworkframework.rb.webtranslator.WebTranslator", loc);
	    return res.getString(message);
	}
	private String locale()
	{
		String lang = customConfig.getString("language");
		if(lang.equalsIgnoreCase("DE") || lang.equalsIgnoreCase("GERMAN"))
			return "de";
		else if(lang.equalsIgnoreCase("EL") || lang.equalsIgnoreCase("GREEK"))
			return "el";
		else if(lang.equalsIgnoreCase("ES") || lang.equalsIgnoreCase("SPANISH"))
			return "es";
		else if(lang.equalsIgnoreCase("FR") || lang.equalsIgnoreCase("FRENCH"))
			return "fr";
		else if(lang.equalsIgnoreCase("IT") || lang.equalsIgnoreCase("ITALIAN"))
			return "it";
		else if(lang.equalsIgnoreCase("JA") || lang.equalsIgnoreCase("JAPANESE"))
			return "ja";
		else if(lang.equalsIgnoreCase("KO") || lang.equalsIgnoreCase("KOREAN"))
			return "ko";
		else if(lang.equalsIgnoreCase("NL") || lang.equalsIgnoreCase("DUTCH"))
			return "nl";
		else if(lang.equalsIgnoreCase("NO") || lang.equalsIgnoreCase("NORWEGIAN"))
			return "no";
		else if(lang.equalsIgnoreCase("PT") || lang.equalsIgnoreCase("PORTUGUESE"))
			return "pt";
		else if(lang.equalsIgnoreCase("RU") || lang.equalsIgnoreCase("RUSSIAN"))
			return "ru";
		else if(lang.equalsIgnoreCase("ZH") || lang.equalsIgnoreCase("CHINESE (SIMPLIFIED)"))
			return "zh";
		else if(lang.equalsIgnoreCase("ZT") || lang.equalsIgnoreCase("CHINESE (TRADITIONAL)"))
			return "zt";
		else
			return "en";
	}
}