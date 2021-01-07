package Domain.GameObjects.ReactionBlockers;

import java.awt.Point;
import Domain.GameObjects.FallingObject;
import Domain.GameObjects.IMovingBehaviour;

public abstract class ReactionBlocker extends FallingObject implements IMovingBehaviour {

	public ReactionBlocker(int ID, Point coordinate) {
		super(ID, coordinate);
	}
	
}
