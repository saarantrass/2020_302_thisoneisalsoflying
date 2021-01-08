package Domain.GameObjects.Atoms.Shields;


import Domain.GameObjects.Throwable;

public class ZetaShield extends ShieldDecorator {
	private double updatedEfficiency;
	private static double ZETA_EFF = 0.2;
	public ZetaShield(Throwable atom) {
		super(atom);
		this.updatedEfficiency=atom.getEfficiency();
	}

	@Override
	public double getEfficiency() {
		return this.updatedEfficiency;
	}
	private void setEfficiency(double eff) {
		this.updatedEfficiency = eff;
	}
	@Override
	public void addShield() {
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.getProton()==this.getNeutron()) {
			effFactor = (1 - this.updatedEfficiency)*ZETA_EFF;
		}
		speedFactor = 0.11;
		setEfficiency(this.updatedEfficiency+this.updatedEfficiency*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
}
