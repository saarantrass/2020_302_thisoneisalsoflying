package UI.Swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Domain.GameController;
import UI.ScreenCoordinator;

@SuppressWarnings("serial")
public class BuildingModePanel extends JPanel{

	private GameController GC;
	
	private JPanel settingsPanel = new JPanel(new GridBagLayout());
	private JPanel playerPanel = new JPanel(new GridBagLayout());
	private JPanel atomNumbersPanel = new JPanel(new GridBagLayout());
	private JPanel moleculeNumbersPanel = new JPanel(new GridBagLayout());
	private JPanel RBNumbersPanel = new JPanel(new GridBagLayout());
	private JPanel PUNumbersPanel = new JPanel(new GridBagLayout());
	private JPanel shieldNumbersPanel = new JPanel(new GridBagLayout());
	private JPanel alphaBetaStructurePanel = new JPanel(new GridBagLayout());
	private JPanel gameEssentialsPanel = new JPanel(new GridBagLayout());
	
	private JLabel alphaAtomNumberLabel = new JLabel("Alpha Atom number: ");
	private JLabel betaAtomNumberLabel = new JLabel("Beta Atom number: ");
	private JLabel gammaAtomNumberLabel = new JLabel("Gamma Atom number: ");
	private JLabel sigmaAtomNumberLabel = new JLabel("Sigma Atom number: ");
	
	private JLabel alphaMoleculeNumberLabel = new JLabel("Alpha- Molecule number: ");
	private JLabel betaMoleculeNumberLabel = new JLabel("Beta- Molecule number: ");
	private JLabel gammaMoleculeNumberLabel = new JLabel("Gamma- Molecule number: ");
	private JLabel sigmaMoleculeNumberLabel = new JLabel("Sigma- Molecule number: ");
	
	private JLabel alphaRBNumberLabel = new JLabel("Alpha-b Reaction Blocker number: ");
	private JLabel betaRBNumberLabel = new JLabel("Beta-b Reaction Blocker number: ");
	private JLabel gammaRBNumberLabel = new JLabel("Gamma-b Reaction Blocker number: ");
	private JLabel sigmaRBNumberLabel = new JLabel("Sigma-b Reaction Blocker number: ");
	
	private JLabel alphaPUNumberLabel = new JLabel("+Alpha-b PowerUp number: ");
	private JLabel betaPUNumberLabel = new JLabel("+Beta-b PowerUp number: ");
	private JLabel gammaPUNumberLabel = new JLabel("+Gamma-b PowerUp number: ");
	private JLabel sigmaPUNumberLabel = new JLabel("+Sigma-b PowerUp number: ");
	
	private JLabel etaNumberLabel = new JLabel("Eta Shield number: ");
	private JLabel lotaNumberLabel = new JLabel("Lota Shield number: ");
	private JLabel thetaNumberLabel = new JLabel("Theta Shield number: ");
	private JLabel zetaNumberLabel = new JLabel("Zeta Shield number: ");
	
	private JLabel playerNameLabel = new JLabel("Player Name: ");
	private JLabel unitLengthLabel = new JLabel("Unit length L: ");
	private JLabel difficultyLevelLabel = new JLabel("Difficulty level:");
	
	private JTextField alphaAtomNumberField = new JTextField("100", 5);
	private JTextField betaAtomNumberField = new JTextField("100", 5);
	private JTextField gammaAtomNumberField = new JTextField("100", 5);
	private JTextField sigmaAtomNumberField = new JTextField("100", 5);
	
	private JTextField alphaMoleculeNumberField = new JTextField("100", 5);
	private JTextField betaMoleculeNumberField = new JTextField("100", 5);
	private JTextField gammaMoleculeNumberField = new JTextField("100", 5);
	private JTextField sigmaMoleculeNumberField = new JTextField("100", 5);
	
	private JTextField alphaRBNumberField = new JTextField("10", 5);
	private JTextField betaRBNumberField = new JTextField("10", 5);
	private JTextField gammaRBNumberField = new JTextField("10", 5);
	private JTextField sigmaRBNumberField = new JTextField("10", 5);
	
	private JTextField alphaPUNumberField = new JTextField("20", 5);
	private JTextField betaPUNumberField = new JTextField("20", 5);
	private JTextField gammaPUNumberField = new JTextField("20", 5);
	private JTextField sigmaPUNumberField = new JTextField("20", 5);
	
