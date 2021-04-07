package net.statifybot.croupier.game.rounds;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import org.bson.Document;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.rounds.bets.SelectionFormatter;
import net.statifybot.croupier.utility.Emote;

public class Round {

	Guild guild;
	Game game;
	Category roundCat;
	TextChannel round;
	Step step;
	Multimap<Long, String> map;
	long messageId;

	public Round(Game game) {
		this.game = game;
		this.guild = this.game.getGuild();
		this.roundCat = this.game.getRoundCategory();

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document doc = collection.find(Filters.eq("guildid", this.guild.getIdLong())).first();

		if (doc != null) {
			this.round = this.guild.getTextChannelById(doc.getLong("channelid"));
			this.step = Step.valueOf(doc.getString("step"));
			this.messageId = doc.getLong("messageid");

			SelectionFormatter formatter = new SelectionFormatter(doc.getString("selection"));
			this.map = formatter.getAsMap();

		} else {
			this.guild.createTextChannel("🎲Roulette♠").setParent(this.roundCat)
					.setTopic("Made with ❤ by RedstonecraftHD#4308").syncPermissionOverrides().queue(channel -> {
						this.round = channel;

						this.map = ArrayListMultimap.create();
						for(int i = 0; i<=36; i++) {
							this.map.put(12l, String.valueOf(i));
							this.map.put(13l, String.valueOf(i));
							this.map.put(14l, String.valueOf(i));
							this.map.put(15l, String.valueOf(i));
							this.map.put(16l, String.valueOf(i));
							this.map.put(17l, String.valueOf(i));
							this.map.put(18l, String.valueOf(i));
							this.map.put(19l, String.valueOf(i));
						}
						this.map.put(12l, "red");
						this.map.put(12l, "black");
						this.map.put(12l, "even");
						this.map.put(12l, "odd");
						this.map.put(12l, "first 12");
						this.map.put(12l, "second 12");
						this.map.put(12l, "third 12");
						this.map.put(12l, "1-18");
						this.map.put(12l, "19-36");
						this.map.put(12l, "1st column");
						this.map.put(12l, "2nd column");
						this.map.put(12l, "3rd column");
						
						this.map.put(13l, "red");
						this.map.put(13l, "black");
						this.map.put(13l, "even");
						this.map.put(13l, "odd");
						this.map.put(13l, "first 12");
						this.map.put(13l, "second 12");
						this.map.put(13l, "third 12");
						this.map.put(13l, "1-18");
						this.map.put(13l, "19-36");
						this.map.put(13l, "1st column");
						this.map.put(13l, "2nd column");
						this.map.put(13l, "3rd column");
						
						this.map.put(14l, "red");
						this.map.put(14l, "black");
						this.map.put(14l, "even");
						this.map.put(14l, "odd");
						this.map.put(14l, "first 12");
						this.map.put(14l, "second 12");
						this.map.put(14l, "third 12");
						this.map.put(14l, "1-18");
						this.map.put(14l, "19-36");
						this.map.put(14l, "1st column");
						this.map.put(14l, "2nd column");
						this.map.put(14l, "3rd column");
					
						this.map.put(15l, "red");
						this.map.put(15l, "black");
						this.map.put(15l, "even");
						this.map.put(15l, "odd");
						this.map.put(15l, "first 12");
						this.map.put(15l, "second 12");
						this.map.put(15l, "third 12");
						this.map.put(15l, "1-18");
						this.map.put(15l, "19-36");
						this.map.put(15l, "1st column");
						this.map.put(15l, "2nd column");
						this.map.put(15l, "3rd column");
						
						this.map.put(16l, "red");
						this.map.put(16l, "black");
						this.map.put(16l, "even");
						this.map.put(16l, "odd");
						this.map.put(16l, "first 12");
						this.map.put(16l, "second 12");
						this.map.put(16l, "third 12");
						this.map.put(16l, "1-18");
						this.map.put(16l, "19-36");
						this.map.put(16l, "1st column");
						this.map.put(16l, "2nd column");
						this.map.put(16l, "3rd column");
						
						this.map.put(17l, "red");
						this.map.put(17l, "black");
						this.map.put(17l, "even");
						this.map.put(17l, "odd");
						this.map.put(17l, "first 12");
						this.map.put(17l, "second 12");
						this.map.put(17l, "third 12");
						this.map.put(17l, "1-18");
						this.map.put(17l, "19-36");
						this.map.put(17l, "1st column");
						this.map.put(17l, "2nd column");
						this.map.put(17l, "3rd column");
						
						this.map.put(18l, "red");
						this.map.put(18l, "black");
						this.map.put(18l, "even");
						this.map.put(18l, "odd");
						this.map.put(18l, "first 12");
						this.map.put(18l, "second 12");
						this.map.put(18l, "third 12");
						this.map.put(18l, "1-18");
						this.map.put(18l, "19-36");
						this.map.put(18l, "1st column");
						this.map.put(18l, "2nd column");
						this.map.put(18l, "3rd column");
						
						this.map.put(19l, "red");
						this.map.put(19l, "black");
						this.map.put(19l, "even");
						this.map.put(19l, "odd");
						this.map.put(19l, "first 12");
						this.map.put(19l, "second 12");
						this.map.put(19l, "third 12");
						this.map.put(19l, "1-18");
						this.map.put(19l, "19-36");
						this.map.put(19l, "1st column");
						this.map.put(19l, "2nd column");
						this.map.put(19l, "3rd column");

						SelectionFormatter formatter = new SelectionFormatter(this.map);

						EmbedBuilder msg = new EmbedBuilder();
						msg.setTitle("🎲Rouelette Round💸");
						msg.setDescription(
								"Write the text of the field you want to select in the Chat.\n*Example:* `1/Odd/Even/1-18/second 12/1st column`");
						msg.addField("", "React with " + new Emote("leave").getMention() + " to leave the Round",
								false);
						msg.setColor(0x33cc33);
						msg.setImage("attachment://field.png");
						msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
						File outputfile = new File("resources/field.png");

						channel.sendFile(outputfile, "field.png").embed(msg.build()).queue(message -> {
							this.messageId = message.getIdLong();
							message.addReaction(new Emote("leave").getEmote()).queue();

							Document document = new Document("channelid", this.round.getIdLong())
									.append("guildid", this.guild.getIdLong()).append("messageid", this.messageId)
									.append("step", Step.CHOOSING.toString())
									.append("selection", formatter.getAsText());
							collection.insertOne(document);
						});

					});
		}

	}

