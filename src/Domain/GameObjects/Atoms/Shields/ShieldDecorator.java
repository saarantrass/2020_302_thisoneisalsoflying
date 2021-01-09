package Domain.GameObjects.Atoms.Shields;

import java.awt.Point;

import Domain.GameObjects.Throwable;

public abstract class ShieldDecorator extends Throwable{
	private double efficiency;
	public Throwable atom;
	public ShieldDecorator(Throwable atom) {
		this.atom = atom;
		this.efficiency=atom.getEfficiency();
	}
	public double getEfficiency() {
		return this.efficiency;
	}

	public abstract void addShield(int type);
	@Override
	public void setSpeed(double speed) {atom.setSpeed(speed);}
	@Override
	public double getSpeed() {return atom.getSpeed();}
	@Override
	public double getxSpeed() {return atom.getxSpeed();}
	@Override
	public void setxSpeed(double xSpeed){atom.setxSpeed(xSpeed);} 
	@Override
	public double getySpeed() {return atom.getySpeed();}
	@Override
	public void setySpeed(double ySpeed) {atom.setySpeed(ySpeed);}
	@Override
	public void setCoordinate(Point coordinate) {atom.setCoordinate(coordinate);}
	@Override
	public void move() {atom.move();}
	@Override
	public void setAngle(double angle) {atom.setAngle(angle);}
	@Override
	public Point getCoordinate() {return atom.getCoordinate();}
	@Override
	public int getAtomID() {return atom.getAtomID();}
	@Override
	public int getNeutron() {return atom.getNeutron();}
	@Override
	public int getProton() {return atom.getProton();}
}
