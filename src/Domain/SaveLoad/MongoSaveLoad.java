package Domain.SaveLoad;

import java.net.UnknownHostException;

import com.mongodb.*;

public class MongoSaveLoad {
	
	private MongoClient mongoClient;
	private DB db;
	private DBCollection collection;
	public boolean status = false;
	
	public MongoSaveLoad() {
		try {
			this.mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://admin:toiaf123456@kuvid.0aptg.mongodb.net/test"));
			this.db = mongoClient.getDB("main");
			this.collection = db.getCollection("savedGames");
			this.status = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.status = false;
		}
	}

	public DBCollection getCollection() {
		return this.collection;
	}
}
