package Classes;

public class Settings {
	private float lengthUnit;
	private int difficultyLevel;
	private boolean isLinear;
	private boolean isSpinning;
	private int atomNumber;
	private int moleculesNumber;
	private int powerUpNumber;
	private int reactionBlockerNumber;
	private float atomSpeed;
	
	public Settings(float lengthUnit, int difficultyLevel, boolean isLinear, boolean isSpinning, int atomNumber,
			int moleculesNumber, int powerUpNumber, int reactionBlockerNumber, float atomSpeed) {
		super();
		this.lengthUnit = lengthUnit;
		this.difficultyLevel = difficultyLevel;
		this.isLinear = isLinear;
		this.isSpinning = isSpinning;
		this.atomNumber = atomNumber;
		this.moleculesNumber = moleculesNumber;
		this.powerUpNumber = powerUpNumber;
		this.reactionBlockerNumber = reactionBlockerNumber;
		this.atomSpeed = atomSpeed;
	}
	
	
}
