package Domain.GameObjects.Atoms;

import java.awt.Point;

public class BetaAtom extends Atom{

	private static double BETA_STABILITY = 0.9;
	public BetaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
		double constant = (0.5*((double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton()));
		this.efficiency = (BETA_STABILITY -constant);
	}
}
