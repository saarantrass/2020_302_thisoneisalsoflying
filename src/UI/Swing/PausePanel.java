package UI.Swing;

import java.awt.Color;
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
		JLabel jlabel = new JLabel("You paused the game. To Continue Press 'R'");
		jlabel.setFont(new Font("Verdana",1,20));
		this.add(jlabel);
		this.setBorder((Border) new LineBorder(Color.BLACK)); // make it easy to see
		this.setSize(510, 200);
		this.setVisible(true);
	}
	

}
