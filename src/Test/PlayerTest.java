package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Domain.Player.Player;
// This is a Black Box Testing.
//Blackbox testing is a way of testing where you don't care how the program manipulates the input; 
//you're only checking to see if the outputs are valid for the specified inputs.



class PlayerTest {

	@Test
	void healthTest() {
		Player p = new Player();
		double oldHealth = p.getHealth();
		p.decreaseHealth(50);
		assertNotEquals(p.getHealth(),oldHealth);
		
	}
	
	void scoreTest() {
		Player p = new Player();
		double oldScore = p.getScore();
		p.increaseScore(20);
		assertNotEquals(p.getScore(),oldScore);
		
		
	}
	
	void getHealthTest() {
		Player p = new Player();
		p.health = 0;
		assertNotEquals(p.getHealth(),0);
		
		
	}
	void getScoreTest() {
		Player p = new Player();
		p.score = 0;
		assertNotEquals(p.getScore(),0);
		
		
	}
	
	void getScoreTest2() {
		Player p = new Player();
		p.score = 100;
		assertNotEquals(p.getScore(),0);
		
		
	}
	
		
	}


