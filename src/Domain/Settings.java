package Domain;

public class Settings {
	
	private int lengthUnit;
	private int difficultyLevel;
	private boolean isLinear;
	private boolean isSpinning;
	private int atomNumber;
	private int moleculeNumber;
	private int powerUpNumber;
	private int reactionBlockerNumber;
	private float atomSpeed;
	
	
	public Settings(int atomNumber, int moleculeNumber, int reactionBlockerNumber, int powerUpNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel) {
		this.atomNumber = atomNumber;
		this.moleculeNumber = moleculeNumber;
		this.reactionBlockerNumber = reactionBlockerNumber;
		this.powerUpNumber = powerUpNumber;
		this.isLinear = isLinear;
		this.isSpinning = isSpinning;
		this.lengthUnit = lengthUnit;
		this.difficultyLevel = difficultyLevel;
	}
	
	
	public int getLengthUnit() {
		return lengthUnit;
	}
	
	
	public int getDifficultyLevel() {
		return difficultyLevel;
	}
	
	
	public boolean isLinear() {
		return isLinear;
	}
	
	
	public boolean isSpinning() {
		return isSpinning;
	}
	
	
	public int getAtomNumber() {
		return atomNumber;
	}
	
	
	public int getMoleculeNumber() {
		return moleculeNumber;
	}
	
	
	public int getPowerUpNumber() {
		return powerUpNumber;
	}
	
	
	public int getReactionBlockerNumber() {
		return reactionBlockerNumber;
	}
	
	
	/*public float getAtomSpeed() {
		return atomSpeed;
	}
	
	
	public void setAtomSpeed(float atomSpeed) {
		this.atomSpeed = atomSpeed;
	}*/
	
}