	private JTextField etaNumberField = new JTextField("50", 5);
	private JTextField lotaNumberField = new JTextField("50", 5);
	private JTextField thetaNumberField = new JTextField("50", 5);
	private JTextField zetaNumberField = new JTextField("50", 5);
	
	private JTextField playerNameField = new JTextField(15);
	private JTextField unitLengthField = new JTextField(5);
	
	private JCheckBox linearCheckBox = new JCheckBox("Linear");
	
	private JRadioButton easyButton = new JRadioButton("Easy");
	private JRadioButton mediumButton = new JRadioButton("Medium");
	private JRadioButton hardButton = new JRadioButton("Hard");
	private JRadioButton stationaryButton = new JRadioButton("Stationary");
	private JRadioButton spinningButton = new JRadioButton("Spinning");
	
	private JButton startGameButton = new JButton("Start Game");

	private ButtonGroup statSpinGroup = new ButtonGroup();
	private ButtonGroup difficultyLevelGroup = new ButtonGroup();
	
	
	public BuildingModePanel(GameController GC) {
		
		this.GC = GC;
		
		this.setLayout(new GridBagLayout());
		
		/*
		 * atom numbers panel design
		 */
		atomNumbersPanel.setBorder(new TitledBorder("Atom Numbers"));
		atomNumbersPanel.setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		atomNumbersPanel.add(alphaAtomNumberLabel, c);
		c.gridx = 1;
		atomNumbersPanel.add(alphaAtomNumberField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		atomNumbersPanel.add(betaAtomNumberLabel, c);
		c.gridx = 1;
		atomNumbersPanel.add(betaAtomNumberField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		atomNumbersPanel.add(gammaAtomNumberLabel, c);
		c.gridx = 1;
		atomNumbersPanel.add(gammaAtomNumberField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		atomNumbersPanel.add(sigmaAtomNumberLabel, c);
		c.gridx = 1;
		atomNumbersPanel.add(sigmaAtomNumberField, c);
		
		/*
		 * molecule numbers panel design
		 */
		moleculeNumbersPanel.setBorder(new TitledBorder("Molecule Numbers"));
		moleculeNumbersPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		moleculeNumbersPanel.add(alphaMoleculeNumberLabel, c);
		c.gridx = 1;
		moleculeNumbersPanel.add(alphaMoleculeNumberField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		moleculeNumbersPanel.add(betaMoleculeNumberLabel, c);
		c.gridx = 1;
		moleculeNumbersPanel.add(betaMoleculeNumberField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		moleculeNumbersPanel.add(gammaMoleculeNumberLabel, c);
		c.gridx = 1;
		moleculeNumbersPanel.add(gammaMoleculeNumberField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		moleculeNumbersPanel.add(sigmaMoleculeNumberLabel, c);
		c.gridx = 1;
		moleculeNumbersPanel.add(sigmaMoleculeNumberField, c);
		
		/*
		 * reaction blocker numbers panel design
		 */
		RBNumbersPanel.setBorder(new TitledBorder("Reaction Blocker Numbers"));
		RBNumbersPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		RBNumbersPanel.add(alphaRBNumberLabel, c);
		c.gridx = 1;
		RBNumbersPanel.add(alphaRBNumberField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		RBNumbersPanel.add(betaRBNumberLabel, c);
		c.gridx = 1;
		RBNumbersPanel.add(betaRBNumberField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		RBNumbersPanel.add(gammaRBNumberLabel, c);
		c.gridx = 1;
		RBNumbersPanel.add(gammaRBNumberField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		RBNumbersPanel.add(sigmaRBNumberLabel, c);
		c.gridx = 1;
		RBNumbersPanel.add(sigmaRBNumberField, c);
		
		/*
		 * power-up numbers panel design
		 */
		PUNumbersPanel.setBorder(new TitledBorder("PowerUp Numbers"));
		PUNumbersPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		PUNumbersPanel.add(alphaPUNumberLabel, c);
		c.gridx = 1;
		PUNumbersPanel.add(alphaPUNumberField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		PUNumbersPanel.add(betaPUNumberLabel, c);
		c.gridx = 1;
		PUNumbersPanel.add(betaPUNumberField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		PUNumbersPanel.add(gammaPUNumberLabel, c);
		c.gridx = 1;
		PUNumbersPanel.add(gammaPUNumberField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		PUNumbersPanel.add(sigmaPUNumberLabel, c);
		c.gridx = 1;
		PUNumbersPanel.add(sigmaPUNumberField, c);
		
		/*
		 * shield numbers panel design
		 */
		shieldNumbersPanel.setBorder(new TitledBorder("Shield Numbers"));
		shieldNumbersPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		shieldNumbersPanel.add(etaNumberLabel, c);
		c.gridx = 1;
		shieldNumbersPanel.add(etaNumberField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		shieldNumbersPanel.add(lotaNumberLabel, c);
		c.gridx = 1;
		shieldNumbersPanel.add(lotaNumberField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		shieldNumbersPanel.add(thetaNumberLabel, c);
		c.gridx = 1;
		shieldNumbersPanel.add(thetaNumberField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		shieldNumbersPanel.add(zetaNumberLabel, c);
		c.gridx = 1;
		shieldNumbersPanel.add(zetaNumberField, c);
		
		/*
		 * alpha beta structure panel design
		 */
		alphaBetaStructurePanel.setBorder(new TitledBorder("Alpha Beta Molecule Structure"));
		alphaBetaStructurePanel.setOpaque(false);
		
		
		linearCheckBox.addItemListener(this.checkBoxListener);
		
		statSpinGroup.add(stationaryButton);
		statSpinGroup.add(spinningButton);
		stationaryButton.setSelected(true);
		
		c.gridx = 0;
		c.gridy = 0;
		alphaBetaStructurePanel.add(linearCheckBox, c);
		
		c.gridy = 1;
		alphaBetaStructurePanel.add(stationaryButton, c);
		c.gridx = 1;
		alphaBetaStructurePanel.add(spinningButton, c);
		
		stationaryButton.setVisible(false);
		spinningButton.setVisible(false);
		
		/*
		 * game essentials panel design
		 */
		gameEssentialsPanel.setBorder(new EtchedBorder());
		gameEssentialsPanel.setOpaque(false);
		
		difficultyLevelGroup.add(easyButton);
		difficultyLevelGroup.add(mediumButton);
		difficultyLevelGroup.add(hardButton);
		easyButton.setSelected(true);
		
		unitLengthField.setText(Integer.toString((int)(ScreenCoordinator.SCREEN_SIZE.getHeight() / 10)));
		c.gridx = 0;
		c.gridy = 0;
		gameEssentialsPanel.add(unitLengthLabel, c);
		c.gridx = 1;
		gameEssentialsPanel.add(unitLengthField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		gameEssentialsPanel.add(difficultyLevelLabel, c);
		c.gridx = 1;
		gameEssentialsPanel.add(easyButton, c);
		c.gridx = 2;
		gameEssentialsPanel.add(mediumButton, c);
		c.gridx = 3;
		gameEssentialsPanel.add(hardButton, c);
		
		/*
		 * settings panel design
		 */
		JPanel firstLinePanel = new JPanel(new GridBagLayout());
		JPanel secondLinePanel = new JPanel(new GridBagLayout());
		firstLinePanel.setOpaque(false);
		secondLinePanel.setOpaque(false);
		playerPanel.setOpaque(false);
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		playerPanel.add(playerNameLabel, c);
		
		c.gridx = 1;
		playerPanel.add(playerNameField, c);
		
		c.gridx = 0;
		c.gridy = 0;
		firstLinePanel.add(atomNumbersPanel, c);
		
		c.gridx = 1;
		firstLinePanel.add(moleculeNumbersPanel, c);
		
		c.gridx = 2;
		firstLinePanel.add(RBNumbersPanel, c);
		
		c.gridx = 0;
		c.gridy = 0;
		secondLinePanel.add(PUNumbersPanel, c);
		
		c.gridx = 1;
		secondLinePanel.add(shieldNumbersPanel, c);
		
		c.gridx = 0;
		c.gridy = 0;
		settingsPanel.add(playerPanel);
		
		c.gridy = 1;
		settingsPanel.add(firstLinePanel, c);
		
		c.gridy = 2;
		settingsPanel.add(secondLinePanel, c);
		
		c.gridy = 3;
		settingsPanel.add(alphaBetaStructurePanel, c);
		
		c.gridy = 4;
		settingsPanel.add(gameEssentialsPanel, c);
		
		startGameButton.addActionListener(this.buttonListener);
		
		c.gridy = 5;
		settingsPanel.add(startGameButton, c);
		
		settingsPanel.setOpaque(false);
		
		this.setOpaque(false);
		this.add(settingsPanel);
	}
	
	
	public boolean setSettings() {
		try {
			String playerName = playerNameField.getText();
			
			int alphaAtomNumber = Integer.parseInt(alphaAtomNumberField.getText());
			int betaAtomNumber = Integer.parseInt(betaAtomNumberField.getText());
			int gammaAtomNumber = Integer.parseInt(gammaAtomNumberField.getText());
			int sigmaAtomNumber = Integer.parseInt(sigmaAtomNumberField.getText());
			
			int alphaMoleculeNumber = Integer.parseInt(alphaMoleculeNumberField.getText());
			int betaMoleculeNumber = Integer.parseInt(betaMoleculeNumberField.getText());
			int gammaMoleculeNumber = Integer.parseInt(gammaMoleculeNumberField.getText());
			int sigmaMoleculeNumber = Integer.parseInt(sigmaMoleculeNumberField.getText());
			
			int alphaRBNumber = Integer.parseInt(alphaRBNumberField.getText());
			int betaRBNumber = Integer.parseInt(betaRBNumberField.getText());
			int gammaRBNumber = Integer.parseInt(gammaRBNumberField.getText());
			int sigmaRBNumber = Integer.parseInt(sigmaRBNumberField.getText());
			
			int alphaPUNumber = Integer.parseInt(alphaPUNumberField.getText());
			int betaPUNumber = Integer.parseInt(betaPUNumberField.getText());
			int gammaPUNumber = Integer.parseInt(gammaPUNumberField.getText());
			int sigmaPUNumber = Integer.parseInt(sigmaPUNumberField.getText());
			
			int etaNumber = Integer.parseInt(etaNumberField.getText());
			int lotaNumber = Integer.parseInt(lotaNumberField.getText());
			int thetaNumber = Integer.parseInt(thetaNumberField.getText());
			int zetaNumber = Integer.parseInt(zetaNumberField.getText());
			
			boolean isLinear = linearCheckBox.isSelected();
			
			boolean isSpinning = false;
			if(isLinear) {
				isSpinning = spinningButton.isSelected();
			}
			
			int L = Integer.parseInt(unitLengthField.getText());
			
			int difficultyLevel = 1;
			if(mediumButton.isSelected())
				difficultyLevel = 2;
			else if(hardButton.isSelected())
				difficultyLevel = 4;
			
			Dimension screenSize = ScreenCoordinator.SCREEN_SIZE;
			
			if(playerName.equals("")) {
				ScreenCoordinator.getInstance().displayError("Enter player name to start");
				return false;
			} else if(alphaAtomNumber < 10 || betaAtomNumber < 10 || 
				gammaAtomNumber < 10 || sigmaAtomNumber < 10 || alphaMoleculeNumber < 10 || 
				betaMoleculeNumber < 10 || gammaMoleculeNumber < 10 || sigmaMoleculeNumber < 10) {
				ScreenCoordinator.getInstance().displayError("Atom and molecule numbers must be greater than 10 for each type");
				return false;
			} else if(L < 50){
				ScreenCoordinator.getInstance().displayError("Length unit L must be greater than 50");
				return false;
			} else {
				GC.editInBuildMode(alphaAtomNumber, betaAtomNumber, gammaAtomNumber, sigmaAtomNumber, alphaMoleculeNumber, betaMoleculeNumber, gammaMoleculeNumber, sigmaMoleculeNumber, alphaRBNumber, betaRBNumber, gammaRBNumber, sigmaRBNumber, alphaPUNumber, betaPUNumber, gammaPUNumber, sigmaPUNumber, etaNumber, lotaNumber, thetaNumber, zetaNumber, isLinear, isSpinning, L, difficultyLevel, screenSize, playerName);
				return true;		
			}
		} catch(NumberFormatException e) {
			ScreenCoordinator.getInstance().displayError("Please enter a valid number");
			return false;
		}
	}
	
	
	private ItemListener checkBoxListener = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == linearCheckBox) {
				
				if(linearCheckBox.isSelected()) {
					stationaryButton.setVisible(true);
					spinningButton.setVisible(true);
					
				} else {
					stationaryButton.setVisible(false);
					spinningButton.setVisible(false);
					
				}
			}
		}
		
	};
	
	
	private ActionListener buttonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startGameButton) {
				if(setSettings())
					ScreenCoordinator.getInstance().startGame();
			}
			ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
		}
		
	};

}
