package Domain.GameObjects;
import java.awt.Point;

public class Molecule {
	public int moleculeID;
	private Point coordinate;
	private boolean isSpinning;
	private boolean isLinear;
	public Molecule (int moleculeID, Point coordinate) {
		this.moleculeID = moleculeID;
		this.coordinate = coordinate;
		this.isSpinning = false;
		this.isLinear = false;
	}
	public Molecule (int moleculeID, Point coordinate,boolean isSpinning, boolean isLinear) {
		this.moleculeID = moleculeID;
		this.coordinate = coordinate;
		this.isSpinning = isSpinning;
		this.isLinear = isLinear;
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
	 
}
