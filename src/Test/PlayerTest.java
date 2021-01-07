package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Domain.Player.Player;

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
	
	
		
	}


