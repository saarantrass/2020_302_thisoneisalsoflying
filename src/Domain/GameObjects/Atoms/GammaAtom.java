package Domain.GameObjects.Atoms;

import java.awt.Point;

public class GammaAtom extends Atom{

	private static double GAMMA_STABILITY = 0.8;
	public GammaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
	}
	
	@Override
	public double getEfficiency() {
		return GAMMA_STABILITY + ((Math.abs(this.getNeutron()-this.getProton())/(2*this.getProton())));
	}
}
