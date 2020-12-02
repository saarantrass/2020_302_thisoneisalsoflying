package UI.GameObjectImages;

public class ReactionBlockerImage extends GameObjectImage{
	
	private static final String ALPHA_RB_IMAGE = "alpha";
	private static final String BETA_RB_IMAGE = "beta";
	private static final String GAMMA_RB_IMAGE = "gamma";
	private static final String SIGMA_RB_IMAGE = "sigma";
	

	public ReactionBlockerImage(int type) {
		super(type);
		setImageName(type);
	}
	

	@Override
	public void setImageName(int type) {
		
		switch(type){
			
			case 1:
				this.imageName = ALPHA_RB_IMAGE;
				break;
			
			case 2:
				this.imageName = BETA_RB_IMAGE;
				break;
				
			case 3:
				this.imageName = GAMMA_RB_IMAGE;
				break;
				
			case 4:
				this.imageName = SIGMA_RB_IMAGE;
				break;
			
			default:
				break;
		
		}
		
	}
	
}
