package UI.GameObjectImages;

import java.awt.Graphics;

import Domain.Player.Shooter;

@SuppressWarnings("serial")
public class ShooterImage extends GameObjectImage {
	
	private static final String SHOOTER_IMAGE = "../../Images/shooter.png";
	private Shooter shooter;
	
	
	public ShooterImage(Shooter shooter, int x, int y) {
		super(0, x, y);
		setImageName(0);
		this.shooter = shooter;
		this.shooter.add(this);
	}
	

	@Override
	public void setImageName(int type) {	
		this.imageName = SHOOTER_IMAGE;	
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(this.x, this.y, 50, 100);
		
	}
	
	
	@Override
	public void update() {
		this.x = shooter.getCoordinate().x;
		this.y = shooter.getCoordinate().y;
		repaint();
	}
	
}
