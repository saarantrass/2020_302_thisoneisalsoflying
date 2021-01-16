package Domain;

import Domain.Player.Inventory;

public class Blender {
   
  
   protected static void useBlender(int sourceAtom, int goalAtom, Inventory inventory ) {
      
      if(verify(sourceAtom,goalAtom, inventory)) {
    	  //----Blend----//
    	  if(sourceAtom < goalAtom ) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,goalAtom-sourceAtom+1);
    	  } 
    	  //----Break----//
    	  else {
    		  inventory.addInventoryAtom(goalAtom,sourceAtom-goalAtom+1);
    	      inventory.removeInventoryAtom(sourceAtom,1);
    	  }
      }  	
   }
   
   private static boolean verify(int sourceAtom, int goalAtom, Inventory inventory){
	   if (sourceAtom > goalAtom)
		   return inventory.getInventoryAtomCount(sourceAtom) >= 1;
	   else if (sourceAtom < goalAtom)
		   return inventory.getInventoryAtomCount(sourceAtom) >= (goalAtom-sourceAtom+1);
	   return false;
   }
   
}
