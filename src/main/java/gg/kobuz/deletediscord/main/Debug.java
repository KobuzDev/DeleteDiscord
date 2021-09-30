package gg.kobuz.deletediscord.main;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import gg.kobuz.deletediscord.media.AudioServer;
import gg.kobuz.deletediscord.media.DeleteAudioBase;
import gg.kobuz.deletediscord.media.DeleteMusicPlayer;
import gg.kobuz.deletediscord.util.Logger;

public class Debug {
	
	public Debug(String token, String serverstr, String userstr) {
		if(serverstr == null || userstr == null) {
			Logger.err("The demo args is missing");
			System.exit(0);
		}
		Logger.info("Logging to the Discord");
		Logger.info("Token: " + token);
		DiscordApi api = new DiscordApiBuilder()
				.setToken(token)
				.login().join();
		Logger.info("Logged on " + api.getYourself().getDiscriminatedName());
		Server server = api.getServerById(serverstr).get();
		Logger.info("Server: " + server.getName());
		User user = server.getMemberById(userstr).get();
		Logger.info("User: " + user.getDisplayName(server));
		ServerVoiceChannel voice = user.getConnectedVoiceChannel(server).get();
		Logger.info("Logging to the channel");
		voice.connect().thenAccept(audioConnection -> {
		Logger.info("Logged !");
		DeleteMusicPlayer player = new DeleteMusicPlayer(api, null);
		player.playSong("", AudioServer.YOUTUBE, audioConnection, null);
		}).exceptionally(e -> {
		    e.printStackTrace();
		    return null;
		});
	}

}
