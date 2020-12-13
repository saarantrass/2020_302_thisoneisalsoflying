package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;

public class ReactionBlocker {
	
	public int reactionBlockerID;
	private Point coordinate;
	//TODO change speed to true
	private int xSpeed = 3;
	private int ySpeed = 3;
	private int travelled = 0;
	
	public ReactionBlocker (int reactionBlockerID, Point coordinate) {
		this.reactionBlockerID = reactionBlockerID;
		this.coordinate = coordinate;
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		//TODO check screen borders
		switch(reactionBlockerID) {
			case 1:
				zigzag();
				break;
			case 2:
				if(this.coordinate.y<175) {
					this.coordinate.y += ySpeed;
				}else {
					zigzag();
				}
				break;
			case 3:
				if(this.coordinate.y<350) {
					this.coordinate.y += ySpeed;
				}else {
					zigzag();
				}
				break;
			case 4:
				this.coordinate.y += ySpeed;
				break;
		}

	}
	public void zigzag() {
		if(travelled<Settings.getInstance().getLengthUnit()) {
			travelled += Math.abs(xSpeed);
		}else {
			this.xSpeed = -xSpeed;
			travelled = 0;
		}
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
