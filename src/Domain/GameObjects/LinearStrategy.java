package Domain.GameObjects;

import java.awt.Point;

import Domain.GameObjects.Molecules.Molecule;

public class LinearStrategy implements IFallingBehaviour{
	private Molecule mol;
	public LinearStrategy(Molecule mol) {
		this.mol = mol;
	}
	@Override
	public void move() {
		mol.setCoordinate(new Point(mol.getCoordinate().x, (int) (mol.getCoordinate().y + mol.getSpeed())));
	}



}
