package Test;


import static junit.framework.TestCase.assertEquals;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import Domain.Settings;
import Domain.GameObjects.LinearStrategy;
import Domain.GameObjects.ZigZagStrategy;
import Domain.GameObjects.Molecules.BetaMolecule;

/**
 * BetaMoleculeTest class tests falling behaviour of the class BetaMolecule using Black Box Testing by 
 * updating the behaviour over and under breakpoints.
 * Additionaly, it tests the constructor of the BetaMolecule class.
 * @author ceyhunaslan
 */
public class BetaMoleculeTest {
	
	private Dimension screenSize = new Dimension(1200, 500);
	private int L = 50;
	
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
		assertEquals(moleculeID, molecule.getMoleculeID());
		assertEquals(coordinate.x, molecule.getCoordinate().x);
		assertEquals(coordinate.y, molecule.getCoordinate().y);
		assertEquals(isSpinning, molecule.isSpinning());
		assertEquals(isLinear, molecule.isLinear());
	}
	
	@Test
	/**
	 * Test falling strategy of the molecule
	 */
	public void fallTest() {
		Settings.getInstance().setSettings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, this.L, 1, this.screenSize); //set settings to get length unit in the shooter constructor
		
		/**
		 * Test LinearStrategy
		 */
		BetaMolecule molecule = new BetaMolecule(2, new Point(0, 0), false, false);
		molecule.updateFallingStrategy();
		// Should be LinearStrategy
		assertEquals(new LinearStrategy(molecule), molecule.getFallingBehaviour());
	
		/**
		 * Test ZigZagStrategy
		 */
		// Set height over the breakpoint for Zigzagstrategy
		Point overBreakPoint = new Point(0, this.screenSize.height / 4 + 10);
		molecule.setCoordinate(overBreakPoint);
		molecule.updateFallingStrategy();
		// Should be ZigzagStrategy
		assertEquals(new ZigZagStrategy(molecule), molecule.getFallingBehaviour());
	}
	
	
	
	
}
