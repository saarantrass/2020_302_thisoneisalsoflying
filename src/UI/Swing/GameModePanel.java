package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Domain.Game;
import Domain.GameController;
import Domain.IObserver;
import UI.ImageDesigner;
import UI.ScreenCoordinator;


@SuppressWarnings("serial")
public class GameModePanel extends JPanel implements IObserver{
	
	private Game game;
	private GameController GC;
	
	private JPanel sidePanel = new JPanel(new GridBagLayout());
	private JPanel playerPanel = new JPanel(new GridBagLayout());
	private JPanel shieldPanel = new JPanel(new GridBagLayout());
	private JPanel powerUpPanel = new JPanel(new GridBagLayout());
	private JPanel atomPanel = new JPanel(new GridBagLayout());
	
	private JLabel scoreLabel = new JLabel("Score: ");
	private JLabel currentScoreField = new JLabel();
	private JLabel timeLabel = new JLabel("Time: ");
	private JLabel currentTimeField = new JLabel();
	private JLabel healthLabel = new JLabel("Health: ");
	private JLabel currentHealthLabel = new JLabel();
	private JLabel blenderLabel = new JLabel();
	
	private JButton etaShieldButton = new JButton("Eta Shield");
	private JLabel currentEtaShieldLabel = new JLabel();
	private JButton lotaShieldButton = new JButton("Lota Shield");
	private JLabel currentLotaShieldLabel = new JLabel();
	private JButton thetaShieldButton = new JButton("Theta Shield");
	private JLabel currentThetaShieldLabel = new JLabel();
	private JButton zetaShieldButton = new JButton("Zeta Shield");
	private JLabel currentZetaShieldLabel = new JLabel();
	
	private JLabel alphaPULabel = new JLabel();
	private JLabel currentAlphaPULabel = new JLabel("0");
	private JLabel betaPULabel = new JLabel();
	private JLabel currentBetaPULabel = new JLabel("0");
	private JLabel gammaPULabel = new JLabel();
	private JLabel currentGammaPULabel = new JLabel("0");
	private JLabel sigmaPULabel = new JLabel();
	private JLabel currentSigmaPULabel = new JLabel("0");
	
	private JLabel alphaAtomLabel = new JLabel();
	private JLabel currentAlphaAtomLabel = new JLabel();
	private JLabel betaAtomLabel = new JLabel();
	private JLabel currentBetaAtomLabel = new JLabel();
	private JLabel gammaAtomLabel = new JLabel();
	private JLabel currentGammaAtomLabel = new JLabel();
	private JLabel sigmaAtomLabel = new JLabel();
	private JLabel currentSigmaAtomLabel = new JLabel();
	
	private MainGamePanel mainGamePanel;
	private PausePanel pausePanel = new PausePanel();
	private GameOverPanel gameOverPanel =  new GameOverPanel();
	private QuitPanel quitPanel = new QuitPanel();
	
