package net.statifybot.croupier.game;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.utility.Emote;

public class Game {

	long channelId;
	TextChannel channel;
	long messageId;
	Category roundCat;
	Guild guild;

	public Game(TextChannel channel, Category roundCat) {
		this.channel = channel;
		this.channelId = this.channel.getIdLong();
		this.roundCat = roundCat;
		this.guild = channel.getGuild();

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
			Document doc = new Document("guildid", this.guild.getIdLong()).append("channelid", this.channelId)
					.append("messageid", this.messageId).append("roundCategory", this.roundCat.getIdLong());
			collection.insertOne(doc);
		});

	}

	public Game(long messageId) {
		this.messageId = messageId;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
		Document doc = collection.find(Filters.eq("messageid", this.messageId)).first();

		if (doc != null) {
			this.guild = Croupier.jda.getGuildById(doc.getLong("guildid"));
			this.channel = this.guild.getTextChannelById(doc.getLong("channelid"));
			this.channelId = channel.getIdLong();
			this.roundCat = this.guild.getCategoryById(doc.getLong("roundCategory"));
		}
	}
	
	public Game(Guild guild) {
		this.guild = guild;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
		Document doc = collection.find(Filters.eq("guildid", this.guild.getIdLong())).first();

		if (doc != null) {
			this.messageId = doc.getLong("messageid");
			this.channel = this.guild.getTextChannelById(doc.getLong("channelid"));
			this.channelId = channel.getIdLong();
			this.roundCat = this.guild.getCategoryById(doc.getLong("roundCategory"));
		}
	}
	
	public Category getRoundCategory() {
		return this.roundCat;
	}
	
	public Guild getGuild() {
		return this.guild;
	}
	
	public TextChannel getChannel() {
		return this.channel;
	}
	
	public long getMessageId() {
		return this.messageId;
	}

}
