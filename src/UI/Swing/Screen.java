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

import UI.GameObjectImages.*;

public class Screen extends JFrame implements IObserver {
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private List<String> objectList;
	
	ShooterImage shooter;
	private static final int L = 10; //TODO
	
	private JPanel sidePanel = new JPanel(new GridBagLayout());
	private JPanel playerPanel = new JPanel(new GridBagLayout());
	private JPanel powerUpPanel = new JPanel(new GridBagLayout());
	private JPanel atomPanel = new JPanel(new GridBagLayout());
	
	private JLabel scoreLabel = new JLabel("Score: ");
	private JLabel timeLabel = new JLabel("Time: ");
	private JLabel healthLabel = new JLabel("Health: ");
	
	private JLabel alphaPULabel = new JLabel("AlphaPU: ");
	private JLabel betaPULabel = new JLabel("BetaPU: ");
	private JLabel gammaPULabel = new JLabel("GammaPU: ");
	private JLabel sigmaPULabel = new JLabel("SigmaPU: ");
	
	private JLabel alphaAtomLabel = new JLabel("Alpha: ");
	private JLabel betaAtomLabel = new JLabel("Beta: ");
	private JLabel gammaAtomLabel = new JLabel("Gamma: ");
	private JLabel sigmaAtomLabel = new JLabel("Sigma: ");
	
	
	public Screen(){
		
		/*
		 * screen JFrame design
		 */
		super("KUVID");
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
		
		c.gridy = 1;
		playerPanel.add(timeLabel, c);
		
		c.gridy = 2;
		playerPanel.add(healthLabel, c);
		
		/*
		 * powerUp panel design
		 */
		powerUpPanel.setBorder(new TitledBorder("PowerUp Panel"));
		
		c.gridx = 0;
		c.gridy = 0;
		powerUpPanel.add(alphaPULabel, c);
		
		c.gridy= 1;
		powerUpPanel.add(betaPULabel, c);
		
		c.gridy = 2;
		powerUpPanel.add(gammaPULabel, c);
		
		c.gridy = 3;
		powerUpPanel.add(sigmaPULabel, c);
		
		/*
		 * atom panel design
		 */
		atomPanel.setBorder(new TitledBorder("Atom Panel"));
		
		c.gridx = 0;
		c.gridy = 0;
		atomPanel.add(alphaAtomLabel, c);
		
		c.gridy = 1;
		atomPanel.add(betaAtomLabel, c);
		
		c.gridy = 2;
		atomPanel.add(gammaAtomLabel, c);
		
		c.gridy = 3;
		atomPanel.add(sigmaAtomLabel, c);
		
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
		
		
		
		
		shooter = new ShooterImage();
		
		
		this.add(sidePanel, BorderLayout.LINE_END);
		this.add(shooter);
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
			
			switch(e.getKeyCode()) {
			
				case KeyEvent.VK_LEFT:
					shooter.move(L, 0);
					break;
					
				case KeyEvent.VK_RIGHT:
					shooter.move(L, 1);
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
			// TODO Auto-generated method stub
			
		}
		
	};

	
}
