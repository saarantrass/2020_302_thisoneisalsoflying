package Domain.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Domain.IObservable;
import UI.IObserver;

public class Shooter implements IObservable{
	
	private Point coordinate;
	private int speed = 10; //TODO
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	
	public Shooter(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	
	public void move(int direction) {
		
		if(direction == 0) { //left
				this.coordinate.x -= speed;
		} else if(direction == 1) { //right
				this.coordinate.x += speed;
		}
		
		publish();
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