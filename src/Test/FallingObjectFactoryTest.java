package Test;

import static junit.framework.TestCase.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Domain.GameObjects.FallingObject;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Molecules.AlphaMolecule;
import Domain.GameObjects.Molecules.BetaMolecule;
import Domain.GameObjects.Molecules.GammaMolecule;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.Molecules.SigmaMolecule;
import Domain.GameObjects.PowerUps.*;
import Domain.GameObjects.ReactionBlockers.*;

class FallingObjectFactoryTest {
	FallingObject alphaMol;
	FallingObject betaMol;
	FallingObject gammaMol;
	FallingObject sigmaMol;
	FallingObject emptyMol;
	
	FallingObject type1p;
	FallingObject type2p;
	FallingObject type3p;
	FallingObject type4p;
	FallingObject emptyp;
	
	FallingObject type1b;
	FallingObject type2b;
	FallingObject type3b;
	FallingObject type4b;
	FallingObject emptyb;
	
	Molecule coordCheckMol;
	
	@Test
	void getInstanceTest() {
		FallingObjectFactory fac = FallingObjectFactory.getInstance();
		
		assertNotNull("Get instance returns not null", fac);
		assertTrue("true instance", fac instanceof FallingObjectFactory);
	}
	
	@Test
	void MoleculeCreationTest() {
		this.alphaMol = FallingObjectFactory.getInstance().getNewMolecule(1, new Point(100,100), false, false);
		this.betaMol = FallingObjectFactory.getInstance().getNewMolecule(2, new Point(100,100), false, false);
		this.gammaMol = FallingObjectFactory.getInstance().getNewMolecule(3, new Point(100,100), false, false);
		this.sigmaMol = FallingObjectFactory.getInstance().getNewMolecule(4, new Point(100,100), false, false);
		this.emptyMol = FallingObjectFactory.getInstance().getNewMolecule(5, new Point(100,100), false, false);
		
		assertNotNull("Alpha molecule is created", this.alphaMol);
		assertTrue("alpha is alpha molecule", alphaMol instanceof AlphaMolecule);
		
		assertNotNull("Beta molecule is created", this.betaMol);
		assertTrue("beta is beta molecule", betaMol instanceof BetaMolecule);
		
		assertNotNull("Gamma molecule is created", this.gammaMol);
		assertTrue("gamma is gamma molecule", gammaMol instanceof GammaMolecule);
		
		assertNotNull("Sigma molecule is created", this.sigmaMol);
		assertTrue("sigma is sigma molecule", sigmaMol instanceof SigmaMolecule);
		
		assertNull("no molecule is not created because invalid type", this.emptyMol);
	}
	
	@Test
	void PowerUpCreationTest() {
		
		this.type1p = FallingObjectFactory.getInstance().getNewPowerUp(1, new Point(100,100),false);
		this.type2p = FallingObjectFactory.getInstance().getNewPowerUp(2, new Point(100,100),false);
		this.type3p = FallingObjectFactory.getInstance().getNewPowerUp(3, new Point(100,100),false);
		this.type4p = FallingObjectFactory.getInstance().getNewPowerUp(4, new Point(100,100),false);
		this.emptyp = FallingObjectFactory.getInstance().getNewPowerUp(5, new Point(100,100),false);
		
		
		assertNotNull("powerup type 1 is created", this.type1p);
		assertTrue("return type is PowerUp", this.type1p instanceof AlphaPowerUp);
		
		
		assertNotNull("powerup type 2 is created", this.type2p);
		assertTrue("return type is PowerUp", this.type2p instanceof BetaPowerUp);
		
		
		assertNotNull("powerup type 3 is created", this.type3p);
		assertTrue("return type is PowerUp", this.type3p instanceof GammaPowerUp);
		
		
		assertNotNull("powerup type 4 is created", this.type4p);
		assertTrue("return type is PowerUp", this.type4p instanceof SigmaPowerUp);
		
		
		assertNull("no PowerUp is created because invalid type", this.emptyp);
	}
	
	@Test
	void ReactionBlockerCreationTest() {
		
		this.type1b = FallingObjectFactory.getInstance().getNewReactionBlocker(1, new Point(100,100));
		this.type2b = FallingObjectFactory.getInstance().getNewReactionBlocker(2, new Point(100,100));
		this.type3b = FallingObjectFactory.getInstance().getNewReactionBlocker(3, new Point(100,100));
		this.type4b = FallingObjectFactory.getInstance().getNewReactionBlocker(4, new Point(100,100));
		this.emptyb = FallingObjectFactory.getInstance().getNewReactionBlocker(5, new Point(100,100));
		
		
		assertNotNull("reaction blocker type 1 is created", this.type1b);
		assertTrue("return type is ReactionBlocker", this.type1b instanceof AlphaReactionBlocker);
		
		
		assertNotNull("reaction blocker type 2 is created", this.type2b);
		assertTrue("return type is ReactionBlocker", this.type2b instanceof BetaReactionBlocker);
		
		
		assertNotNull("reaction blocker type 3 is created", this.type3b);
		assertTrue("return type is ReactionBlocker", this.type3b instanceof GammaReactionBlocker);
		
		
		assertNotNull("reaction blocker type 4 is created", this.type4b);
		assertTrue("return type is ReactionBlocker", this.type4b instanceof SigmaReactionBlocker);
		
		
		assertNull("no ReactionBlocker is created because invalid type", this.emptyb);
	}
	
	@Test
	void createdObjectCoordinatesTest() {
		
		this.coordCheckMol = FallingObjectFactory.getInstance().getNewMolecule(1, new Point(100,100), false, false);
		
		
		assertEquals("created molecule is on true x coord", this.coordCheckMol.getCoordinate().getX(), 100.0);
		assertEquals("created molecule is on true y coord", this.coordCheckMol.getCoordinate().getY(), 100.0);
	}

}
