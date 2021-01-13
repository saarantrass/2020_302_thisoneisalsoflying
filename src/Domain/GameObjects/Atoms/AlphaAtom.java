package Domain.GameObjects.Atoms;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

public class AlphaAtom extends Atom{
	
	private static double ALPHA_STABILITY = 0.85;
	public AlphaAtom(int atomID, Point coordinate) {
		super(atomID, coordinate);
		this.atomID = 1;
		this.neutron = Arrays.asList(7,8,9).get(new Random().nextInt(3));
		double constant = ((double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton());
		this.setEfficiency(ALPHA_STABILITY * (1- constant));
	}
}
