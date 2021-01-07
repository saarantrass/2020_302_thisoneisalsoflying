package Test;

import static junit.framework.TestCase.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Molecules.AlphaMolecule;
import Domain.GameObjects.Molecules.BetaMolecule;
import Domain.GameObjects.Molecules.GammaMolecule;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.Molecules.SigmaMolecule;

class FallingObjectFactoryTest {
	Molecule alpha;
	Molecule beta;
	Molecule gamma;
	Molecule sigma;

	@Test
	void getInstanceTest() {
		FallingObjectFactory fac = FallingObjectFactory.getInstance();
		assertNotNull("Get instance returns not null", fac);
		assertTrue("true instance", fac instanceof FallingObjectFactory);
	}
	
	@Test
	void MoleculeCreationTest() {
		this.alpha = FallingObjectFactory.getInstance().getNewMolecule(1, new Point(100,100), false, false);
		this.beta = FallingObjectFactory.getInstance().getNewMolecule(2, new Point(100,100), false, false);
		this.gamma = FallingObjectFactory.getInstance().getNewMolecule(3, new Point(100,100), false, false);
		this.sigma = FallingObjectFactory.getInstance().getNewMolecule(4, new Point(100,100), false, false);
		assertTrue("alpha is alpha molecule", alpha instanceof AlphaMolecule);
		assertTrue("beta is beta molecule", beta instanceof BetaMolecule);
		assertTrue("gamma is gamma molecule", gamma instanceof GammaMolecule);
		assertTrue("sigma is sigma molecule", sigma instanceof SigmaMolecule);
	}

}
