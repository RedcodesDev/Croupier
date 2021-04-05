package net.statifybot.croupier.game.commands;

import java.awt.Color;
import java.util.EnumSet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.user.CUser;

public class SetupCommand extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		String[] args = e.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(Croupier.prefix + "setup")) {
			CUser user = new CUser(e.getMember());

			EmbedBuilder msg = new EmbedBuilder();
			msg.setTitle("Setup is starting...");
			msg.setColor(Color.YELLOW);
			msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
			e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).mentionRepliedUser(false)
					.queue(message -> {
						EmbedBuilder category = new EmbedBuilder();
						category.setTitle("Creating Categories...");
						category.setColor(Color.YELLOW);
						category.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
						message.editMessage(category.build()).queue();

						e.getGuild().createCategory("🎲Roulette Rounds🎲").addRolePermissionOverride(
								e.getGuild().getPublicRole().getIdLong(), 0l, Permission.ALL_PERMISSIONS).queue(cat -> {
									EmbedBuilder txt = new EmbedBuilder();
									txt.setTitle("Creating Textchannel...");
									txt.setColor(Color.YELLOW);
									txt.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
									message.editMessage(txt.build()).queue();

									e.getGuild().createTextChannel("🎱Roulette🎲")
											.setTopic("Made with ❤ by RedstonecraftHD#4308")
											.addRolePermissionOverride(e.getGuild().getPublicRole().getIdLong(),
													EnumSet.of(Permission.CREATE_INSTANT_INVITE,
															Permission.MESSAGE_HISTORY, Permission.MESSAGE_READ,
															Permission.VIEW_CHANNEL, Permission.MESSAGE_EXT_EMOJI),
													EnumSet.of(Permission.MANAGE_CHANNEL, Permission.MANAGE_PERMISSIONS,
															Permission.MANAGE_WEBHOOKS, Permission.MESSAGE_WRITE,
															Permission.MESSAGE_TTS, Permission.MESSAGE_EMBED_LINKS,
															Permission.MESSAGE_ATTACH_FILES,
															Permission.MESSAGE_ADD_REACTION,
															Permission.MESSAGE_MENTION_EVERYONE,
															Permission.USE_SLASH_COMMANDS, Permission.MESSAGE_MANAGE))
											.queue(channel -> {
												EmbedBuilder data = new EmbedBuilder();
												data.setTitle("Saving Data...");
												data.setColor(Color.YELLOW);
												data.setFooter("© Croupier Discord Bot " + Croupier.year,
														Croupier.icon);
												message.editMessage(data.build()).queue();

												Game game = new Game(channel, cat);
											});

								});
					});
		}

	}

}
