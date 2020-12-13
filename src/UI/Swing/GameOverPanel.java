package UI.Swing;

import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel{
	
	public GameOverPanel() { //TODO
		
		this.setLayout(new GridBagLayout());
		
		/**
		 * game over label design
		 */
		JLabel pauseLabel = new JLabel("GAME OVER");
		pauseLabel.setFont(new Font("Verdana",1,18));
		
		this.add(pauseLabel);
		this.setOpaque(false);
		this.setVisible(true);
	}
}
