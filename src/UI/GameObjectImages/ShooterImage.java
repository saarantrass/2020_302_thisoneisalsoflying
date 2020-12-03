package UI.GameObjectImages;

import java.awt.Graphics;
import java.awt.Toolkit;

public class ShooterImage extends GameObjectImage {
	
	Toolkit t = Toolkit.getDefaultToolkit();
	int x;
	int y;
	
	private static final String SHOOTER_IMAGE = "rectangle.jpg";
	
	
	public ShooterImage() {
		super(0);
		setImageName(0);
		x = t.getScreenSize().width / 2;
		y = t.getScreenSize().height / 2;
	}
	

	@Override
	public void setImageName(int type) {	
		this.imageName = SHOOTER_IMAGE;	
	}
	
	
	public void move(int L, int direction) {
		if(direction == 0) {
			x = x - L;
			repaint();
		} else if(direction == 1) {
			x = x + L;
			repaint();
		}
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//Toolkit t = Toolkit.getDefaultToolkit();  
		//this.image = t.getImage(imageName);
		//g.drawImage(image, t.getScreenSize().width / 2, t.getScreenSize().height, 50, 100, this);
		g.drawRect(x, y, 50, 100);
		
	}

}
