package Domain.SaveLoad;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaveLoad {
	
	private MongoClient mongoClient;
	private MongoDatabase db;
	private MongoCollection<Document> collection;
	
	public MongoSaveLoad() {
		// Mongo Connection setup
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		this.mongoClient = MongoClients.create("mongodb+srv://admin:toiaf123456@kuvid.0aptg.mongodb.net/"); // uri connection to the server
		
		this.db = mongoClient.getDatabase("main");
		this.collection = db.getCollection("savedGames");
	}

	public void insert(Document obj) {
		String username = (String) obj.get("username");
		
		// Check if username was already used and update if exists
		Document query = new Document("username", username);
		long count = this.collection.count(query);
		if (count > 0) {
			Document toUpdate = new Document();
			toUpdate.append("$set", obj);
			this.collection.replaceOne(query, obj);
		} else {
			this.collection.insertOne(obj);			
		}
		
		System.out.println("Saved to mongodb");
	}
	
	public Document read(String username) {
		Document query = new Document("username", username);
		Document loadDocument = this.collection.find(query).first();
		return loadDocument;
	}
}
