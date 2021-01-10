package Domain.GameObjects.ReactionBlockers;

import java.awt.Point;
import Domain.GameObjects.LinearStrategy;

public class SigmaReactionBlocker extends ReactionBlocker {

	public SigmaReactionBlocker(int ID, Point coordinate) {
		super(ID, coordinate);
		setFallingStrategy(new LinearStrategy(this));
	}

	@Override
	public void updateFallingStrategy() {}
}
