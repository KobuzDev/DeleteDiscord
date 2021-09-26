package gg.kobuz.deletediscord.main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import gg.kobuz.deletediscord.DeleteDiscord;
import gg.kobuz.deletediscord.util.DateUtil;
import gg.kobuz.deletediscord.util.JarPath;
import gg.kobuz.deletediscord.util.Logger;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class Main {
	
	public static String version = "1.0_BETA";
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		OptionParser parser = new OptionParser();
		parser.accepts("debug");
		parser.accepts("demo");
		OptionSpec<String> debugUserSpec = parser.accepts("user").withOptionalArg();
		OptionSpec<String> debugServerSpec = parser.accepts("server").withOptionalArg();
		OptionSpec<String> debugTokenSpec = parser.accepts("token").withOptionalArg();
		OptionSpec<String> unknownSpec = parser.nonOptions();
        OptionSet optionset = parser.parse(args);
        List<String> list = optionset.valuesOf(unknownSpec);
        String path = new JarPath().get().getPath();
        File file = new File(path);
        Logger.init(new File(file, "log").toPath().resolve("log-" + DateUtil.dateFormat2(new Date(System.currentTimeMillis())) + ".log"), optionset.has("debug"));
        Logger.info("Starting deleteDiscord");
        Version.versionChecking(version);
        Logger.info("Path" + path);
        if(optionset.has("demo")) {
        	new Debug(debugTokenSpec.value(optionset), debugServerSpec.value(optionset), debugUserSpec.value(optionset));
        	return;
        }
        Configuration config = new Configuration(new File(path, "config.properties"));
        
        DeleteDiscord delete = new DeleteDiscord(config);
	}

}
