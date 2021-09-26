package gg.kobuz.deletediscord.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import gg.kobuz.deletediscord.util.Logger;

public class Configuration {
	
	private Properties prop;
	private File file;
	
	@SuppressWarnings("deprecation")
	public Configuration(File file) throws IOException {
		prop = new Properties();
		if(!file.exists()) {
			Properties create = new Properties();
			InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
			if(input != null) {
				create.load(input);
			}else {
				throw new FileNotFoundException();
			}
			create.save(new FileOutputStream(file), "DeleteDiscord configuration");
		}
		prop.load(new FileInputStream(file));
		if(!prop.containsKey("token") || prop.getProperty("token").equals("0")) {
			
			Logger.err("The token is not specified.");
			Logger.info("Please enter the token:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String token = reader.readLine();
		    prop.setProperty("token", token);
		    Logger.info("The token is " + token);
		}
		if(!prop.containsKey("command-channel") || prop.getProperty("command-channel").equals("0")) {
			Logger.err("The channel of command is not specified.");
			Logger.info("Please enter the id of channel:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String id = reader.readLine();
		    prop.setProperty("command-channel", id);
		    Logger.info("The id is " + id);
		
		}
		if(!prop.containsKey("activity-type")) {
			prop.setProperty("activity-type", "PLAYING");
		}
		if(!prop.containsKey("activity-text")) {
			prop.setProperty("activity-text", "paypal.me/kobuzgangx");
		}
		prop.save(new FileOutputStream(file), "#DeleteDiscord configuration");
		prop.load(new FileInputStream(file));
	}
	
	public String getData(String tag) {
		return prop.getProperty(tag);
	}
	
	@SuppressWarnings("deprecation")
	public void setData(String tag, String data) {
		prop.setProperty(tag, data);
		try {
			prop.save(new FileOutputStream(file), "DeleteDiscord configuration");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void save() throws FileNotFoundException {
		prop.save(new FileOutputStream(file), "#DeleteDiscord configuration");
	}

	public File getFile() {
		return file;
	}
	
	

}
