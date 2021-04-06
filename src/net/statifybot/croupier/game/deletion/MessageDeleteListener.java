package net.statifybot.croupier.game.deletion;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;

public class MessageDeleteListener extends ListenerAdapter {

	public void onGuildMessageDelete(GuildMessageDeleteEvent e) {

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
		Document doc = collection.find(Filters.eq("messageid", e.getMessageIdLong())).first();

		if (doc != null) {
			TextChannel channel = e.getGuild().getTextChannelById(doc.getLong("channelid"));
			if (channel != null) {
				channel.delete().queue();
			}

			Category cat = e.getGuild().getCategoryById(doc.getLong("roundCategory"));
			if (cat != null) {
				cat.delete().queue();
			}

			collection.deleteOne(doc);
		}
	}

}
