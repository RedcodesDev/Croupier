package net.statifybot.croupier.game.rounds.bets;

import java.awt.Color;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.rounds.Round;
import net.statifybot.croupier.game.rounds.Step;
import net.statifybot.croupier.user.CUser;

public class SelectionListener extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document doc = collection.find(Filters.eq("channelid", e.getChannel().getIdLong())).first();

		if (doc != null) {

			if (!e.getAuthor().isBot()) {

				if (Step.valueOf(doc.getString("step")).equals(Step.CHOOSING)) {

					CUser user = new CUser(e.getMember());

					Round round = new Round(new Game(e.getGuild()));

					e.getChannel().getManager().putPermissionOverride(e.getMember(),
							EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_EXT_EMOJI,
									Permission.CREATE_INSTANT_INVITE, Permission.MESSAGE_HISTORY,
									Permission.MESSAGE_READ),
							EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MESSAGE_WRITE, Permission.MANAGE_WEBHOOKS,
									Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
									Permission.MANAGE_PERMISSIONS, Permission.MESSAGE_ADD_REACTION,
									Permission.MESSAGE_MENTION_EVERYONE, Permission.USE_SLASH_COMMANDS,
									Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
							.queue();

					if (user.getChips() >= 1) {

						try {

							int num = Integer.valueOf(e.getMessage().getContentRaw().trim());

							if (num <= 36 && num >= 0) {

								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), String.valueOf(num));

								user.removeChips(1);

								EmbedBuilder msg = new EmbedBuilder();
								msg.setTitle("Your bet has been set");
								msg.setColor(0x33cc33);
								msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});

							}

						} catch (NumberFormatException ex) {

							String txt = e.getMessage().getContentRaw().trim().toLowerCase();
							switch (txt) {

							case "red":

								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg = new EmbedBuilder();
								msg.setTitle("Your bet has been set");
								msg.setColor(0x33cc33);
								msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "black":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg1 = new EmbedBuilder();
								msg1.setTitle("Your bet has been set");
								msg1.setColor(0x33cc33);
								msg1.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg1.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "even":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg11 = new EmbedBuilder();
								msg11.setTitle("Your bet has been set");
								msg11.setColor(0x33cc33);
								msg11.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg11.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "odd":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg111 = new EmbedBuilder();
								msg111.setTitle("Your bet has been set");
								msg111.setColor(0x33cc33);
								msg111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg111.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "1-18":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg1111 = new EmbedBuilder();
								msg1111.setTitle("Your bet has been set");
								msg1111.setColor(0x33cc33);
								msg1111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg1111.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "19-36":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg11111 = new EmbedBuilder();
								msg11111.setTitle("Your bet has been set");
								msg11111.setColor(0x33cc33);
								msg11111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg11111.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "first 12":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg111111 = new EmbedBuilder();
								msg111111.setTitle("Your bet has been set");
								msg111111.setColor(0x33cc33);
								msg111111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg111111.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "second 12":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg1111111 = new EmbedBuilder();
								msg1111111.setTitle("Your bet has been set");
								msg1111111.setColor(0x33cc33);
								msg1111111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg1111111.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "third 12":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg11111111 = new EmbedBuilder();
								msg11111111.setTitle("Your bet has been set");
								msg11111111.setColor(0x33cc33);
								msg11111111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg11111111.build()).append(e.getMember().getAsMention())
										.queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "1st column":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg111111111 = new EmbedBuilder();
								msg111111111.setTitle("Your bet has been set");
								msg111111111.setColor(0x33cc33);
								msg111111111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg111111111.build())
										.append(e.getMember().getAsMention()).queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "2nd column":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg1111111111 = new EmbedBuilder();
								msg1111111111.setTitle("Your bet has been set");
								msg1111111111.setColor(0x33cc33);
								msg1111111111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg1111111111.build())
										.append(e.getMember().getAsMention()).queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							case "3rd column":
								e.getMessage().delete().queue();

								round.addBet(e.getMember().getIdLong(), txt);

								user.removeChips(1);
								EmbedBuilder msg11111111111 = new EmbedBuilder();
								msg11111111111.setTitle("Your bet has been set");
								msg11111111111.setColor(0x33cc33);
								msg11111111111.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								round.getChannel().sendMessage(msg11111111111.build())
										.append(e.getMember().getAsMention()).queue(message -> {
											message.delete().queueAfter(5, TimeUnit.SECONDS);
											e.getChannel().getManager().putPermissionOverride(e.getMember(),
													EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_EXT_EMOJI,
															Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
															Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
															Permission.MANAGE_PERMISSIONS,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS,
															Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
													.queue();
										});
								break;

							default:
								e.getMessage().delete().queue();
								EmbedBuilder error = new EmbedBuilder();
								error.setTitle("Your bet is invalid");
								error.setDescription(
										"Please use the text of the field you want to select\\n*Example:* `1/Odd/Even/1-18/second 12/1st column`");
								error.setColor(Color.RED);
								error.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
								e.getChannel().sendMessage(error.build()).queue(message -> {
									message.delete().queueAfter(5, TimeUnit.SECONDS);
									e.getChannel().getManager().putPermissionOverride(e.getMember(),
											EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE,
													Permission.MESSAGE_EXT_EMOJI, Permission.CREATE_INSTANT_INVITE,
													Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ),
											EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_WEBHOOKS,
													Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_TTS,
													Permission.MANAGE_PERMISSIONS, Permission.MESSAGE_ADD_REACTION,
													Permission.MESSAGE_MENTION_EVERYONE, Permission.USE_SLASH_COMMANDS,
													Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE))
											.queue();
								});
								break;

							}

						}
					} else {
						e.getMessage().delete().queue();

						EmbedBuilder error = new EmbedBuilder();
						error.setTitle("You don`t have any Chips left.");
						error.setColor(Color.RED);
						error.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
						e.getChannel().sendMessage(error.build()).append(e.getMember().getAsMention())
								.queue(message -> {

									message.delete().queueAfter(3, TimeUnit.SECONDS);

								});
					}

				}
			}

		}

	}

}
