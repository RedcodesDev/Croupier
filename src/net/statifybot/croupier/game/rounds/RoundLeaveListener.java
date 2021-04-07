package net.statifybot.croupier.game.rounds;

import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;

public class RoundLeaveListener extends ListenerAdapter {

	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {

		if (!e.getUser().isBot()) {

			MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
			Document doc = collection.find(Filters.eq("messageid", e.getMessageIdLong())).first();

			if (doc != null) {
				switch (e.getReactionEmote().getName()) {

				case "leave":

					Round round = new Round(new Game(e.getGuild()));
					e.getReaction().removeReaction(e.getUser()).queue(msg -> {
						round.leave(e.getMember());
					});
					break;

				default:
					e.getReaction().removeReaction(e.getUser()).queue();
					break;

				}
			}

		}
	}

}
