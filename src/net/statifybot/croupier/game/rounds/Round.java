package net.statifybot.croupier.game.rounds;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.draw.Draw;
import net.statifybot.croupier.game.rounds.bets.SelectionFormatter;
import net.statifybot.croupier.game.rounds.bets.SelectionImage;
import net.statifybot.croupier.user.CUser;
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
									.append("step", Step.CHOOSING.toString()).append("selection", formatter.getAsText())
									.append("drawTime", null);
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
		if (this.round != null) {
			this.round.getManager().removePermissionOverride(memb).queue(i -> {
				EmbedBuilder msg = new EmbedBuilder();
				msg.setTitle("Roulette");
				msg.setDescription("React with " + new Emote("join").getMention() + " to join the Round");
				String players = "";
				for (PermissionOverride perm : this.round.getMemberPermissionOverrides()) {
					players = players + new Emote("greendot").getMention() + " " + perm.getMember().getAsMention()
							+ "\n";
				}
				if (players.equals("")) {
					msg.addField("Players", new Emote("yellowdot").getMention() + " Waiting for Players...", false);
				} else {
					msg.addField("Players", players, false);
				}
				msg.setColor(0x33cc33);
				msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
				this.game.getChannel().editMessageById(this.game.getMessageId(), msg.build()).queue();
				if (this.round.getMemberPermissionOverrides().size() == 0) {
					this.round.delete().queue();
					MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
					collection.deleteOne(Filters.eq("messageid", this.messageId));
				}
				return;
			}, (error) -> {
				if (error instanceof ErrorResponseException) {
					ErrorResponseException ex = (ErrorResponseException) error;
					if (!ex.getErrorResponse().equals(ErrorResponse.UNKNOWN_CHANNEL)) {
						ex.printStackTrace();
					}
				}
			});
		}
		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("Roulette");
		msg.setDescription("React with " + new Emote("join").getMention() + " to join the Round");
		msg.addField("Players", new Emote("yellowdot").getMention() + " Waiting for Players...", false);
		msg.setColor(0x33cc33);
		msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
		this.game.getChannel().editMessageById(this.game.getMessageId(), msg.build()).queue();

	}

	public Multimap<Long, String> getBetMap() {
		return this.map;
	}

	public void addBet(long memberid, String field) {
		this.map.put(memberid, field);

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document doc = collection.find(Filters.eq("messageid", this.messageId)).first();
		SelectionFormatter formatter = new SelectionFormatter(this.map);
		collection.updateOne(Filters.eq("messageid", this.messageId), Updates.set("selection", formatter.getAsText()));

		if (doc.getString("drawTime") == null) {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.GERMANY)
					.withZone(ZoneId.of("Europe/Berlin"));
			String instant = dateFormatter.format(Instant.now().plus(2, ChronoUnit.MINUTES));

			collection.updateOne(Filters.eq("messageid", this.messageId), Updates.set("drawTime", instant));
		}

		this.round.deleteMessageById(this.messageId).queue();

		new SelectionImage(this).renderImage();

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("🎲Rouelette Round💸");
		msg.setDescription(
				"Write the text of the field you want to select in the Chat.\n*Example:* `1/Odd/Even/1-18/second 12/1st column`");
		msg.addField("", "React with " + new Emote("leave").getMention() + " to leave the Round", false);
		msg.setColor(0x33cc33);
		msg.setImage("attachment://drawedfield.png");
		msg.setFooter("© Croupier Discord Bot " + Croupier.year + " • bets end at", Croupier.icon);
		msg.setTimestamp(getDrawTime());
		File outputfile = new File("resources/drawedfield.png");

		this.round.sendFile(outputfile, "drawedfield.png").embed(msg.build()).queue(message -> {

			setMessageId(message.getIdLong());
			message.addReaction(new Emote("leave").getEmote()).queue();
		});
	}

	public void draw() {

		this.round.deleteMessageById(this.messageId).queue();
		setStep(Step.RESULTS);

		EmbedBuilder msg = new EmbedBuilder();
		msg.setTitle("🎲Rouelette Round💸");
		msg.setDescription(new Emote("yellowdot").getMention() + " Waiting for Results...");
		msg.setColor(0x33cc33);
		msg.setImage("http://visionvenue.de/roulette.gif");
		msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);

		this.round.sendMessage(msg.build()).queue(message -> {

			setMessageId(message.getIdLong());

			Draw draw = new Draw(this);
			draw.selectNumber();
			draw.retrieveWinners();

			EmbedBuilder results = new EmbedBuilder();
			results.setTitle("🎲Rouelette Round💸");
			String result = "Selected Number: **" + draw.getNumber() + "**\n\n";
			for (Entry<Long, Integer> entry : draw.getChips().entrySet()) {
				if (entry.getValue() > 0) {
					result += new Emote("greendot").getMention() + " <@" + entry.getKey() + "> won " + entry.getValue()
							+ " Chip/s\n";
					CUser user = new CUser(entry.getKey());
					user.addChips(entry.getValue());
					user.addGamePlayed();
					user.addWin();
				} else {
					result += new Emote("reddot").getMention() + " <@" + entry.getKey() + "> won no Chips\n";
					CUser user = new CUser(entry.getKey());
					user.addGamePlayed();
					user.addLose();
				}
			}
			result += "\n*A new Round will start in 1 minute*";
			results.setDescription(result);
			results.setColor(0x33cc33);
			results.setFooter("© Cropier Discord Bot " + Croupier.year, Croupier.icon);
			this.round.sendMessage(results.build()).queueAfter(3, TimeUnit.SECONDS, msg1 -> {
				message.delete().queue();

				setMessageId(msg1.getIdLong());

				msg1.addReaction(new Emote("leave").getEmote()).queue();

				MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.GERMANY)
						.withZone(ZoneId.of("Europe/Berlin"));
				String instant = dateFormatter.format(Instant.now().plus(1, ChronoUnit.MINUTES));

				collection.updateOne(Filters.eq("messageid", this.messageId), Updates.set("drawTime", instant));

			});

		});

	}

	public Round(Game game, TextChannel channel) {
		{
			this.game = game;
			this.guild = this.game.getGuild();
			this.roundCat = this.game.getRoundCategory();

			MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");

			this.round = channel;

			this.map = ArrayListMultimap.create();

			SelectionFormatter formatter = new SelectionFormatter(this.map);

			EmbedBuilder msg = new EmbedBuilder();
			msg.setTitle("🎲Rouelette Round💸");
			msg.setDescription(
					"Write the text of the field you want to select in the Chat.\n*Example:* `1/Odd/Even/1-18/second 12/1st column`");
			msg.addField("", "React with " + new Emote("leave").getMention() + " to leave the Round", false);
			msg.setColor(0x33cc33);
			msg.setImage("attachment://field.png");
			msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
			File outputfile = new File("resources/field.png");

			channel.sendFile(outputfile, "field.png").embed(msg.build()).queue(message -> {
				this.messageId = message.getIdLong();
				message.addReaction(new Emote("leave").getEmote()).queue();

				Document document = new Document("channelid", this.round.getIdLong())
						.append("guildid", this.guild.getIdLong()).append("messageid", this.messageId)
						.append("step", Step.CHOOSING.toString()).append("selection", formatter.getAsText())
						.append("drawTime", null);
				collection.insertOne(document);
			});

		}

	}

	public void restart() {

		this.round.deleteMessageById(this.messageId).queue();

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		collection.deleteOne(Filters.eq("messageid", this.messageId));

		new Round(this.game, this.round);

	}

	public TextChannel getChannel() {
		return this.round;
	}

	public long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		collection.updateOne(Filters.eq("channelid", this.round.getIdLong()), Updates.set("messageid", this.messageId));
	}

	public void setStep(Step step) {
		this.step = step;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		collection.updateOne(Filters.eq("channelid", this.round.getIdLong()),
				Updates.set("step", this.step.toString()));
	}

	public Instant getDrawTime() {
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document doc = collection.find(Filters.eq("channelid", this.round.getIdLong())).first();
		if (doc != null) {
			Instant instant = LocalDateTime
					.parse(doc.getString("drawTime"),
							DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.GERMANY))
					.atZone(ZoneId.of("Europe/Berlin")).toInstant();
			return instant;
		}

		return null;
	}

}
