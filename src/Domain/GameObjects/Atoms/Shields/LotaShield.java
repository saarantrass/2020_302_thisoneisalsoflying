package Domain.GameObjects.Atoms.Shields;

import Domain.GameObjects.Atoms.Atom;

public class LotaShield extends AtomDecorator {
	private static double LOTA_EFF = 0.1;
	public LotaShield(Atom atom) {
		super(atom);
		updateEfficiency();
	}

	@Override
	public void updateEfficiency() {
		double effFactor = (1 - this.getEfficiency()) * LOTA_EFF;
		double speedFactor = 0.07;
		this.setEfficiency(this.getEfficiency()+this.getEfficiency()*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}

}
