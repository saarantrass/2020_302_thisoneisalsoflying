package Domain.GameObjects.Atoms.Shields;

import java.awt.Point;
import Domain.GameObjects.Atoms.Atom;

public abstract class ShieldDecorator extends Atom{

	public Atom atom;
	public ShieldDecorator(Atom atom) {
		super(atom.getCoordinate());//TODO: olmaz b�yle
		this.atom = atom;
	}
	public abstract void addShield();

}
