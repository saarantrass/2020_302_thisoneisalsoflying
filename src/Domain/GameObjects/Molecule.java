package Domain.GameObjects;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Domain.IObservable;
import UI.IObserver;

public class Molecule implements IObservable{
	
	public int moleculeID;
	private Point coordinate;
	private boolean isSpinning;
	private boolean isLinear;
	private List<IObserver> observers = new ArrayList<IObserver>();
	//TODO change speed to true
	private int xSpeed = 0;
	private int ySpeed = 5;
	
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
		this.coordinate.x += xSpeed;
		this.coordinate.y += ySpeed;
		publish();
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
