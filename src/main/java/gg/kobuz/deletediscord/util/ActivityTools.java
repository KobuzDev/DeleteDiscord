package gg.kobuz.deletediscord.util;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class ActivityTools {
	
	private static String text;
	private static ActivityType type;
	private static DiscordApi api;
	
	public static void init(String text, ActivityType type, DiscordApi api) {
		api.updateActivity(type, text);
		ActivityTools.text = text;
		ActivityTools.type = type;
		ActivityTools.api = api;
	}
	
	public static void updateActivity(AudioTrack track) {
		api.updateActivity(ActivityType.LISTENING, track.getInfo().title + " | " + track.getInfo().author);
	}

	public static void resetActivity() {
		api.updateActivity(type, text);
	}

}
