package net.statifybot.croupier.user;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.utility.MapSorter;

public class LeaderboardCommand extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		String[] args = e.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(Croupier.prefix + "leaderboard")) {

			CUser user = new CUser(e.getMember());
			if (args.length == 2) {

				EmbedBuilder generating = new EmbedBuilder();
				generating.setTitle("Generating Leaderboard...");
				generating.setColor(Color.YELLOW);
				generating.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
				e.getChannel().sendMessage(generating.build()).reference(e.getMessage()).mentionRepliedUser(false)
						.queue(message -> {

							switch (args[1]) {

							case "coins":

								MongoCollection<Document> collection = MongoDBHandler.getDatabase()
										.getCollection("users");
								FindIterable<Document> iterable = collection.find();
								Iterator<Document> iterator = iterable.iterator();

								HashMap<Long, Integer> coinsMap = new HashMap<Long, Integer>();

								while (iterator.hasNext()) {

									Document doc = iterator.next();

									coinsMap.put(doc.getLong("_id"), doc.getInteger("coins"));
								}

								EmbedBuilder coins = new EmbedBuilder();
								coins.setTitle("Coin Leaderboard");
								coins.setColor(0x33cc33);
								coins.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);

								String coinsMsg = "";
								int coinsPos = 0;

								for (Entry<Long, Integer> entry : MapSorter.sortLongMap(coinsMap, 10).entrySet()) {
									coinsPos++;
									coinsMsg += coinsPos + ". <@" + entry.getKey() + "> - $" + entry.getValue() + "\n";
								}

								coins.setDescription(coinsMsg);
								e.getChannel().sendMessage(coins.build()).reference(e.getMessage()).queue();
								message.delete().queue();

								break;

							case "wins":

								MongoCollection<Document> collection1 = MongoDBHandler.getDatabase()
										.getCollection("users");
								FindIterable<Document> iterable1 = collection1.find();
								Iterator<Document> iterator1 = iterable1.iterator();

								HashMap<Long, Integer> winsMap = new HashMap<Long, Integer>();

								while (iterator1.hasNext()) {

									Document doc = iterator1.next();

									winsMap.put(doc.getLong("_id"), doc.getInteger("wins"));
								}

								EmbedBuilder wins = new EmbedBuilder();
								wins.setTitle("Wins Leaderboard");
								wins.setColor(0x33cc33);
								wins.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);

								String winsMsg = "";
								int winsPos = 0;

								for (Entry<Long, Integer> entry : MapSorter.sortLongMap(winsMap, 10).entrySet()) {
									winsPos++;
									winsMsg += winsPos + ". <@" + entry.getKey() + "> - " + entry.getValue()
											+ " Wins\n";
								}

								wins.setDescription(winsMsg);
								e.getChannel().sendMessage(wins.build()).reference(e.getMessage()).queue();
								message.delete().queue();

								break;

							case "loses":
								MongoCollection<Document> collection2 = MongoDBHandler.getDatabase()
										.getCollection("users");
								FindIterable<Document> iterable2 = collection2.find();
								Iterator<Document> iterator2 = iterable2.iterator();

								HashMap<Long, Integer> losesMap = new HashMap<Long, Integer>();

								while (iterator2.hasNext()) {

									Document doc = iterator2.next();

									losesMap.put(doc.getLong("_id"), doc.getInteger("loses"));
								}

								EmbedBuilder loses = new EmbedBuilder();
								loses.setTitle("Lost Rounds Leaderboard");
								loses.setColor(0x33cc33);
								loses.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);

								String losesMsg = "";
								int losesPos = 0;

								for (Entry<Long, Integer> entry : MapSorter.sortLongMap(losesMap, 10).entrySet()) {
									losesPos++;
									losesMsg += losesPos + ". <@" + entry.getKey() + "> - " + entry.getValue()
											+ " Loses\n";
								}

								loses.setDescription(losesMsg);
								e.getChannel().sendMessage(loses.build()).reference(e.getMessage()).queue();
								message.delete().queue();
								break;

							case "plays":

								MongoCollection<Document> collection3 = MongoDBHandler.getDatabase()
										.getCollection("users");
								FindIterable<Document> iterable3 = collection3.find();
								Iterator<Document> iterator3 = iterable3.iterator();

								HashMap<Long, Integer> playsMap = new HashMap<Long, Integer>();

								while (iterator3.hasNext()) {

									Document doc = iterator3.next();

									playsMap.put(doc.getLong("_id"), doc.getInteger("gamesPlayed"));
								}

								EmbedBuilder plays = new EmbedBuilder();
								plays.setTitle("Played Rounds Leaderboard");
								plays.setColor(0x33cc33);
								plays.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);

								String playsMsg = "";
								int playsPos = 0;

								for (Entry<Long, Integer> entry : MapSorter.sortLongMap(playsMap, 10).entrySet()) {
									playsPos++;
									playsMsg += playsPos + ". <@" + entry.getKey() + "> - " + entry.getValue()
											+ " Played Rounds\n";
								}

								plays.setDescription(playsMsg);
								e.getChannel().sendMessage(plays.build()).reference(e.getMessage()).queue();
								message.delete().queue();
								break;

							default:
								EmbedBuilder error = new EmbedBuilder();
								error.setTitle("Syntax Error");
								error.setDescription("Use `c!leaderboard <coins/wins/loses/plays>` instead");
								error.setColor(Color.RED);
								error.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								e.getChannel().sendMessage(error.build()).reference(e.getMessage()).queue();
								message.delete().queue();
								break;

							}

						});

			} else {
				EmbedBuilder error = new EmbedBuilder();
				error.setTitle("Syntax Error");
				error.setDescription("Use `c!leaderboard <coins/wins/loses/plays>` instead");
				error.setColor(Color.RED);
				error.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
				e.getChannel().sendMessage(error.build()).reference(e.getMessage()).queue();
			}
		}

	}

}
