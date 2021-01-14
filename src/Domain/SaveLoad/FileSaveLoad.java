package Domain.SaveLoad;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Domain.Game;
import Domain.Settings;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.Player.Inventory;
import Domain.Player.Player;
import Domain.Player.Shooter;

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
	
	public void read(String userName) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();

        Object obj = new JsonParser().parse(new FileReader("Saves/save.json")); 
        JsonObject jo = (JsonObject) obj; //all json imported
        
        Settings s = gson.fromJson(new FileReader("Saves/save.json"), Settings.class); //settings
        
        Inventory inv = gson.fromJson(jo.get("shooter"), Inventory.class); //shooter inventory
        
        JsonObject shooter = (JsonObject) jo.getAsJsonObject("shooter");
        Point shcoord = gson.fromJson(shooter.get("coordinate"), Point.class);
        Shooter sh = new Shooter(shcoord);
        sh.inventory = inv;
        
        Player p = new Player();
        p.health = shooter.get("health").getAsDouble();
        p.score = shooter.get("score").getAsDouble();
        
        //Game.getInstance().barrelAtom = gson.fromJson((JsonObject) jo.getAsJsonObject("barrelAtom"), Atom.class);
        //Game.getInstance().barrelPowerUp = gson.fromJson((JsonObject) jo.getAsJsonObject("barrelPowerUp"), PowerUp.class);
        Game.getInstance().shooter = sh;
        Game.getInstance().player = p;
        Game.getInstance().L = jo.get("L").getAsInt();
        
        //CopyOnWriteArrayList<Atom> onScAtomList = gson.fromJson(jo.get("onScreenAtomList"), new TypeToken<CopyOnWriteArrayList<Atom>>(){}.getType());
        //System.out.println(onScAtomList);
	}
	
}
