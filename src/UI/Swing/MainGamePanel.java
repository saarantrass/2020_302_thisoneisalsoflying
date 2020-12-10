package UI.Swing;

import java.awt.Graphics;
import javax.swing.JPanel;

import Domain.Game;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class MainGamePanel extends JPanel{
	
	public ShooterImage shooterImage; //TODO
	private Game game;
	private static MainGamePanel main_game_panel = null;
	
	
	private MainGamePanel() {
		
	}
	
	
	public static MainGamePanel getInstance() {
		if(main_game_panel == null) {
			main_game_panel = new MainGamePanel();
		}
		
		return main_game_panel;
	}
	
	public void initialize() {
		this.game = Game.getInstance();
		this.shooterImage = new ShooterImage(game.getShooter(), game.getShooter().getCoordinate().x, game.getShooter().getCoordinate().y);
		game.getShooter().add(shooterImage);
		this.setOpaque(false);
		this.setFocusable(false);
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		shooterImage.paint(g);
		//System.out.println(this.getWidth());
		//System.out.println(this.getHeight());
	}
	
}
