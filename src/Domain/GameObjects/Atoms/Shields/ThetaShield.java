package Domain.GameObjects.Atoms.Shields;

import java.util.Random;

import Domain.GameObjects.Throwable;

public class ThetaShield extends ShieldDecorator {
	private double updatedEfficiency;
	private double THETA_EFF;
	public ThetaShield(Throwable atom) {
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
	public void addShield(int type) {
		Random rn = new Random();
		THETA_EFF = rn.nextDouble()*(0.10)+0.05;
		double effFactor = (1 - this.updatedEfficiency) * THETA_EFF;
		double speedFactor = 0.09;
		setEfficiency(this.updatedEfficiency+this.updatedEfficiency*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
	
}
