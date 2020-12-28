package UI.Swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class QuitPanel extends JPanel{
	
	public QuitPanel(ActionListener buttonListener) {
		this.setLayout(new GridBagLayout());
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBorder(new EtchedBorder());
		container.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		/*
		 * buttons design
		 */
		JButton saveQuitButton = new JButton("Save & Quit");
		saveQuitButton.addActionListener(buttonListener);
		
		JButton quitWithoutSaveButton = new JButton("Quit");
		quitWithoutSaveButton.addActionListener(buttonListener);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(buttonListener);
		
		/*
		 * add components
		 */
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		container.add(saveQuitButton, c);
		
		c.gridy = 1;
		container.add(quitWithoutSaveButton, c);
		
		c.gridy = 2;
		container.add(backButton, c);
		
		this.add(container);
		this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		this.setVisible(true);
	}
}
