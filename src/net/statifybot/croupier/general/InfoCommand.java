package net.statifybot.croupier.general;

import java.time.Instant;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.user.CUser;

public class InfoCommand extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		CUser user = new CUser(e.getMember());
		
		if(args[0].equalsIgnoreCase(Croupier.prefix + "info")) {
			
			int users = 0;
			for(Guild guild : e.getJDA().getGuilds()) {
				users += guild.getMemberCount();
			}
			
			long duration = Instant.now().getEpochSecond() - Croupier.online.getEpochSecond();
			long days = duration / 86400;
			long hours = duration / 3600 % 24;
			long minutes = duration / 60 % 60;
			long seconds = duration / 1 % 60;
			
			EmbedBuilder msg = new EmbedBuilder();
			msg.setTitle("Croupier Information");
			msg.addField("Guilds", String.valueOf(e.getJDA().getGuilds().size()), true);
			msg.addField("User", String.valueOf(users), true);
			msg.addField("Onlinetime", days + " Days, " + hours + " Hours, " + minutes + " Minutes, " + seconds + " Seconds", true);
			msg.addField("Bot Version", Croupier.Version, true);
			msg.addField("Java Version", System.getProperty("java.version"), true);
			msg.addField("JDA Version", "4.2.1_253", true);
			msg.addField("", "> [`Invite Bot`](https://discord.com/oauth2/authorize?client_id=828674781196582974&scope=bot&permissions=2147871808) [`Github`](https://github.com/RedstonecraftHD/Croupier)", false);
			msg.setColor(0x33cc33);
			msg.setFooter("© Croupier Discord Bot " + Croupier.year, Croupier.icon);
			e.getChannel().sendMessage(msg.build()).reference(e.getMessage()).queue();
			
		}
	}
	
}
