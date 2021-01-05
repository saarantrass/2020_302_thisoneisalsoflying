package Domain.GameObjects;

import java.awt.Point;

import Domain.GameObjects.Molecules.Molecule;

public class ZigZagStrategy implements IFallingBehaviour{
	private Molecule mol;
	public ZigZagStrategy(Molecule mol) {
		this.mol = mol;
	}
	@Override
	public void move() {
		if(mol.getTravelled() < mol.getL() * Math.sin(Math.toRadians(45))) {
			mol.setTravelled(mol.getTravelled() + Math.abs(mol.getxSpeed()));
		} else {
			mol.setxSpeed(-mol.getxSpeed());
			mol.setTravelled(0.0);
		}
		mol.setCoordinate(new Point((int) (mol.getCoordinate().x + mol.getxSpeed()), (int) (mol.getCoordinate().y + mol.getySpeed())));

	}
	

}
