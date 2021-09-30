package gg.kobuz.deletediscord;

import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import gg.kobuz.deletediscord.media.AudioServer;
import gg.kobuz.deletediscord.media.DeleteMusicPlayer;
import gg.kobuz.deletediscord.util.ActivityTools;
import gg.kobuz.deletediscord.util.Logger;

public class CommandManager {
	
	public CommandManager(User user, TextChannel channel, String cmdentiere, Server server, DiscordApi api, DeleteDiscord discord, MessageCreateEvent event) {
		if(!cmdentiere.contains(" ")) {
			if(cmdentiere.equalsIgnoreCase("!disconnect")) {
				discord.disconnectChannel();
				ActivityTools.resetActivity();
				EmbedList.disconnect(channel, user);
			}else if(cmdentiere.contains("pause")) {
				discord.getPlayer().pause(user);
				Logger.info("Music paused");
			}else if(cmdentiere.contains("resume")) {
				discord.getPlayer().resume(user);
				Logger.info("MusicResume");
			}else if(cmdentiere.contains("info")) {
				EmbedList.info(channel, user);
			}else if(cmdentiere.contains("loop")) {
				if(discord.isLoop) {
					discord.isLoop = false;
					EmbedList.loop(channel, user, false);
					Logger.info("Loop enabled");
				}else {
					discord.isLoop = false;
					EmbedList.loop(channel, user, false);
					Logger.info("Loop disable");
				}
			}
		}else {
		String[] resulttab = cmdentiere.split(" ");
		String cmd = resulttab[0];
		String margs = resulttab[1];
		
		
		if(cmd.startsWith(discord.getConfig().getData("prefix") + "ytplay")) {
			String[] result = cmd.split(" ");
			event.getMessage().delete();
			ServerVoiceChannel voice = user.getConnectedVoiceChannel(server).get();
			if(api.getYourself().isConnected(voice)) {
				discord.getPlayer().playSong(margs, AudioServer.YOUTUBE, discord.getAudioConnect(), user);
			}else {
				discord.connectToTheChannelAndPlay(voice, margs, AudioServer.YOUTUBE, user);
			}
			
			
		}
		}
	}

}
