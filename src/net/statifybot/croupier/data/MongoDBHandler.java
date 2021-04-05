package net.statifybot.croupier.data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import net.statifybot.croupier.token.DONOTOPEN;

public class MongoDBHandler {

	static MongoDatabase db;
	static MongoClient client;

	public static void connect() {

			ConnectionString connString = new ConnectionString(
					DONOTOPEN.getMongoConnectionString());
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString)
					.retryWrites(true).build();
			client = MongoClients.create(settings);
			MongoDatabase database = client.getDatabase("Database");
			System.out.println("Connected to database");
			db = database;
	}
	
	public static void disconnect() {
		client.close();
	}

	public static MongoDatabase getDatabase() {
		return db;
	}
}