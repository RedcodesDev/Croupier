package net.statifybot.croupier.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.user.CUser;

public class InviteCommand extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		CUser user = new CUser(e.getMember());
		
		if(args[0].equalsIgnoreCase(Croupier.prefix + "invite")) {
			
			EmbedBuilder msg = new EmbedBuilder();
			msg.setTitle("Invite Croupier", "https://discord.com/oauth2/authorize?client_id=828674781196582974&scope=bot&permissions=2147871808");
			msg.setDescription("Invite Croupier to play Roulette on your own Discord Server for free!");
			msg.addField("", "[**Click here**](https://discord.com/oauth2/authorize?client_id=828674781196582974&scope=bot&permissions=2147871808)", false);
			msg.setColor(0x33cc33);
			msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
			e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).queue();
			
		}
		
	}
	
}
