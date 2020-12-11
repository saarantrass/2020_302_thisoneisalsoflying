package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import Domain.GameController;
import UI.ImageResizer;


@SuppressWarnings("serial")
public class GameModePanel extends JPanel {
	
	private GameController GC;
	private boolean isPaused = false;
	
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
	
	private JButton alphaPUButton = new JButton();
	private JLabel currentAlphaPULabel = new JLabel("0");
	private JButton betaPUButton = new JButton();
	private JLabel currentBetaPULabel = new JLabel("0");
	private JButton gammaPUButton = new JButton();
	private JLabel currentGammaPULabel = new JLabel("0");
	private JButton sigmaPUButton = new JButton();
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
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public GameModePanel(GameController GC) {
		
		setFocusTraversalKeysEnabled(false);
		
		this.GC = GC;
		
		this.setLayout(new GridBagLayout());
		this.setSidePanelImages();
		this.setCurrentAtomNumbers();
		
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
		alphaPUButton.addActionListener(this.powerUpButtonListener);
		powerUpPanel.add(alphaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentAlphaPULabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		betaPUButton.addActionListener(this.powerUpButtonListener);
		powerUpPanel.add(betaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentBetaPULabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		gammaPUButton.addActionListener(this.powerUpButtonListener);
		powerUpPanel.add(gammaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentGammaPULabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		sigmaPUButton.addActionListener(this.powerUpButtonListener);
		powerUpPanel.add(sigmaPUButton, c);
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
		atomPanel.add(alphaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentAlphaAtomLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		atomPanel.add(betaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentBetaAtomLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		atomPanel.add(gammaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentGammaAtomLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		atomPanel.add(sigmaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentSigmaAtomLabel, c);
		
		/*
		 * side panel design
		 */
		sidePanel.setMaximumSize(new Dimension((int) this.screenSize.getWidth() / 8, this.getHeight()));
		sidePanel.setMinimumSize(new Dimension((int) this.screenSize.getWidth() / 8, this.getHeight()));
		sidePanel.setPreferredSize(new Dimension((int) this.screenSize.getWidth() / 8, this.getHeight()));
		sidePanel.setBorder(new LineBorder(new Color(0.0f, 0.0f, 0.0f, 0.5f), 2));
		sidePanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		sidePanel.setLayout(new GridLayout(3,0));
		sidePanel.add(playerPanel);
		sidePanel.add(powerUpPanel);
		sidePanel.add(atomPanel);
		
		background.add(mainGamePanel);
		background.add(sidePanel, BorderLayout.LINE_END);
		
		this.add(background);
		this.setFocusable(true);
		this.addKeyListener(this.runningModeListener);
		
		this.mainGamePanel.initialize();
	}
	
	private void setCurrentAtomNumbers() {
		currentAlphaAtomLabel.setText(Integer.toString(GC.settings.getAtomNumber()));
		currentBetaAtomLabel.setText(Integer.toString(GC.settings.getAtomNumber()));
		currentGammaAtomLabel.setText(Integer.toString(GC.settings.getAtomNumber()));
		currentSigmaAtomLabel.setText(Integer.toString(GC.settings.getAtomNumber()));
	}

	private void setSidePanelImages() {
		try {
			
		    Image img = ImageIO.read(getClass().getResource("../../Images/powerups/+alpha-b.png"));
		    img = ImageResizer.getResizedImage(img, 50, 50);
		    alphaPUButton.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+beta-b.png"));
		    img = ImageResizer.getResizedImage(img, 50, 50);
		    betaPUButton.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+gamma-b.png"));
		    img = ImageResizer.getResizedImage(img, 50, 50);
		    gammaPUButton.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/powerups/+sigma-b.png"));
		    img = ImageResizer.getResizedImage(img, 50, 50);
		    sigmaPUButton.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/alpha.png"));
		    img = ImageResizer.getResizedImage(img, 35, 35);
		    alphaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/beta.png"));
		    img = ImageResizer.getResizedImage(img, 35, 35);
		    betaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/gamma.png"));
		    img = ImageResizer.getResizedImage(img, 35, 35);
		    gammaAtomLabel.setIcon(new ImageIcon(img));
		    img = ImageIO.read(getClass().getResource("../../Images/atoms/sigma.png"));
		    img = ImageResizer.getResizedImage(img, 35, 35);
		    sigmaAtomLabel.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("../../Images/mixer.png"));
		    img = ImageResizer.getResizedImage(img, 35, 35);
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
	
	
	private KeyListener runningModeListener = new KeyListener() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			
				case KeyEvent.VK_UP: //shoot atom-powerUp
					GC.shoot();
					break;
			
				case KeyEvent.VK_LEFT: //move shooter left
					GC.moveShooter(0);
					break;
					
				case KeyEvent.VK_RIGHT: //move shooter right
					GC.moveShooter(1);
					break;
					
				case KeyEvent.VK_A: //rotate shooter left
					GC.rotateShooter(0);
					break;
					
				case KeyEvent.VK_D: //rotate shooter right
					GC.rotateShooter(1);
					break;
					
				case KeyEvent.VK_C: //change atom on shooter
					GC.changeAtomOnBarrel();
					break;
					
				case KeyEvent.VK_P: //pause game
					GC.pauseGame();
					displayPausePanel();
					pausePanel.requestFocus();
					isPaused = true;
					break;
					
				case KeyEvent.VK_B: //use blender
					//GC.useBlender();
					break;
					
				default:
					break;
				
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}

	};
	
	private KeyListener pausePanelListener = new KeyListener() {

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
		
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	};
	
	
	private ActionListener powerUpButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isPaused) {
				pausePanel.requestFocus();
			} else {
				if(e.getSource() == alphaPUButton) {
					
				} else if(e.getSource() == betaPUButton) {
					
				} else if(e.getSource() == gammaPUButton) {
					
				} else if(e.getSource() == sigmaPUButton) {
					
				}
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();				
			}
			
		}
		
	};
	
}
