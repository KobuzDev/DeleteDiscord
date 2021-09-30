package gg.kobuz.deletediscord;

import java.awt.Color;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import gg.kobuz.deletediscord.main.Main;

public class EmbedList {
	
	public static void notFound(TextChannel channel, User user) {
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle("Aucun résultat")
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		channel.sendMessage(embed);
	}
	
	public static void playingSong(TextChannel channel, User user, AudioTrack track) {
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle("Ecoute en cours")
				.addField("Nom", track.getInfo().title)
				.addField("Auteur", track.getInfo().author)
				.addField("Lien", track.getInfo().uri)
				.setUrl(track.getInfo().uri)
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		channel.sendMessage(embed);
	}
	
	public static void disconnect(TextChannel channel, User user) {
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle("Déconnecté du salon")
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		channel.sendMessage(embed);
	}
	
	public static void addedPlaylist(TextChannel channel, User user, AudioTrack track, int placeIn) {
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle("Ajouté à la file d'attente")
				.addField("Nom", track.getInfo().title)
				.addField("Auteur", track.getInfo().author)
				.addField("Lien", track.getInfo().uri)
				.addField("Position", String.valueOf(placeIn))
				.setUrl(track.getInfo().uri)
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		channel.sendMessage(embed);
	}
	
	public static void pauseResume(TextChannel channel, User user, boolean isPaused) {
		EmbedBuilder embed = new EmbedBuilder()
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		if(isPaused) {
			embed.setTitle("Musique mis en pause");
		}else {
			embed.setTitle("Musique sorti de pause");
		}
		channel.sendMessage(embed);
	}
	
	public static void info(TextChannel channel, User user) {
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle("DeleteDiscord - Made by Kobuz")
				.setDescription("A simple music player bot for Discord in Java")
				.addField("Version", Main.version)
				.setUrl("https://github.com/KobuzDev/DeleteDiscord")
				.addField("Discord", "https://discord.io/PheonixInc")
				.addInlineField("Twitch", "https://twitch.tv/kobuztv")
				.addInlineField("GitHub", "https://github.com/KobuzDev")
				.addInlineField("Twitter", "https://twitter.com/KobuzTV")
				.addInlineField("Instagram", "https://instagram.com/kobuztv")
				.addInlineField("PayPal", "https://paypal.me/kobuzgangx")
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		channel.sendMessage(embed);
	}
	
	public static void loop(TextChannel channel, User user, boolean isLoop) {
		EmbedBuilder embed = new EmbedBuilder()
				.setColor(Color.MAGENTA)
				.setAuthor(user);
		if(isLoop) {
			embed.setTitle("La musique sera joué en boucle");
		}else {
			embed.setTitle("La musique ne sera plus joué en boucle");
		}
		channel.sendMessage(embed);
	}

}
