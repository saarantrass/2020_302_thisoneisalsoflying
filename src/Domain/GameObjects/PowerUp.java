package Domain.GameObjects;

import java.awt.Point;

public class PowerUp {
	
	public int powerUpID;
	private Point coordinate;
	private boolean isThrown;
	//TODO change speed to true
	private int xSpeed = 1;
	private int ySpeed = 1;
	
	
	public PowerUp(int powerUpID, Point coordinate, boolean isThrown) {
		this.powerUpID = powerUpID;
		this.coordinate = coordinate;
		this.isThrown = isThrown;
	}
	
	
	public PowerUp(int powerUpID, Point coordinate) {
		this(powerUpID, coordinate, false);
	} 
	
	
	public Point getCoordinate() {
		return coordinate;
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		//TODO check screen borders
		this.coordinate.x += xSpeed;
		this.coordinate.y += ySpeed;
	}
	
	
	public boolean isThrown() {
		return isThrown;
	}
	
	
	public void setThrown(boolean isThrown) {
		this.isThrown = isThrown;
	}
	
	
	public int getPowerUpID() {
		return powerUpID;
	}
}
