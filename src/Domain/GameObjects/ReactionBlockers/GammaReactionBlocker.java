package Domain.GameObjects.ReactionBlockers;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;

public class GammaReactionBlocker extends ReactionBlocker {

	public GammaReactionBlocker(int ID, Point coordinate) {
		super(ID, coordinate);
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
