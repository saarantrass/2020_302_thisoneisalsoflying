package Domain.GameObjects;


import Domain.Settings;

public class ShootedStrategy implements IMovingBehaviour{
	private transient FallingObject mol;
	private transient Throwable atom;
	
	public ShootedStrategy(FallingObject mol) {
		this.mol = mol;
		this.atom = null;
	}
	
	public ShootedStrategy(Throwable atom) {
		this.mol = null;
		this.atom = atom;
	}
	
	@Override
	public void move() {
		if (mol != null) {
			if(mol.getCoordinate().x < 0) {
				mol.setxSpeed(-Math.abs(mol.getxSpeed()));				
			} else if(mol.getCoordinate().x > (Settings.getInstance().getScreenSize().getWidth() * 7/8)) {
				mol.setxSpeed(Math.abs(mol.getxSpeed()));
			}
			mol.getCoordinate().x -= mol.getxSpeed();
			mol.getCoordinate().y -= mol.getySpeed();
		}else if (atom != null) {
			if(atom.getCoordinate().x < 0) {
				atom.setxSpeed(Math.abs(atom.getxSpeed()));				
			} else if(atom.getCoordinate().x > (Settings.getInstance().getScreenSize().getWidth() * 7/8)) {
				atom.setxSpeed(-Math.abs(atom.getxSpeed()));
			}
			atom.getCoordinate().x += atom.getxSpeed();
			atom.getCoordinate().y -= atom.getySpeed();
		}
	}

}
