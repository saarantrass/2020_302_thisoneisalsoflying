package Domain.GameObjects.Atoms;

import java.awt.Point;

public class AlphaAtom extends Atom{
	
	private static double ALPHA_STABILITY = 0.85;
	public AlphaAtom(int atomID, Point coordinate, int neutron) {
		super(atomID, coordinate, neutron);
		
		double constant = ((double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton());
		this.efficiency = ALPHA_STABILITY*(1- constant);
	}
}
