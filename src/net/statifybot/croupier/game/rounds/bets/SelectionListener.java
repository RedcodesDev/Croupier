package net.statifybot.croupier.game.rounds.bets;

import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.rounds.Round;
import net.statifybot.croupier.game.rounds.Step;

public class SelectionListener extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document doc = collection.find(Filters.eq("channelid", e.getChannel().getIdLong())).first();

		if (doc != null) {

			if (Step.valueOf(doc.getString("step")).equals(Step.CHOOSING)) {
				Round round = new Round(new Game(e.getGuild()));

				try {

					int num = Integer.valueOf(e.getMessage().getContentRaw().trim());

					if (num <= 36 && num >= 0) {

						round.addBet(e.getMember().getIdLong(), String.valueOf(num));

						e.getMessage().delete().queue();
						
						EmbedBuilder msg = new EmbedBuilder();
						msg.setTitle("Your bet has been set");
						msg.setColor(0x33cc33);
						msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
						round.getChannel().sendMessage(msg.build()).append(e.getMember().getAsMention()).queue(message -> {
							message.delete().queueAfter(5, TimeUnit.SECONDS);
							
						});
						;
					}

				} catch (NumberFormatException ex) {

				}

			}

		}

	}

}
