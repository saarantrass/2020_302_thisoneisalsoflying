package UI.Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel{
	
	private JLabel gameOverLabel = new JLabel("Game Over");
	private JLabel scoreLabel = new JLabel("Score: ");
	private JButton quitButton = new JButton("Quit");
	
	public GameOverPanel() {
		this.setLayout(new GridBagLayout());
		
		/*
		 * game over label design
		 */
		gameOverLabel.setFont(new Font("Verdana",1,18));
		
		/*
		 * game over panel design
		 */
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(gameOverLabel, c);
		
		c.gridy = 1;
		this.add(scoreLabel, c);
		
		c.gridy = 2;
		this.add(quitButton, c);
		
		this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		this.setVisible(true);
	}
	
	public void setScore(String score) {
		this.scoreLabel.setText("Score: " + score);
		this.validate();
		this.repaint();
	}
	
	public void addQuitButtonListener(ActionListener l) {
		this.quitButton.addActionListener(l);
	}
	
	public void removeQuitButtonListener(ActionListener l) {
		this.quitButton.removeActionListener(l);
	}
}
