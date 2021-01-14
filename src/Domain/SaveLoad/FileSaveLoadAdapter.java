package Domain.SaveLoad;

import Domain.Game;

public class FileSaveLoadAdapter implements ISaveLoadAdapter {

	private FileSaveLoad fileSaveLoad;
	private SaveObject currSave;
	
	public FileSaveLoadAdapter(Game game) {
		this.fileSaveLoad = new FileSaveLoad();
		this.currSave = new SaveObject(game);
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
