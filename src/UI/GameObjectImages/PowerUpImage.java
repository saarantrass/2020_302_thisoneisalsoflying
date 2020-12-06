package UI.GameObjectImages;

import java.awt.Graphics;
import Domain.GameObjects.PowerUp;

@SuppressWarnings("serial")
public class PowerUpImage extends GameObjectImage{
	
	private static final String ALPHA_POWERUP_IMAGE = "../../Images/powerups/+alpha-b.png";
	private static final String BETA_POWERUP_IMAGE = "../../Images/powerups/+beta-b.png";
	private static final String GAMMA_POWERUP_IMAGE = "../../Images/powerups/+gamma-b.png";
	private static final String SIGMA_POWERUP_IMAGE = "../../Images/powerups/+sigma-b.png";
	private PowerUp PU;
	
	
	public PowerUpImage(PowerUp PU, int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
		this.PU = PU;
		this.PU.add(this);
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
		super.paint(g);
		g.drawRect(this.x, this.y, 25, 25);
		
	}


	@Override
	public void update() {
		this.x = PU.getCoordinate().x;
		this.y = PU.getCoordinate().y;
		repaint();
	}

}
