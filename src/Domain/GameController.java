package Domain;

import java.awt.Point;
import java.util.ArrayList;

import Domain.GameObjects.Atom;
import Domain.GameObjects.Molecule;
import Domain.GameObjects.PowerUp;
import Domain.GameObjects.ReactionBlocker;
import Domain.Player.*;
import UI.Swing.GameModePanel;

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
		shooter.rotate(direction);
		
	}
	public void quitGame() {
		
	}
	public void resumeGame() {
		
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
		//isRunningGame = false;
		Game.getInstance().pauseGame();
		//mainFrame.setContentPane(pausePanel);
		//mainFrame.setVisible(true);	
	}
}
