package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;

public class PowerUp {
	
	public int powerUpID;
	private Point coordinate;
	private boolean isThrown;
	private int L;
	private double speed;
	private double angle = 0;
	
	
	public PowerUp(int powerUpID, Point coordinate, boolean isThrown) {
		this.powerUpID = powerUpID;
		this.coordinate = coordinate;
		this.isThrown = isThrown;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
	}
	
	
	public PowerUp(int powerUpID, Point coordinate) {
		this(powerUpID, coordinate, false);
	} 
	
	
	public Point getCoordinate() {
		return coordinate;
	}
	
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		if(isThrown) {
			double speedX = speed * Math.sin(Math.toRadians(angle));
			double speedY = speed * Math.cos(Math.toRadians(angle));
			this.coordinate.x += speedX;
			this.coordinate.y -= speedY;
		}else {
			this.coordinate.x += speed;
		}
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
