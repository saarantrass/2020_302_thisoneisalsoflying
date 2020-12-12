package Domain.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Domain.IObservable;
import UI.IObserver;
import UI.Swing.ScreenCoordinator;

public class Shooter implements IObservable{
	
	private Point coordinate;
	private int L = 70; //TODO
	private int speed;
	private int angle = 0;
	private int moveDirection;
	private int rotateDirection;
	private boolean isMoving = false;
	private boolean isRotating = false;
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	
	public Shooter(Point coordinate) {
		this.coordinate = coordinate;
		this.speed = this.L/10;
		
		this.moveThread.start();
		this.rotateThread.start();
		
	}
	
	
	public Point getCoordinate() {
		return this.coordinate;
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
		System.out.println("MOVE THREAD");
		while(true) {
			
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
		System.out.println("ROTATE THREAD");
		while(true) {
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
		//System.out.println("shooter");
		for(IObserver o: this.observers) o.update();
	}
	
}
