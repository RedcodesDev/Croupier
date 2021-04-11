package net.statifybot.croupier.game.deletion;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.rounds.Round;

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
		
		MongoCollection<Document> roundCollection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document roundDoc = roundCollection.find(Filters.eq("channelid", e.getChannel().getIdLong())).first();
		
		if(roundDoc != null) {
			
			Round round = new Round(new Game(e.getGuild()));
			
			for(PermissionOverride perm : e.getChannel().getMemberPermissionOverrides()) {
				round.leave(perm.getMember());
			}
			
			roundCollection.deleteOne(Filters.eq("channelid", e.getChannel().getIdLong()));
		}
		
		
	}
	
}
