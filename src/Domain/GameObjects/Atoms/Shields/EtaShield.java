package Domain.GameObjects.Atoms.Shields;

import Domain.GameObjects.Throwable;

public class EtaShield extends ShieldDecorator  {
	private double updatedEfficiency;
	private static double ETA_EFF = 0.05;
	public EtaShield(Throwable atom) {
		super(atom);
		this.updatedEfficiency = atom.getEfficiency();
		addShield();
	}

	@Override
	public double getEfficiency() {
	System.out.println("our eff"+this.updatedEfficiency);
		return this.updatedEfficiency;
	}
	public void setEfficiency(double eff) {
		/***
		 * REQUIRES: New efficiency
		 * EFFECTS: Updated efficiency of Throwable
		 */
		this.updatedEfficiency = eff;
	}

	@Override
	public void addShield() {
		/***
		 * REQUIRES: Type of the Shield
		 * MODIFIES: Speed of Throwable
		 * EFFECTS: Updated efficiency of Throwable
		 */
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.getProton()!= this.getNeutron()) {
			effFactor = (1 - this.updatedEfficiency)*(double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton();
		}else {
			effFactor = (1 - this.updatedEfficiency) * ETA_EFF;
		}
		speedFactor = 0.05;
		setEfficiency(this.updatedEfficiency+this.updatedEfficiency*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
	

}
