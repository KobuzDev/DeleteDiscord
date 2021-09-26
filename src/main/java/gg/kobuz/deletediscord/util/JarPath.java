package gg.kobuz.deletediscord.util;

import java.io.File;
import java.net.URISyntaxException;

public class JarPath {
	
	public File get() throws URISyntaxException {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI());
		return jarDir;
	}

}
