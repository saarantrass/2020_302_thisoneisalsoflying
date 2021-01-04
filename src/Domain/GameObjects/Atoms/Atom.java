package Domain.GameObjects;

import java.awt.Point;

import Domain.Settings;
import UI.Swing.ScreenCoordinator;

public class Atom extends Throwable {
	
	private int atomID;
	private Point coordinate;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private double angle;
	
	private int neutron;
	private int proton;
	
	
	public Atom (int atomID, Point coordinate, int neutron) {
		this.atomID = atomID;
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
		
		this.proton=8;
		for(int i=1;i<atomID;i++) {this.proton *= 2;}
		this.neutron = neutron;
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
	
	public double getEfficiency() {
		double eff = 0.0;
		switch (atomID) {
			case 1:
				eff = 0.85*(1- (Math.abs(neutron-proton)/proton));
				break;
			case 2:
				eff = 0.9 - (0.5*(Math.abs(neutron-proton)/proton));
				break;
			case 3:
				eff = 0.8 + (Math.abs(neutron-proton)/(2*proton));
				break;
			case 4:
				eff = (1.7)/2 + (Math.abs(neutron-proton)/proton);
				break;
		}
		
		return eff;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return this.speed;
	}
	public int getNeutron() {
		return this.neutron;
	}
	public int getProton() {
		return this.proton;
	}
}
