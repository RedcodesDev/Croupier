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
			msg.addField(new Emote("arrow").getMention() + " How to play", "You play how you feel like lol", false);
			msg.addField(new Emote("arrow").getMention() + " Commands",
					"All Commands can be found here: https://example.com", false);
			msg.setColor(0x33cc33);
			msg.setFooter("© Croupier Discord Bot" + Croupier.year, Croupier.icon);
			e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).queue();
		}

	}

}
