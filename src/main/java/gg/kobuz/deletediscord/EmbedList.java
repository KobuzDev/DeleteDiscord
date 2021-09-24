package gg.kobuz.deletediscord;

import java.awt.Color;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

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

}
