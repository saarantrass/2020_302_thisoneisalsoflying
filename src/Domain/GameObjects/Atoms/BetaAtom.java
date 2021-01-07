package Domain.GameObjects.Atoms;

import java.awt.Point;

public class BetaAtom extends Atom{

	private static double BETA_STABILITY = 0.9;
	public BetaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
	}
	
	@Override
	public double getEfficiency() {
		double constant = (0.5*((double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton()));
		return (BETA_STABILITY -constant);
	}
}