	public void join(Member memb) {
		this.round.getManager().putPermissionOverride(memb,
				EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE, Permission.MESSAGE_EXT_EMOJI,
						Permission.CREATE_INSTANT_INVITE, Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
				EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS, Permission.MESSAGE_ATTACH_FILES,
						Permission.MESSAGE_TTS, Permission.MANAGE_PERMISSIONS, Permission.MESSAGE_ADD_REACTION,
						Permission.MESSAGE_MENTION_EVERYONE, Permission.USE_SLASH_COMMANDS,
						Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
				.queue(i -> {
					EmbedBuilder msg = new EmbedBuilder();
					msg.setTitle("Roulette");
					msg.setDescription("React with " + new Emote("join").getMention() + " to join the Round");
					String players = "";
					for (PermissionOverride perm : this.round.getMemberPermissionOverrides()) {
						players = players + new Emote("greendot").getMention() + " " + perm.getMember().getAsMention()
								+ "\n";
					}
					msg.addField("Players", players, false);
					msg.setColor(0x33cc33);
					msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
					this.game.getChannel().editMessageById(this.game.getMessageId(), msg.build()).queue();
				});
	}

	public void leave(Member memb) {
		this.round.getManager().removePermissionOverride(memb).queue(i -> {
			EmbedBuilder msg = new EmbedBuilder();
			msg.setTitle("Roulette");
			msg.setDescription("React with " + new Emote("join").getMention() + " to join the Round");
			String players = "";
			for (PermissionOverride perm : this.round.getMemberPermissionOverrides()) {
				players = players + new Emote("greendot").getMention() + " " + perm.getMember().getAsMention() + "\n";
			}
			if (players.equals("")) {
				msg.addField("Players", new Emote("yellowdot").getMention() + " Waiting for Players...", false);
			} else {
				msg.addField("Players", players, false);
			}
			msg.setColor(0x33cc33);
			msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
			this.game.getChannel().editMessageById(this.game.getMessageId(), msg.build()).queue();
		});
	}
	
	public Multimap<Long, String> getBetMap() {
		return this.map;
	}

}
