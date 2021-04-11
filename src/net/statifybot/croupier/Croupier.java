package net.statifybot.croupier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import javax.security.auth.login.LoginException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;
import net.statifybot.croupier.game.commands.SetupCommand;
import net.statifybot.croupier.game.deletion.CategoryDeleteListener;
import net.statifybot.croupier.game.deletion.ChannelDeleteListener;
import net.statifybot.croupier.game.deletion.MessageDeleteListener;
import net.statifybot.croupier.game.rounds.Round;
import net.statifybot.croupier.game.rounds.RoundJoinListener;
import net.statifybot.croupier.game.rounds.RoundLeaveListener;
import net.statifybot.croupier.game.rounds.Step;
import net.statifybot.croupier.game.rounds.bets.SelectionListener;
import net.statifybot.croupier.general.HelpCommand;
import net.statifybot.croupier.general.InfoCommand;
import net.statifybot.croupier.general.InviteCommand;
import net.statifybot.croupier.token.DONOTOPEN;
import net.statifybot.croupier.user.LeaderboardCommand;
import net.statifybot.croupier.user.UserCommand;

public class Croupier {

	private Thread loop;

	public static JDA jda;

	public static String Version = "Preview Release 2021.4";

	public static boolean Dev = false;

	public static String year = "2021";

	public static String icon = "https://visionvenue.de/Croupier-Logo.png";

	public static String prefix = "c!";

	public static Instant online = Instant.now();

	public static void main(String[] args) {
		try {
			new Croupier();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public Croupier() throws LoginException, IllegalArgumentException {

		MongoDBHandler.connect();

		String token = null;
		if (Dev) {
			token = DONOTOPEN.getDevToken();
		} else {
			token = DONOTOPEN.getToken();
		}

		JDABuilder builder = JDABuilder.createDefault(token);

		builder.setActivity(Activity.watching("Bot starting..."));
		builder.setStatus(OnlineStatus.IDLE);

		builder.setEnabledIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);
		
		builder.addEventListeners(new UserCommand());
		builder.addEventListeners(new InfoCommand());
		builder.addEventListeners(new LeaderboardCommand());
		builder.addEventListeners(new InviteCommand());
		builder.addEventListeners(new HelpCommand());
		builder.addEventListeners(new SetupCommand());

		builder.addEventListeners(new ChannelDeleteListener());
		builder.addEventListeners(new CategoryDeleteListener());
		builder.addEventListeners(new MessageDeleteListener());

		builder.addEventListeners(new RoundJoinListener());
		builder.addEventListeners(new RoundLeaveListener());
		builder.addEventListeners(new SelectionListener());

		jda = builder.build();
		System.out.println("The Bot is now Online!");

		year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

		shutdown();
		runLoop();

	}

	public void shutdown() {

		new Thread(() -> {

			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			try {
				while ((line = reader.readLine()) != null) {
					if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("stop")) {
						shutdown = true;
						if (jda != null) {
							jda.getPresence().setStatus(OnlineStatus.OFFLINE);
							jda.shutdown();
							MongoDBHandler.disconnect();
							System.out.println("The Bot is now Offline!");
						}
						if (loop != null) {
							loop.interrupt();
						}
						reader.close();
						System.exit(0);
						break;

					} else {
						System.out.println("Use 'exit' or 'stop' to shutdown");
					}
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}).start();
	}

	public boolean shutdown = false;

	public void runLoop() {
		this.loop = new Thread(() -> {
			long time = System.currentTimeMillis();

			while (!shutdown) {
				if (System.currentTimeMillis() >= time + 1000) {
					time = System.currentTimeMillis();
					onSecond();

				}
			}

		});
		this.loop.setName("Loop");
		this.loop.start();
	}

	int next = 7;
	int roundCheck = 30;
	String[] status = new String[] { "%prefix%help", "%members% User", "%version%", "%guilds% Guilds"

	};

	public void onSecond() {
		if (next <= 0) {
			Random rand = new Random();
			int i = rand.nextInt(status.length);

			int users = 0;

			for (Guild guild : jda.getGuilds()) {
				users = users + guild.getMemberCount();
			}

			String text = status[i].replace("%members%", String.valueOf(users)).replace("%version%", Version)
					.replace("%prefix%", prefix).replace("%guilds%", String.valueOf(jda.getGuilds().size()));

			if (!jda.getPresence().getActivity().getName().equals(text)) {

				if (!Dev) {
					jda.getPresence().setStatus(OnlineStatus.ONLINE);
					if (text.contains("help")) {
						jda.getPresence().setActivity(Activity.listening(text));
					} else if (text.contains("User") || text.contains("Guilds")) {
						jda.getPresence().setActivity(Activity.watching(text));
					} else {
						jda.getPresence().setActivity(Activity.playing(text));
					}
				} else {
					jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
					jda.getPresence().setActivity(Activity.watching("Development"));
				}
			} else {
				onSecond();
			}

			next = 7;
		} else {
			next--;
		}

		if (roundCheck <= 0) {

			MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
			FindIterable<Document> iterable = collection.find();
			Iterator<Document> iterator = iterable.iterator();

			while (iterator.hasNext()) {

				Document doc = iterator.next();

				if (doc.getString("drawTime") != null) {
					if (Step.valueOf(doc.getString("step")).equals(Step.CHOOSING)) {
						Round round = new Round(new Game(jda.getGuildById(doc.getLong("guildid"))));

						Instant instant = LocalDateTime
								.parse(doc.getString("drawTime"),
										DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.GERMANY))
								.atZone(ZoneId.of("Europe/Berlin")).toInstant();

						if (instant.isBefore(Instant.now())) {
							round.draw();
						}

					} else {
						Round round = new Round(new Game(jda.getGuildById(doc.getLong("guildid"))));
						
						Instant instant = LocalDateTime
								.parse(doc.getString("drawTime"),
										DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.GERMANY))
								.atZone(ZoneId.of("Europe/Berlin")).toInstant();

						if (instant.isBefore(Instant.now())) {
							round.restart();
						}
					}
				} 

			}

			roundCheck = 30;
		} else {
			roundCheck--;
		}

	}

}
