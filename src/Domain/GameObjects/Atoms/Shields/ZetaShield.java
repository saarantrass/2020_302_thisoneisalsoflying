package Domain.GameObjects.Atoms.Shields;
import Domain.GameObjects.Atoms.Atom;

public class ZetaShield extends AtomDecorator {

	private static double ZETA_EFF = 0.2;
	public ZetaShield(Atom atom) {
		super(atom);
		updateEfficiency();
	} 

	@Override 
	public void updateEfficiency() {
		double effFactor = 0.0;
		double speedFactor = 0.0;
		if(this.getProton()==this.getNeutron()) {
			effFactor = (1 - this.getEfficiency())*ZETA_EFF;
		}
		speedFactor = 0.11;
		this.setEfficiency(this.getEfficiency()+this.getEfficiency()*effFactor);
		this.setSpeed(this.getSpeed()*(1-speedFactor));

	}
}
