package Domain;

public class GameController {
	public Settings settings;
	
	
	public GameController() {
		this.settings = Settings.getInstance();
	}
	
	
	public void useBlender(int source, int goal) {
		Blender.useBlender(source, goal, Game.getInstance().shooter.inventory);
	}
	
	
	public void editInBuildMode(int aAtomNumber, int bAtomNumber, int gAtomNumber, int sAtomNumber, int aMoleculeNumber, int bMoleculeNumber, int gMoleculeNumber, int sMoleculeNumber, int aRBNumber, int bRBNumber, int gRBNumber, int sRBNumber, int aPUNumber, int bPUNumber, int gPUNumber, int sPUNumber, int etaNumber, int lotaNumber, int thetaNumber, int zetaNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel) {
		this.settings.setSettings(aAtomNumber, bAtomNumber, gAtomNumber, sAtomNumber, aMoleculeNumber, bMoleculeNumber, gMoleculeNumber, sMoleculeNumber, aRBNumber, bRBNumber, gRBNumber, sRBNumber, aPUNumber, bPUNumber, gPUNumber, sPUNumber, etaNumber, lotaNumber, thetaNumber, zetaNumber, isLinear, isSpinning, lengthUnit, difficultyLevel);
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
	
	
	public void rotateShooter(int direction) {
		Game.getInstance().shooter.rotate(direction);
	}
	
	
	public void stopRotateShooter() {
		Game.getInstance().shooter.stopRotate();
	}
	
	
	public void addShield(int type) {
		//TODO
	}
	
	
	public void saveGame() {
		System.out.println("saveGame GC");
	}
	
	
	public void loadGame(){
		System.out.println("loadGame GC");
	}
	
	
	public void quitGame() {
		//TODO
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
}
