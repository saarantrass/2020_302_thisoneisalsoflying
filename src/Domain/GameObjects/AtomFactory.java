package Domain.GameObjects;

import java.awt.Point;
import Domain.GameObjects.Atoms.*;
import Domain.GameObjects.Atoms.Shields.EtaShield;
import Domain.GameObjects.Atoms.Shields.LotaShield;
import Domain.GameObjects.Atoms.Shields.ThetaShield;
import Domain.GameObjects.Atoms.Shields.ZetaShield;

public class AtomFactory {
	private static AtomFactory singleton;

	public AtomFactory() {}

	public Atom addNewShield(int type, Atom atom) {

		switch (type) {
		case 1:
			return new EtaShield(atom);

		case 2:
			return new LotaShield(atom);

		case 3:
			return new ThetaShield(atom);

		case 4:
			return new ZetaShield(atom);
		}
		return null;
	}

	public Atom getNewAtom(int type, Point coord) {
		switch (type) {
		case 1:
			return new AlphaAtom(type,coord);

		case 2:
			return new BetaAtom(type,coord);

		case 3:
			return new GammaAtom(type,coord);

		case 4:
			return new SigmaAtom(type,coord);
		}
		return null;
	}

	public static AtomFactory getInstance(){
		if (singleton == null){
			singleton = new AtomFactory();
		}
		return singleton;
	}

}
