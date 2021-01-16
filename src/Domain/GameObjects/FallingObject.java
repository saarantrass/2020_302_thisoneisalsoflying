package Domain.GameObjects;

import java.awt.Point;
import Domain.Settings;

public abstract class FallingObject {
	
	private int ID;
	protected Point coordinate;
	protected boolean isLinear;
	protected boolean isSpinning;
	protected int L;
	protected double speed;
	protected double xSpeed;
	protected double ySpeed;
	protected double angle;
	protected double travelled = 0.0;
	

	protected IMovingBehaviour movingBehaviour;
	
	public FallingObject (int ID, Point coordinate, boolean isLinear, boolean isSpinning) {
		this.ID = ID;
		this.coordinate = coordinate;
		this.isLinear = isLinear;
		this.isSpinning = isSpinning;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed =  (double) L / Settings.timeMult;
		this.xSpeed = this.speed * Math.sin(Math.toRadians(45));
		this.ySpeed = this.speed * Math.cos(Math.toRadians(45));
		this.angle = 0;
	}
	
	public FallingObject (int ID, Point coordinate) {
		this(ID, coordinate, false, false);
	}
	
	public void setMovingStrategy(IMovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	
	public IMovingBehaviour getMovingStrategy() {
		return this.movingBehaviour;
	}
	
	public abstract void updateMovingStrategy() ;
	
	public void move() {
		updateAngle();
		updateMovingStrategy();
		movingBehaviour.move();
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
	}

	public boolean isSpinning() {
		return isSpinning;
	}
	
	public boolean isLinear() {
		return isLinear;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
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
	
	public double getTravelled() {
		return travelled;
	}
	
	public void setTravelled(double travelled) {
		this.travelled = travelled;
	}
	
	public int getL() {
		return L;
	}

	public void updateAngle() {
		if (isSpinning) {
			this.angle+= 10;
		}
	}


	
}
