package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
	private boolean isPaused = false;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	private JLabel background  = new Background();
	
	private JPanel sidePanel = new JPanel(new GridBagLayout());
	private JPanel playerPanel = new JPanel(new GridBagLayout());
	private JPanel powerUpPanel = new JPanel(new GridBagLayout());
	private JPanel atomPanel = new JPanel(new GridBagLayout());
	
	private JLabel scoreLabel = new JLabel("Score: ");
	private JLabel currentScoreField = new JLabel();
	private JLabel timeLabel = new JLabel("Time: ");
	private JLabel currentTimeField = new JLabel();
	private JLabel healthLabel = new JLabel("Health: ");
	private JLabel currentHealthLabel = new JLabel();
	private JLabel blenderLabel = new JLabel();
	
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
	
	public GameModePanel(GameController GC) {
		
		setFocusTraversalKeysEnabled(false);
		
		this.GC = GC;
		Game.getInstance().add(this);
		
		this.setLayout(new GridBagLayout());
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
				if(!isPaused) {
					GC.getPowerUpOnBarrel(1);
					ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
				} else {
					pausePanel.requestFocus();
				}
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
				if(!isPaused) {
					GC.getPowerUpOnBarrel(2);
					ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
				} else {
					pausePanel.requestFocus();
				}
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
				if(!isPaused) {
					GC.getPowerUpOnBarrel(3);
					ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
				} else {
					pausePanel.requestFocus();
				}
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
				if(!isPaused) {
					GC.getPowerUpOnBarrel(4);
					ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
				} else {
					pausePanel.requestFocus();
				}
			}
		});
		powerUpPanel.add(sigmaPULabel, c);
		c.gridx = 1;
		powerUpPanel.add(currentSigmaPULabel, c);
		
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
		sidePanel.setMaximumSize((new Dimension((int) (this.background.getWidth() / 8), this.background.getHeight())));
		sidePanel.setMinimumSize((new Dimension((int) (this.background.getWidth() / 8), this.background.getHeight())));
		sidePanel.setPreferredSize((new Dimension((int) (this.background.getWidth() / 8), this.background.getHeight())));
		sidePanel.setSize((new Dimension((int) (this.background.getWidth() / 8), this.background.getHeight())));
		sidePanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		sidePanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		sidePanel.setLayout(new GridBagLayout());
		
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
		sidePanel.add(atomPanel, c);
		
		background.add(mainGamePanel);
		background.add(sidePanel, BorderLayout.LINE_END);
		
		this.add(background);
		this.setFocusable(true);
		this.addKeyListener(this.runningModeListener);
		
		GameObjectImageFactory.getInstance().setLengthUnit(GC.settings.getLengthUnit());
		this.mainGamePanel.initialize();
	}
	
	private void setSidePanel() {
		this.currentScoreField.setText(Double.toString(Game.getInstance().shooter.score));
		this.currentTimeField.setText(df2.format(Settings.getInstance().timeRemaining / 60000));
		this.currentHealthLabel.setText(Double.toString(Game.getInstance().shooter.health));
		
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
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1700), (int) (img.getHeight(this) * screenSize.getHeight()/1700));
		    alphaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+beta-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1700), (int) (img.getHeight(this) * screenSize.getHeight()/1700));
		    betaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+gamma-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1700), (int) (img.getHeight(this) * screenSize.getHeight()/1700));
		    gammaPULabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+sigma-b.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1700), (int) (img.getHeight(this) * screenSize.getHeight()/1700));
		    sigmaPULabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/alpha.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1000), (int) (img.getHeight(this) * screenSize.getHeight()/1000));
		    alphaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/beta.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1000), (int) (img.getHeight(this) * screenSize.getHeight()/1000));
		    betaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/gamma.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1000), (int) (img.getHeight(this) * screenSize.getHeight()/1000));
		    gammaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/sigma.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/1000), (int) (img.getHeight(this) * screenSize.getHeight()/1000));
		    sigmaAtomLabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/mixer.png"));
		    img = ImageResizer.getResizedImage(img, (int) (img.getWidth(this) * screenSize.getHeight()/6000), (int) (img.getHeight(this) * screenSize.getHeight()/6000));
		    blenderLabel.setIcon(new ImageIcon(img));
			
		} catch (Exception ex) {
		    System.out.println(ex);
		}
	}
	
	public void displayPausePanel() {
		pausePanel.addKeyListener(pausePanelListener);
		this.mainGamePanel.add(pausePanel);
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
	}
	
	public void removePausePanel() {
		pausePanel.removeKeyListener(pausePanelListener);
		this.mainGamePanel.remove(pausePanel);
		this.mainGamePanel.validate();
		this.mainGamePanel.repaint();
	}
	
	public void displayGameOverPanel() {
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
				pausePanel.requestFocus();
				isPaused = true;
			}
			
			firstEventCode = secondEventCode;
			secondEventCode = currentEventCode;
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			//System.out.println("KEY RELEASED: " + e.getKeyCode());
			//System.out.println(KeyEvent.VK_LEFT);
			
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
				GC.resumeGame();
				removePausePanel();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
				isPaused = false;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_S) {
				//save game
			}
			
			if(e.getKeyCode() == KeyEvent.VK_L) {
				//load game
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
