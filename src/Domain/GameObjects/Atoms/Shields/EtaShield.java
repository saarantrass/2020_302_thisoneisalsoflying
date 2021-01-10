package Domain.GameObjects.Atoms.Shields;

import Domain.GameObjects.Throwable;

public class EtaShield extends ShieldDecorator  {
	/***
	 * Overview: EtaShield class is a concrete class that presents one of the shield types
	 * that can be added to the atom. It creates some effects on the speed of the atom and
	 * efficiency, which is later used for score calculations.
	 */
	private double updatedEfficiency;
	private static double ETA_EFF = 0.05;
	public EtaShield(Throwable atom) {
		super(atom);
		this.updatedEfficiency = atom.getEfficiency();
		addShield();
	}

	@Override
	public double getEfficiency() {
		return this.updatedEfficiency;
	}
	@Override
	public void setEfficiency(double eff) {
		/***
		 * @REQUIRES: New value of the efficiency
		 * @EFFECTS: Updated efficiency of Throwable
		 */
		this.updatedEfficiency = eff;
	}

	@Override
	public void addShield() {
		/***
		 * @MODIFIES: Speed of Throwable
		 * @MODIFIES: Updated Efficiency of the Shielded Atom
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
