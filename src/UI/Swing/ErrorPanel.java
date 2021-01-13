package UI.Swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ErrorPanel extends JPanel{
	
	private JPanel messagePanel = new JPanel();
	private JLabel errorLabel;
	private JButton okButton = new JButton("Ok");
	
	public ErrorPanel(String msg) {
		this.setLayout(new GridBagLayout());
		this.messagePanel.setLayout(new GridBagLayout());
		this.errorLabel = new JLabel(msg);
		this.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenCoordinator.getInstance().buildingModeScreen();
			}
			
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		this.messagePanel.add(errorLabel, c);
		
		c.gridy = 1;
		this.messagePanel.add(okButton, c);
		
		this.messagePanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		this.add(messagePanel);
		this.setOpaque(false);
		this.setVisible(true);
	}
}
