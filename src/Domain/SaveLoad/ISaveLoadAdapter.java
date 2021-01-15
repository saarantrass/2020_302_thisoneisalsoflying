package Domain.SaveLoad;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface ISaveLoadAdapter {
	public void save();
	public void load() throws JsonSyntaxException, JsonIOException, FileNotFoundException;
}
