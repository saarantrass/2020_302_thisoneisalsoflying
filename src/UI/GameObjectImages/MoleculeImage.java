package UI.GameObjectImages;

public class MoleculeImage extends GameObjectImage{
	
	private static final String ALPHA_MOLECULE_IMAGE = "alpha";
	private static final String BETA_MOLECULE_IMAGE = "beta";
	private static final String GAMMA_MOLECULE_IMAGE = "gamma";
	private static final String SIGMA_MOLECULE_IMAGE = "sigma";
	
	
	public MoleculeImage(int type) {
		super(type);
		setImageName(type);
	}
	

	@Override
	public void setImageName(int type) {
		
		switch(type){
			
			case 1:
				this.imageName = ALPHA_MOLECULE_IMAGE;
				break;
			
			case 2:
				this.imageName = BETA_MOLECULE_IMAGE;
				break;
				
			case 3:
				this.imageName = GAMMA_MOLECULE_IMAGE;
				break;
				
			case 4:
				this.imageName = SIGMA_MOLECULE_IMAGE;
				break;
			
			default:
				break;
		
		}
		
	}

}
