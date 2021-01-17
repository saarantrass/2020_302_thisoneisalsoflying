package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import Domain.Game;
import Domain.IObserver;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;
import UI.GameObjectImages.GameObjectImageFactory;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class MainGamePanel extends JPanel implements IObserver{
	
	private Game game;
	public ShooterImage shooterImage;
	private GameObjectImageFactory factory;
	
	
	public MainGamePanel() {
		this.game = Game.getInstance();
		this.game.add(this);
		
		this.setFactory();
		this.setShooter();
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setFocusable(false);
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		for(Atom atom: this.game.onScreenAtomList) {
			Image image = this.factory.getGameObjectImage("Atom", atom.getAtomID(), false, false, 0);
			g.drawImage(image, atom.getCoordinate().x, atom.getCoordinate().y, null);
		}
		
		for(Molecule molecule: this.game.onScreenMoleculeList) {			
			Image image = this.factory.getGameObjectImage("Molecule", molecule.getID(), molecule.isLinear(), molecule.isSpinning(), molecule.getAngle());
			g.drawImage(image, molecule.getCoordinate().x ,  molecule.getCoordinate().y, null);
		}
		
		for(ReactionBlocker reactionBlocker: this.game.onScreenReactionBlockerList) {
			Image image = this.factory.getGameObjectImage("ReactionBlocker", reactionBlocker.getID(), false, false, 0);
			g.drawImage(image, reactionBlocker.getCoordinate().x, reactionBlocker.getCoordinate().y, null);
		}
		
		for(PowerUp powerUp: this.game.onScreenPowerUpList) {
			Image image = this.factory.getGameObjectImage("PowerUp", powerUp.getID(), false, false, 0);
			g.drawImage(image, powerUp.getCoordinate().x, powerUp.getCoordinate().y, null);
		}
		
		if(this.game.barrelAtom != null) {
			Image image = this.factory.getGameObjectImage("Atom", this.game.barrelAtom.getAtomID(), false, false, 0);
			g.drawImage(image, this.game.barrelAtom.getCoordinate().x, this.game.barrelAtom.getCoordinate().y, null);
		} else if (this.game.barrelPowerUp != null) {
			Image image = this.factory.getGameObjectImage("PowerUp", this.game.barrelPowerUp.getID(), false, false, 0);
			g.drawImage(image, this.game.barrelPowerUp.getCoordinate().x, this.game.barrelPowerUp.getCoordinate().y, null);
		}
		
		shooterImage.paint(g);
		super.paint(g);
		g.dispose();
	}
	
	public void setShooter() {
		this.shooterImage = new ShooterImage(this.game.shooter, this.game.shooter.getCoordinate().x, this.game.shooter.getCoordinate().y, this.game.getLengthUnit());
		this.game.shooter.add(shooterImage);
	}
	
	public void setFactory() {
		this.factory = new GameObjectImageFactory(this.game.getLengthUnit());
	}


	@Override
	public void update() {
		repaint();	
	}
	
}
