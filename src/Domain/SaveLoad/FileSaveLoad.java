package Domain.SaveLoad;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Domain.Settings;


public class FileSaveLoad {
	
	public void write(SaveObject currSave) {
		JsonObject save = currSave.generateSaveJson();
		String name = "save_" + Settings.getInstance().getPlayerName();
		try (FileWriter file = new FileWriter("Saves/"+ name +".json")) {
			 
            file.write(save.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println("Saved to file");
	}
	
	public JsonObject read() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		String name = "save_" + Settings.getInstance().getPlayerName();
        Object obj = new JsonParser().parse(new FileReader("Saves/"+ name +".json")); 
        JsonObject jo = (JsonObject) obj; //all json imported
        
        return jo;
        
	}
	
}
