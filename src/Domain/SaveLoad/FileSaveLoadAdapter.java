package Domain.SaveLoad;

public class FileSaveLoadAdapter implements ISaveLoadAdapter {

	private FileSaveLoad fileSaveLoad;
	private SaveObject currSave;
	
	public FileSaveLoadAdapter() {
		this.fileSaveLoad = new FileSaveLoad();
		this.currSave = new SaveObject();
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		this.fileSaveLoad.write(currSave);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

}
