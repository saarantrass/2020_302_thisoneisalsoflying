package Domain.GameObjects.Atoms.Shields;

import Domain.GameObjects.Atoms.Throwable;

public class EtaShield extends ShieldDecorator  {
	private double updatedEfficiency;
	private static double ETA_EFF = 0.05;
	public EtaShield(Throwable atom) {
		super(atom);
		this.updatedEfficiency = atom.getEfficiency();
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
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.getProton()!= this.getNeutron()) {
			effFactor = (1 - this.getEfficiency())*Math.abs(this.getNeutron()-this.getProton())/this.getProton();
		}else {
			effFactor = (1 - this.getEfficiency()) * ETA_EFF;
		}
		speedFactor = 0.05;
		setEfficiency(this.updatedEfficiency+this.updatedEfficiency*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
	

}
