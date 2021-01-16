package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import UI.ScreenCoordinator;

@SuppressWarnings("serial")
public class ErrorFrame extends JFrame{
	
	private JPanel errorPanel = new JPanel();
	private JLabel errorLabel;
	private JButton okButton = new JButton("Ok");
	
	public ErrorFrame(String msg) {
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		this.errorPanel.setLayout(new GridBagLayout());
		this.errorLabel = new JLabel(msg);
		this.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenCoordinator.getInstance().removeError();
			}
			
		});
		
		this.errorLabel.setForeground(Color.WHITE);
		this.errorPanel.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		this.errorPanel.add(errorLabel, c);
		
		c.gridy = 1;
		this.errorPanel.add(okButton, c);
		
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.add(errorPanel);
		this.pack();
	}
}
