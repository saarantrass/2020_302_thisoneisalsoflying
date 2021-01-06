package Domain.GameObjects.Atoms;

import java.awt.Point;

public class SigmaAtom extends Atom{

	private static double SIGMA_STABILITY = 0.8;

	public SigmaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
	}
	@Override
	public double getEfficiency() {
		return (1 + SIGMA_STABILITY)/2 + ((Math.abs(this.getNeutron()-this.getProton())/(this.getProton())));
	}
}
