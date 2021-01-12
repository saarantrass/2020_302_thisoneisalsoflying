package Domain.GameObjects.PowerUps;

import java.awt.Point;
import Domain.GameObjects.FallingObject;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ShootedStrategy;

public abstract class PowerUp extends FallingObject implements IMovingBehaviour{
	public boolean isThrown;
	public PowerUp(int ID, Point coordinate, boolean isThrown) {
		super(ID, coordinate);
		this.isThrown = isThrown;
		this.fallingBehaviour = new LinearStrategy(this);
	}

	public boolean isThrown() {
		return isThrown;
	}
	
	public void setThrown(boolean isThrown) {
		this.isThrown = isThrown;
	}
	
	@Override
	public void updateFallingStrategy() {
		if (isThrown) {
			this.fallingBehaviour = new ShootedStrategy(this);
		}
	}

}
