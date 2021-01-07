package Domain.GameObjects;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Domain.GameObjects.Atoms.*;
import Domain.GameObjects.Atoms.Shields.EtaShield;
import Domain.GameObjects.Atoms.Shields.LotaShield;
import Domain.GameObjects.Atoms.Shields.ShieldDecorator;
import Domain.GameObjects.Atoms.Shields.ThetaShield;
import Domain.GameObjects.Atoms.Shields.ZetaShield;

public class AtomFactory {
	private static AtomFactory singleton;

	public AtomFactory() {}

	public ShieldDecorator addNewShield(int type, Throwable atom) {

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

	public Throwable getNewAtom(int type, Point coord) {
		int neutron = getRandomNeutron(type);
		switch (type) {
		case 1:
			return new AlphaAtom(type,coord,neutron);

		case 2:
			return new BetaAtom(type,coord,neutron);

		case 3:
			return new GammaAtom(type,coord,neutron);

		case 4:
			return new SigmaAtom(type,coord,neutron);
		}
		return null;
	}

	private int getRandomNeutron(int type) {
		Random rn = new Random();
		List<Integer> list = null;
		switch(type) {
		case 1:		list = Arrays.asList(7,8,9);			break;
		case 2:		list = Arrays.asList(15,16,17,18,21);	break;
		case 3:		list = Arrays.asList(29,32,33);			break;
		case 4:		list = Arrays.asList(63,64,67);			break;
		}
		return list.get(rn.nextInt(list.size()));
	}

	public static AtomFactory getInstance(){
		if (singleton == null){
			singleton = new AtomFactory();
		}
		return singleton;
	}

}
