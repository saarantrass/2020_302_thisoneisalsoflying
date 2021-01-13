package Domain.GameObjects.Molecules;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;

public class BetaMolecule extends Molecule {

	public BetaMolecule(int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		super(moleculeID, coordinate, isLinear, isSpinning);
		setMovingStrategy(new LinearStrategy(this));
	}
	
	/**
	 * Updates the Moving strategy of the BetaMolecule
	 */
	@Override
	public void updateMovingStrategy() {
		// MODIFIES: MovingBehaviour
		// EFFECTS: changes the Moving movement of the molecule
		if(this.getCoordinate().y < Settings.getInstance().getScreenSize().getHeight()/4) {
			this.movingBehaviour = new LinearStrategy(this);
		}else {
			this.movingBehaviour = new ZigZagStrategy(this);
		}
	
	}
}
