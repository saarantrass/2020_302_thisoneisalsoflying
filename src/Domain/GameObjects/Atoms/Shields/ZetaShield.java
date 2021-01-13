package Domain.GameObjects.Atoms.Shields;
import Domain.GameObjects.Atoms.Atom;

public class ZetaShield extends AtomDecorator {
	private double updatedEfficiency;
	private static double ZETA_EFF = 0.2;
	public ZetaShield(Atom atom) {
		super(atom);
		this.updatedEfficiency=atom.getEfficiency();
		updateEfficiency();
	} 

	@Override
	public double getEfficiency() {
		return this.updatedEfficiency;
	}
	@Override
	public void setEfficiency(double eff) {
		eff = eff*100;
		eff = Math.round(eff);
		eff = eff /100;
		this.updatedEfficiency = eff;
	}
	@Override 
	public void updateEfficiency() {
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.getProton()==this.getNeutron()) {
			effFactor = (1 - this.updatedEfficiency)*ZETA_EFF;
		}
		speedFactor = 0.11;
		this.setEfficiency(this.updatedEfficiency+this.updatedEfficiency*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
}
