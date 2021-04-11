package net.statifybot.croupier.game.rounds;

import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.rounds.bets.SelectionImage;

public class RoundJoinListener extends ListenerAdapter {

	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {

		if (!e.getUser().isBot()) {

			MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("games");
			Document doc = collection.find(Filters.eq("messageid", e.getMessageIdLong())).first();

			if (doc != null) {
				switch (e.getReactionEmote().getName()) {

				case "join":

					Round round = new Round(new Game(e.getMessageIdLong()));
					e.getReaction().removeReaction(e.getUser()).queueAfter(1, TimeUnit.SECONDS, msg -> {
						round.join(e.getMember());
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
