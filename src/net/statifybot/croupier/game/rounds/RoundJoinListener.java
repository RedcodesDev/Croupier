package net.statifybot.croupier.game.rounds;


import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;

public class RoundJoinListener extends ListenerAdapter {

	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
		
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
		Document doc = collection.find(Filters.eq("messageid", e.getMessageIdLong())).first();
		
		if(doc != null) {
			switch(e.getReactionEmote().getName()) {
			
			case "join":
				
				Round round = new Round(new Game(e.getMessageIdLong()));
				e.getReaction().removeReaction(e.getUser()).queue();
				break;
			
			default:
				e.getReaction().removeReaction(e.getUser()).queue();
				break;
			
			}
		}
		
	}
	
}
