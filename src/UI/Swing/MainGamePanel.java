package UI.Swing;

import java.awt.Graphics;
import javax.swing.JPanel;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class MainGamePanel extends JPanel{
	
	public ShooterImage shooter; //TODO
	
	private static MainGamePanel main_game_panel = null;
	
	
	private MainGamePanel() {
		this.setOpaque(false);
		this.setFocusable(false);
	}
	
	
	public static MainGamePanel getInstance() {
		if(main_game_panel == null) {
			main_game_panel = new MainGamePanel();
		}
		
		return main_game_panel;
	}
	
	
	@Override
	public void paint(Graphics g) {
		shooter.paint(g);
		
	}
	
}
