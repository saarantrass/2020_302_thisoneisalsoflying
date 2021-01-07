package Domain.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Domain.Game;
import Domain.IObservable;
import Domain.Settings;
import UI.IObserver;

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
		this.speed = this.L/10;
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

	
	public Point getBarrelCoordinate() {
		int dy = 0;
		int dx = 0;
		if (this.angle != 0) {
			double radians = Math.toRadians(this.angle);
			dy = (int) (L * (1 - Math.cos(radians)));
			dx = (int) (L * Math.sin(radians));
		}
		
		return new Point(this.coordinate.x + L/4-L/20 + dx, this.coordinate.y - L/10 + dy);
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
			if(!Game.getInstance().isFinished && !Game.getInstance().isPaused) {
				if(this.isMoving) {
					if(this.moveDirection == 0) { //left
						if(this.coordinate.x > 0) {
							this.coordinate.x -= speed;						
						}
					} else if(moveDirection == 1) { //right
						if(this.coordinate.x < (int)(Settings.getInstance().getScreenSize().width * 7/8) - L/2) {
							this.coordinate.x += speed;
						}
					}
					publish();
				}
			} else {
				try {
					Thread.sleep(100);
                } catch(InterruptedException e) {
                    // nothing
                }
			}
			
			//to prevent crash
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e2) {
						break;
					}
				}
			}
				//to prevent crash
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
			if(!Game.getInstance().isFinished && !Game.getInstance().isPaused) {
				if(this.isRotating) {
					if(this.rotateDirection == 0) { //left
						if(this.angle >-90) {
							this.angle -= 9;
						}
					} else if(this.rotateDirection == 1) { //right
						if(this.angle<90) {
							this.angle += 9;
						}
					}
					
					publish();
				}
			} else {
				try {
					Thread.sleep(100);
                } catch(InterruptedException e) {
                    // nothing
                }
			}
			
			//to prevent crash
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e2) {
						break;
					}
				}
			}
				//to prevent crash
		}
	});
	

	
	
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
