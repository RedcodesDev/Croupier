package net.statifybot.croupier.game.deletion;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;

public class ChannelDeleteListener extends ListenerAdapter {

	public void onTextChannelDelete(TextChannelDeleteEvent e) {
		
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
		Document doc = collection.find(Filters.eq("channelid", e.getChannel().getIdLong())).first();
		
		if(doc != null) {
			Category cat = e.getGuild().getCategoryById(doc.getLong("roundCategory"));
			if(cat != null) {
				cat.delete().queue();
				
				collection.deleteOne(doc);
			}
		}
		
		
	}
	
}
