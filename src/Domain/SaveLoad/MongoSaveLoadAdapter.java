package Domain.SaveLoad;

import java.awt.Point;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Domain.Game;
import Domain.Settings;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ShootedStrategy;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;
import Domain.Player.Inventory;
import Domain.Player.Player;
import Domain.Player.Shooter;

public class MongoSaveLoadAdapter implements ISaveLoadAdapter {

	private MongoSaveLoad mongoSaveLoad;
	private SaveObject currSave;
	
	public MongoSaveLoadAdapter() {
		this.mongoSaveLoad = new MongoSaveLoad();
		this.currSave = new SaveObject();
	}
	
	@Override
	public void save() {
		this.mongoSaveLoad.insert(currSave.toDBObject());
	}

	@Override
	public void load() {
		Document loadDoc = this.mongoSaveLoad.read((String) currSave.toDBObject().get("username"));
		if (loadDoc.equals(null)) {
			// No document was found with given username
			return;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(IMovingBehaviour.class, (InstanceCreator<IMovingBehaviour>) type -> new LinearStrategy(FallingObjectFactory.getInstance().getNewPowerUp(3, new Point(0,0), true)));
		gsonBuilder.registerTypeAdapter(PowerUp.class, (InstanceCreator<PowerUp>) type -> FallingObjectFactory.getInstance().getNewPowerUp(3, new Point(0,0), true));
		Gson gson = gsonBuilder.create();
		
		JsonObject jo = (JsonObject) new JsonParser().parse(loadDoc.toJson());
		// Same operations with file
		
		JsonObject settingsobj = (JsonObject) jo.getAsJsonObject("settings");
        Settings s = gson.fromJson(settingsobj, Settings.class); //settings
        
        Settings.getInstance().setSettings(s.getAtomNumber(1), s.getAtomNumber(2), s.getAtomNumber(3), s.getAtomNumber(4), s.getMoleculeNumber(1), s.getMoleculeNumber(2), s.getMoleculeNumber(3), s.getMoleculeNumber(4), s.getReactionBlockerNumber(1), s.getReactionBlockerNumber(2), s.getReactionBlockerNumber(3), s.getReactionBlockerNumber(4), s.getPowerUpNumber(1), s.getPowerUpNumber(2), s.getPowerUpNumber(3), s.getPowerUpNumber(4), s.getShieldNumber(1), s.getShieldNumber(2), s.getShieldNumber(3), s.getShieldNumber(4), s.isLinear(), s.isSpinning(), s.getLengthUnit(), s.getDifficultyLevel(), Settings.getInstance().getScreenSize(), s.getPlayerName(), s.timeRemaining);
        
        Inventory inv = gson.fromJson(jo.getAsJsonObject("shooter").getAsJsonObject("inventory"), Inventory.class); //shooter inventory
        HashMap<Integer, CopyOnWriteArrayList<Atom>> atomMap = inv.getAtomMap();
        
        for(Atom at : atomMap.get(1)) {
        	at.setMovingBehaviour(new ShootedStrategy(at));
        }
        
        for(Atom at : atomMap.get(2)) {
        	at.setMovingBehaviour(new ShootedStrategy(at));
        }
        
        for(Atom at : atomMap.get(3)) {
        	at.setMovingBehaviour(new ShootedStrategy(at));
        }
        
        for(Atom at : atomMap.get(4)) {
        	at.setMovingBehaviour(new ShootedStrategy(at));
        }
        
        JsonObject shooter = (JsonObject) jo.getAsJsonObject("shooter");
        Point shcoord = gson.fromJson(shooter.get("coordinate"), Point.class);
        Shooter sh = new Shooter(shcoord);
        sh.setAngle(shooter.get("angle").getAsDouble());
        sh.inventory = inv;
        
        Player p = new Player();
        p.health = shooter.get("health").getAsDouble();
        p.score = shooter.get("score").getAsDouble();
        
        if (!jo.get("barrelAtom").isJsonNull()) {
        	JsonElement at = jo.get("barrelAtom");
        	while(at.getAsJsonObject().get("atom") != null) {
        		at = at.getAsJsonObject().get("atom");
        	}
        	Game.getInstance().barrelAtom = new Atom((JsonObject) at.getAsJsonObject());
        }
        
        if (!jo.get("barrelPowerUp").isJsonNull()) {
        	Game.getInstance().barrelPowerUp = gson.fromJson((JsonObject) jo.getAsJsonObject("barrelPowerUp"), PowerUp.class);        	
        }
        
        Game.getInstance().shooter = sh;
        Game.getInstance().player = p;
        Game.getInstance().setLengthUnit(jo.get("L").getAsInt());
        Game.getInstance().timer = jo.get("timer").getAsInt();
        
        CopyOnWriteArrayList<Atom> onScAtomList = new CopyOnWriteArrayList<Atom>();
        for (JsonElement at : jo.get("onScreenAtomList").getAsJsonArray()) {
        	while(at.getAsJsonObject().get("atom") != null) {
        		at = at.getAsJsonObject().get("atom");
        	}
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
        
        System.out.println("End Mongo load");
	}

}
