package UI.Swing;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import Domain.Game;
import Domain.GameObjects.Molecule;
import Domain.GameObjects.ReactionBlocker;
import UI.IObserver;
import UI.GameObjectImages.MoleculeImage;
import UI.GameObjectImages.ReactionBlockerImage;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class MainGamePanel extends JPanel implements IObserver{
	
	public ShooterImage shooterImage; //TODO
	private Game game;
	private static MainGamePanel main_game_panel = null;
	
	
	private MainGamePanel() {
		Game.getInstance().add(this);
		this.setLayout(new GridBagLayout());
	}
	
	
	public static MainGamePanel getInstance() {
		if(main_game_panel == null) {
			main_game_panel = new MainGamePanel();
		}
		
		return main_game_panel;
	}
	
	public void initialize() {
		this.game = Game.getInstance();
		this.shooterImage = new ShooterImage(game.getGC().shooter, game.getGC().shooter.getCoordinate().x, game.getGC().shooter.getCoordinate().y);
		game.getGC().shooter.add(shooterImage);
		this.setOpaque(false);
		this.setFocusable(false);
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		shooterImage.paint(g);
		
		for (Molecule mol: Game.getInstance().onScreenMoleculeList) {
			MoleculeImage img = new MoleculeImage(mol, mol.moleculeID, mol.getCoordinate().x,mol.getCoordinate().y);
			img.paint(g);
		}
		for (ReactionBlocker mol: Game.getInstance().onScreenReactionBlockerList) {
			ReactionBlockerImage img = new ReactionBlockerImage(mol, mol.reactionBlockerID, mol.getCoordinate().x,mol.getCoordinate().y);
			img.paint(g);
		}
		//System.out.println(this.getWidth());
		//System.out.println(this.getHeight());
	}


	@Override
	public void update() {
		if(Game.getInstance().isPaused) {
			PausePanel panel = new PausePanel();
			this.add(panel);
			System.out.print("heloo");
			panel.setVisible(true);
			
		}
		
	}
	
}
