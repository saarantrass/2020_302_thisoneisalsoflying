package Domain.GameObjects.Molecules;
import java.awt.Point;

import Domain.Settings;
import Domain.GameObjects.IFallingBehaviour;

public abstract class Molecule implements IFallingBehaviour{
	
	private int moleculeID;
	protected Point coordinate;
	private boolean isLinear;
	private boolean isSpinning;
	protected int L;
	protected double speed;
	protected double xSpeed;
	protected double ySpeed;
	protected double travelled = 0.0;
	

	protected IFallingBehaviour fallingBehaviour;
	
	public Molecule (int moleculeID, Point coordinate, boolean isLinear, boolean isSpinning) {
		this.moleculeID = moleculeID;
		this.coordinate = coordinate;
		this.isLinear = isLinear;
		this.isSpinning = isSpinning;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = L/10;
		this.xSpeed = this.speed * Math.sin(Math.toRadians(45));
		this.ySpeed = this.speed * Math.cos(Math.toRadians(45));
	}

	public void setFallingStrategy(IFallingBehaviour fallingBehaviour) {
		this.fallingBehaviour = fallingBehaviour;
	}
	public abstract void updateFallingStrategy() ;
	
	public void move() {
		//TODO check screen borders
		/*if(this.coordinate.x < 0 || this.coordinate.x > (ScreenCoordinator.SCREEN_SIZE.width * 7/8)) {
			this.xSpeed = -this.xSpeed;
			this.travelled = 0;
		}
		*/
		this.updateFallingStrategy();
		fallingBehaviour.move();
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
	
	public boolean isSpinning() {
		return isSpinning;
	}
	
	
	public boolean isLinear() {
		return isLinear;
	}
	
	
	public int getMoleculeID() {
		return this.moleculeID;
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
	
	public double getTravelled() {
		return travelled;
	}
	
	public void setTravelled(double travelled) {
		this.travelled = travelled;
	}
	
	public int getL() {
		return L;
	}

	public IFallingBehaviour getFallingBehaviour() {
		return fallingBehaviour;
	}

}
