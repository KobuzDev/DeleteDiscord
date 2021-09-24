package gg.kobuz.deletediscord.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import gg.kobuz.deletediscord.util.Logger;

public class Configuration {
	
	private Properties prop;
	private File file;
	
	@SuppressWarnings("deprecation")
	public Configuration(File file) throws IOException {
		prop = new Properties();
		Logger.info(file.getPath());
		if(!file.exists()) {
			Properties create = new Properties();
			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
			if(input != null) {
				create.load(input);
			}else {
				throw new FileNotFoundException();
			}
			create.save(new FileOutputStream(file), "DeleteDiscord configuration");
		}
		prop.load(new FileInputStream(file));
	}
	
	public String getData(String tag) {
		return prop.getProperty(tag);
	}
	
	public void setData(String tag, String data) {
		prop.setProperty(tag, data);
		try {
			prop.save(new FileOutputStream(file), "DeleteDiscord configuration");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
