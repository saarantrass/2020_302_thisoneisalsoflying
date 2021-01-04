package Domain.GameObjects.Molecules;
import java.awt.Point;

import Domain.GameObjects.ZigZagStrategy;

public class AlphaMolecule extends Molecule {

	public AlphaMolecule(int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(moleculeID, coordinate, isLinear, isSpinning);
		setFallingStrategy(new ZigZagStrategy(this));
	}

	

}
