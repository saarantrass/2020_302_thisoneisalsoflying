package Test;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import Domain.Settings;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.Molecules.BetaMolecule;

/**
 * BetaMoleculeTest class tests falling behaviour of the class BetaMolecule using Black Box Testing by 
 * updating the behaviour over and under breakpoints.
 * Additionaly, it tests the constructor of the BetaMolecule class.
 * @author ceyhunaslan
 * 
 */
public class BetaMoleculeTest {
	
	private static Dimension screenSize = new Dimension(1200, 500);
	private static int L = 50;
	
	@BeforeClass
	/**
	 * Set settings to get length unit
	 * Run it once before starting the tests with @BeforeClass
	 */
	public static void initSettings() {
		Settings.getInstance().setSettings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, L, 1, screenSize, "", 600000);
	}
	
	@Test
	/**
	 * Test setting the initial properties of the molecule with the constructor
	 */
	public void constructorTest() {
		Point coordinate = new Point(100, 100);
		int moleculeID = 2; 
		boolean isLinear = false;
		boolean isSpinning = false;
		BetaMolecule molecule = new BetaMolecule(moleculeID, coordinate, isLinear, isSpinning);
		assertEquals(moleculeID, molecule.getID());
		assertEquals(coordinate.x, molecule.getCoordinate().x);
		assertEquals(coordinate.y, molecule.getCoordinate().y);
		assertEquals(isSpinning, molecule.isSpinning());
		assertEquals(isLinear, molecule.isLinear());
	}
	
	@Test
	/**
	 * Test gettes and setters of BetaMolecule
	 */
	public void getterSetterTest() {
		BetaMolecule molecule = new BetaMolecule(2, new Point(0, 0), false, false);
		
		/**
		 * test setCoordinate and getCoordinate
		 */
		molecule.setCoordinate(new Point(200, 210));
		assertEquals(200, molecule.getCoordinate().x);
		assertEquals(210, molecule.getCoordinate().y);
		
		/**
		 * test setSpeed and getSpeed
		 */
		molecule.setSpeed(10.0);
		assertEquals(10.0, molecule.getSpeed());
		
		/**
		 * test setTravelled and getTravelled
		 */
		molecule.setTravelled(10.0);
		assertEquals(10.0, molecule.getTravelled());
	
		/**
		 * test setxSpeed and getxSpeed
		 */
		molecule.setxSpeed(10.0);
		assertEquals(10.0, molecule.getxSpeed());
	}
	
	@Test
	/**
	 * Test updateFallingStategy method of the molecule
	 */
	public void updateFallingStrategyTest() {
		/**
		 * Test LinearStrategy
		 */
		BetaMolecule molecule = new BetaMolecule(2, new Point(0, 0), false, false);
		molecule.updateMovingStrategy();
		// Should be LinearStrategy
		IMovingBehaviour oldStrat = molecule.getMovingStrategy();
	
		/**
		 * Test ZigZagStrategy
		 */
		// Set height over the breakpoint for Zigzagstrategy
		Point overBreakPoint = new Point(0, screenSize.height / 4 + 10);
		molecule.setCoordinate(overBreakPoint);
		molecule.updateMovingStrategy();
		// Should be ZigzagStrategy
		assertNotEquals(oldStrat, molecule.getMovingStrategy());
	}
	
	@Test
	/**
	 * Test the falling in when the falling strategy is LinearStrategy
	 */
	public void moveLinearTest() {
		/**
		 * init molecule with LinearStrategy
		 */
		BetaMolecule molecule = new BetaMolecule(2, new Point(0, 0), false, false);
	
		molecule.move();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(0, molecule.getCoordinate().x);
		assertNotEquals(0, molecule.getCoordinate().y);
	}
	
	@Test
	/**
	 * Test the falling in when the falling strategy is ZigZagStrategy
	 */
	public void moveZigZagTest() {
		/**
		 * init molecule with ZigZagStrategy
		 */
		BetaMolecule molecule = new BetaMolecule(2, new Point(0, 0), false, false);
		molecule.move();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotEquals(50, molecule.getCoordinate().x);
		assertNotEquals(50, molecule.getCoordinate().y);
	}
	
	
	
	
}
