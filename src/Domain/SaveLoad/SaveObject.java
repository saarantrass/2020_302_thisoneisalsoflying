package Domain.SaveLoad;

import Domain.Game;
import Domain.Settings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SaveObject {
	public Game currGame;
	public Settings currSettings;

	public SaveObject() {
		Game g = Game.getInstance();
		Settings s = Settings.getInstance();
		this.currGame = g;
		this.currSettings = s;
	}
	
	public JsonObject generateSaveJson() {
		JsonObject save = new JsonObject();
		Gson gsonBuilder = new GsonBuilder().create();
		JsonParser jsonParser = new JsonParser();
		
		
		//Start - Game
		save.addProperty("L", this.currGame.L);
		save.addProperty("isPaused", this.currGame.isPaused);
		save.addProperty("isFinished", this.currGame.isFinished);
		
		String onScreenAtomList = gsonBuilder.toJson(this.currGame.onScreenAtomList);
		JsonArray onScreenAtomListJsonArray = jsonParser.parse(onScreenAtomList).getAsJsonArray();
		save.add("onScreenAtomList", onScreenAtomListJsonArray);
		
		String onScreenMoleculeList = gsonBuilder.toJson(this.currGame.onScreenMoleculeList);
		JsonArray onScreenMoleculeListJsonArray = jsonParser.parse(onScreenMoleculeList).getAsJsonArray();
		save.add("onScreenMoleculeList", onScreenMoleculeListJsonArray);
		
		String onScreenPowerUpList = gsonBuilder.toJson(this.currGame.onScreenPowerUpList);
		JsonArray onScreenPowerUpListJsonArray = jsonParser.parse(onScreenPowerUpList).getAsJsonArray();
		save.add("onScreenPowerUpList", onScreenPowerUpListJsonArray);
		
		String onScreenReactionBlockerList = gsonBuilder.toJson(this.currGame.onScreenReactionBlockerList);
		JsonArray onScreenReactionBlockerListJsonArray = jsonParser.parse(onScreenReactionBlockerList).getAsJsonArray();
		save.add("onScreenReactionBlockerList", onScreenReactionBlockerListJsonArray);
		
		String barrelAtom = gsonBuilder.toJson(this.currGame.barrelAtom);
		save.addProperty("barrelAtom", barrelAtom);
		
		String barrelPowerUp = gsonBuilder.toJson(this.currGame.barrelPowerUp);
		save.addProperty("barrelPowerUp", barrelPowerUp);
		//End - Game
		
		//Start - Shooter
		JsonObject shooter = new JsonObject();
		
		shooter.addProperty("coordinate", gsonBuilder.toJson(this.currGame.shooter.getCoordinate()));
		shooter.addProperty("health", this.currGame.shooter.health);
		shooter.addProperty("score", this.currGame.shooter.score);
		
		String inventory = gsonBuilder.toJson(this.currGame.shooter.inventory);
		shooter.addProperty("inventory", inventory);
		
		save.add("shooter", shooter);
		//End - Shooter
		
		//Start - Settings
		String settings = gsonBuilder.toJson(this.currSettings);
		save.addProperty("settings", settings);
		//End - Settings
		
		return save;
	}

}
