package gg.kobuz.deletediscord;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;

import gg.kobuz.deletediscord.main.Configuration;
import gg.kobuz.deletediscord.media.AudioServer;
import gg.kobuz.deletediscord.media.DeleteMusicPlayer;
import gg.kobuz.deletediscord.util.ActivityTools;
import gg.kobuz.deletediscord.util.Logger;

public class DeleteDiscord {
	
	public static DeleteDiscord theDelete;
	
	private DiscordApi api;
	private TextChannel command;
	private Configuration config;
	private DeleteMusicPlayer player;
	
	private AudioConnection audioconnect;
	
	public DeleteDiscord(Configuration config) {
		this.config = config;
		DeleteDiscord.theDelete = this;
		Logger.info("Logging to the Discord Server...");
		Logger.info("Token: " + config.getData("token"));
		DiscordApi api = new DiscordApiBuilder()
				.setToken(config.getData("token"))
				.login().join();
		
		ActivityTools.init(config.getData("activity-text"), ActivityType.valueOf(config.getData("activity-type")), api);
		
		command = api.getTextChannelById(config.getData("command-channel")).get();
		player = new DeleteMusicPlayer(api, this);
		
		api.addMessageCreateListener(event -> {
			if(!event.getMessageAuthor().asUser().get().isBot()) {
				if(event.getChannel().equals(command)) {
					if(event.getMessageContent().startsWith("!")) {
						User user = event.getMessageAuthor().asUser().get();
						Logger.info(user.getDiscriminatedName() + " executed a command: " + event.getMessageContent());
						new CommandManager(user, command, event.getMessageContent(), event.getServer().get(), api, this, event);
					}
				}
			}
		});
	}
	
	public AudioConnection connectToTheChannel(ServerVoiceChannel voice) {
		Logger.info("Logging to the channel");
		voice.connect().thenAccept(audioConnection -> {
		Logger.info("Logged !");
		audioconnect = audioConnection;
		}).exceptionally(e -> {
		    e.printStackTrace();
		    return null;
		});
		return audioconnect;
	}
	
	public void disconnectChannel() {
		audioconnect.close();
	}

	public DiscordApi getApi() {
		return api;
	}

	public void setApi(DiscordApi api) {
		this.api = api;
	}

	public TextChannel getCommand() {
		return command;
	}

	public void setCommand(TextChannel command) {
		this.command = command;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public AudioConnection getAudioConnect() {
		return audioconnect;
	}

	public void setAudioConnect(AudioConnection audioconnect) {
		this.audioconnect = audioconnect;
	}

	public DeleteMusicPlayer getPlayer() {
		return player;
	}

	public void connectToTheChannelAndPlay(ServerVoiceChannel voice, String url, AudioServer server, User user) {
		Logger.info("Logging to the channel");
		voice.connect().thenAccept(audioConnection -> {
		Logger.info("Logged !");
		audioconnect = audioConnection;
		player.playSong(url, AudioServer.YOUTUBE, audioconnect, user);
		}).exceptionally(e -> {
		    e.printStackTrace();
		    return null;
		});
	}
	
	
	
	
	
	
	
	

}
