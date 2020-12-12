package Domain;

import java.awt.Point;
import Domain.Player.*;

public class GameController {
	public Player player;
	public Shooter shooter;
	public Settings settings;
	
	
	public GameController() {
		this.player = new Player();
		this.shooter = new Shooter(null);
		this.settings = Settings.getInstance();
	}
	
	
	public void useBlender(int source, int goal) {
		System.out.println(source + " blender " + goal);
	}
	
	
	public void editInBuildMode(int atomNumber, int moleculeNumber, int reactionBlockerNumber, int powerUpNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel) {
		this.settings.setSettings(atomNumber, moleculeNumber, reactionBlockerNumber, powerUpNumber, isLinear, isSpinning, lengthUnit, difficultyLevel);
	}
	
	
	public void startGame() {
		Game.getInstance().startGame(this);
	}
	
	
	public int getRandomAtom() {
		return 0;
	}
	
	
	public void moveShooter(int direction) {
		shooter.move(direction);
	}
	
	
	public void stopMoveShooter() {
		shooter.stopMove();
	}
	
	
	public void rotateShooter(int direction) {
		shooter.rotate(direction);
		
	}
	
	
	public void stopRotateShooter() {
		shooter.stopRotate();
	}
	
	
	public void quitGame() {
		
	}
	
	
	public void resumeGame() {
		Game.getInstance().resumeGame();
	}
	
	
	public void shoot() {
		Game.getInstance().shoot();
	}
	
	
	public void changeAtomOnBarrel() {
		Game.getInstance().getRandomAtomToBarrel();
	}
	
	
	public void getPowerUpOnBarrel(int type) {
		Game.getInstance().getPowerUpToBarrel(type);
	}
	
	
	public void pauseGame() {
		Game.getInstance().pauseGame();	
	}
	
	
	public void setInitialShooterCoordinate(Point coordinate) {
		shooter.setCoordinate(coordinate);
	}
}
