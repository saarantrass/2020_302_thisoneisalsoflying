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
   
   protected static boolean verifyBreak(int sourceAtom, int goalAtom, Inventory inventory) {
	   return (sourceAtom>goalAtom && inventory.getInventoryAtomCount(sourceAtom) >= 1) ;
   }
   protected static boolean verifyBlend(int sourceAtom, int goalAtom, Inventory inventory) {
	   return (sourceAtom<goalAtom && inventory.getInventoryAtomCount(sourceAtom) >= (goalAtom-sourceAtom+1)) ;
   }
   
   protected static boolean verify(int sourceAtom, int goalAtom, Inventory inventory){
	   if (verifyBreak(sourceAtom, goalAtom, inventory) || verifyBlend(sourceAtom, goalAtom, inventory) ) {
		   return true;
	   }
	   return false;
   }
   
}
