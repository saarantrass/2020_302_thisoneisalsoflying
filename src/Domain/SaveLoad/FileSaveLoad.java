package Domain.SaveLoad;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Domain.Game;
import Domain.Settings;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;
import Domain.Player.Inventory;
import Domain.Player.Player;
import Domain.Player.Shooter;


public class FileSaveLoad {
	
	//TODO code to write to a text file and read from a text file
	public void write(SaveObject currSave) {
		JsonObject save = currSave.generateSaveJson();
		String name = "save_" + Settings.getInstance().getPlayerName();
		try (FileWriter file = new FileWriter("Saves/"+ name +".json")) {
			 
            file.write(save.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println(save);
	}
	
	public JsonObject read(String userName) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		String name = "save_" + Settings.getInstance().getPlayerName();
        Object obj = new JsonParser().parse(new FileReader("Saves/"+ name +".json")); 
        JsonObject jo = (JsonObject) obj; //all json imported
        
        return jo;
        
	}
	
}
