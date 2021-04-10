package net.statifybot.croupier.user;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;

public class UserCommand extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		String[] args = e.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(Croupier.prefix + "user")) {
			if (args.length == 2) {
				boolean integer = true;
				String mentionId;

				if (args[1].contains("<@!") || args[1].contains("<@")) {
					if (args[1].contains(">")) {
						mentionId = args[1].replace("<@!", "").replace(">", "").replace("<@", "");
					} else {
						mentionId = args[1];
					}
				} else {
					mentionId = args[1];
				}

				try {
					e.getGuild().getMemberById(mentionId);
				} catch (NumberFormatException ex) {
					integer = false;
				}
				if (integer && e.getGuild().getMemberById(mentionId) != null) {
					Member memb = e.getGuild().getMemberById(mentionId);
					CUser user = new CUser(memb);

					if (!user.getMember().getUser().isBot()) {

						EmbedBuilder msg = new EmbedBuilder();
						msg.setTitle(user.getMember().getEffectiveName() + "`s Profile");
						msg.setDescription(user.getMember().getAsMention());
						msg.addField("Chips", String.valueOf(user.getChips()), true);
						msg.addField("Games Played", String.valueOf(user.getGamesPlayed()), false);
						msg.addField("Wins", String.valueOf(user.getWins()), false);
						msg.addField("Loses", String.valueOf(user.getLoses()), false);
						msg.setColor(0x33cc33);
						msg.setThumbnail(user.getMember().getUser().getAvatarUrl());
						msg.setFooter("© Croupier Discord Bot" + Croupier.year, Croupier.icon);
						e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).queue();

					} else {
						EmbedBuilder error = new EmbedBuilder();
						error.setTitle("Bots are not allowed");
						error.setColor(Color.RED);
						error.setFooter("© Croupier Discord Bot" + Croupier.year, Croupier.icon);
						e.getChannel().sendMessage(error.build()).reference(e.getMessage()).queue();
					}
				} else {
					EmbedBuilder error = new EmbedBuilder();
					error.setTitle("The User was not found");
					error.setColor(Color.RED);
					error.setFooter("© Croupier Discord Bot" + Croupier.year, Croupier.icon);
					e.getChannel().sendMessage(error.build()).reference(e.getMessage()).queue();
				}
			} else {
				CUser user = new CUser(e.getMember());

				if (!user.getMember().getUser().isBot()) {

					EmbedBuilder msg = new EmbedBuilder();
					msg.setTitle(user.getMember().getEffectiveName() + "`s Profile");
					msg.setDescription(user.getMember().getAsMention());
					msg.addField("Chips", String.valueOf(user.getChips()), true);
					msg.addField("Games Played", String.valueOf(user.getGamesPlayed()), false);
					msg.addField("Wins", String.valueOf(user.getWins()), false);
					msg.addField("Loses", String.valueOf(user.getLoses()), false);
					msg.setColor(0x33cc33);
					msg.setThumbnail(user.getMember().getUser().getAvatarUrl());
					msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
					e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).queue();

				} else {
					EmbedBuilder error = new EmbedBuilder();
					error.setTitle("Bots are not allowed");
					error.setColor(Color.RED);
					error.setFooter("© Croupier Discord Bot" + Croupier.year, Croupier.icon);
					e.getChannel().sendMessage(error.build()).reference(e.getMessage()).queue();
				}

			}
		}

	}

}
