package gg.kobuz.deletediscord.util;

import java.nio.file.Path;

public class Logger {
	
	private static fr.flowarg.flowlogger.Logger log;
	private static boolean isDebug;
	
	public static void init(Path path, boolean isDebug) {
		log = new fr.flowarg.flowlogger.Logger("[DeleteDiscord]", path);
		Logger.isDebug = isDebug;
	}
	
	public static void info(String msg) {
		log.info(msg);
	}
	
	public static void err(String msg) {
		log.err(msg);
	}
	
	public static void debug(String msg) {
		if(isDebug) {
			log.debug(msg);
		}
	}

}
