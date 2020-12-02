package Domain;

import Domain.Player.Inventory;

public class Blender {
   
  
   protected static void useBlender(int sourceAtom, int goalAtom, Inventory inventory ) {
      
      if(verify(sourceAtom,goalAtom, inventory)) {
      //inventory.addInventoryAtom(goalAtom);
      //inventory.removeInventoryAtom(sourceAtom);
      }
    	
   }
   
    protected static boolean verify(int SA,int GA, Inventory inventory){
        /*while((!(SA==1) || !(SA==2) || SA==3 ||SA==4) || (!(GA==1) || !(GA==2) || !(GA==3) || !(GA==4))){
            //User entered an invalid value, program asks user again and again until user enters the proper values.
            useBlender(SA,GA, Inventory,inventory);
        }*/
    	//TODO add exception for invalid input/output combination
    	return true;
    } 
    
}
