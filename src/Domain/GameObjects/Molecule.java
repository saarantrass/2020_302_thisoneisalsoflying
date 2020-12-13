package Domain.GameObjects;
import java.awt.Point;

import Domain.Settings;

public class Molecule {
	
	public int moleculeID;
	private Point coordinate;
	private boolean isSpinning;
	private boolean isLinear;
	//TODO change speed to true
	private int xSpeed = 3;
	private int ySpeed = 3;
	private int travelled = 0;
	
	public Molecule (int moleculeID, Point coordinate,boolean isSpinning, boolean isLinear) {
		this.moleculeID = moleculeID;
		this.coordinate = coordinate;
		this.isSpinning = isSpinning;
		this.isLinear = isLinear;
	}
	
	public Molecule (int moleculeID, Point coordinate) {
		this(moleculeID, coordinate, false, false);
	}
	
	public void move() {
		//TODO check screen borders
		switch(moleculeID) {
			case 1:
				zigzag();
				break;
			case 2:
				if(this.coordinate.y<175) {
					this.coordinate.y += ySpeed;
				}else {
					zigzag();
				}
				break;
			case 3:
				if(this.coordinate.y<350) {
					this.coordinate.y += ySpeed;
				}else {
					zigzag();
				}
				break;
			case 4:
				this.coordinate.y += ySpeed;
				break;
		}

	}
	public void zigzag() {
		if(travelled<Settings.getInstance().getLengthUnit()) {
			travelled += Math.abs(xSpeed);
		}else {
			this.xSpeed = -this.xSpeed;
			travelled = 0;
		}
		this.coordinate.x += this.xSpeed;
		this.coordinate.y += this.ySpeed;
	}
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public boolean isSpinning() {
		return isSpinning;
	}
	
	
	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}
	
	
	public boolean isLinear() {
		return isLinear;
	}
	
	
	public void setLinear(boolean isLinear) {
		this.isLinear = isLinear;
	}
	
	
	public int getMoleculeID() {
		return this.moleculeID;
	}
	
}
