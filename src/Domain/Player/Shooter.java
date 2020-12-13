package Domain.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Domain.Game;
import Domain.IObservable;
import Domain.Settings;
import UI.IObserver;
import UI.Swing.ScreenCoordinator;

public class Shooter implements IObservable{
	
	private Point coordinate;
	private int L;
	private double speed;
	private double angle = 0;
	private int moveDirection;
	private int rotateDirection;
	private boolean isMoving = false;
	private boolean isRotating = false;
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	public Inventory inventory;
	public double health = 100;
	public double score = 0;
	
	public Shooter(Point coordinate) {
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = this.L/10;
		this.inventory = new Inventory();
		this.moveThread.start();
		this.rotateThread.start();
		
	}
	
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public Point getBarrelCoordinate() {
		int dy = 0;
		int dx = 0;
		if (this.angle != 0) {
			double radians = Math.toRadians(this.angle);
			dy = (int) (L * (1 - Math.cos(radians)));
			dx = (int) (L * Math.sin(radians));
		}
		
		return new Point(this.coordinate.x + L/4 + dx, this.coordinate.y - L/10 + dy);
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	
	public void move(int direction) {
		this.moveDirection = direction;
		this.isMoving = true;
	}
	
	
	public void stopMove() {
		this.isMoving = false;
	}
	
	
	public void rotate(int direction) {
		this.rotateDirection = direction;
		this.isRotating = true;
	}
	
	
	public void stopRotate() {
		this.isRotating = false;
	}
	
	
	public double getAngle() {
		return this.angle;
	}
	
	
	private Thread moveThread = new Thread(() -> {
		while(true) {
			if(!Game.getInstance().isFinished && !Game.getInstance().isPaused) {
				if(this.isMoving) {
					if(this.moveDirection == 0) { //left
						if(this.coordinate.x > 0) {
							this.coordinate.x -= speed;						
						}
					} else if(moveDirection == 1) { //right
						if(this.coordinate.x <= (int)(ScreenCoordinator.SCREEN_SIZE.width * 7/8) - L/2) {
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
	
	
	public void increaseScore(double points) {
		this.score += points;
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
