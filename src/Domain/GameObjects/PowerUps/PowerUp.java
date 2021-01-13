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
		this.movingBehaviour = new LinearStrategy(this);
	}

	public boolean isThrown() {
		return isThrown;
	}
	
	public void setThrown(boolean isThrown) {
		this.isThrown = isThrown;
	}
	
	@Override
	public void updateMovingStrategy() {
		if (isThrown) {
			this.movingBehaviour = new ShootedStrategy(this);
		}
	}

}
