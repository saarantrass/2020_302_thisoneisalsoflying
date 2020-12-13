package Domain.Player;

import java.util.HashMap;

import Domain.Settings;

public class Inventory {
	
	private HashMap<Integer, Integer> inventoryAtom;
	private HashMap<Integer, Integer> inventoryPowerUp;
	
	
	public Inventory() {
		this.inventoryAtom = new HashMap<Integer, Integer>();
		this.inventoryPowerUp = new HashMap<Integer, Integer>();
		
		inventoryAtom.put(1, Settings.getInstance().getAtomNumber(1));
		inventoryAtom.put(2, Settings.getInstance().getAtomNumber(2));
		inventoryAtom.put(3, Settings.getInstance().getAtomNumber(3));
		inventoryAtom.put(4, Settings.getInstance().getAtomNumber(4));
		
		inventoryPowerUp.put(1, 0);
		inventoryPowerUp.put(2, 0);
		inventoryPowerUp.put(3, 0);
		inventoryPowerUp.put(4, 0);
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
	
	
	public void addInventoryAtom(int type, int howmany) {
		int curr = this.inventoryAtom.get(type);
		this.inventoryAtom.replace(type, curr+howmany);
	}
	
	
	public void addInventoryPowerUp(int type) {
		int curr = this.inventoryPowerUp.get(type);
		this.inventoryPowerUp.replace(type, curr+1);
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
	
}
