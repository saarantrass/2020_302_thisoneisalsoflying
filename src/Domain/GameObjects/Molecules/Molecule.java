package Domain.GameObjects;
import java.awt.Point;

import Domain.Settings;
import UI.Swing.ScreenCoordinator;

public class Molecule {
	
	private int moleculeID;
	private Point coordinate;
	private boolean isLinear;
	private boolean isSpinning;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private int travelled = 0;
	
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
	
	
	public void move() {
		//TODO check screen borders
		/*if(this.coordinate.x < 0 || this.coordinate.x > (ScreenCoordinator.SCREEN_SIZE.width * 7/8)) {
			this.xSpeed = -this.xSpeed;
			this.travelled = 0;
		}*/
		switch(moleculeID) {
			case 1:
				zigzag();
				break;
			case 2:
				if(this.coordinate.y < ScreenCoordinator.SCREEN_SIZE.getHeight()/4) {
					this.coordinate.y += this.speed;
				}else {
					zigzag();
				}
				break;
			case 3:
				if(this.coordinate.y < ScreenCoordinator.SCREEN_SIZE.getHeight()/2) {
					this.coordinate.y += this.speed;
				}else {
					zigzag();
				}
				break;
			case 4:
				this.coordinate.y += this.speed;
				break;
		}

	}
	
	
	public void zigzag() {
		//TODO: Bounce off from the wall
		
		if(this.travelled < L * Math.sin(Math.toRadians(45))) {
			this.travelled += Math.abs(xSpeed);
		} else {
			xSpeed = -xSpeed;
			this.travelled = 0;
		}
		this.coordinate.x += xSpeed;
		this.coordinate.y += ySpeed;
	}
	
	
	public Point getCoordinate() {
		return this.coordinate;
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
	
}
