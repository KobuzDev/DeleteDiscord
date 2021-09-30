package gg.kobuz.deletediscord.media;

import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.audio.AudioSourceBase;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;

import gg.kobuz.deletediscord.DeleteDiscord;
import gg.kobuz.deletediscord.EmbedList;
import gg.kobuz.deletediscord.util.ActivityTools;
import gg.kobuz.deletediscord.util.Logger;

public class DeleteMusicPlayer {
	
	private DiscordApi api;
	
	private AudioPlayer player;
	private AudioTrack track;
	private User user;

	private DeleteDiscord delete;
	
	public DeleteMusicPlayer(DiscordApi api, DeleteDiscord delete) {
		super();
		this.api = api;
		this.delete = delete;
	}

	public void playSong(String url, AudioServer server, AudioConnection audioconnect, User user) {
		AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
		switch (server) {
		case YOUTUBE:
			playerManager.registerSourceManager(new YoutubeAudioSourceManager());
			break;
		case TWITCH:
			playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
			break;
		case BANDCAMP:
			playerManager.registerSourceManager(new BandcampAudioSourceManager());
			break;
		case HTTP:
			playerManager.registerSourceManager(new HttpAudioSourceManager());
			break;
		case VIMEO:
			playerManager.registerSourceManager(new VimeoAudioSourceManager());
			break;
		default:
			playerManager.registerSourceManager(new YoutubeAudioSourceManager());
			break;
		}
		player = playerManager.createPlayer();
		TextChannel text = delete.getCommand();
		// Create an audio source and add it to the audio connection's queue
		AudioSource source = (AudioSource) new DeleteAudioBase(api, player);
		audioconnect.setAudioSource(source);
		player.addListener(new AudioEvent());
		// You can now use the AudioPlayer like you would normally do with Lavaplayer, e.g.,
		playerManager.loadItem(url, new AudioLoadResultHandler() {
			
			
			
		    @Override
		    public void trackLoaded(AudioTrack track) {
		        player.playTrack(track);
		        DeleteDiscord.theDelete.getPlayer().user = user;
		        EmbedList.playingSong(text, user, track);
		        Logger.info("Starting song " + track.getInfo().title + " by " + track.getInfo().author + " on " + audioconnect.getChannel().getName() + " requested by " + user.getDiscriminatedName());
		        ActivityTools.updateActivity(track);
		    }
		  

		    @Override
		    public void playlistLoaded(AudioPlaylist playlist) {
		        for (AudioTrack track : playlist.getTracks()) {
		            player.playTrack(track);
		        }
		    }

		    @Override
		    public void noMatches() {
		        audioconnect.close();
		        EmbedList.notFound(text, user);
		    }

		    @Override
		    public void loadFailed(FriendlyException throwable) {
		        // Notify the user that everything exploded
		    }
		
		});
		
		track = player.getPlayingTrack();
	}
	
	public void playTrack(AudioTrack track) {
		player.playTrack(track);
		EmbedList.playingSong(api.getTextChannelById(DeleteDiscord.theDelete.getConfig().getData("channel-command")).get(), user, track);
        Logger.info("Starting song " + track.getInfo().title + " by " + track.getInfo().author + " on " + DeleteDiscord.theDelete.getAudioConnect().getChannel().getName() + " requested by " + user.getDiscriminatedName());
        ActivityTools.updateActivity(track);
	}
	
	public void pause(User user) {
		api.unsetActivity();
		EmbedList.pauseResume(delete.getCommand(), user, true);
	}
	
	public void resume(User user) {
		EmbedList.pauseResume(delete.getCommand(), user, false);
		ActivityTools.updateActivity(track);
	}
	
	


}
