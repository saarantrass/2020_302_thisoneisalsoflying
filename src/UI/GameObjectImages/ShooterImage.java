package UI.GameObjectImages;

public class ShooterImage extends GameObjectImage{
	
	private static final String SHOOTER_IMAGE = "shooter";
	
	
	public ShooterImage(int type) {
		super(type);
		setImageName(type);
	}
	

	@Override
	public void setImageName(int type) {	
		this.imageName = SHOOTER_IMAGE;	
	}

}
