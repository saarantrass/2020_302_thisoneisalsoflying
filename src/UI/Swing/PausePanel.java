package UI.Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PausePanel extends JPanel { //TODO resize the panel to make it more beautiful

	public PausePanel() { //TODO
		
		this.setLayout(new GridBagLayout());
		
		/**
		 * pause label design
		 */
		JLabel pauseLabel = new JLabel("You paused the game. To Continue Press 'R'");
		pauseLabel.setFont(new Font("Verdana",1,18));
		pauseLabel.setBorder((Border) new LineBorder(Color.BLACK)); // make it easy to see
		
		this.add(pauseLabel);
		//Dimension size = new Dimension(100, 100);
		//this.setMaximumSize(size);
		//this.setMinimumSize(size);
		//this.setPreferredSize(size);
		//this.setSize(size);
		this.setOpaque(false);
		//this.setBackground(Color.GREEN);
		this.setVisible(true);
	}
	
}
