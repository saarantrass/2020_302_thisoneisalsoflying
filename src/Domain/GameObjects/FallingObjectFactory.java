package Domain.GameObjects;
import java.awt.Point;
import Domain.GameObjects.Molecules.*;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.*;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.*;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;

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


	public PowerUp getNewPowerUp(int type, Point coords,boolean isThrown) {
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

	public ReactionBlocker getNewReactionBlocker(int type, Point coords) {
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
		if (singleton == null){
			singleton = new FallingObjectFactory();
		}
		return singleton;
	}

}
