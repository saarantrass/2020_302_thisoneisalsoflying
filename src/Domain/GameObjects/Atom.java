package Domain.GameObjects;

import java.awt.Point;

public class Atom {
	
	public int atomID;
	private Point coordinate;
	//TODO change speed to true
	private int xSpeed = 1;
	private int ySpeed = 1;
	
	
	public Atom (int atomID, Point coordinate) {
		this.atomID = atomID;
		this.coordinate = coordinate;
	}
	
	public Atom (int atomID, Point coordinate, int xSpeed, int ySpeed) {
		this.atomID = atomID;
		this.coordinate = coordinate;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
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
	
	
	public int getAtomID() {
		return this.atomID;
	}
	
}
