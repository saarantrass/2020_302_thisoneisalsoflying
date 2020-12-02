package Domain.GameObjects;

import java.awt.Point;

public class PowerUp {
	public int powerUpID;
	private Point coordinate;
	private boolean isThrown;
	
	public PowerUp(int powerUpID, Point coordinate) {
		this.powerUpID = powerUpID;
		this.coordinate = coordinate;
		this.isThrown = false;
	} 
	public PowerUp(int powerUpID, Point coordinate, boolean isThrown) {
		this.powerUpID = powerUpID;
		this.coordinate = coordinate;
		this.isThrown = isThrown;
	} 
	public Point getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
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
