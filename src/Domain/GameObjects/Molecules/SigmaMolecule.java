package Domain.GameObjects.Molecules;

import java.awt.Point;
import Domain.GameObjects.LinearStrategy;

public class SigmaMolecule extends Molecule {

	public SigmaMolecule(int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(moleculeID, coordinate, false, false);
		setFallingStrategy(new LinearStrategy(this));
	}

	@Override
	public void updateFallingStrategy() {}
}
