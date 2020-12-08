package Domain;

import Domain.Player.Inventory;

public class Blender {
   
  
   protected static void useBlender(int sourceAtom, int goalAtom, Inventory inventory ) {
      
      if(verify(sourceAtom,goalAtom, inventory)) {
      inventory.addInventoryAtom(goalAtom);
      inventory.removeInventoryAtom(sourceAtom);
      }
      useBlender(sourceAtom,goalAtom,inventory);
    	
   }
   
    protected static boolean verify(int SA,int GA, Inventory inventory){
        if(inventory.checkAtomAvailability(SA)) {
        	return true;
        }else { return false;
        	
        }}
    	
    
    
}
