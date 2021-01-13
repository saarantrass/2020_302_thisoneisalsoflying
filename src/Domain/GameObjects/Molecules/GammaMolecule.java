package Domain.GameObjects.Molecules;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;

public class GammaMolecule extends Molecule {

	public GammaMolecule(int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(moleculeID, coordinate, false, false);
		setMovingStrategy(new LinearStrategy(this));
	}
	@Override
	public void updateMovingStrategy() {
		if(this.getCoordinate().y < Settings.getInstance().getScreenSize().getHeight()/2) {
			this.movingBehaviour = new LinearStrategy(this);
		}else {
			this.movingBehaviour = new ZigZagStrategy(this);
		}
	
	}
}
