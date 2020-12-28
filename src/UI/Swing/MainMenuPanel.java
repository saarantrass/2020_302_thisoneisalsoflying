package UI.Swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Domain.GameController;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel{
	
	@SuppressWarnings("unused")
	private GameController GC;
	
	private JPanel mainPanel = new JPanel(new GridBagLayout());
	private JLabel gameLabel = new JLabel();
	private JButton newGameButton = new JButton("New Game");
	private JButton loadGameButton = new JButton("Load Game");
	
	public MainMenuPanel(GameController GC) {
		
		this.GC = GC;
		
		this.setLayout(new GridBagLayout());
		//mainPanel.setLayout(new GridBagLayout());
		
		/*
		 * game label design
		 */
		try {
			Image img = ImageIO.read(getClass().getResource("../../Images/kuvid.png"));
			gameLabel.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * new game button
		 */
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenCoordinator.getInstance().buildingModeScreen();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
		});
		
		/*
		 * load game button
		 */
		loadGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GC.loadGame();
				ScreenCoordinator.getInstance().startGame();
				ScreenCoordinator.getInstance().getCurrentPanel().requestFocus();
			}
			
		});
		
		/*
		 * add components
		 */
		Dimension screenSize = ScreenCoordinator.SCREEN_SIZE;
		mainPanel.setMaximumSize(screenSize);
		mainPanel.setMinimumSize(screenSize);
		mainPanel.setPreferredSize(screenSize);
		mainPanel.setSize(new Dimension(screenSize));
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(gameLabel, c);
		
		c.gridy = 1;
		mainPanel.add(newGameButton, c);
		
		c.gridy = 2;
		mainPanel.add(loadGameButton, c);
		
		//mainPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.3f));
		mainPanel.setOpaque(false);
		
	    this.setOpaque(false);
	    this.add(mainPanel);
	}

}
