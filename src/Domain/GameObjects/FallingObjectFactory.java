package Domain.GameObjects;
import java.awt.Point;
import Domain.GameObjects.Molecules.*;
import Domain.GameObjects.Molecules.Molecule;

public class FallingObjectFactory {
	private static FallingObjectFactory singleton;
	
	public FallingObjectFactory() {}
	
	public Molecule getNewMolecule(int type, Point coords, boolean isSpinning, boolean isLinear) {
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
		return new PowerUp(type, coords);
	}
	
	public ReactionBlocker getNewReactionBlocker(int type, Point coords) {
		return new ReactionBlocker(type, coords);
	}
	
	public static FallingObjectFactory getInstance(){
        if (singleton == null){
            singleton = new FallingObjectFactory();
        }
        return singleton;
    }
    
}
