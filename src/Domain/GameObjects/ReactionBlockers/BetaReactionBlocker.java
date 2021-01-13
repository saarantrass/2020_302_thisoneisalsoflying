package Domain.GameObjects.ReactionBlockers;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;

public class BetaReactionBlocker extends ReactionBlocker {

	public BetaReactionBlocker(int ID, Point coordinate) {
		super(ID, coordinate);
		setMovingStrategy(new LinearStrategy(this));
	}

	@Override
	public void updateMovingStrategy() {
		if(this.getCoordinate().y < Settings.getInstance().getScreenSize().getHeight()/4) {
			this.movingBehaviour = new LinearStrategy(this);
		}else {
			this.movingBehaviour = new ZigZagStrategy(this);
		}
	
	}
}
