package net.statifybot.croupier.game.rounds;

import java.util.EnumSet;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.statifybot.croupier.data.MongoDBHandler;
import net.statifybot.croupier.game.Game;

public class Round {

	Guild guild;
	Game game;
	Category roundCat;
	TextChannel round;
	Step step;

	public Round(Game game) {
		this.game = game;
		this.guild = this.game.getGuild();
		this.roundCat = this.game.getRoundCategory();

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("rounds");
		Document doc = collection.find(Filters.eq("guildid", this.guild.getIdLong())).first();

		if (doc != null) {
			this.round = this.guild.getTextChannelById(doc.getLong("channelid"));
			this.step = Step.valueOf(doc.getString("step"));
		} else {
			this.guild.createTextChannel("🎲Roulette♠").setParent(this.roundCat)
					.setTopic("Made with ❤ by RedstonecraftHD#4308").syncPermissionOverrides().queue(channel -> {
						this.round = channel;
						Document document = new Document("channelid", this.round.getIdLong()).append("guildid", this.guild.getIdLong()).append("step",
								Step.CHOOSING.toString());
						collection.insertOne(document);
					});
		}

	}

	public void join(Member memb) {
		this.round.getManager().putPermissionOverride(memb, EnumSet.of(Permission.ADMINISTRATOR), null)
	}

	public void leave(Member memb) {

	}

}
