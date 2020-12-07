package UI.GameObjectImages;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import Domain.Player.Shooter;

@SuppressWarnings("serial")
public class ShooterImage extends GameObjectImage {
	
	private static final String SHOOTER_IMAGE = "../../Images/shooter.png";
	private Shooter shooter;
	//private int L = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10);
	
	public ShooterImage(Shooter shooter, int x, int y) {
		super(0, x, y);
		setImageName(0);
		this.shooter = shooter;
		this.shooter.add(this);
		this.image = new ImageIcon(getClass().getResource(this.imageName)).getImage();
		/*Dimension size = new Dimension(L/2, L);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setSize(size);*/
	}
	

	@Override
	public void setImageName(int type) {	
		this.imageName = SHOOTER_IMAGE;	
	}

	
	@Override
	public void paint(Graphics g) {
	    g.drawImage(this.image, this.x, this.y, this);
	}
	
	
	@Override
	public void update() {
		this.x = shooter.getCoordinate().x;
		this.y = shooter.getCoordinate().y;
		repaint();
	}
	
}
