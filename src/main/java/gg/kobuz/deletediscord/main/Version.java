package gg.kobuz.deletediscord.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import gg.kobuz.deletediscord.util.Logger;

public class Version {
	
	public static void versionChecking(String version) {
		try {
            URL google = new URL("https://raw.githubusercontent.com/KobuzDev/DeleteDiscord/master/version.check");
            BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
            String reply; 
 
            while ((reply = in.readLine()) != null) {
                if(!reply.equals(version)) {
                	Logger.info("A new version is avalible. Please download on https://github.com/sedmelluq/lavaplayer/releases/tag/" + version);
                }else {
                	Logger.info("This version is up.");
                }
            }
            in.close(); 
 
        } catch (MalformedURLException me) {
        	me.printStackTrace();
        	System.exit(0);
 
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        	System.exit(0);
        }
	}

}
