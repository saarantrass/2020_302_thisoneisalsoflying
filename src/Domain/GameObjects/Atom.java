package Domain.GameObjects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Domain.IObservable;
import UI.IObserver;

public class Atom implements IObservable{
	
	public int atomID;
	private Point coordinate;
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	
	public Atom (int atomID, Point coordinate) {
		this.atomID = atomID;
		this.coordinate = coordinate;
	}
	
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	
	public int getAtomID() {
		return this.atomID;
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
