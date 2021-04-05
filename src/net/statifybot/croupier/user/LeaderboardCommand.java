package net.statifybot.croupier.user;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.statifybot.croupier.Croupier;

public class LeaderboardCommand extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		
		String[] args = e.getMessage().getContentRaw().split("\\s+");
		CUser user = new CUser(e.getMember());
		
		if(args[0].equalsIgnoreCase(Croupier.prefix + "leaderboard")) {
			
			switch(args[1]) {
			
			case "coins":
				
				break;
			
			case "wins":
				
				break;
				
			case "loses":
				
				break;
				
			case "plays":
				
				break;
			
			
			default:
				break;
			
			}
			
		}
		
	}
	

}
