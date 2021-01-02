package Domain.SaveLoad;


public class MongoSaveLoadAdapter implements ISaveLoadAdapter {

	private MongoSaveLoad mongoSaveLoad;
	private SaveObject currSave;
	
	public MongoSaveLoadAdapter() {
		this.mongoSaveLoad = new MongoSaveLoad();
		this.currSave = new SaveObject();
	}
	
	@Override
	public void save() {
		System.out.println("INSIDE SAVE");
		this.mongoSaveLoad.insert(currSave.toDBObject());
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
