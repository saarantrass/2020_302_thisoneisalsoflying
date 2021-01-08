package Domain.GameObjects;
import java.awt.Point;
import Domain.GameObjects.Molecules.*;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.*;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.*;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;

public class FallingObjectFactory {
	/**
	 * Rep invariant:
	 * if type != 1,2,3,4; no object is created
	 */
	
	/***
	 * Overview: FallingObjectFactory class is a singleton class that we can use its functions to create objects of given types that
	 * are going to fall from top of screen.
	 */
	private static FallingObjectFactory singleton;
	public FallingObjectFactory() {}
	
	/***
	 * getNewMolecule method for creating new molecule
	 * @param type		type of molecule
	 * @param coors		first coords of created Molecule
	 * @param isSpinning boolean that represents if that molecule is going to spin
	 * @param isLinear  boolean that represents if that molecule is linear or not
	 */
	 
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

	/***
	 * getNewPowerUp method for creating new PowerUp
	 * @param type		type of PowerUp
	 * @param coors		first coords of created PowerUp
	 */
	public PowerUp getNewPowerUp(int type, Point coords,boolean isThrown) {
	    /***
		* @EFFECTS return a corresponding PowerUp object created using variables type and coords.
		*/
		switch (type) {
		case 1:
			return new AlphaPowerUp(type, coords, isThrown);
		case 2:
			return new BetaPowerUp(type, coords, isThrown);
		case 3:
			return new GammaPowerUp(type, coords, isThrown);
		case 4:
			return new SigmaPowerUp(type, coords, isThrown);
		}
		return null;
	}
	/***
	 * getNewReactionBlocker method for creating new ReactionBlocker
	 * @param type		type of ReactionBlocker
	 * @param coors		first coords of created ReactionBlocker
	 */
	public ReactionBlocker getNewReactionBlocker(int type, Point coords) {
		/***
		* @EFFECTS return a corresponding ReactionBlocker object created using variables type and coords.
		*/
		switch (type) {
		case 1:
			return new AlphaReactionBlocker(type, coords);
		case 2:
			return new BetaReactionBlocker(type, coords);
		case 3:
			return new GammaReactionBlocker(type, coords);
		case 4:
			return new SigmaReactionBlocker(type, coords);
		}
		return null;
	}

	public static FallingObjectFactory getInstance(){
		/***
		* @EFFECTS if singleton is null, this function creates an instance of FallingObjectFactory and returns it. Else 
		* returns previously created instance.
		*/
        if (singleton == null){
            singleton = new FallingObjectFactory();
        }
        return singleton;
    }
    
}
