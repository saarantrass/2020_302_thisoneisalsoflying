package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import Domain.Game;
import Domain.Settings;
import Domain.GameObjects.Atom;
import Domain.GameObjects.Molecule;
import Domain.GameObjects.PowerUp;
import Domain.GameObjects.ReactionBlocker;
import UI.IObserver;
import UI.GameObjectImages.GameObjectImageCreator;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class MainGamePanel extends JPanel implements IObserver{
	
	public ShooterImage shooterImage;
	private static MainGamePanel main_game_panel = null;
	
	
	private MainGamePanel() {
		Game.getInstance().add(this);
		this.setLayout(new BorderLayout());
	}
	
	
	public static MainGamePanel getInstance() {
		if(main_game_panel == null) {
			main_game_panel = new MainGamePanel();
		}
		
		return main_game_panel;
	}
	
	public void initialize() { //TODO GAME???
		this.shooterImage = new ShooterImage(Game.getInstance().shooter, Game.getInstance().shooter.getCoordinate().x, Game.getInstance().shooter.getCoordinate().y, Settings.getInstance().getLengthUnit());
		Game.getInstance().shooter.add(shooterImage);
		this.setOpaque(false);
		this.setFocusable(false);
	}
	
	
	@Override
	public void paint(Graphics g) { //TODO ATOM MOLECULE VS OLABİLİR Mİ?? BURDA
		
		for(Atom atom: Game.getInstance().onScreenAtomList) {
			Image image = GameObjectImageCreator.getInstance().getAtomImage(atom.atomID);
			g.drawImage(image, atom.getCoordinate().x, atom.getCoordinate().y, null);
		}
		
		for(Molecule molecule: Game.getInstance().onScreenMoleculeList) {
			Image image = GameObjectImageCreator.getInstance().getMoleculeImage(molecule.moleculeID, false);
			g.drawImage(image, molecule.getCoordinate().x, molecule.getCoordinate().y, null);
		}
		
		for(ReactionBlocker reactionBlocker: Game.getInstance().onScreenReactionBlockerList) {
			Image image = GameObjectImageCreator.getInstance().getRBImage(reactionBlocker.reactionBlockerID);
			g.drawImage(image, reactionBlocker.getCoordinate().x, reactionBlocker.getCoordinate().y, null);
		}
		
		for(PowerUp powerUp: Game.getInstance().onScreenPowerUpList) {
			Image image = GameObjectImageCreator.getInstance().getPowerUpImage(powerUp.powerUpID);
			g.drawImage(image, powerUp.getCoordinate().x, powerUp.getCoordinate().y, null);
		}
		
		if(Game.getInstance().barrelAtom != null) {
			Image image = GameObjectImageCreator.getInstance().getAtomImage(Game.getInstance().barrelAtom.atomID);
			g.drawImage(image, Game.getInstance().barrelAtom.getCoordinate().x, Game.getInstance().barrelAtom.getCoordinate().y, null);
		} else if (Game.getInstance().barrelPowerUp != null) {
			Image image = GameObjectImageCreator.getInstance().getAtomImage(Game.getInstance().barrelPowerUp.powerUpID);
			g.drawImage(image, Game.getInstance().barrelPowerUp.getCoordinate().x, Game.getInstance().barrelPowerUp.getCoordinate().y, null);
		}
		
		shooterImage.paint(g);
		super.paint(g);
	}


	@Override
	public void update() {
		repaint();	
	}
	
}
