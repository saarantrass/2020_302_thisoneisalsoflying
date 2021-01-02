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
	public boolean status = false;
	
	public MongoSaveLoad() {
		// Mongo Connection setup
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		this.mongoClient = MongoClients.create("mongodb+srv://admin:toiaf123456@kuvid.0aptg.mongodb.net/"); // uri connection to the server
		
		this.db = mongoClient.getDatabase("main");
		this.collection = db.getCollection("savedGames");
		this.status = true;
	}

	public void insert(Document obj) {
		this.collection.insertOne(obj);
	}
}
