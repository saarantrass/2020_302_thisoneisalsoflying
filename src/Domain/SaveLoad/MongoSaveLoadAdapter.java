package Domain.SaveLoad;

import com.mongodb.*;

public class MongoSaveLoadAdapter implements ISaveLoadAdapter {

	private MongoSaveLoad mongoSaveLoad;
	
	public MongoSaveLoadAdapter() {
		mongoSaveLoad = new MongoSaveLoad();
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
