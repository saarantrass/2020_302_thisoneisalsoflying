package Domain.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Domain.IObservable;
import Domain.IObserver;
import Domain.Settings;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.PowerUps.PowerUp;

/**
 * Shooter is the player in the game. It has an atom/power-up on its barrel and the goal of the player is to 
 * hit the right molecules by shooting the atoms/power-ups on the barrel of the Shooter. 
 * In order to achieve this goal, player moves and rotates the shooter.
 * @author dogademirturk
 *
 */
public class Shooter implements IObservable{
	
	private Point coordinate; //coordinate of the shooter
	private int L; //length unit of the game
	private double speed; //speed of the shooter
	private double angle = 0; //angle of the shooter
	private int moveDirection; //moving direction of the shooter
	private int rotateDirection; //rotating direction of the shooter
	private boolean isMoving = false; //flag that indicates shooter is moving
	private boolean isRotating = false; //flag that indicates shooter is rotating
	private List<IObserver> observers = new ArrayList<IObserver>(); //list of observers of the class
	
	public Inventory inventory; //inventory of the shooter
	
	/**
	 * Constructor of the Shooter class
	 * @param coordinate: initial coordinate of the shooter
	 */
	public Shooter(Point coordinate) {
		//EFFECTS: initializes the coordinate from given coordinate, length unit from Settings, 
		//speed according to length unit, inventory and starts the move and rotate threads
		
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = (double) this.L / Settings.timeMult;
		this.inventory = new Inventory();
		this.moveThread.start();
		this.rotateThread.start();
	}
	
	/**
	 * Getter for the coordinate of the Shooter
	 * @return Point: coordinate
	 */
	public Point getCoordinate() {
		//EFFECTS: returns the coordinate of the Shooter
		return this.coordinate;
	}
	
	/**
	 * Setter for the coordinate of the Shooter
	 * @param coordinate: new coordinate of the Shooter
	 */
	public void setCoordinate(Point coordinate) {
		//REQUIRES: coordinate must be in boundaries of the screen
		//MODIFIES: coordinate
		//EFFECTS: sets coordinate of the Shooter to given coordinate
		this.coordinate = coordinate;
	}
	
	/**
	 * Calculates and returns the coordinate of the barrel according to the coordinate and 
	 * the angle of the Shooter. Used to determine barrel atom coordinate.
	 * @return Point: barrel coordinate
	 */
	public Point getBarrelCoordinate() {
		//REQUIRES: shooter coordinate must be not null
		//EFFECTS: calculates and returns the coordinate of the barrel
		//calculation is as follows:
		//if angle is 0: shooter x coordinate + L/4 - L/20 as barrel coordinate x 
		//					and shooter y coordinate - L/10 as barrel coordinate y
		//if angle is not 0: shooter x coordinate + L/4 - L/20 + L * sin(a) as barrel coordinate x 
		//					and shooter y coordinate - L/10 + L * (1 - cos(a)) as barrel coordinate y
		int dy = 0;
		int dx = 0;
		if (this.angle != 0) {
			double radians = Math.toRadians(this.angle);
			dy = (int) (L * (1 - Math.cos(radians)));
			dx = (int) (L * Math.sin(radians));
		}
		
		return new Point((int) (this.coordinate.x + L/4-L/20 + dx), (int) (this.coordinate.y - L/10 + dy));
	}
	
	/**
	 * Starts moving the Shooter in the given direction
	 * @param direction: direction of the movement. 0 if left, 1 if right
	 */
	public void move(int direction) {
		//REQUIRES: direction must be 0 or 1
		//MODIFIES: moveDirection and isMoving
		//EFFECTS: changes moveDirection to the given direction and 
		//starts moving the Shooter by setting isMoving flag to be true
		this.moveDirection = direction;
		this.isMoving = true;
	}	
	
	/**
	 * Starts rotating the Shooter in the given direction
	 * @param direction: direction of the rotation. 0 if left, 1 if right
	 */
	public void rotate(int direction) {
		//REQUIRES: direction must be 0 or 1
		//MODIFIES: rotateDirection and isRotating
		//EFFECTS: changes rotateDirection to the given direction and 
		//starts rotating the Shooter by setting isRotating flag to be true
		this.rotateDirection = direction;
		this.isRotating = true;
	}
	
