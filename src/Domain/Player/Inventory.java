package Domain.Player;

import java.util.ArrayList;
import java.util.HashMap;

import Domain.Settings;
import Domain.GameObjects.Atom;

public class Inventory {
	
	private HashMap<Integer, Integer> inventoryAtom;
	private HashMap<Integer, Integer> inventoryPowerUp;
	private HashMap<Integer, Integer> inventoryShield;
	//private HashMap<Integer, ArrayList<Atom>> ýnv;//TODO
	
	
	public Inventory() {
		this.inventoryAtom = new HashMap<Integer, Integer>();
		this.inventoryPowerUp = new HashMap<Integer, Integer>();
		this.inventoryShield = new HashMap<Integer, Integer>();
		
		inventoryAtom.put(1, Settings.getInstance().getAtomNumber(1));
		inventoryAtom.put(2, Settings.getInstance().getAtomNumber(2));
		inventoryAtom.put(3, Settings.getInstance().getAtomNumber(3));
		inventoryAtom.put(4, Settings.getInstance().getAtomNumber(4));
		
		inventoryPowerUp.put(1, 0);
		inventoryPowerUp.put(2, 0);
		inventoryPowerUp.put(3, 0);
		inventoryPowerUp.put(4, 0);
		
		inventoryShield.put(1, Settings.getInstance().getShieldNumber(1));
		inventoryShield.put(2, Settings.getInstance().getShieldNumber(2));
		inventoryShield.put(3, Settings.getInstance().getShieldNumber(3));
		inventoryShield.put(4, Settings.getInstance().getShieldNumber(4));
	}
	
	
	public boolean checkAtomAvailability(int id, int howmany) {
		if(inventoryAtom.get(id) >= howmany) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean checkPowerUpAvailability(int id, int howmany) {
		if(inventoryPowerUp.get(id) >= howmany) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public int getInventoryAtomCount(int type) {
		return this.inventoryAtom.get(type);
	}
	
	
	public int getInventoryPowerUpCount(int type) {
		return this.inventoryPowerUp.get(type);
	}
	
	
	public int getInventoryShieldCount(int type) {
		return this.inventoryShield.get(type);
	}
	
	
	public void addInventoryAtom(int type, int howmany) {
		int curr = this.inventoryAtom.get(type);
		this.inventoryAtom.replace(type, curr+howmany);
	}
	
	
	public void addInventoryPowerUp(int type) {
		int curr = this.inventoryPowerUp.get(type);
		this.inventoryPowerUp.replace(type, curr+1);
	}
	
	
	public void addInventoryShield(int type) {
		int curr = this.inventoryShield.get(type);
		this.inventoryShield.replace(type, curr+1);
	}
	
	
	public void removeInventoryAtom(int type, int howmany) {
		int curr = this.inventoryAtom.get(type);
		if(curr > 0) {
			this.inventoryAtom.replace(type, curr-howmany);
		}
	}
	
	
	public void removeInventoryPowerUp(int type) {
		int curr = this.inventoryPowerUp.get(type);
		if(curr > 0) {
			this.inventoryPowerUp.replace(type, curr-1);
		}
	}
	
	
	public void removeInventoryShield(int type) {
		int curr = this.inventoryShield.get(type);
		if(curr > 0) {
			this.inventoryShield.replace(type, curr-1);
		}
	}
	
}
