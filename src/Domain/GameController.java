package Domain;

import Domain.Player.*;

public class GameController {
	public Player player;
	public Shooter shooter;
	
	public GameController() {
	}
	
	public void useBlender(int source, int goal) {
		
	}
	public void editInBuildMode(int atomNumber, int moleculeNumber, int RBNumber, int PUNumber, boolean isLinear, boolean isSpinning, int L, int difficultyLevel) {
		System.out.println(atomNumber + " " + moleculeNumber + " " +RBNumber + " " + PUNumber + " " + isLinear +  " " + isSpinning + " " + L + " " + difficultyLevel);
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
