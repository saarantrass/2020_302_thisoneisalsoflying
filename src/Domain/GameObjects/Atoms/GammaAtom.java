package Domain.GameObjects.Atoms;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

public class GammaAtom extends Atom{

	private static double GAMMA_STABILITY = 0.8;
	public GammaAtom(int atomID, Point coordinate) {
		super(atomID,coordinate);
		this.atomID = 3;
		this.neutron = 	Arrays.asList(29,32,33).get(new Random().nextInt(3));	
		double constant = ((double)Math.abs(this.getNeutron()-this.getProton())/(double)(2*this.getProton()));
		this.setEfficiency(GAMMA_STABILITY +constant);
	}
}