	/**
	 * Stops moving the Shooter
	 */
	public void stopMove() {
		//MODIFIES: isMoving
		//EFFECTS: stops moving the Shooter by setting isMoving flag to be false
		this.isMoving = false;
	}

	/**
	 * Stops rotating the Shooter
	 */
	public void stopRotate() {
		//MODIFIES: isRotating
		//EFFECTS: stops rotating the Shooter by setting isRotating flag to be false
		this.isRotating = false;
	}
	
	/**
	 * Setter for the angle of the Shooter
	 * @param angle: angle of the shooter
	 */
	public void setAngle(double angle) {
		//REQUIRES: angle must be between -90 and 90
		//MODIFIES: angle
		//EFFECTS: sets angle of the Shooter to given angle
		this.angle = angle;
	}
	
	/**
	 * Getter for the angle of the Shooter
	 * @return double: angle
	 */
	public double getAngle() {
		//EFFECTS: returns the angle of the Shooter
		return this.angle;
	}
	
	/**
	 * Thread that moves the shooter if the isMoving flag is true and game is not paused nor finished.
	 * Thread started at the constructor.
	 * If Shooter is moving, it modifies the coordinates of the shooter according to speed and moveDirection.
	 * If Shooter moves, calls publish method to notify observers.
	 */
	private Thread moveThread = new Thread(() -> {
		while(true) {
			
			if(this.isMoving) {
				if(this.moveDirection == 0) { //left
					if(this.coordinate.x > 0) {
						if(this.getBarrelCoordinate().x > 0) {
							this.coordinate.x -= speed;														
						}
					}
				} else if(moveDirection == 1) { //right
					if(this.coordinate.x < (int)(Settings.getInstance().getScreenSize().width * 7/8) - L/2) {
						if(this.getBarrelCoordinate().x < (Settings.getInstance().getScreenSize().width * 7/8 - this.L/10)) {												
							this.coordinate.x += speed;
						}
					}
				}
				publish();
			}
			
			try {
				Thread.sleep(1000 / Settings.timeMult);
			} catch (InterruptedException e) {
				System.out.println(e);
				break;
			}
		}
	});
	
	/**
	 * Thread that rotates the shooter if the isRotating flag is true and game is not paused nor finished.
	 * Thread started at the constructor.
	 * If Shooter is rotating, it modifies the angle of the shooter according to rotateDirection.
	 * If Shooter rotates, calls publish method to notify observers.
	 */
	private Thread rotateThread = new Thread(() -> {
		while(true) {
			
			if(this.isRotating) {
				if(this.rotateDirection == 0) { //left
					if(this.angle >-90) {
						if(this.getBarrelCoordinate().x > 0) {
							this.angle -= 90 / Settings.timeMult;								
						}
					}
				} else if(this.rotateDirection == 1) { //right
					if(this.angle<90) {
						if(this.getBarrelCoordinate().x < (Settings.getInstance().getScreenSize().width * 7/8 - this.L/10)) {
							this.angle += 90 / Settings.timeMult;								
						}
					}
				}
				
				publish();
			}
			
			try {
				Thread.sleep(1000 / Settings.timeMult);
			} catch (InterruptedException e) {
				System.out.println(e);
				break;
			}
		}
	});
	
	
	public String toString() {
		return "Shooter[Coordinate: (" + this.coordinate.x + ", " + this.coordinate.y + 
				"), Speed: " + this.speed + ", Angle: " + this.angle + "]";
	}
	
	
	public PowerUp getInventoryPowerUp(int type) {
		return this.inventory.getPowerUp(type);
	}
	
	
	public void addInventoryAtom(Atom barrelAtom) {
		this.inventory.addInventoryAtom(barrelAtom);
	}
	
	
	public void addInventoryPowerUp(PowerUp barrelPowerUp) {
		this.inventory.addInventoryPowerUp(barrelPowerUp);
	}
	
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	
	@Override
	public void add(IObserver o) {
		this.observers.add(o);
	}


	@Override
	public void remove(IObserver o) {
		this.observers.remove(o);	
	}
	
	
	@Override
	public void publish() {
		for(IObserver o: this.observers) o.update();
	}

}
