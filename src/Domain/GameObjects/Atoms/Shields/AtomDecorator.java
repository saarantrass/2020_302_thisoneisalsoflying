package Domain.GameObjects.Atoms.Shields;

import java.awt.Point;

import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.ShootedStrategy;
import Domain.GameObjects.Atoms.Atom;

public abstract class AtomDecorator extends Atom{
	protected Atom atom;
	public AtomDecorator () {}
	
	public AtomDecorator(Atom atom) {
		this.atom = atom;
	}
	
	public Atom getAtom() {
		return this.atom;
	}
	public abstract void updateEfficiency();
	@Override
	public int getAtomID() {return this.atom.getAtomID();}
	@Override
	public Point getCoordinate() {return this.atom.getCoordinate();}
	@Override
	public void setCoordinate(Point coord) { this.atom.setCoordinate(coord);}
	@Override
	public double getSpeed() {return this.atom.getSpeed();}
	@Override
	public double getxSpeed() {return this.atom.getxSpeed();}
	@Override
	public double getySpeed() {return this.atom.getySpeed();}
	@Override
	public int getNeutron() {return this.atom.getNeutron();}
	@Override
	public int getProton() {return this.atom.getProton();}
	@Override
	public IMovingBehaviour getMovingBehaviour() {return new ShootedStrategy(atom);}
	@Override 
	public double getAngle() {return this.atom.getAngle();}
	@Override
	public double getEfficiency() {return this.atom.getEfficiency();}
	@Override
	public void move() {this.atom.getMovingBehaviour().move();}
}
