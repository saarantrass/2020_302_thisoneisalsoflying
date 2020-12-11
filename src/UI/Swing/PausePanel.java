package UI.Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PausePanel extends JPanel {

	public PausePanel() {
		this.setLayout(new GridBagLayout());
		JLabel pauseLabel = new JLabel("You paused the game. To Continue Press 'R'");
		pauseLabel.setFont(new Font("Verdana",1,18));
		pauseLabel.setBorder((Border) new LineBorder(Color.BLACK)); // make it easy to see
		pauseLabel.setBackground(Color.CYAN);
		this.add(pauseLabel);
		Dimension size = new Dimension(100, 100);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setPreferredSize(size);
		this.setOpaque(false);
		this.setVisible(true);
	}
	
}
