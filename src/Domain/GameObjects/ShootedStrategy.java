package Domain.GameObjects;


import Domain.Settings;
import Domain.GameObjects.Atoms.Atom;

public class ShootedStrategy implements IMovingBehaviour{
	private transient FallingObject powerUp;
	private transient Atom atom;
	
	public ShootedStrategy(FallingObject powerUp) {
		this.powerUp = powerUp;
		this.atom = null;
	}
	
	public ShootedStrategy(Atom atom) {
		this.powerUp = null;
		this.atom = atom;
	}
	
	@Override
	public void move() {
		if (powerUp != null) {
			if(powerUp.getCoordinate().x < 0 || powerUp.getCoordinate().x > (Settings.getInstance().getScreenSize().getWidth() * 7/8)) {
				powerUp.setxSpeed(-powerUp.getxSpeed());
			}
			powerUp.getCoordinate().x += powerUp.getxSpeed();
			powerUp.getCoordinate().y -= powerUp.getySpeed();
		}else if (atom != null) {
			if(atom.getCoordinate().x < 0 || (atom.getCoordinate().x > (Settings.getInstance().getScreenSize().getWidth() * 7/8))) {
				atom.setxSpeed(-atom.getxSpeed());
			}
			atom.getCoordinate().x += atom.getxSpeed();
			atom.getCoordinate().y -= atom.getySpeed();
		}
	}

}
