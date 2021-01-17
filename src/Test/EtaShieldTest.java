package Test;

import Domain.GameObjects.AtomFactory;
import Domain.GameObjects.Atoms.*;
import Domain.GameObjects.Atoms.Shields.EtaShield;
import Domain.GameObjects.Atoms.Shields.AtomDecorator;

import java.lang.reflect.InvocationTargetException;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

import java.awt.Point;

public class EtaShieldTest {
	Atom atom1;
	Atom atom2;
	Atom shieldedAtom;
	Atom shieldedAtom2;
	Atom shieldedAtom3;
	@Before
	public void setUp(){
		/***
		 * atom1, atom2 and shieldedAtom are initialized here to avoid code repetition
		 * because we use them like this in every test.
		 * shieldedAtom2 and 3 are not initialized since we initialize
		 * them differently in different tests.
		 */
		atom1 = AtomFactory.getInstance().getNewAtom(1, new Point(0,0));
		atom2 = AtomFactory.getInstance().getNewAtom(2, new Point(0,0));
		atom1.setSpeed(10);
		atom2.setSpeed(20);
		shieldedAtom = AtomFactory.getInstance().addNewShield(1, atom1); 
	}
	
	@Test
	public void initialTest() {
		/***
		 * initial test to check out whether we have initialized our
		 * atoms and shields correctly.
		 * All of the abstract and concrete classes one atom or 
		 * shield may have are tested.
		 */
		assertEquals(1, atom1.getAtomID());
		assertEquals(2, atom2.getAtomID());
		assertEquals(1, shieldedAtom.getAtomID());
		
		assertNotNull("Alpha atom is created", this.atom1);
		assertTrue("Alpha is Alpha Atom", atom1 instanceof AlphaAtom);
		assertTrue("Alpha is Atom", atom1 instanceof Atom);
		
		assertNotNull("Beta Atom is created", this.atom2);
		assertTrue("Beta is Beta Atom", atom2 instanceof BetaAtom);
		assertTrue("Beta is Atom", atom2 instanceof Atom);

		assertNotNull("Eta Shielded atom is created", this.shieldedAtom);
		assertTrue("Eta Shielded atom is Eta Shield", shieldedAtom instanceof EtaShield);
		assertTrue("Eta Shielded atom is Shield Decorator", shieldedAtom instanceof AtomDecorator);
		
	}
	
	@Test
	public void setEfficiencyTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/***
		 * Set efficiency method is tested here.
		 * Since shield decorator overrides the base efficiency of the shield,
		 * we use assertEquals to show that override
		 */
		atom1.setEfficiency(1.7);
		assertEquals(1.7, atom1.getEfficiency());
		
		shieldedAtom.setEfficiency(1.2);
		assertEquals(1.2, shieldedAtom.getEfficiency());
		
		assertEquals(atom1.getEfficiency(), shieldedAtom.getEfficiency()); 
		
		shieldedAtom3 = AtomFactory.getInstance().addNewShield(1, shieldedAtom);
		
		shieldedAtom3.setEfficiency(0.7);
		assertEquals(0.7, shieldedAtom3.getEfficiency());
		
		
		assertEquals(atom1.getEfficiency(), shieldedAtom3.getEfficiency());
		
	}
	
	@Test
	public void addMoreShieldTest() {
		/***
		 * Add shield method is tested here.
		 * Since shield decorator overrides the base efficiency of the shield,
		 * we use assertNotEquals to show that override.
		 * AddShield is not explicitly called here, as it called in the constructor
		 * when a shielded atom is created.
		 */
		
		assertEquals(atom1.getEfficiency(), shieldedAtom.getEfficiency());
		
		shieldedAtom2 = AtomFactory.getInstance().addNewShield(1, shieldedAtom);
		assertEquals(shieldedAtom.getEfficiency(), shieldedAtom2.getEfficiency());
		
		shieldedAtom3 = AtomFactory.getInstance().addNewShield(1, shieldedAtom2);
		
		assertEquals(shieldedAtom.getEfficiency(), shieldedAtom3.getEfficiency());
		assertEquals(shieldedAtom2.getEfficiency(), shieldedAtom3.getEfficiency());
		
	}
	
	@Test
	public void getSpeedTest() {
		/***
		 * getSpeed method is tested here.
		 * The speeds in the setUp step are used to compare.
		 */
		
		assertEquals(atom1.getSpeed(), shieldedAtom.getSpeed());
		
		shieldedAtom2 = AtomFactory.getInstance().addNewShield(1, atom2);
		assertEquals(atom2.getSpeed(), shieldedAtom2.getSpeed());

		assertNotEquals(shieldedAtom.getSpeed(), shieldedAtom2.getSpeed());
		assertNotEquals(atom1.getSpeed(), shieldedAtom2.getSpeed());
	}
	
	@Test
	public void setSpeedTest() {
		/***
		 * setSpeed method is tested here.
		 * assertEquals are used since the decorator directly changes the
		 * Throwable's speed.
		 */
		shieldedAtom.setSpeed(10);
		assertEquals(atom1.getSpeed(), shieldedAtom.getSpeed());
		
		atom1.setSpeed(12);
		assertEquals(atom1.getSpeed(), shieldedAtom.getSpeed());
		
		shieldedAtom3 = AtomFactory.getInstance().addNewShield(1, shieldedAtom);
		shieldedAtom3.setSpeed(20);
		
		assertEquals(atom1.getSpeed(), shieldedAtom3.getSpeed());
	}

}
