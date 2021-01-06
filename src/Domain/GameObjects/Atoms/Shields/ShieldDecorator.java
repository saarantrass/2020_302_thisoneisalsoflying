package Domain.GameObjects.Atoms.Shields;

import java.awt.Point;


import Domain.GameObjects.Atoms.Throwable;

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
