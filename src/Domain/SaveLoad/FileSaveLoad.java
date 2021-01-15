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

import Domain.Game;
import Domain.Settings;
import Domain.GameObjects.FallingObjectFactory;
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
        
        Game.getInstance().barrelAtom = new Atom((JsonObject) jo.getAsJsonObject("barrelAtom"));
        
        if (!jo.get("barrelPowerUp").isJsonNull()) {
        	Game.getInstance().barrelPowerUp = gson.fromJson((JsonObject) jo.getAsJsonObject("barrelPowerUp"), PowerUp.class);        	
        }
        Game.getInstance().shooter = sh;
        Game.getInstance().player = p;
        Game.getInstance().L = jo.get("L").getAsInt();
        
        CopyOnWriteArrayList<Atom> onScAtomList = new CopyOnWriteArrayList<Atom>();
        for (JsonElement at : jo.get("onScreenAtomList").getAsJsonArray()) {
        	onScAtomList.add(new Atom((JsonObject)at));
        }
        Game.getInstance().onScreenAtomList = onScAtomList;
        
        CopyOnWriteArrayList<Molecule> onScMoleculeList = new CopyOnWriteArrayList<Molecule>();
        for (JsonElement at : jo.get("onScreenMoleculeList").getAsJsonArray()) {
        	JsonObject objTemp = at.getAsJsonObject();
        	onScMoleculeList.add(FallingObjectFactory.getInstance().getNewMolecule(objTemp.get("ID").getAsInt(), gson.fromJson((JsonObject) objTemp.getAsJsonObject("coordinate"), Point.class), objTemp.get("isLinear").getAsBoolean(), objTemp.get("isSpinning").getAsBoolean()));    
        }
        Game.getInstance().onScreenMoleculeList = onScMoleculeList;
        
        
        CopyOnWriteArrayList<PowerUp> onScPowerUpList = new CopyOnWriteArrayList<PowerUp>();
        for (JsonElement at : jo.get("onScreenPowerUpList").getAsJsonArray()) {
        	JsonObject objTemp = at.getAsJsonObject();
        	onScPowerUpList.add(FallingObjectFactory.getInstance().getNewPowerUp(objTemp.get("ID").getAsInt(), gson.fromJson((JsonObject) objTemp.getAsJsonObject("coordinate"), Point.class),false));
        }
        Game.getInstance().onScreenPowerUpList = onScPowerUpList;
        
        CopyOnWriteArrayList<ReactionBlocker> onReactionBlockerList = new CopyOnWriteArrayList<ReactionBlocker>();
        for (JsonElement at : jo.get("onScreenReactionBlockerList").getAsJsonArray()) {
        	JsonObject objTemp = at.getAsJsonObject();
        	onReactionBlockerList.add(FallingObjectFactory.getInstance().getNewReactionBlocker(objTemp.get("ID").getAsInt(), gson.fromJson((JsonObject) objTemp.getAsJsonObject("coordinate"), Point.class)));
        }
        Game.getInstance().onScreenReactionBlockerList = onReactionBlockerList;
        
        System.out.println("End load");
        
	}
	
}
