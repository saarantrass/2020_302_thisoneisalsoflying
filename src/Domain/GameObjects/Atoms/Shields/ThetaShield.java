package Domain.GameObjects.Atoms.Shields;
import java.util.Random;
import Domain.GameObjects.Atoms.Atom;

public class ThetaShield extends AtomDecorator {

	private double THETA_EFF;
	public ThetaShield(Atom atom) {
		super(atom);
		updateEfficiency();
	}

	@Override
	public void updateEfficiency() {
		Random rn = new Random();
		THETA_EFF = rn.nextDouble()*(0.10)+0.05;
		double effFactor = (1 - this.getEfficiency()) * THETA_EFF;
		double speedFactor = 0.09;
		this.setEfficiency(this.getEfficiency()+this.getEfficiency()*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));
	}
	
}
