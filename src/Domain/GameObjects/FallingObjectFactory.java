package Domain.GameObjects;
import java.awt.Point;

import Domain.GameObjects.Atoms.*;
import Domain.GameObjects.Atoms.Throwable;
import Domain.GameObjects.Molecules.*;
import Domain.GameObjects.Molecules.Molecule;

public class FallingObjectFactory {
	private static FallingObjectFactory singleton;
	
	public FallingObjectFactory() {}
	
	public Molecule getNewMolecule(int type, Point coords, boolean isSpinning, boolean isLinear) {
		Molecule mol = null;
		switch (type) {
			case 1:
				mol = new AlphaMolecule(type, coords, isSpinning, isLinear);
				break;
			case 2:
				mol = new BetaMolecule(type, coords, isSpinning, isLinear);
				break;
			case 3:
				mol = new GammaMolecule(type, coords, isSpinning, isLinear);
				break;
			case 4:
				mol = new SigmaMolecule(type, coords, isSpinning, isLinear);
				break;
		}
		return mol;
	}
	
	public ShieldDecorator addNewShield(int type, Throwable atom) {
		ShieldDecorator shield = null;
		switch (type) {
			case 1:
				shield = new EtaShield(atom);
				break;
			case 2:
				shield = new LotaShield(atom);
				break;
			case 3:
				shield = new ThetaShield(atom);
				break;
			case 4:
				shield = new ZetaShield(atom);
				break;
		}
		return shield;
		
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
