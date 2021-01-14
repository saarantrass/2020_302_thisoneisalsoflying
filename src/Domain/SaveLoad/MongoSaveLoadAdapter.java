package Domain.SaveLoad;

import org.bson.Document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MongoSaveLoadAdapter implements ISaveLoadAdapter {

	private MongoSaveLoad mongoSaveLoad;
	private SaveObject currSave;
	
	public MongoSaveLoadAdapter() {
		this.mongoSaveLoad = new MongoSaveLoad();
		this.currSave = new SaveObject();
	}
	
	@Override
	public void save() {
		this.mongoSaveLoad.insert(currSave.toDBObject());
	}

	@Override
	public void load() {
		Document loadDoc = this.mongoSaveLoad.read((String) currSave.toDBObject().get("username"));
		if (loadDoc.equals(null)) {
			// No document was found with given username
			return;
		}
		
		JsonObject jo = (JsonObject) new JsonParser().parse(loadDoc.toJson());
		// Same operations with file
		
	}

}
