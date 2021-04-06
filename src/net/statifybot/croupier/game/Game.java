package net.statifybot.croupier.game;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.utility.Emote;

public class Game {

	long channelId;
	TextChannel channel;
	long messageId;
	Category roundCat;
	
	public Game(TextChannel channel, Category roundCat) {
		this.channel = channel;
		this.channelId = this.channel.getIdLong();
		this.roundCat = roundCat;
		
		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Roulette");
		msg.setDescription("React with " + new Emote("join").getMention() + " to join the Round");
		msg.addField("Players", new Emote("yellowdot").getMention() + " Waiting for Players...", false);
		msg.setColor(0x33cc33);
		msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
		channel.sendMessage(msg.build()).queue(message -> {
			this.messageId = message.getIdLong();
			
			message.addReaction(new Emote("join").getEmote()).queue();
			
			MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
			Document doc = new Document("guildid", this.channel.getGuild().getIdLong()).append("channelid", this.channelId).append("messageid", this.messageId).append("roundCategory", this.roundCat.getIdLong());
			collection.insertOne(doc);
		});
		
		
	}
	
	public Game(long messageId) {
		this.messageId = messageId;
	}
	
}
