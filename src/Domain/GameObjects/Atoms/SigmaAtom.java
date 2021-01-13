package Domain.GameObjects.Atoms;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

public class SigmaAtom extends Atom{

	private static double SIGMA_STABILITY = 0.8;
	public SigmaAtom(int atomID, Point coordinate) {
		super(atomID,coordinate);
		this.atomID = 4 ;
		this.neutron = Arrays.asList(63,64,67).get(new Random().nextInt(3));
		double constant = ((double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton());
		this.setEfficiency( (double)((1 + SIGMA_STABILITY)/2) +constant );
	}
}
