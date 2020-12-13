package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;
import UI.Swing.ScreenCoordinator;

public class ReactionBlocker {
	
	private int reactionBlockerID;
	private Point coordinate;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private int travelled = 0;
	
	public ReactionBlocker (int reactionBlockerID, Point coordinate) {
		this.reactionBlockerID = reactionBlockerID;
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
		this.xSpeed = this.speed * Math.sin(Math.toRadians(45));
		this.ySpeed = this.speed * Math.cos(Math.toRadians(45));
	}
	
	
	public void move() {
		//TODO check screen borders
		switch(this.reactionBlockerID) {
			case 1:
				zigzag();
				break;
			case 2:
				if(this.coordinate.y < ScreenCoordinator.SCREEN_SIZE.getHeight()/4) {
					this.coordinate.y += this.speed;
				}else {
					zigzag();
				}
				break;
			case 3:
				if(this.coordinate.y < ScreenCoordinator.SCREEN_SIZE.getHeight()/2) {
					this.coordinate.y += this.speed;
				}else {
					zigzag();
				}
				break;
			case 4:
				this.coordinate.y += this.speed;
				break;
		}

	}
	
	
	public void zigzag() {
		if(this.travelled < L * Math.sin(Math.toRadians(45))) {
			this.travelled += Math.abs(xSpeed);
		} else {
			xSpeed = -xSpeed;
			this.travelled = 0;
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
