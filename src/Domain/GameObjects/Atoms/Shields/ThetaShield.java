package Domain.GameObjects.Atoms.Shields;
import java.util.Random;
import Domain.GameObjects.Atoms.Atom;

public class ThetaShield extends AtomDecorator {
	private double updatedEfficiency;
	private double THETA_EFF;
	public ThetaShield(Atom atom) {
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
		Random rn = new Random();
		THETA_EFF = rn.nextDouble()*(0.10)+0.05;
		double effFactor = (1 - this.updatedEfficiency) * THETA_EFF;
		double speedFactor = 0.09;
		this.setEfficiency(this.updatedEfficiency+this.updatedEfficiency*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
	
}
