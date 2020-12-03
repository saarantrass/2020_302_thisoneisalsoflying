package Domain.Player;

import java.util.HashMap;

public class Inventory {
	
	private HashMap<Integer, Integer> inventoryAtom;
	private HashMap<Integer, Integer> inventoryPowerUp;
	
	public Inventory(HashMap<Integer, Integer> inventoryAtom, HashMap<Integer, Integer> inventoryPoweUp) {
		
		this.inventoryAtom = new HashMap<Integer, Integer>();
		this.inventoryPowerUp = new HashMap<Integer, Integer>();
		
		inventoryAtom.put(0, 0);
		inventoryAtom.put(1, 0);
		inventoryAtom.put(2, 0);
		inventoryAtom.put(3, 0);
		
		inventoryPowerUp.put(0, 0);
		inventoryPowerUp.put(1, 0);
		inventoryPowerUp.put(2, 0);
		inventoryPowerUp.put(3, 0);
	}
	
	public void addInventoryAtom(int type) {
		int curr = this.inventoryAtom.get(type);
		this.inventoryAtom.replace(type, curr+1);
	}
	
	public void addInventoryPowerUp(int type) {
		int curr = this.inventoryPowerUp.get(type);
		this.inventoryPowerUp.replace(type, curr+1);
	}
	
	public void removeInventoryAtom(int type) {
		int curr = this.inventoryAtom.get(type);
		if(curr > 0) {
			this.inventoryAtom.replace(type, curr-1);
		}else {
			//TODO add no more item case
		}
	}
	
	public void removeInventoryPowerUp(int type) {
		int curr = this.inventoryPowerUp.get(type);
		if(curr > 0) {
			this.inventoryPowerUp.replace(type, curr-1);
		}else {
			//TODO add no more item case
		}
	}
	
	
}
