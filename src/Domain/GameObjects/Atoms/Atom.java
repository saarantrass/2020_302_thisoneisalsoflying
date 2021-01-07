package Domain.GameObjects.Atoms;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.ShootedStrategy;
import Domain.GameObjects.Throwable;

public abstract class Atom extends Throwable {
	
	private int atomID;
	private Point coordinate;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private double angle;
	private int neutron;
	private int proton;


	protected IMovingBehaviour fallingBehaviour;
	
	public Atom (int atomID, Point coordinate, int neutron) {
		this.atomID = atomID;
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
		this.proton=8;
		for(int i=1;i<atomID;i++) {this.proton *= 2;}
		this.neutron = neutron;
		this.fallingBehaviour = new ShootedStrategy(this);
	}
	
	public int getAtomID() {
		return this.atomID;
	}
	
	public abstract double getEfficiency() ;
	

	public void move() {
		fallingBehaviour.move();
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		this.xSpeed = speed * Math.sin(Math.toRadians(this.angle));
		this.ySpeed = speed * Math.cos(Math.toRadians(this.angle));
	}
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return this.speed;
	}
	
	public double getxSpeed() {
		return xSpeed;
	}
	
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	public double getySpeed() {
		return ySpeed;
	}
	
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public int getNeutron() {
		return this.neutron;
	}
	
	public int getProton() {
		return this.proton;
	}
}
