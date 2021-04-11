package net.statifybot.croupier.user;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import net.dv8tion.jda.api.entities.Member;
import net.statifybot.croupier.Croupier;
import net.statifybot.croupier.data.MongoDBHandler;

public class CUser {

	Member memb;
	int chips;
	int wins;
	int loses;
	int gamesPlayed;
	long memberid;

	public CUser(Member memb) {
		this.memb = memb;
		this.memberid = this.memb.getIdLong();
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		Document doc = collection.find(Filters.eq("_id", this.memb.getIdLong())).first();

		if (doc != null) {
			this.chips = doc.getInteger("chips");
			this.wins = doc.getInteger("wins");
			this.loses = doc.getInteger("loses");
			this.gamesPlayed = doc.getInteger("gamesPlayed");
		} else {
			doc = new Document("_id", this.memb.getIdLong()).append("chips", 20).append("wins", 0).append("loses", 0)
					.append("gamesPlayed", 0);
			collection.insertOne(doc);

			this.chips = 20;
			this.wins = 0;
			this.loses = 0;
			this.gamesPlayed = 0;
		}
	}

	public CUser(long memberId) {
		this.memberid = memberId;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		Document doc = collection.find(Filters.eq("_id", this.memberid)).first();

		if (doc != null) {
			this.chips = doc.getInteger("chips");
			this.wins = doc.getInteger("wins");
			this.loses = doc.getInteger("loses");
			this.gamesPlayed = doc.getInteger("gamesPlayed");
		} else {
			doc = new Document("_id", this.memberid).append("chips", 20).append("wins", 0).append("loses", 0)
					.append("gamesPlayed", 0);
			collection.insertOne(doc);

			this.chips = 20;
			this.wins = 0;
			this.loses = 0;
			this.gamesPlayed = 0;
		}
	}

	public Member getMember() {
		return this.memb;
	}
	
	public long getMemberId() {
		return this.memberid;
	}

	public int getChips() {
		return this.chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("chips", this.chips));
	}

	public void addChips(int chips) {
		this.chips += chips;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("chips", this.chips));
	}

	public void removeChips(int chips) {
		this.chips -= chips;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("chips", this.chips));
	}

	public void multiplyChips(int chips) {
		this.chips *= chips;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("chips", this.chips));
	}

	public void divideChips(int chips) {
		this.chips /= chips;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("chips", this.chips));
	}
	
	public void addWin() {
		this.wins++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("wins", this.wins));
	}
	
	public void addLose() {
		this.loses++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("loses", this.loses));
	}

	public void addGamePlayed() {
		this.gamesPlayed++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.memberid), Updates.set("playedGames", this.gamesPlayed));
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
