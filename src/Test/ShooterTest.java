package Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import Domain.Settings;
import Domain.Player.Shooter;

/**
 * ShooterTest class tests move and rotate functionalities of the class Shooter using Black Box Testing 
 * by testing the boundaries of the actions. It also tests the move and rotate of the shooter with Glass Box 
 * Testing by testing the non-boundary cases for both directions after the boundaries are handled by 
 * Black Box testing.
 * Additionally, it tests the constructor of the Shooter class, getters and setters of the 
 * related attributes and getBarrelCoordinate method which calculates the coordinate of the barrel of the 
 * shooter according to the coordinate and the angle of the shooter and returns it.
 * @author dogademirturk
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class ShooterTest {
	
	private Dimension screenSize = new Dimension(1200, 500);
	private int L = 50;
	
	@Test
	@Order(1)
	/**
	 * test getters and setters of the shooter coordinates and angle
	 */
	public void getterSetterTest() {
		Shooter shooter = new Shooter(new Point(100,100));
		
		/*
		 * test setCoordinate and getCoordinate
		 */
		shooter.setCoordinate(new Point(115, 450));
		assertEquals(115, shooter.getCoordinate().x); //test x
		assertEquals(450, shooter.getCoordinate().y); //test y
		
		/*
		 * test setAngle and getAngle
		 */
		shooter.setAngle(75);
		assertEquals((double) 75, shooter.getAngle()); //test angle
	}
	
	@Test
	@Order(2)
	/**
	 * test setting initial coordinate of the shooter with constructor
	 */
	public void constructorTest() {
		Point initialPoint = new Point(100,200); //initial point of the shooter
		Shooter shooter = new Shooter(initialPoint); //constructor with initial point
		assertEquals(100,shooter.getCoordinate().x); //test x
		assertEquals(200,shooter.getCoordinate().y); //test y
	}
	
	@Test
	@Order(3)
	/**
	 * test move of the shooter on the boundaries of the screen
	 */
	public void moveTest() {
		
		Settings.getInstance().setSettings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, this.L, 1, this.screenSize); //set settings to get length unit in the shooter constructor
		
		/*
		 * test left boundary of the screen
		 */
		Point leftBoundary = new Point(0, this.screenSize.height); //left boundary of the screen
		Shooter shooter = new Shooter(leftBoundary); //initialize shooter on left boundary
		shooter.move(0); //move left
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertEquals(0, shooter.getCoordinate().x); //BB test left boundary x
		assertEquals(this.screenSize.height, shooter.getCoordinate().y); //test y - not changed
		
		shooter.move(1); //move right
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertNotEquals(0, shooter.getCoordinate().x); //GB test - (not boundary case) shooter must move since it is not boundary for right move
		assertEquals(this.screenSize.height, shooter.getCoordinate().y); //test y - not changed	
		
		/*
		 * test right boundary of the screen
		 */
		Point rightBoundary = new Point((this.screenSize.width * 7/8) - this.L/2, this.screenSize.height); //right boundary of the screen
		shooter.setCoordinate(rightBoundary); //set coordinates of the shooter to right boundary
		shooter.move(1); //move right
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertEquals((int) ((this.screenSize.width * 7/8) - this.L/2), shooter.getCoordinate().x); //BB test right boundary x
		assertEquals(this.screenSize.height, shooter.getCoordinate().y); //test y - not changed
		
		shooter.move(0); //move left
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertNotEquals(0, shooter.getCoordinate().x); //GB test - (not boundary case) shooter must move since it is not boundary for left move
		assertEquals(this.screenSize.height, shooter.getCoordinate().y); //test y - not changed
		
	}
	
	@Test
	@Order(4)
	/**
	 * test rotate of the shooter on degrees 90 and -90 (maximum and minimum rotate angle)
	 */
	public void rotateTest() {
		
		Settings.getInstance().setSettings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, this.L, 1, this.screenSize); //set settings to get length unit in the shooter constructor
		
		/*
		 * test maximum angle (90 degrees) - to right
		 */
		Shooter shooter = new Shooter(new Point(100, 100));
		shooter.setAngle(90);
		shooter.rotate(1); //rotate right
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertEquals((double) 90, shooter.getAngle()); //BB test right boundary angle
		
		shooter.rotate(0); //rotate left
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertNotEquals((double) 90, shooter.getAngle()); //GB test - (not boundary case) shooter must rotate since it is not boundary for left rotate
		
		/*
		 * test minimum angle (-90 degrees) - to left
		 */
		shooter.setAngle(-90);
		shooter.rotate(0); //rotate left
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertEquals((double) -90, shooter.getAngle()); //BB test left boundary angle
		
		shooter.rotate(1); //rotate right
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertNotEquals((double) -90, shooter.getAngle()); //GB test - (not boundary case) shooter must rotate since it is not boundary for right rotate		
		
	}
	
	@Test
	@Order(5)
	/**
	 * test coordinate of the barrel when shooter is initialized, moved and rotated
	 */
	public void barrelCoordinateTest() {
		Settings.getInstance().setSettings(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, this.L, 1, this.screenSize); //set settings to get length unit in the shooter constructor
		Shooter shooter = new Shooter(new Point(this.screenSize.width/2, this.screenSize.height));
		
		/*
		 * shooter is at its initial position and the angle is 0
		 */
		assertEquals((int) (this.screenSize.width/2 + L/4 - L/20), shooter.getBarrelCoordinate().x); //test x
		assertEquals((int) (this.screenSize.height - L/10), shooter.getBarrelCoordinate().y); //test y
		
		/*
		 * shooter is moved and the angle is 0 - only x coordinate is changed since shooter moves horizontally
		 */
		shooter.move(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertEquals((int) (shooter.getCoordinate().x + L/4 - L/20), shooter.getBarrelCoordinate().x); //test x
		assertEquals((int) (shooter.getCoordinate().y - L/10), shooter.getBarrelCoordinate().y); //test y
		
		/*
		 * shooter is moved and rotated
		 */
		shooter.rotate(0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertEquals((int) (shooter.getCoordinate().x + L/4 - L/20 + (int) (L*Math.sin(Math.toRadians(shooter.getAngle())))), shooter.getBarrelCoordinate().x); //test x
		assertEquals((int) (shooter.getCoordinate().y - L/10 + (int) (L*(1-Math.cos(Math.toRadians(shooter.getAngle()))))), shooter.getBarrelCoordinate().y); //test y
		
	}

}
