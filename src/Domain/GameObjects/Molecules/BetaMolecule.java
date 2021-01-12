package Domain.GameObjects.Molecules;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;

public class BetaMolecule extends Molecule {

	public BetaMolecule(int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(moleculeID, coordinate, isLinear, isSpinning);
		setFallingStrategy(new LinearStrategy(this));
	}
	
	/**
	 * Updates the falling strategy of the BetaMolecule
	 */
	@Override
	public void updateFallingStrategy() {
		// MODIFIES: fallingBehaviour
		// EFFECTS: changes the falling movement of the molecule
		if(this.getCoordinate().y < Settings.getInstance().getScreenSize().getHeight()/4) {
			this.fallingBehaviour = new LinearStrategy(this);
		}else {
			this.fallingBehaviour = new ZigZagStrategy(this);
		}
	
	}
}