	public GameModePanel(GameController GC) {
		
		setFocusTraversalKeysEnabled(false);
		
		this.GC = GC;
		this.game = Game.getInstance();
		this.game.add(this);
		
		this.mainGamePanel = new MainGamePanel();
		
		this.setLayout(new BorderLayout());
		this.setSidePanelImages();
		this.setSidePanel();
		
		/*
		 * player panel design
		 */
		playerPanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		playerPanel.setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		playerPanel.add(scoreLabel, c);
		c.gridx = 1;
		playerPanel.add(currentScoreField, c);
		
		c.gridx = 0;
		c.gridy = 1;
		playerPanel.add(timeLabel, c);
		c.gridx = 1;
		playerPanel.add(currentTimeField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		playerPanel.add(healthLabel, c);
		c.gridx = 1;
		playerPanel.add(currentHealthLabel, c);
		
		/*
		 * powerUp panel design
		 */
		powerUpPanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		powerUpPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		alphaPULabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GC.getPowerUpToBarrel(1);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
		});
		powerUpPanel.add(alphaPULabel, c);
		c.gridx = 1;
		powerUpPanel.add(currentAlphaPULabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		betaPULabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GC.getPowerUpToBarrel(2);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
		});
		powerUpPanel.add(betaPULabel, c);
		c.gridx = 1;
		powerUpPanel.add(currentBetaPULabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		gammaPULabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GC.getPowerUpToBarrel(3);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
		});
		powerUpPanel.add(gammaPULabel, c);
		c.gridx = 1;
		powerUpPanel.add(currentGammaPULabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		sigmaPULabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GC.getPowerUpToBarrel(4);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
		});
		powerUpPanel.add(sigmaPULabel, c);
		c.gridx = 1;
		powerUpPanel.add(currentSigmaPULabel, c);
		
		/*
		 * shield panel design
		 */
		shieldPanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		shieldPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		etaShieldButton.addActionListener(shieldButtonListener);
		shieldPanel.add(etaShieldButton, c);
		c.gridx = 1;
		shieldPanel.add(currentEtaShieldLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		lotaShieldButton.addActionListener(shieldButtonListener);
		shieldPanel.add(lotaShieldButton, c);
		c.gridx = 1;
		shieldPanel.add(currentLotaShieldLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		thetaShieldButton.addActionListener(shieldButtonListener);
		shieldPanel.add(thetaShieldButton, c);
		c.gridx = 1;
		shieldPanel.add(currentThetaShieldLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		zetaShieldButton.addActionListener(shieldButtonListener);
		shieldPanel.add(zetaShieldButton, c);
		c.gridx = 1;
		shieldPanel.add(currentZetaShieldLabel, c);
		
		/*
		 * atom panel design
		 */
		atomPanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		atomPanel.setOpaque(false);
		
		c.gridx = 0;
		c.gridy = 0;
		atomPanel.add(blenderLabel);
		
		c.gridx = 0;
		c.gridy = 1;
		atomPanel.add(currentAlphaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(alphaAtomLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		atomPanel.add(currentBetaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(betaAtomLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		atomPanel.add(currentGammaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(gammaAtomLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		atomPanel.add(currentSigmaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(sigmaAtomLabel, c);
		
		/*
		 * side panel design
		 */
		Dimension screenSize = ScreenCoordinator.SCREEN_SIZE;
		
		sidePanel.setMaximumSize(new Dimension((int) (screenSize.getWidth() / 8), (int) screenSize.getHeight()));
		sidePanel.setMinimumSize(new Dimension((int) (screenSize.getWidth() / 8), (int) screenSize.getHeight()));
		sidePanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() / 8), (int) screenSize.getHeight()));
		sidePanel.setSize(new Dimension((int) (screenSize.getWidth() / 8), (int) screenSize.getHeight()));
		sidePanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		sidePanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		sidePanel.add(playerPanel, c);
		
		c.gridy = 1;
		sidePanel.add(powerUpPanel, c);
		
		c.gridy = 2;
		sidePanel.add(shieldPanel, c);
		
		c.gridy = 3;
		sidePanel.add(atomPanel, c);
		
		this.pausePanel.addKeyListener(pausePanelListener);
		this.pausePanel.addQuitButtonListener(quitButtonsListener);
		this.quitPanel.addQuitButtonListener(quitButtonsListener);
		this.gameOverPanel.addQuitButtonListener(quitButtonsListener);
		
		this.setOpaque(false);
		this.add(mainGamePanel);
		this.add(sidePanel, BorderLayout.LINE_END);
		this.setFocusable(true);
		this.addKeyListener(this.runningModeListener);
		
	}
	
	private void setSidePanel() {
		int integerPart = (int) Math.floor(this.game.getPlayerScore());
		int fractionalPart = (int) (Math.round(this.game.getPlayerScore() * 100) % 100);
		if(fractionalPart < 10)
			 this.currentScoreField.setText(integerPart + ".0" + fractionalPart);
		 else
			 this.currentScoreField.setText(integerPart + "." + fractionalPart);
		
		int minutes = (int) Math.floor((this.game.getRemainingTime() / 1000) / 60);
        int seconds = (int) Math.floor((this.game.getRemainingTime() / 1000) % 60);
        if(seconds < 10)
        	this.currentTimeField.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
        else
        	this.currentTimeField.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
        
        int currHealth = (int) Math.round(this.game.getPlayerHealth());
        if(currHealth <= 0)
        	this.currentHealthLabel.setText("0");
        else
        	this.currentHealthLabel.setText(Integer.toString(currHealth));
		
		this.currentEtaShieldLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryShieldCount(1)));
		this.currentLotaShieldLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryShieldCount(2)));
		this.currentThetaShieldLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryShieldCount(3)));
		this.currentZetaShieldLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryShieldCount(4)));
		
		this.currentAlphaPULabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryPowerUpCount(1)));
		this.currentBetaPULabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryPowerUpCount(2)));
		this.currentGammaPULabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryPowerUpCount(3)));
		this.currentSigmaPULabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryPowerUpCount(4)));
		
		this.currentAlphaAtomLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryAtomCount(1)));
		this.currentBetaAtomLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryAtomCount(2)));
		this.currentGammaAtomLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryAtomCount(3)));
		this.currentSigmaAtomLabel.setText(Integer.toString(this.game.getShooterInventory().getInventoryAtomCount(4)));
		
		this.validate();
		this.repaint();
	}

	private void setSidePanelImages() {
		try {
			Dimension screenSize = ScreenCoordinator.SCREEN_SIZE;
			
		    Image img = ImageIO.read(getClass().getResource("../../Images/powerups/+alpha-b.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    alphaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+beta-b.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    betaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+gamma-b.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    gammaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+sigma-b.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    sigmaPULabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/alpha.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    alphaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/beta.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    betaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/gamma.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    gammaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/sigma.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    sigmaAtomLabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/mixer.png"));
		    img = ImageDesigner.getResizedImage(img, (int) (img.getWidth(null) * screenSize.getHeight()/8000), (int) (img.getHeight(this) * screenSize.getHeight()/8000));
		    blenderLabel.setIcon(new ImageIcon(img));
			
		} catch (Exception ex) {
		    System.out.println(ex);
		}
	}
	
	
	public void displayPanel(JPanel panel) {
		ScreenCoordinator.getInstance().setCurrentPanel(panel);
		this.mainGamePanel.add(panel);
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
		panel.requestFocus();
	}
	
	
	public void removePanel(JPanel panel) {
		this.mainGamePanel.remove(panel);
		ScreenCoordinator.getInstance().gameScreen();
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
		ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
	}
	
	
	private KeyListener runningModeListener = new KeyAdapter() {
		int firstEventCode = 0; //initially 0 since there is no key event code 0
		int secondEventCode = 0; //initially 0 since there is no key event code 0
		int currentEventCode = 0; //initially 0 since there is no key event code 0
		
		@Override
		public void keyPressed(KeyEvent e) {
			currentEventCode = e.getKeyCode();
			
			if(firstEventCode == KeyEvent.VK_B) {
				if(secondEventCode == KeyEvent.VK_1 || secondEventCode == KeyEvent.VK_2 || secondEventCode == KeyEvent.VK_3 || secondEventCode == KeyEvent.VK_4) {
					if(currentEventCode == KeyEvent.VK_1 || currentEventCode == KeyEvent.VK_2 || currentEventCode == KeyEvent.VK_3 || currentEventCode == KeyEvent.VK_4) {
						GC.useBlender(secondEventCode - 48, currentEventCode - 48); //since event code of key 1 is 49 and it is ordered
					}
				}
			} else if(currentEventCode == KeyEvent.VK_UP) { //shoot atom/powerUp
				GC.shoot();
			} else if(currentEventCode == KeyEvent.VK_LEFT) { //move shooter left
				GC.moveShooter(0);
			} else if(currentEventCode == KeyEvent.VK_RIGHT) { //move shooter right
				GC.moveShooter(1);
			} else if(currentEventCode == KeyEvent.VK_A) { //rotate shooter left
				GC.rotateShooter(0);
			} else if(currentEventCode == KeyEvent.VK_D) { //rotate shooter right
				GC.rotateShooter(1);
			} else if(currentEventCode == KeyEvent.VK_C) { //change atom on barrel
				GC.changeAtomOnBarrel();
			} else if(currentEventCode == KeyEvent.VK_P) { //pause game
				GC.stopMoveShooter();
				GC.stopRotateShooter();
				GC.pauseGame();
				displayPanel(pausePanel);
			}
			
			firstEventCode = secondEventCode;
			secondEventCode = currentEventCode;
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				GC.stopMoveShooter();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
				GC.stopRotateShooter();
			}
		}

	};
	
	private KeyListener pausePanelListener = new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_R) {
				removePanel(pausePanel);
				GC.resumeGame();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_S) {
				GC.saveGame();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_L) {
				GC.loadGame();
				mainGamePanel.setShooter();
				mainGamePanel.setFactory();
				removePanel(pausePanel);
				GC.resumeGame();
			}
			
		}
		
	};
	
	private ActionListener shieldButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == etaShieldButton) {
				GC.addShield(1);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
			if(e.getSource() == lotaShieldButton) {
				GC.addShield(2);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
			if(e.getSource() == thetaShieldButton) {
				GC.addShield(3);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
			if(e.getSource() == zetaShieldButton) {
				GC.addShield(4);
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
		}
		
	};
	
	private ActionListener quitButtonsListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Quit Game")) {
				removePanel(pausePanel);
				displayPanel(quitPanel);
			}
			
			if(e.getActionCommand().equals("Save & Quit")) {
				GC.saveGame();
				GC.quitGame();
				removePanel(quitPanel);
				ScreenCoordinator.getInstance().initialize();
			}
			
			if(e.getActionCommand().equals("Quit")) {
				removePanel(quitPanel);
				removePanel(gameOverPanel);
				GC.quitGame();
				ScreenCoordinator.getInstance().initialize();
			}
			
			if(e.getActionCommand().equals("Back")) {
				removePanel(quitPanel);
				displayPanel(pausePanel);
			}
		}
		
	};
	
	@Override
	public void update() {
		if(this.game.isFinished()) {
			GC.stopMoveShooter();
			GC.stopRotateShooter();
			this.gameOverPanel.setScore(Integer.toString((int) this.game.getPlayerScore()));
			this.displayPanel(gameOverPanel);
		} else {
			this.setSidePanel();			
		}
	}
	
}
