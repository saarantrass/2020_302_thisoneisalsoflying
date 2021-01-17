package UI.GameObjectImages;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Domain.IObserver;
import Domain.Player.Shooter;
import UI.ImageDesigner;

@SuppressWarnings("serial")
public class ShooterImage extends Canvas implements IObserver{
	
	protected int x;
	protected int y;
	protected Image image;
	
	private static final String SHOOTER_IMAGE = "../../Images/shooter.png";
	private Shooter shooter;
	private int L;
	
	
	public ShooterImage(Shooter shooter, int x, int y, int L) {
		this.x = x;
		this.y = y;
		this.L = L;
		this.shooter = shooter;
		this.shooter.add(this);
		this.image = ImageDesigner.getResizedImage(new ImageIcon(getClass().getResource(SHOOTER_IMAGE)).getImage(), L/2, L);
	}
	
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.rotate(Math.toRadians(shooter.getAngle()), this.x + L/4, this.y +L);
	    g2d.drawImage(this.image, this.x, this.y, this);
	}
	
	
	@Override
	public void update() {
		this.x = shooter.getCoordinate().x;
		this.y = shooter.getCoordinate().y;
	}
	
}
