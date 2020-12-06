package Domain;

import Domain.Player.*;

public class GameController {
	public Player player;
	private Shooter shooter;
	
	public GameController(Shooter s) {
		this.shooter = s;
	}
	
	public void useBlender(int source, int goal) {
		
	}
	public void editInBuildMode() {
		
	}
	public int getRandomAtom() {
		return 0;
	}
	public void moveShooter(int direction) {
		shooter.move(direction);
	}
	public void rotateShooter(int direction) {
		
	}
	public void quitGame() {
		
	}
	public void resumeGame() {
		
	}
	public void pauseGame() {
		
	}
	public void shootPowerUp(int powerUpID) {
		
	}
	public void shootAtom(int atomID) {
		
	}
}
