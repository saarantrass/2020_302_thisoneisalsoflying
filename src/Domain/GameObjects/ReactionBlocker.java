package Domain.GameObjects;

import java.awt.Point;

public class ReactionBlocker {
	
	public int reactionBlockerID;
	private Point coordinate;
	//TODO change speed to true
	private int xSpeed = 1;
	private int ySpeed = 1;
	
	public ReactionBlocker (int reactionBlockerID, Point coordinate) {
		this.reactionBlockerID = reactionBlockerID;
		this.coordinate = coordinate;
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		//TODO check screen borders
		this.coordinate.x += xSpeed;
		this.coordinate.y += ySpeed;
	}
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public int getReactionBlockerID() {
		return this.reactionBlockerID;
	}
}
