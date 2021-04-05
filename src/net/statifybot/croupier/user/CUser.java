package net.statifybot.croupier.user;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import net.dv8tion.jda.api.entities.Member;
import net.statifybot.croupier.data.MongoDBHandler;

public class CUser {

	Member memb;
	int coins;
	int wins;
	int loses;
	int gamesPlayed;

	public CUser(Member memb) {
		this.memb = memb;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		Document doc = collection.find(Filters.eq("_id", this.memb.getIdLong())).first();

		if (doc != null) {
			this.coins = doc.getInteger("coins");
			this.wins = doc.getInteger("wins");
			this.loses = doc.getInteger("loses");
			this.gamesPlayed = doc.getInteger("gamesPlayed");
		} else {
			doc = new Document("_id", this.memb.getIdLong()).append("coins", 1000).append("wins", 0).append("loses", 0)
					.append("gamesPlayed", 0);
			collection.insertOne(doc);
			
			this.coins = 1000;
			this.wins = 0;
			this.loses = 0;
			this.gamesPlayed = 0;
		}
	}

	public Member getMember() {
		return this.memb;
	}

	public int getCoins() {
		return this.coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memb.getIdLong()), Updates.set("coins", this.coins));
	}

	public void addCoins(int coins) {
		this.coins += coins;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memb.getIdLong()), Updates.set("coins", this.coins));
	}

	public void removeCoins(int coins) {
		this.coins -= coins;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memb.getIdLong()), Updates.set("coins", this.coins));
	}

	public void multiplyCoins(int coins) {
		this.coins *= coins;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memb.getIdLong()), Updates.set("coins", this.coins));
	}

	public void divideCoins(int coins) {
		this.coins /= coins;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memb.getIdLong()), Updates.set("coins", this.coins));
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public int getLoses() {
		return this.loses;
	}
	
	public int getGamesPlayed() {
		return this.gamesPlayed;
	}

}
