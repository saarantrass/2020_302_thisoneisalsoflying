package Domain.GameObjects;

import java.awt.Point;

public class ReactionBlocker {
	public int reactionBlockerID;
	private Point coordinate;
	public ReactionBlocker (int reactionBlockerID, Point coordinate) {
		this.reactionBlockerID = reactionBlockerID;
		this.coordinate = coordinate;
	}
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	public Point getCoordinate() {
		return this.coordinate;
	}
	public int getReactionBlockerID() {
		return this.reactionBlockerID;
	}
	
}
