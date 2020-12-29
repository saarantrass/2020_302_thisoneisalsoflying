package Domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class GameSave {
	// TODO add other data which needs to be saved
	public String username;
	
	public DBObject toDBObject() {
		return new BasicDBObject().append("username", username);
	}
}
