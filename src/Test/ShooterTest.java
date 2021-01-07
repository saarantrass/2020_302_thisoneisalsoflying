package Test;

import static junit.framework.TestCase.assertEquals;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import Domain.Settings;
import Domain.Player.Shooter;

public class ShooterTest {
	
	private Dimension screenSize = new Dimension(1200, 500);
	private int L = 50;
	
	@Test
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertEquals(0, shooter.getCoordinate().x); //test x
		assertEquals(this.screenSize.height, shooter.getCoordinate().y); //test y
		
		
		/*
		 * test right boundary of the screen
		 */
		Point rightBoundary = new Point((this.screenSize.width * 7/8) - this.L/2, this.screenSize.height); //right boundary of the screen
		shooter.setCoordinate(rightBoundary); //set coordinates of the shooter to right boundary
		shooter.move(1); //move right
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopMove();
		assertEquals((int) ((this.screenSize.width * 7/8) - this.L/2), shooter.getCoordinate().x); //test x
		assertEquals(this.screenSize.height, shooter.getCoordinate().y); //test y
		
	}
	
	@Test
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertEquals((double) 90, shooter.getAngle()); //test angle
		
		
		/*
		 * test minimum angle (-90 degrees) - to left
		 */
		shooter.setAngle(-90);
		shooter.rotate(0); //rotate left
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		shooter.stopRotate();
		assertEquals((double) -90, shooter.getAngle()); //test angle
		
	}

}
