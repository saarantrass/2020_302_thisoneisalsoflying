package UI.GameObjectImages;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Domain.GameObjects.PowerUp;
import UI.ImageResizer;
import UI.Swing.MainGamePanel;

@SuppressWarnings("serial")
public class PowerUpImage extends GameObjectImage{
	
	private static final String ALPHA_POWERUP_IMAGE = "../../Images/powerups/+alpha-b.png";
	private static final String BETA_POWERUP_IMAGE = "../../Images/powerups/+beta-b.png";
	private static final String GAMMA_POWERUP_IMAGE = "../../Images/powerups/+gamma-b.png";
	private static final String SIGMA_POWERUP_IMAGE = "../../Images/powerups/+sigma-b.png";
	private PowerUp PU;
	private int L = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10); //TODO
	
	
	public PowerUpImage(PowerUp PU, int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
		this.PU = PU;
		this.PU.add(this);
		this.image = new ImageIcon(getClass().getResource(this.imageName)).getImage();
		this.image = ImageResizer.getResizedImage(image, L / 10, L / 10);
	}
	

	@Override
	public void setImageName(int type) {
		
		switch(type){
			
			case 1:
				this.imageName = ALPHA_POWERUP_IMAGE;
				break;
			
			case 2:
				this.imageName = BETA_POWERUP_IMAGE;
				break;
				
			case 3:
				this.imageName = GAMMA_POWERUP_IMAGE;
				break;
				
			case 4:
				this.imageName = SIGMA_POWERUP_IMAGE;
				break;
			
			default:
				break;
		
		}
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, this.x, this.y, this);
	}


	@Override
	public void update() {
		this.x = PU.getCoordinate().x;
		this.y = PU.getCoordinate().y;
		MainGamePanel.getInstance().repaint();
	}

}
