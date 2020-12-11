package Domain.GameObjects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Domain.IObservable;
import UI.IObserver;

public class ReactionBlocker implements IObservable{
	
	public int reactionBlockerID;
	private Point coordinate;
	private List<IObserver> observers = new ArrayList<IObserver>();
	//TODO change speed to true
	private int xSpeed = 0;
	private int ySpeed = 5;
	
	public ReactionBlocker (int reactionBlockerID, Point coordinate) {
		this.reactionBlockerID = reactionBlockerID;
		this.coordinate = coordinate;
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
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public int getReactionBlockerID() {
		return this.reactionBlockerID;
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
