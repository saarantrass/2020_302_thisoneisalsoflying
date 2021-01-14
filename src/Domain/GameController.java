package Domain;

import java.awt.Dimension;

public class GameController {
	
	Game game;
	
	public GameController(Game game) {this.game = game;}
	
	
	public void editInBuildMode(int aAtomNumber, int bAtomNumber, int gAtomNumber, int sAtomNumber, int aMoleculeNumber, int bMoleculeNumber, int gMoleculeNumber, int sMoleculeNumber, int aRBNumber, int bRBNumber, int gRBNumber, int sRBNumber, int aPUNumber, int bPUNumber, int gPUNumber, int sPUNumber, int etaNumber, int lotaNumber, int thetaNumber, int zetaNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel,Dimension screenSize, String playerName) {
		Settings.getInstance().setSettings(aAtomNumber, bAtomNumber, gAtomNumber, sAtomNumber, aMoleculeNumber, bMoleculeNumber, gMoleculeNumber, sMoleculeNumber, aRBNumber, bRBNumber, gRBNumber, sRBNumber, aPUNumber, bPUNumber, gPUNumber, sPUNumber, etaNumber, lotaNumber, thetaNumber, zetaNumber, isLinear, isSpinning, lengthUnit, difficultyLevel, screenSize, playerName);
	}
	
	
	public void startGame() {
		this.game.startGame();
	}
	
	
	public void moveShooter(int direction) {
		this.game.moveShooter(direction);
	}
	
	
	public void stopMoveShooter() {
		this.game.stopMoveShooter();
	}
	
	
	public void rotateShooter(int direction) {
		this.game.rotateShooter(direction);
	}
	
	
	public void stopRotateShooter() {
		this.game.stopRotateShooter();
	}
	
	
	public void useBlender(int source, int goal) {
		Blender.useBlender(source, goal, this.game.getShooterInventory());
	}
	
	
	public void addShield(int type) {
		this.game.addShield(type);
	}
	
	
	public void shoot() {
		this.game.shoot();
	}
	
	
	public void changeAtomOnBarrel() {
		this.game.getRandomAtomToBarrel();
	}
	
	
	public void getPowerUpOnBarrel(int type) {
		this.game.getPowerUpToBarrel(type);
	}
	
	
	public void saveGame() {
		this.game.saveGame();
	}
	
	
	public void loadGame(){
		this.game.loadGame();
	}
	
	
	public void quitGame() {
		this.game.quitGame();
	}
	
	
	public void resumeGame() {
		this.game.resumeGame();
	}
	
	
	public void pauseGame() {
		this.game.pauseGame();	
	}
}
