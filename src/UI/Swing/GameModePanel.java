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
import Domain.Settings;
import UI.IObserver;
import UI.ImageResizer;
import UI.GameObjectImages.GameObjectImageFactory;


@SuppressWarnings("serial")
public class GameModePanel extends JPanel implements IObserver{
	
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
	
	private MainGamePanel mainGamePanel = MainGamePanel.getInstance();
	private PausePanel pausePanel = new PausePanel();
	private GameOverPanel gameOverPanel =  new GameOverPanel();
	private QuitPanel quitPanel;
	
	public GameModePanel(GameController GC) {
		
		setFocusTraversalKeysEnabled(false);
		
		this.GC = GC;
		Game.getInstance().add(this);
		
		this.setLayout(new BorderLayout());
		this.setSidePanelImages();
		this.setSidePanel();
		
		quitPanel = new QuitPanel(quitPanelButtonsListener);
		
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
				GC.getPowerUpOnBarrel(1);
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
				GC.getPowerUpOnBarrel(2);
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
				GC.getPowerUpOnBarrel(3);
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
				GC.getPowerUpOnBarrel(4);
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
		
		this.setOpaque(false);
		this.add(mainGamePanel);
		this.add(sidePanel, BorderLayout.LINE_END);
		this.setFocusable(true);
		this.addKeyListener(this.runningModeListener);
		
		GameObjectImageFactory.getInstance().setLengthUnit(GC.settings.getLengthUnit());
		this.mainGamePanel.initialize();
	}
	
	private void setSidePanel() { //TODO GET ALL FROM SETTİNGS
		this.currentScoreField.setText(Double.toString(Game.getInstance().player.getScore()));
		
		int minutes = (int) Math.floor((Settings.getInstance().timeRemaining / 1000) / 60);
        int seconds = (int) Math.floor((Settings.getInstance().timeRemaining / 1000) % 60);
        if(seconds < 10) {
        	this.currentTimeField.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
        } else {
        	this.currentTimeField.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
        }
		this.currentHealthLabel.setText(Double.toString(Game.getInstance().player.getHealth()));
		
		this.currentEtaShieldLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryShieldCount(1)));
		this.currentLotaShieldLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryShieldCount(2)));
		this.currentThetaShieldLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryShieldCount(3)));
		this.currentZetaShieldLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryShieldCount(4)));
		
		this.currentAlphaPULabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryPowerUpCount(1)));
		this.currentBetaPULabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryPowerUpCount(2)));
		this.currentGammaPULabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryPowerUpCount(3)));
		this.currentSigmaPULabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryPowerUpCount(4)));
		
		this.currentAlphaAtomLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryAtomCount(1)));
		this.currentBetaAtomLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryAtomCount(2)));
		this.currentGammaAtomLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryAtomCount(3)));
		this.currentSigmaAtomLabel.setText(Integer.toString(Game.getInstance().shooter.inventory.getInventoryAtomCount(4)));
		
		this.validate();
		this.repaint();
	}

	private void setSidePanelImages() {
		try {
			Dimension screenSize = ScreenCoordinator.SCREEN_SIZE;
			
		    Image img = ImageIO.read(getClass().getResource("../../Images/powerups/+alpha-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    alphaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+beta-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    betaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+gamma-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    gammaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+sigma-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/2000), (int) (img.getHeight(this) * screenSize.getHeight()/2000));
		    sigmaPULabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/alpha.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    alphaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/beta.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    betaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/gamma.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    gammaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/sigma.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1300), (int) (img.getHeight(this) * screenSize.getHeight()/1300));
		    sigmaAtomLabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/mixer.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/8000), (int) (img.getHeight(this) * screenSize.getHeight()/8000));
		    blenderLabel.setIcon(new ImageIcon(img));
			
		} catch (Exception ex) {
		    System.out.println(ex);
		}
	}
	
	public void displayPausePanel() {
		ScreenCoordinator.getInstance().setCurrentPanel(pausePanel);
		pausePanel.addKeyListener(pausePanelListener);
		pausePanel.addQuitButtonListener(quitButtonListener);
		this.mainGamePanel.add(pausePanel);
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
	}
	
	public void removePausePanel() {
		pausePanel.removeKeyListener(pausePanelListener);
		pausePanel.removeQuitButtonListener(quitButtonListener);
		this.mainGamePanel.remove(pausePanel);
		ScreenCoordinator.getInstance().gameScreen();
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
	}
	
	public void displayQuitPanel() {
		ScreenCoordinator.getInstance().setCurrentPanel(quitPanel);
		this.mainGamePanel.add(quitPanel);
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
	}
	
	public void removeQuitPanel() {
		this.mainGamePanel.remove(quitPanel);
		ScreenCoordinator.getInstance().gameScreen();
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
	}
	
	public void displayGameOverPanel() {
		ScreenCoordinator.getInstance().setCurrentPanel(gameOverPanel);
		this.mainGamePanel.add(gameOverPanel);
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
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
				GC.pauseGame();
				displayPausePanel();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
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
				removePausePanel();
				GC.resumeGame();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_S) {
				GC.saveGame();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_L) { //TODO
				GC.loadGame();
				
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
	
	private ActionListener quitButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Quit Game")) {
				removePausePanel();
				displayQuitPanel();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
		}
		
	};
	
	private ActionListener quitPanelButtonsListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Save & Quit")) { //TODO
				removeQuitPanel();
				GC.saveGame();
				GC.quitGame();
				ScreenCoordinator.getInstance().initialize();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
			if(e.getActionCommand().equals("Quit")) { //TODO
				removeQuitPanel();
				GC.quitGame();
				ScreenCoordinator.getInstance().initialize();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
			if(e.getActionCommand().equals("Back")) {
				removeQuitPanel();
				displayPausePanel();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
		}
		
	};
	
	
	@Override
	public void update() {
		if(Game.getInstance().isFinished) {
			this.displayGameOverPanel();
			this.gameOverPanel.requestFocus();
		}
		this.setSidePanel();
	}
	
}
