package UI.GameObjectImages;

public class AtomImage extends GameObjectImage{
	
	private static final String ALPHA_ATOM_IMAGE = "alpha";
	private static final String BETA_ATOM_IMAGE = "beta";
	private static final String GAMMA_ATOM_IMAGE = "gamma";
	private static final String SIGMA_ATOM_IMAGE = "sigma";
	
	
	public AtomImage(int type) {
		super(type);
		setImageName(type);
	}
	

	@Override
	public void setImageName(int type) {
		
		switch(type){
			
			case 1:
				this.imageName = ALPHA_ATOM_IMAGE;
				break;
			
			case 2:
				this.imageName = BETA_ATOM_IMAGE;
				break;
				
			case 3:
				this.imageName = GAMMA_ATOM_IMAGE;
				break;
				
			case 4:
				this.imageName = SIGMA_ATOM_IMAGE;
				break;
			
			default:
				break;
		
		}
		
	}
	
}
