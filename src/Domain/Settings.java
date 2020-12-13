package Domain;

public class Settings {
	
	private int lengthUnit;
	private int difficultyLevel;
	private boolean isLinear;
	private boolean isSpinning;
	
	private int alphaAtomNumber;
	private int betaAtomNumber;
	private int gammaAtomNumber;
	private int sigmaAtomNumber;
	
	
	private int alphaMoleculeNumber;
	private int betaMoleculeNumber;
	private int gammaMoleculeNumber;
	private int sigmaMoleculeNumber;
	
	private int alphaPowerUpNumber;
	private int betaPowerUpNumber;
	private int gammaPowerUpNumber;
	private int sigmaPowerUpNumber;
	
	private int alphaReactionBlockerNumber;
	private int betaReactionBlockerNumber;
	private int gammaReactionBlockerNumber;
	private int sigmaReactionBlockerNumber;
	
	private static Settings settings = null;
	public double timeRemaining = 600000; // TODO change the default value and set from setSettings

	
	private Settings() {}
	
	
	public static Settings getInstance() {
		if(settings == null) {
			settings = new Settings();
		}
		
		return settings;
	}
	
		
	public void setSettings(int atomNumber, int moleculeNumber, int reactionBlockerNumber, int powerUpNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel) {
		this.alphaAtomNumber = atomNumber;
		this.betaAtomNumber = atomNumber;
		this.gammaAtomNumber = atomNumber;
		this.sigmaAtomNumber = atomNumber;
		
		this.alphaMoleculeNumber = moleculeNumber;
		this.betaMoleculeNumber = moleculeNumber;
		this.gammaMoleculeNumber = moleculeNumber;
		this.sigmaMoleculeNumber = moleculeNumber;
		
		this.alphaReactionBlockerNumber = reactionBlockerNumber;
		this.betaReactionBlockerNumber = reactionBlockerNumber;
		this.gammaReactionBlockerNumber = reactionBlockerNumber;
		this.sigmaReactionBlockerNumber = reactionBlockerNumber;
		
		this.alphaPowerUpNumber = powerUpNumber;
		this.betaPowerUpNumber = powerUpNumber;
		this.gammaPowerUpNumber = powerUpNumber;
		this.sigmaPowerUpNumber = powerUpNumber;
		
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
	
	
	public void decreaseMoleculeNumber(int type) {
		switch(type) {
			case 1:
				this.alphaMoleculeNumber--;
				break;
			case 2:
				this.betaMoleculeNumber--;
				break;
			case 3:
				this.gammaMoleculeNumber--;
				break;
			case 4:
				this.sigmaMoleculeNumber--;
				break;
			default:
				break;
		}
	}
	
	
	public void decreasePowerUpNumber(int type) {
		switch(type) {
			case 1:
				this.alphaPowerUpNumber--;
				break;
			case 2:
				this.betaPowerUpNumber--;
				break;
			case 3:
				this.gammaPowerUpNumber--;
				break;
			case 4:
				this.sigmaPowerUpNumber--;
				break;
			default:
				break;
		}
	}
	
	
	public void decreaseReactionBlockerNumber(int type) {
		switch(type) {
			case 1:
				this.alphaReactionBlockerNumber--;
				break;
			case 2:
				this.betaReactionBlockerNumber--;
				break;
			case 3:
				this.gammaReactionBlockerNumber--;
				break;
			case 4:
				this.sigmaReactionBlockerNumber--;
				break;
			default:
				break;
		}
	}
	
	
	public int getAtomNumber(int type) {
		switch(type) {
			case 1:
				return this.alphaAtomNumber;
			case 2:
				return this.betaAtomNumber;
			case 3:
				return this.gammaAtomNumber;
			case 4:
				return this.sigmaAtomNumber;
			default:
				return 0;
		}
	}
	
	
	public int getMoleculeNumber(int type) {
		switch(type) {
			case 1:
				return this.alphaMoleculeNumber;
			case 2:
				return this.betaMoleculeNumber;
			case 3:
				return this.gammaMoleculeNumber;
			case 4:
				return this.sigmaMoleculeNumber;
			default:
				return 0;
		}
	}
	
	
	public int getPowerUpNumber(int type) {
		switch(type) {
			case 1:
				return this.alphaPowerUpNumber;
			case 2:
				return this.betaPowerUpNumber;
			case 3:
				return this.gammaPowerUpNumber;
			case 4:
				return this.sigmaPowerUpNumber;
			default:
				return 0;
		}
	}
	
	
	public int getReactionBlockerNumber(int type) {
		switch(type) {
			case 1:
				return this.alphaReactionBlockerNumber;
			case 2:
				return this.betaReactionBlockerNumber;
			case 3:
				return this.gammaReactionBlockerNumber;
			case 4:
				return this.sigmaReactionBlockerNumber;
			default:
				return 0;
		}
	}
}
