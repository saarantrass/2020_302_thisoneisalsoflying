package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;
import UI.Swing.ScreenCoordinator;

public class PowerUp {
	
	private int powerUpID;
	private Point coordinate;
	private boolean isThrown;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private double angle = 0;
	
	
	public PowerUp(int powerUpID, Point coordinate, boolean isThrown) {
		this.powerUpID = powerUpID;
		this.coordinate = coordinate;
		this.isThrown = isThrown;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
		this.xSpeed = speed * Math.sin(Math.toRadians(angle));
		this.ySpeed = speed * Math.cos(Math.toRadians(angle));
	}
	
	
	public PowerUp(int powerUpID, Point coordinate) {
		this(powerUpID, coordinate, false);
	} 
	
	
	public Point getCoordinate() {
		return coordinate;
	}
	
	
	public void setAngle(double angle) {
		this.angle = angle;
		this.xSpeed = speed * Math.sin(Math.toRadians(angle));
		this.ySpeed = speed * Math.cos(Math.toRadians(angle));
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		if(isThrown) {
			if(this.coordinate.x < 0 || this.coordinate.x > (ScreenCoordinator.SCREEN_SIZE.width * 7/8)) {
				this.xSpeed = -this.xSpeed;
			}
			this.coordinate.x += this.xSpeed;
			this.coordinate.y -= this.ySpeed;
		} else {
			this.coordinate.y += speed;
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
