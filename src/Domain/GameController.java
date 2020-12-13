package Domain;

public class GameController {
	public Settings settings;
	
	
	public GameController() {
		this.settings = Settings.getInstance();
	}
	
	
	public void useBlender(int source, int goal) {
		Blender.useBlender(source, goal, Game.getInstance().shooter.inventory);
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
}
