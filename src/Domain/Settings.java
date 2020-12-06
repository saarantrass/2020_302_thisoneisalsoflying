package Domain;

public class Settings {
	
	private double lengthUnit;
	private int difficultyLevel;
	private boolean isLinear;
	private boolean isSpinning;
	private int atomNumber;
	private int moleculeNumber;
	private int powerUpNumber;
	private int reactionBlockerNumber;
	private float atomSpeed;
	
	public double getLengthUnit() {
		return lengthUnit;
	}
	public void setLengthUnit(double lengthUnit) {
		this.lengthUnit = lengthUnit;
	}
	public int getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public boolean isLinear() {
		return isLinear;
	}
	public void setLinear(boolean isLinear) {
		this.isLinear = isLinear;
	}
	public boolean isSpinning() {
		return isSpinning;
	}
	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}
	public int getAtomNumber() {
		return atomNumber;
	}
	public void setAtomNumber(int atomNumber) {
		this.atomNumber = atomNumber;
	}
	public int getMoleculeNumber() {
		return moleculeNumber;
	}
	public void setMoleculeNumber(int moleculeNumber) {
		this.moleculeNumber = moleculeNumber;
	}
	public int getPowerUpNumber() {
		return powerUpNumber;
	}
	public void setPowerUpNumber(int powerUpNumber) {
		this.powerUpNumber = powerUpNumber;
	}
	public int getReactionBlockerNumber() {
		return reactionBlockerNumber;
	}
	public void setReactionBlockerNumber(int reactionBlockerNumber) {
		this.reactionBlockerNumber = reactionBlockerNumber;
	}
	public float getAtomSpeed() {
		return atomSpeed;
	}
	public void setAtomSpeed(float atomSpeed) {
		this.atomSpeed = atomSpeed;
	}

}
