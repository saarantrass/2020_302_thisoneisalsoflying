package Domain;

import Domain.Player.Inventory;

public class Blender {
   
  
   protected static void useBlender(int sourceAtom, int goalAtom, Inventory inventory ) {
      
      if(verify(sourceAtom,goalAtom, inventory)) {
    	  
    	  //----Blend----//
    	  if(sourceAtom == 1 && goalAtom == 2) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,2);
    	  }
    	  if(sourceAtom == 1 && goalAtom == 3) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,3);
    	  }
    	  if(sourceAtom == 1 && goalAtom == 4) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,4);
    	  }
    	  
    	  if(sourceAtom == 2 && goalAtom == 3) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,2);
    	  }
    	  if(sourceAtom == 2 && goalAtom == 4) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,3);
    	  }
    	  
    	  if(sourceAtom == 3 && goalAtom == 4) {
    		  inventory.addInventoryAtom(goalAtom,1);
    	      inventory.removeInventoryAtom(sourceAtom,2);
    	  }
    	  
    	  //----Break----//
    	  if(sourceAtom == 2 && goalAtom == 1) {
    		  inventory.addInventoryAtom(goalAtom,2);
    	      inventory.removeInventoryAtom(sourceAtom,1);
    	  }
    	  
    	  if(sourceAtom == 3 && goalAtom == 1) {
    		  inventory.addInventoryAtom(goalAtom,3);
    	      inventory.removeInventoryAtom(sourceAtom,1);
    	  }
    	  if(sourceAtom == 4 && goalAtom == 1) {
    		  inventory.addInventoryAtom(goalAtom,4);
    	      inventory.removeInventoryAtom(sourceAtom,1);
    	  }
    	  
    	  if(sourceAtom == 3 && goalAtom == 2) {
    		  inventory.addInventoryAtom(goalAtom,2);
    	      inventory.removeInventoryAtom(sourceAtom,1);
    	  }
    	  if(sourceAtom == 4 && goalAtom == 2) {
    		  inventory.addInventoryAtom(goalAtom,3);
    	      inventory.removeInventoryAtom(sourceAtom,1);
    	  }
    	  
    	  if(sourceAtom == 4 && goalAtom == 3) {
    		  inventory.addInventoryAtom(goalAtom,3);
    	      inventory.removeInventoryAtom(sourceAtom,2);
    	  }
      }
    	
   }
 //-----------------Blend Rules---------------------//
   
   public static boolean alphaToBeta(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 1 && goalAtom == 2) && inventory.checkAtomAvailability(sourceAtom,2)) ;
   }
   
   public static boolean alphaToGamma(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 1 && goalAtom == 3) && inventory.checkAtomAvailability(sourceAtom,3));
   }
   
   public static boolean alphaToSigma(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 1 && goalAtom == 4) && inventory.checkAtomAvailability(sourceAtom,4));
   }
   
   public static boolean betaToGamma(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 2 && goalAtom == 3) && inventory.checkAtomAvailability(sourceAtom,2));
   }
   
   public static boolean betaToSigma(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 2 && goalAtom == 4) && inventory.checkAtomAvailability(sourceAtom,3));
   }
   
   public static boolean gammaToSigma(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 3 && goalAtom == 4) && inventory.checkAtomAvailability(sourceAtom,2));
   }
    //-----------------Break Rules---------------------//
  
   
   public static boolean betaToAlpha(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 2 && goalAtom == 1) && inventory.checkAtomAvailability(sourceAtom,1)) ;
   }
   
   public static boolean gammaToAlpha(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 3 && goalAtom == 1) && inventory.checkAtomAvailability(sourceAtom,1));
   }
   
   public static boolean sigmaToAlpha(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 4 && goalAtom == 1) && inventory.checkAtomAvailability(sourceAtom,1));
   }
   
   public static boolean gammaToBeta(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 3 && goalAtom == 2) && inventory.checkAtomAvailability(sourceAtom,1));
   }
   
   public static boolean sigmaToBeta(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 4 && goalAtom == 2) && inventory.checkAtomAvailability(sourceAtom,1));
   }
   
   public static boolean sigmaToGamma(int sourceAtom, int goalAtom, Inventory inventory) {
	   return ((sourceAtom == 4 && goalAtom == 3) && inventory.checkAtomAvailability(sourceAtom,1));
   }
   
   protected static boolean verify(int sourceAtom, int goalAtom, Inventory inventory){
	   if(alphaToBeta(sourceAtom, goalAtom, inventory) || alphaToGamma(sourceAtom, goalAtom, inventory) || alphaToSigma(sourceAtom, goalAtom, inventory) || betaToGamma(sourceAtom, goalAtom, inventory) || betaToSigma(sourceAtom, goalAtom, inventory) || gammaToSigma(sourceAtom, goalAtom, inventory) || betaToAlpha(sourceAtom, goalAtom, inventory) || gammaToAlpha(sourceAtom, goalAtom, inventory) || sigmaToAlpha(sourceAtom, goalAtom, inventory) || gammaToBeta(sourceAtom, goalAtom,  inventory) || sigmaToBeta(sourceAtom, goalAtom, inventory) || sigmaToGamma(sourceAtom, goalAtom, inventory)) {
		   return true;
	   }else { 
		   return false;
	   }
   }
   
}
