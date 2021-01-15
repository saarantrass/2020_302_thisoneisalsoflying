package Domain.GameObjects.Atoms;

import java.awt.Point;
import Domain.Settings;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.ShootedStrategy;

public abstract class Atom{
	
	protected int atomID;
	private Point coordinate;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private double angle;
	protected int neutron;
	private int proton;
	protected double efficiency;

	protected IMovingBehaviour movingBehaviour;
	
	public Atom (int atomID, Point coordinate) {
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = (double) L / Settings.timeMult;
		this.proton = 8;
		for(int i=1;i<atomID;i++) {this.proton *= 2;}
		this.movingBehaviour = new ShootedStrategy(this);
		this.efficiency = 0;
	}
	
	public Atom () {}
	
	public int getAtomID() {
		return this.atomID;
	}
	
	public double getEfficiency() {
		return this.efficiency;
	}

	public void setEfficiency(double eff) {
		this.efficiency = eff;	
	}

	public void move() {
		this.movingBehaviour.move();
	}
	
	public IMovingBehaviour getMovingBehaviour() {
		return this.movingBehaviour;
	}
	
	public double getAngle() {
		return this.angle;
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
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
		setxSpeed(speed*Math.sin(Math.toRadians(this.angle)));
		setySpeed(speed*Math.cos(Math.toRadians(this.angle)));
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
