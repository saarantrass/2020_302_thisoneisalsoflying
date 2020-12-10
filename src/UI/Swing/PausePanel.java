package UI.Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PausePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PausePanel() {
	
    this.setLayout(new GridBagLayout());
    JPanel panel = new JPanel();
    JLabel jlabel = new JLabel("You paused the game. To Continue Press 'R'");
    jlabel.setFont(new Font("Verdana",1,20));
    panel.add(jlabel);
    panel.setBorder((Border) new LineBorder(Color.BLACK)); // make it easy to see
    this.add(panel, new GridBagConstraints());
    this.setSize(510, 200);
    this.setVisible(true);
	}
	

}
