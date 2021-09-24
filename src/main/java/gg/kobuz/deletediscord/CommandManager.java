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
		
		if(cmdentiere.equalsIgnoreCase("!disconnect")) {
			discord.disconnectChannel();
			ActivityTools.resetActivity();
			EmbedList.disconnect(channel, user);
		}
		
		String[] resulttab = cmdentiere.split(" ");
		String cmd = resulttab[0];
		String margs = resulttab[1];
		
		
		if(cmd.startsWith("!ytplay")) {
			String[] result = cmd.split(" ");
			event.getMessage().delete();
			ServerVoiceChannel voice = user.getConnectedVoiceChannel(server).get();
			
			discord.connectToTheChannelAndPlay(voice, margs, AudioServer.YOUTUBE, user);
		}
	}

}
