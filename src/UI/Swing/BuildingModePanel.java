package UI.Swing;

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

@SuppressWarnings("serial")
public class BuildingModePanel extends ModePanel{

	private GameController GC;
	
	private JLabel background = new Background();
	
	private JPanel settingsPanel = new JPanel(new GridBagLayout());
	private JPanel gameObjectNumbersPanel = new JPanel(new GridBagLayout());
	private JPanel alphaBetaStructurePanel = new JPanel(new GridBagLayout());
	private JPanel gameEssentialsPanel = new JPanel(new GridBagLayout());
	
	private JLabel atomNumberLabel = new JLabel("Atom number (for each type): ");
	private JLabel moleculeNumberLabel = new JLabel("Molecule number (for each type): ");
	private JLabel RBNumberLabel = new JLabel("Reaction blocker number (for each type): ");
	private JLabel PUNumberLabel = new JLabel("PowerUp number (for each type): ");
	private JLabel unitLengthLabel = new JLabel("Unit length L: ");
	private JLabel difficultyLevelLabel = new JLabel("Difficulty level:");
	
	private JTextField atomNumberField = new JTextField("100", 5);
	private JTextField moleculeNumberField = new JTextField("100", 5);
	private JTextField RBNumberField = new JTextField("10", 5);
	private JTextField PUNumberField = new JTextField("20", 5);
	private JTextField unitLengthField = new JTextField("10", 5);
	
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
		 * game object numbers panel design
		 */
		gameObjectNumbersPanel.setBorder(new TitledBorder("Game Object Numbers"));
		gameObjectNumbersPanel.setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		gameObjectNumbersPanel.add(atomNumberLabel, c);
		c.gridx = 1;
		gameObjectNumbersPanel.add(atomNumberField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		gameObjectNumbersPanel.add(moleculeNumberLabel, c);
		c.gridx = 1;
		gameObjectNumbersPanel.add(moleculeNumberField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		gameObjectNumbersPanel.add(RBNumberLabel, c);
		c.gridx = 1;
		gameObjectNumbersPanel.add(RBNumberField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		gameObjectNumbersPanel.add(PUNumberLabel, c);
		c.gridx = 1;
		gameObjectNumbersPanel.add(PUNumberField, c);
		
		/*
		 * alpha beta structure panel design
		 */
		alphaBetaStructurePanel.setBorder(new TitledBorder("Alpha Beta Molecule Structure"));
		alphaBetaStructurePanel.setOpaque(false);
		
		
		linearCheckBox.addItemListener(this.itemListener);
		
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
		c.gridx = 0;
		c.gridy = 0;
		settingsPanel.add(gameObjectNumbersPanel, c);
		
		c.gridy = 1;
		settingsPanel.add(alphaBetaStructurePanel, c);
		
		c.gridy = 2;
		settingsPanel.add(gameEssentialsPanel, c);
		
		startGameButton.addActionListener(this.actionListener);
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 3;
		settingsPanel.add(startGameButton, c);
		
		settingsPanel.setOpaque(false);
		
		background.add(settingsPanel);
		this.add(background);
	}
	
	
	public void setSettings() {
		int atomNumber = Integer.parseInt(atomNumberField.getText());
		int moleculeNumber = Integer.parseInt(moleculeNumberField.getText());
		int reactionBlockerNumber = Integer.parseInt(RBNumberField.getText());
		int powerUpNumber = Integer.parseInt(PUNumberField.getText());
		boolean isLinear = linearCheckBox.isSelected();
		
		boolean isSpinning = false;
		if(isLinear) {
			if(spinningButton.isSelected())
				isSpinning = true;
		}
		
		int L = Integer.parseInt(unitLengthField.getText());
		
		int difficultyLevel = 1;
		if(mediumButton.isSelected())
			difficultyLevel = 2;
		else if(hardButton.isSelected())
			difficultyLevel = 3;
		
		GC.editInBuildMode(atomNumber, moleculeNumber, reactionBlockerNumber, powerUpNumber, isLinear, isSpinning, L, difficultyLevel);
	}
	
	
	private ItemListener itemListener = new ItemListener() {

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
	
	
	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startGameButton) {
				setSettings();
				ScreenCoordinator.getInstance().startGame();
			}
			ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
		}
		
	};


	@Override
	public void removeListeners() {
		linearCheckBox.removeItemListener(this.itemListener);
		startGameButton.removeActionListener(this.actionListener);
		this.setFocusable(false);
	}

}
