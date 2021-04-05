package net.statifybot.croupier.utility;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;

public class Emote {

	String key;
	long id;
	String mention;

	public Emote(String key) {
		this.key = key;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("emotes");
		Document doc = collection.find(Filters.eq("_id", this.key)).first();

		if (doc != null) {
			this.id = doc.getLong("emoteid");
			this.mention = Croupier.jda.getGuildById(580732235313971211l).getEmoteById(this.id).getAsMention();
		} else {
			throw new NullPointerException("Emote \"" + this.key + "\" was not found!");
		}

	}

	public String getKey() {
		return this.key;
	}
	
	public String getMention() {
		return this.mention;
	}

}
