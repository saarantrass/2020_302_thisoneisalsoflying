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
	
	JButton saveQuitButton = new JButton("Save & Quit");
	JButton quitWithoutSaveButton = new JButton("Quit");
	JButton backButton = new JButton("Back");
	
	public QuitPanel() {
		this.setLayout(new GridBagLayout());
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBorder(new EtchedBorder());
		container.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
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
	
	public void addQuitButtonListener(ActionListener l) {
		this.saveQuitButton.addActionListener(l);
		this.quitWithoutSaveButton.addActionListener(l);
		this.backButton.addActionListener(l);
	}
	
	public void removeQuitButtonListener(ActionListener l) {
		this.saveQuitButton.removeActionListener(l);
		this.quitWithoutSaveButton.removeActionListener(l);
		this.backButton.removeActionListener(l);
	}
}
