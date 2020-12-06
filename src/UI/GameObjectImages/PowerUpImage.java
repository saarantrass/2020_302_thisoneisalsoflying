package UI.GameObjectImages;

public class PowerUpImage extends GameObjectImage{
	
	private static final String ALPHA_POWERUP_IMAGE = "alpha";
	private static final String BETA_POWERUP_IMAGE = "beta";
	private static final String GAMMA_POWERUP_IMAGE = "gamma";
	private static final String SIGMA_POWERUP_IMAGE = "sigma";
	
	
	public PowerUpImage(int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
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
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
