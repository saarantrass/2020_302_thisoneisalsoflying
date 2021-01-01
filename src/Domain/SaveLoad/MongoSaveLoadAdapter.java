package Domain.SaveLoad;

import com.mongodb.*;

public class MongoSaveLoadAdapter implements ISaveLoadAdapter {

	private MongoSaveLoad mongoSaveLoad;
	private SaveObject currSave;
	
	public MongoSaveLoadAdapter() {
		mongoSaveLoad = new MongoSaveLoad();
		currSave = new SaveObject();
	}
	
	@Override
	public void save() {
		// TODO arrange DBObject to save
		//mongoSaveLoad.getCollection().insert(null);
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
