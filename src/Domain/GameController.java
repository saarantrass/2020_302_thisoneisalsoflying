package Domain;

import java.awt.Dimension;

public class GameController {
	
	public GameController() {}
	
	
	public void editInBuildMode(int aAtomNumber, int bAtomNumber, int gAtomNumber, int sAtomNumber, int aMoleculeNumber, int bMoleculeNumber, int gMoleculeNumber, int sMoleculeNumber, int aRBNumber, int bRBNumber, int gRBNumber, int sRBNumber, int aPUNumber, int bPUNumber, int gPUNumber, int sPUNumber, int etaNumber, int lotaNumber, int thetaNumber, int zetaNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel,Dimension screenSize, String playerName) {
		Settings.getInstance().setSettings(aAtomNumber, bAtomNumber, gAtomNumber, sAtomNumber, aMoleculeNumber, bMoleculeNumber, gMoleculeNumber, sMoleculeNumber, aRBNumber, bRBNumber, gRBNumber, sRBNumber, aPUNumber, bPUNumber, gPUNumber, sPUNumber, etaNumber, lotaNumber, thetaNumber, zetaNumber, isLinear, isSpinning, lengthUnit, difficultyLevel, screenSize, playerName, 600000);
	}
	
	
	public void startGame() {
		Game.getInstance().startGame();
	}
	
	
	public void moveShooter(int direction) {
		Game.getInstance().moveShooter(direction);
	}
	
	
	public void stopMoveShooter() {
		Game.getInstance().stopMoveShooter();
	}
	
	
	public void rotateShooter(int direction) {
		Game.getInstance().rotateShooter(direction);
	}
	
	
	public void stopRotateShooter() {
		Game.getInstance().stopRotateShooter();
	}
	
	
	public void useBlender(int sourceAtom, int goalAtom) {
		Blender.useBlender(sourceAtom, goalAtom, Game.getInstance().getShooterInventory());
	}
	
	
	public void addShield(int type) {
		Game.getInstance().addShield(type);
	}
	
	
	public void shoot() {
		Game.getInstance().shoot();
	}
	
	
	public void changeAtomOnBarrel() {
		Game.getInstance().getRandomAtomToBarrel();
	}
	
	
	public void getPowerUpToBarrel(int type) {
		Game.getInstance().getPowerUpToBarrel(type);
	}
	
	
	public void saveGame() {
		Game.getInstance().saveGame();
	}
	
	
	public void loadGame(){
		Game.getInstance().loadGame();
	}
	
	
	public void quitGame() {
		Game.getInstance().quitGame();
	}
	
	
	public void resumeGame() {
		Game.getInstance().resumeGame();
	}
	
	
	public void pauseGame() {
		Game.getInstance().pauseGame();	
	}
}
