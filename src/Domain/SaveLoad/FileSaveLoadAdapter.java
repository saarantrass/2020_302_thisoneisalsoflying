package Domain.SaveLoad;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

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
	public void load() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		// TODO Auto-generated method stub
		this.fileSaveLoad.read("a");
	}

}
