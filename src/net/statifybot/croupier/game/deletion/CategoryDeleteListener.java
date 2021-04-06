package net.statifybot.croupier.game.deletion;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;

public class CategoryDeleteListener extends ListenerAdapter {

	public void onCategoryDelete(CategoryDeleteEvent e) {
		
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
		Document doc = collection.find(Filters.eq("roundCategory", e.getCategory().getIdLong())).first();
		
		if(doc != null) {
			TextChannel channel = e.getGuild().getTextChannelById(doc.getLong("channelid"));
			if(channel != null) {
				channel.delete().queue();
				
				collection.deleteOne(doc);
			}
		}
		
	}
	
}
