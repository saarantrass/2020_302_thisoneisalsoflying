package Domain.GameObjects.Atoms;

import java.awt.Point;

public class GammaAtom extends Atom{

	private static double GAMMA_STABILITY = 0.8;
	public GammaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
		double constant = ((double)Math.abs(this.getNeutron()-this.getProton())/(double)(2*this.getProton()));
		this.efficiency = GAMMA_STABILITY +constant;
	}
}
