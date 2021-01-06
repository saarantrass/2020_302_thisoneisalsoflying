package Domain.GameObjects.Atoms;

import java.awt.Point;

public class AlphaAtom extends Atom{
	
	private static double ALPHA_STABILITY = 0.85;
	public AlphaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
	}
	
	@Override
	public double getEfficiency() {
		return ALPHA_STABILITY*(1- (Math.abs(this.getNeutron()-this.getProton())/this.getProton()));
	}
}
