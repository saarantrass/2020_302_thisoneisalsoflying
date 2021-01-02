package Domain.SaveLoad;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonObject;

public class FileSaveLoad {
	
	//TODO code to write to a text file and read from a text file
	public void write(SaveObject currSave) {
		JsonObject save = currSave.generateSaveJson();
		try (FileWriter file = new FileWriter("Saves/save.json")) {
			 
            file.write(save.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println(save);
	}
	
}
