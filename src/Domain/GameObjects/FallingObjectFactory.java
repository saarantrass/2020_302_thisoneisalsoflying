package Domain.GameObjects;
import java.awt.Point;
import Domain.GameObjects.Molecules.*;
import Domain.GameObjects.Molecules.Molecule;

public class FallingObjectFactory {
	/**
	 * Rep invariant:
	 * if type != 1,2,3,4; return null
	 */
	
	/***
	 * Overview: FallingObjectFactory class is a singleton class that we can use its functions to create objects of given types that
	 * are going to fall from top of screen.
	 */
	private static FallingObjectFactory singleton;
	
	//constructors
	public FallingObjectFactory() {}
	
	//methods
	public Molecule getNewMolecule(int type, Point coords, boolean isSpinning, boolean isLinear) {
		/***
		* @EFFECTS return a corresponding Molecule object created using variables type, coords, isSpinning and isLinear.
		*/
		switch (type) {
			case 1:
				return new AlphaMolecule(type, coords, isSpinning, isLinear);
			case 2:
				return new BetaMolecule(type, coords, isSpinning, isLinear);
			case 3:
				return new GammaMolecule(type, coords, isSpinning, isLinear);
			case 4:
				return new SigmaMolecule(type, coords, isSpinning, isLinear);
		}
		 return null;
	}
	

	public PowerUp getNewPowerUp(int type, Point coords) {
		/***
		* @EFFECTS return a corresponding PowerUp object created using variables type and coords.
		*/
		if(type == 1 || type == 2 || type == 3 || type == 4) {
			return new PowerUp(type, coords);
		}
		return null;
	}
	
	public ReactionBlocker getNewReactionBlocker(int type, Point coords) {
		/***
		* @EFFECTS return a corresponding ReactionBlocker object created using variables type and coords.
		*/
		if(type == 1 || type == 2 || type == 3 || type == 4) {
			return new ReactionBlocker(type, coords);
		}
		return null;
	}
	
	public static FallingObjectFactory getInstance(){
		/***
		* @EFFECTS if singleton is null, this funtion creates an instance of FallingObjectFactory and returns it. Else 
		* returns previously created instance.
		*/
        if (singleton == null){
            singleton = new FallingObjectFactory();
        }
        return singleton;
    }
    
}
