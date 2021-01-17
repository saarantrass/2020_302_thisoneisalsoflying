package Domain;

import java.awt.Dimension;

public class Settings {
	
	private String playerName;
	
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
	
	private int etaNumber;
	private int lotaNumber;
	private int thetaNumber;
	private int zetaNumber;
	
	private Dimension screenSize;
	
	public int timeRemaining;
	public static int timeMult = 20;
	
	private static Settings settings = null;
	
	private Settings() {}
	
	
	public static Settings getInstance() {
		if(settings == null) {
			settings = new Settings();
		}
		
		return settings;
	}
	
		
	public void setSettings(int aAtomNumber, int bAtomNumber, int gAtomNumber, int sAtomNumber, int aMoleculeNumber, int bMoleculeNumber, int gMoleculeNumber, int sMoleculeNumber, int aRBNumber, int bRBNumber, int gRBNumber, int sRBNumber, int aPUNumber, int bPUNumber, int gPUNumber, int sPUNumber, int etaNumber, int lotaNumber, int thetaNumber, int zetaNumber, boolean isLinear, boolean isSpinning, int lengthUnit, int difficultyLevel, Dimension screenSize, String playerName, int timeRemaining) {
		this.playerName = playerName;
		
		this.alphaAtomNumber = aAtomNumber;
		this.betaAtomNumber = bAtomNumber;
		this.gammaAtomNumber = gAtomNumber;
		this.sigmaAtomNumber = sAtomNumber;
		
		this.alphaMoleculeNumber = aMoleculeNumber;
		this.betaMoleculeNumber = bMoleculeNumber;
		this.gammaMoleculeNumber = gMoleculeNumber;
		this.sigmaMoleculeNumber = sMoleculeNumber;
		
		this.alphaReactionBlockerNumber = aRBNumber;
		this.betaReactionBlockerNumber = bRBNumber;
		this.gammaReactionBlockerNumber = gRBNumber;
		this.sigmaReactionBlockerNumber = sRBNumber;
		
		this.alphaPowerUpNumber = aPUNumber;
		this.betaPowerUpNumber = bPUNumber;
		this.gammaPowerUpNumber = gPUNumber;
		this.sigmaPowerUpNumber = sPUNumber;
		
		this.etaNumber = etaNumber;
		this.lotaNumber = lotaNumber;
		this.thetaNumber = thetaNumber;
		this.zetaNumber = zetaNumber;
		
		this.isLinear = isLinear;
		this.isSpinning = isSpinning;
		this.lengthUnit = lengthUnit;
		this.difficultyLevel = difficultyLevel;
		this.screenSize = screenSize;
		
		this.timeRemaining = timeRemaining;
	}
	
	
	public String getPlayerName() {
		return this.playerName;
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
	
	
	public Dimension getScreenSize() {
		return this.screenSize;
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
	
	
	public int getShieldNumber(int type) {
		switch(type) {
			case 1:
				return this.etaNumber;
			case 2:
				return this.lotaNumber;
			case 3:
				return this.thetaNumber;
			case 4:
				return this.zetaNumber;
			default:
				return 0;
		}
	}


	public boolean checkFallingObjectLeft() {
		if(this.alphaMoleculeNumber > 0 || this.betaMoleculeNumber > 0 || this.gammaMoleculeNumber > 0 || this.sigmaMoleculeNumber > 0 ||
				this.alphaPowerUpNumber > 0 || this.betaPowerUpNumber > 0 || this.gammaPowerUpNumber > 0 || this.sigmaPowerUpNumber > 0 ||
				this.alphaReactionBlockerNumber > 0 || this.betaReactionBlockerNumber > 0 || this.gammaReactionBlockerNumber > 0 || this.sigmaReactionBlockerNumber > 0)
			return true;
		return false;
	}
}
