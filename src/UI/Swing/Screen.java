package UI.Swing;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import Domain.GameController;
import Domain.Player.Shooter;
import UI.IObserver;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class Screen extends JFrame implements IObserver {
	
	private GameController GC;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private List<String> objectList;
	
	//ShooterImage shooter;
	//private static final int L = 10; //TODO
	
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
	
	private JButton alphaPUButton = new JButton("AlphaPU");
	private JLabel currentAlphaPULabel = new JLabel();
	private JButton betaPUButton = new JButton("BetaPU");
	private JLabel currentBetaPULabel = new JLabel();
	private JButton gammaPUButton = new JButton("GammaPU");
	private JLabel currentGammaPULabel = new JLabel();
	private JButton sigmaPUButton = new JButton("SigmaPU");
	private JLabel currentSigmaPULabel = new JLabel();
	
	private JLabel alphaAtomLabel = new JLabel("Alpha: ");
	private JLabel currentAlphaAtomLabel = new JLabel();
	private JLabel betaAtomLabel = new JLabel("Beta: ");
	private JLabel currentBetaAtomLabel = new JLabel();
	private JLabel gammaAtomLabel = new JLabel("Gamma: ");
	private JLabel currentGammaAtomLabel = new JLabel();
	private JLabel sigmaAtomLabel = new JLabel("Sigma: ");
	private JLabel currentSigmaAtomLabel = new JLabel();
	
	
	public Screen(GameController GC){
		super("KUVID");
		this.GC = GC;
		
		/*
		 * screen JFrame design
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(new BorderLayout());
		
		/*
		 * player panel design
		 */
		playerPanel.setBorder(new TitledBorder("Player Panel"));
		
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
		playerPanel.add(currentHealthLabel);
		
		/*
		 * powerUp panel design
		 */
		powerUpPanel.setBorder(new TitledBorder("PowerUp Panel"));
		
		c.gridx = 0;
		c.gridy = 0;
		alphaPUButton.addActionListener(actionListener);
		powerUpPanel.add(alphaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentAlphaPULabel);
		
		c.gridx = 0;
		c.gridy = 1;
		betaPUButton.addActionListener(actionListener);
		powerUpPanel.add(betaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentBetaPULabel);
		
		c.gridx = 0;
		c.gridy = 2;
		gammaPUButton.addActionListener(actionListener);
		powerUpPanel.add(gammaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentGammaPULabel);
		
		c.gridx = 0;
		c.gridy = 3;
		sigmaPUButton.addActionListener(actionListener);
		powerUpPanel.add(sigmaPUButton, c);
		c.gridx = 1;
		powerUpPanel.add(currentSigmaPULabel);
		
		/*
		 * atom panel design
		 */
		atomPanel.setBorder(new TitledBorder("Atom Panel"));
		
		c.gridx = 0;
		c.gridy = 0;
		atomPanel.add(alphaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentAlphaAtomLabel);
		
		c.gridx = 0;
		c.gridy = 1;
		atomPanel.add(betaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentBetaAtomLabel);
		
		c.gridx = 0;
		c.gridy = 2;
		atomPanel.add(gammaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentGammaAtomLabel);
		
		c.gridx = 0;
		c.gridy = 3;
		atomPanel.add(sigmaAtomLabel, c);
		c.gridx = 1;
		atomPanel.add(currentSigmaAtomLabel);
		
		/*
		 * side panel design
		 */
		sidePanel.setBackground(Color.PINK);
		
		c.gridx = 0;
		c.gridy = 0;
		sidePanel.add(playerPanel, c);
		
		c.gridy = 1;
		sidePanel.add(powerUpPanel, c);
		
		c.gridy = 2;
		sidePanel.add(atomPanel, c);
		
		
		
		this.add(sidePanel, BorderLayout.LINE_END);
		this.addKeyListener(this.keyListener);
		this.setVisible(true);
	}

	
	
	public List<String> getObjectList() {
		return objectList;
	}



	public void setObjectList(List<String> objectList) {
		this.objectList = objectList;
	}
	
	

	public void update() {
		
	}

	
	private KeyListener keyListener = new KeyListener() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e);
			switch(e.getKeyCode()) {
			
				case KeyEvent.VK_UP: //shoot atom-powerUp
					//GC.shootAtom();
					//GC.shootPowerUp();
					break;
			
				case KeyEvent.VK_LEFT: //move shooter left
					System.out.println("here");
					GC.moveShooter(0);
					//shooter.update();
					break;
					
				case KeyEvent.VK_RIGHT: //move shooter right
					GC.moveShooter(1);
					break;
					
				case KeyEvent.VK_A: //rotate shooter left
					GC.rotateShooter(0);
					break;
					
				case KeyEvent.VK_D: //rotate shooter right
					GC.rotateShooter(1);
					
				case KeyEvent.VK_C: //change atom on shooter
					break;
					
				case KeyEvent.VK_P: //pause game
					GC.pauseGame();
					break;
					
				case KeyEvent.VK_R: //resume game
					GC.resumeGame();
					break;
				
				case KeyEvent.VK_S: //save game
					break;
					
				case KeyEvent.VK_L: //load game
					break;
					
				case KeyEvent.VK_B: //use blender
					//GC.useBlender();
					break;
					
				default:
					break;
				
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(e.getActionCommand()) {
				
				case "AlphaPU":
					break;
					
				case "BetaPU":
					break;
				
				case "GammaPU":
					break;
					
				case "SigmaPU":
					break;
					
				default:
					break;
					
			}
			
		}
		
	};

	
}
