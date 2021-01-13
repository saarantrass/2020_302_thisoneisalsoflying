package Domain.GameObjects.Atoms;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

public class BetaAtom extends Atom{

	private static double BETA_STABILITY = 0.9;
	public BetaAtom(int atomID, Point coordinate) {
		super(atomID,coordinate);
		this.atomID = 2;
		this.neutron = Arrays.asList(15,16,17,18,21).get(new Random().nextInt(5));
		double constant = (0.5*((double)Math.abs(this.getNeutron()-this.getProton())/(double)this.getProton()));
		this.setEfficiency(BETA_STABILITY -constant);

	}
}
