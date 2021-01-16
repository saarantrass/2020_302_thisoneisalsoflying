package Domain.Player;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import Domain.Settings;
import Domain.GameObjects.AtomFactory;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.PowerUps.PowerUp;

public class Inventory {
	
	private HashMap<Integer, CopyOnWriteArrayList<Atom>> inventoryAtom;
	private HashMap<Integer, CopyOnWriteArrayList<PowerUp>> inventoryPowerUp;
	private HashMap<Integer, Integer> inventoryShield;
	
	public Inventory() {
		this.inventoryAtom = new HashMap<Integer, CopyOnWriteArrayList<Atom>>();
		this.inventoryPowerUp = new HashMap<Integer, CopyOnWriteArrayList<PowerUp>>();
		this.inventoryShield = new HashMap<Integer, Integer>();
		
		inventoryAtom.put(1, new CopyOnWriteArrayList<Atom>());
		inventoryAtom.put(2, new CopyOnWriteArrayList<Atom>());
		inventoryAtom.put(3, new CopyOnWriteArrayList<Atom>());
		inventoryAtom.put(4, new CopyOnWriteArrayList<Atom>());
		
		for (int i = 0; i < Settings.getInstance().getAtomNumber(1); i++) {
			inventoryAtom.get(1).add(AtomFactory.getInstance().getNewAtom(1, new Point(0,0)));
		}
		
		for (int i = 0; i < Settings.getInstance().getAtomNumber(2); i++) {
			inventoryAtom.get(2).add(AtomFactory.getInstance().getNewAtom(2, new Point(0,0)));
		}
		
		for (int i = 0; i < Settings.getInstance().getAtomNumber(3); i++) {
			inventoryAtom.get(3).add(AtomFactory.getInstance().getNewAtom(3, new Point(0,0)));
		}
		
		for (int i = 0; i < Settings.getInstance().getAtomNumber(4); i++) {
			inventoryAtom.get(4).add(AtomFactory.getInstance().getNewAtom(4, new Point(0,0)));
		}
		
		inventoryPowerUp.put(1, new CopyOnWriteArrayList<PowerUp>());
		inventoryPowerUp.put(2, new CopyOnWriteArrayList<PowerUp>());
		inventoryPowerUp.put(3, new CopyOnWriteArrayList<PowerUp>());
		inventoryPowerUp.put(4, new CopyOnWriteArrayList<PowerUp>());
		
		inventoryShield.put(1, Settings.getInstance().getShieldNumber(1));
		inventoryShield.put(2, Settings.getInstance().getShieldNumber(2));
		inventoryShield.put(3, Settings.getInstance().getShieldNumber(3));
		inventoryShield.put(4, Settings.getInstance().getShieldNumber(4));
	}
	
	
	public int getInventoryAtomCount(int type) {
		return this.inventoryAtom.get(type).size();
	}
	
	
	public int getInventoryPowerUpCount(int type) {
		return this.inventoryPowerUp.get(type).size();
	}
	
	
	public int getInventoryShieldCount(int type) {
		return this.inventoryShield.get(type);
	}
	
	
	public void addInventoryAtom(int type, int count) {
		for(int i = 0; i < count; i++) {
			Atom newAt = AtomFactory.getInstance().getNewAtom(type, new Point(0,0));
			this.inventoryAtom.get(type).add(newAt);			
		}
	}
	
	public void addInventoryAtom(Atom barrelAtom) {
		this.inventoryAtom.get(barrelAtom.getAtomID()).add(barrelAtom);
	}
	
	
	public void addInventoryPowerUp(int type) {
		PowerUp newPow = FallingObjectFactory.getInstance().getNewPowerUp(type, new Point(0,0), true);
		this.inventoryPowerUp.get(type).add(newPow);
	}
	
	public void addInventoryPowerUp(PowerUp pw) {
		this.inventoryPowerUp.get(pw.getID()).add(pw);
	}
	
	public void addInventoryShield(int type) {
		int curr = this.inventoryShield.get(type);
		this.inventoryShield.replace(type, curr+1);
	}
	
	
	public void removeInventoryAtom(int type, int count) {
		int curr = this.inventoryAtom.get(type).size();
		if(curr >= count) {
			for (int i = 0; i < count; i++) {
				this.inventoryAtom.get(type).remove(this.inventoryAtom.get(type).size()-1);
			}
			
		}
	}
	
	
	public void removeInventoryPowerUp(int type) {
		int curr = this.inventoryPowerUp.get(type).size();
		if(curr > 0) {
			this.inventoryPowerUp.get(type).remove(curr-1);
		}
	}
	
	
	public void removeInventoryShield(int type) {
		int curr = this.inventoryShield.get(type);
		if(curr > 0) {
			this.inventoryShield.replace(type, curr-1);
		}
	}
	
	public Atom getRandomAtom() {
		if(!(this.inventoryAtom.get(1).size() <= 0 && this.inventoryAtom.get(2).size() <= 0 && this.inventoryAtom.get(3).size() <= 0 && this.inventoryAtom.get(4).size() <= 0)) {
			Random rn = new Random();
			int type = rn.nextInt(4)+1;
			while(this.inventoryAtom.get(type).size() < 1) {
				type = rn.nextInt(4)+1;
			}
			Atom at = this.inventoryAtom.get(type).remove(this.inventoryAtom.get(type).size() - 1);
			return at;
		}
		return null;
	}
	
	public PowerUp getPowerUp(int type) {
		if(this.inventoryPowerUp.get(type).size() > 0) {
			PowerUp pw = this.inventoryPowerUp.get(type).remove(this.inventoryPowerUp.get(type).size() - 1);
			return pw;
		}else return null;
	}
	
	public HashMap<Integer, CopyOnWriteArrayList<Atom>> getAtomMap(){
		return this.inventoryAtom;
	}
	
}
