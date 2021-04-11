package net.statifybot.croupier.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.user.CUser;
import net.statifybot.croupier.utility.Emote;

public class HelpCommand extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		String[] args = e.getMessage().getContentRaw().split("\\s+");

		if (args[0].equalsIgnoreCase(Croupier.prefix + "help")) {
			CUser user = new CUser(e.getMember());

			EmbedBuilder msg = new EmbedBuilder();
			msg.setTitle("Croupier Help");
			msg.addField(new Emote("arrow").getMention() + " How to play",
					"Bet on a Number, Color, Column, etc. and win Chips.", false);
			msg.addField(new Emote("arrow").getMention() + " Commands",
					"> `c!setup` - Starts the Setup Process\n> `c!user @User` - Displays Information about a user\n> `c!leaderboard chips/wins/loses/plays` - Displays a Leaderboard for the specific Category\n> `c!info` - Displays Informations about the Discord Bot\n> `c!invite` - Sends you the invite URL for the Bot",
					false);
			msg.setColor(0x33cc33);
			msg.setFooter("© Croupier Discord Bot" + Croupier.year, Croupier.icon);
			e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).queue();
		}

	}

}
