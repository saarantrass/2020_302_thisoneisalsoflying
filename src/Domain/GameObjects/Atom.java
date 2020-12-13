package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;
import UI.Swing.ScreenCoordinator;

public class Atom {
	
	public int atomID;
	private Point coordinate;
	private int L;
	private double speed;
	private double angle = 0;
	
	
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
		double speedX = speed * Math.sin(Math.toRadians(angle));
		double speedY = speed * Math.cos(Math.toRadians(angle));
		/*if(this.coordinate.x <= -1 || this.coordinate.x >= (ScreenCoordinator.SCREEN_SIZE.width * 7/8)) {
			speedX = -speedX;
		}*/
		this.coordinate.x += speedX;
		this.coordinate.y -= speedY;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public int getAtomID() {
		return this.atomID;
	}
	
}
