package Domain.GameObjects.Molecules;
import java.awt.Point;

import Domain.GameObjects.FallingObject;
import Domain.GameObjects.IMovingBehaviour;

public abstract class Molecule extends FallingObject implements IMovingBehaviour{
	
	public Molecule(int ID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(ID, coordinate, isLinear, isSpinning);
	}
	
}
