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
public class PausePanel extends JPanel {
	
	private JLabel pauseLabel = new JLabel("You paused the game. To Continue Press 'R'");
	private JButton quitButton = new JButton("Quit Game");

	public PausePanel() {
		this.setLayout(new GridBagLayout());
		
		/*
		 * pause label design
		 */
		pauseLabel.setFont(new Font("Verdana",1,18));
		
		/*
		 * pause panel design
		 */
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(pauseLabel, c);
		
		c.gridy = 1;
		this.add(quitButton, c);
		
		this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		this.setVisible(true);
	}
	
	public void addQuitButtonListener(ActionListener l) {
		this.quitButton.addActionListener(l);
	}
	
	public void removeQuitButtonListener(ActionListener l) {
		this.quitButton.removeActionListener(l);
	}
	
}
