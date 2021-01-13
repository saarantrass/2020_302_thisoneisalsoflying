package Domain.GameObjects.Atoms.Shields;

import Domain.GameObjects.Atoms.Atom;

public class EtaShield extends AtomDecorator  {
	/***
	 * Overview: EtaShield class is a concrete class that presents one of the shield types
	 * that can be added to the atom. It creates some effects on the speed of the atom and
	 * efficiency, which is later used for score calculations.
	 */
	private static double ETA_EFF = 0.05;
	public EtaShield(Atom atom) {
		super(atom);
		updateEfficiency();
	}
	
	@Override
	public void updateEfficiency() {
		/***
		 * @MODIFIES: Speed of Throwable
		 * @MODIFIES: Updated Efficiency of the Shielded Atom
		 */
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.getProton()!= this.getNeutron()) {
			effFactor = (1 - this.getEfficiency())*(double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton();
		}else {
			effFactor = (1 - this.getEfficiency()) * ETA_EFF;
		}
		speedFactor = 0.05;
		this.setEfficiency(this.getEfficiency()+this.getEfficiency()*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
		
	}

}
