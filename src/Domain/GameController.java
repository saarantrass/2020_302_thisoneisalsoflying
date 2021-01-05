package Domain;

import java.awt.Dimension;

public class GameController {
	public Settings settings;
	
	
	public GameController() {
		this.settings = Settings.getInstance();
	}
	
	public void editInBuildMode(int aAtomNumber, int bAtomNumber, int gAtomNumber, int sAtomNumber, int aMoleculeNumber, int bMoleculeNumber, int gMoleculeNumber, int sMoleculeNumber, int aRBNumber, int bRBNumber, int gRBNumber, int sRBNumber, int aPUNumber, int bPUNumber, int gPUNumber, int sPUNumber, int etaNumber, int lotaNumber, int thetaNumber, int zetaNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel,Dimension screenSize) {
		this.settings.setSettings(aAtomNumber, bAtomNumber, gAtomNumber, sAtomNumber, aMoleculeNumber, bMoleculeNumber, gMoleculeNumber, sMoleculeNumber, aRBNumber, bRBNumber, gRBNumber, sRBNumber, aPUNumber, bPUNumber, gPUNumber, sPUNumber, etaNumber, lotaNumber, thetaNumber, zetaNumber, isLinear, isSpinning, lengthUnit, difficultyLevel, screenSize);
	}
	
	
	public void startGame() {
		Game.getInstance().startGame(this);
	}
	
	
	public void moveShooter(int direction) {
		Game.getInstance().shooter.move(direction);
	}
	
	
	public void stopMoveShooter() {
		Game.getInstance().shooter.stopMove();
	}
	
	
	public void rotateShooter(int direction) {//TODO: 10 derece dönüyormuþ
		Game.getInstance().shooter.rotate(direction);
	}
	
	
	public void stopRotateShooter() {
		Game.getInstance().shooter.stopRotate();
	}
	
	
	public void useBlender(int source, int goal) {
		Blender.useBlender(source, goal, Game.getInstance().shooter.inventory);
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
	
	
	public void getPowerUpOnBarrel(int type) {
		Game.getInstance().getPowerUpToBarrel(type);
	}
	
	
	public void saveGame() {
		System.out.println("saveGame GC");
	}
	
	
	public void loadGame(){ //TODO need to set settings - no need to set inventory
		System.out.println("loadGame GC");
		//uncomment the code below to get load work - an example set of settings
		//this.editInBuildMode(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, false, false, 80, 1);
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
