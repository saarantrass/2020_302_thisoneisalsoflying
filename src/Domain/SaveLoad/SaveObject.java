package Domain.SaveLoad;

import Domain.Game;
import Domain.Settings;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SaveObject {
	public Game currGame;
	public Settings currSettings;
	public String username;
	
	public SaveObject() {
		Game g = Game.getInstance();
		Settings s = Settings.getInstance();
		this.currGame = g;
		this.currSettings = s;
		this.username = s.getPlayerName();
	}
	
	public JsonObject generateSaveJson() {
		JsonObject save = new JsonObject();
		Gson gsonBuilder = new GsonBuilder().create();
		JsonParser jsonParser = new JsonParser();
		
		//Start - Game
		save.addProperty("L", this.currGame.getLengthUnit());
		save.addProperty("isPaused", this.currGame.isPaused());
		save.addProperty("isFinished", this.currGame.isFinished());
		save.addProperty("timer", this.currGame.timer);
		
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
		
		JsonElement barrelAtomElement = jsonParser.parse(gsonBuilder.toJson(this.currGame.barrelAtom));
		JsonObject barrelAtom = barrelAtomElement.isJsonObject() ? barrelAtomElement.getAsJsonObject() : null;
		save.add("barrelAtom", barrelAtom);
		
		JsonElement barrelPowerUpElement = jsonParser.parse(gsonBuilder.toJson(this.currGame.barrelPowerUp));
		JsonObject barrelPowerUp = barrelPowerUpElement.isJsonObject() ? barrelAtomElement.getAsJsonObject() : null;
		save.add("barrelPowerUp", barrelPowerUp);
		//End - Game
		
		//Start - Shooter
		JsonObject shooter = new JsonObject();
		
		JsonElement coorElement = jsonParser.parse(gsonBuilder.toJson(this.currGame.shooter.getCoordinate()));
		shooter.add("coordinate", coorElement.isJsonObject() ? coorElement.getAsJsonObject() : null);
		shooter.addProperty("angle", this.currGame.shooter.getAngle());
		shooter.addProperty("health", this.currGame.player.getHealth());
		shooter.addProperty("score", this.currGame.player.getScore());
		
		JsonElement inventory = jsonParser.parse(gsonBuilder.toJson(this.currGame.shooter.inventory));
		shooter.add("inventory", inventory.isJsonObject() ? inventory.getAsJsonObject() : null);
		
		save.add("shooter", shooter);
		//End - Shooter
		
		//Start - Settings
		String settings = gsonBuilder.toJson(this.currSettings);
		save.add("settings", jsonParser.parse(settings).getAsJsonObject());
		//End - Settings
		
		// add username
		save.addProperty("username", this.username);
		
		return save;
	}
	
	public Document toDBObject() {
		return Document.parse(this.generateSaveJson().toString());
	}

}
