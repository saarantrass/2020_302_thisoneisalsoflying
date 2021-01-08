package Test;

import Domain.Game;
import Domain.GameObjects.AtomFactory;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Throwable;
import Domain.GameObjects.Atoms.*;
import Domain.GameObjects.Atoms.Shields.EtaShield;
import Domain.GameObjects.Atoms.Shields.ShieldDecorator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class EtaShieldTest {
	Throwable atom1;
	Throwable atom2;
	ShieldDecorator shieldedAtom;
	ShieldDecorator shieldedAtom2;
	ShieldDecorator shieldedAtom3;
	Method setter;
	@Before
	public void setUp(){
		atom1 = AtomFactory.getInstance().getNewAtom(1, new Point(0,0));
		atom2 = AtomFactory.getInstance().getNewAtom(2, new Point(0,0));
		atom1.setSpeed(10);
		
		shieldedAtom = AtomFactory.getInstance().addNewShield(1, atom1);

	}
	
	@Test
	public void initialTest() {
		assertEquals(1, atom1.getAtomID());
		assertEquals(2, atom2.getAtomID());
		assertEquals(1, shieldedAtom.getAtomID());
		assertNotNull("Alpha atom is created", this.atom1);
		assertTrue("Alpha is Alpha Atom", atom1 instanceof AlphaAtom);
		
		assertNotNull("Beta Atom is created", this.atom2);
		assertTrue("Beta is Beta Atom", atom2 instanceof BetaAtom);

		
	}
	@Test
	public void getSpeedTest() {
		assertNotEquals(atom1.getSpeed(), shieldedAtom.getSpeed());
	}
	@Test
	public void setSpeedTest() {
		
		assertNotEquals(atom1.getSpeed(), shieldedAtom.getSpeed());
	}
	@Test
	public void setEfficiencyTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		setter = Throwable.class.getDeclaredMethod("setEfficiency");
		setter.setAccessible(true);
		setter.invoke(5);
		assertEquals(5,shieldedAtom.getEfficiency());
	}
	@Test
	public void addMoreShieldTest() {
		double initialEfficiency = shieldedAtom.getEfficiency();
		shieldedAtom2 = AtomFactory.getInstance().addNewShield(1, shieldedAtom);
		shieldedAtom3 = AtomFactory.getInstance().addNewShield(1, shieldedAtom2);
		assertNotEquals(initialEfficiency, shieldedAtom3.getEfficiency());
	}
}
