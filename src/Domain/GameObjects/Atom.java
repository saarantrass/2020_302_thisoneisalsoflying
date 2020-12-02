package Domain.GameObjects;
import java.awt.Point;

public class Atom {
	public int atomID;
	private Point coordinate;
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
	
}
