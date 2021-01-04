package Domain.GameObjects.Molecules;

import java.awt.Point;

import Domain.Settings;
import Domain.GameObjects.IFallingBehaviour;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;

public class GammaMolecule extends Molecule {

	public GammaMolecule(int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(moleculeID, coordinate, false, false);
		setFallingStrategy(new LinearStrategy(this));
	}
	@Override
	public void setFallingStrategy(IFallingBehaviour fallingBehaviour) {
		if(this.getCoordinate().y < Settings.getInstance().getScreenSize().getHeight()/2) {
			this.fallingBehaviour = new LinearStrategy(this);
		}else {
			this.fallingBehaviour = new ZigZagStrategy(this);
		}
	
	}
}
