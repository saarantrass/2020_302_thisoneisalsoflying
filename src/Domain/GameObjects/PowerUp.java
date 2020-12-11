package Domain.GameObjects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Domain.IObservable;
import UI.IObserver;

public class PowerUp implements IObservable{
	
	public int powerUpID;
	private Point coordinate;
	private boolean isThrown;
	private List<IObserver> observers = new ArrayList<IObserver>();
	//TODO change speed to true
	private int xSpeed = 0;
	private int ySpeed = 5;
	
	
	public PowerUp(int powerUpID, Point coordinate, boolean isThrown) {
		this.powerUpID = powerUpID;
		this.coordinate = coordinate;
		this.isThrown = isThrown;
	}
	
	
	public PowerUp(int powerUpID, Point coordinate) {
		this(powerUpID, coordinate, false);
	} 
	
	
	public Point getCoordinate() {
		return coordinate;
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public void move() {
		//TODO check screen borders
		this.coordinate.x += xSpeed;
		this.coordinate.y += ySpeed;
		publish();
	}
	
	
	public boolean isThrown() {
		return isThrown;
	}
	
	
	public void setThrown(boolean isThrown) {
		this.isThrown = isThrown;
	}
	
	
	public int getPowerUpID() {
		return powerUpID;
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
