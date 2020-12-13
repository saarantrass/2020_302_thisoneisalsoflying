package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;
import UI.Swing.ScreenCoordinator;

public class Atom {
	
	private int atomID;
	private Point coordinate;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private double angle;
	
	
	public Atom (int atomID, Point coordinate) {
		this.atomID = atomID;
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
	}
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		if(this.coordinate.x < 0 || this.coordinate.x > (ScreenCoordinator.SCREEN_SIZE.width * 7/8)) {
			this.xSpeed = -this.xSpeed;
		}
		this.coordinate.x += this.xSpeed;
		this.coordinate.y -= this.ySpeed;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		this.xSpeed = speed * Math.sin(Math.toRadians(this.angle));
		this.ySpeed = speed * Math.cos(Math.toRadians(this.angle));
	}
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public int getAtomID() {
		return this.atomID;
	}
	
}
