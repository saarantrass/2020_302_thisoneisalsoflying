package Domain.GameObjects.ReactionBlockers;
import java.awt.Point;

import Domain.GameObjects.ZigZagStrategy;

public class AlphaReactionBlocker extends ReactionBlocker {

	public AlphaReactionBlocker(int ID, Point coordinate) {
		super(ID, coordinate);
		setMovingStrategy(new ZigZagStrategy(this));
	}

	@Override
	public void updateMovingStrategy() {}

}
