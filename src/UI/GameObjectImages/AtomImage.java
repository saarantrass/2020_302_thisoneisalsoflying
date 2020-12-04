package UI.GameObjectImages;

import java.awt.Graphics;

@SuppressWarnings("serial")
public class AtomImage extends GameObjectImage{
	
	private static final String ALPHA_ATOM_IMAGE = "alpha";
	private static final String BETA_ATOM_IMAGE = "beta";
	private static final String GAMMA_ATOM_IMAGE = "gamma";
	private static final String SIGMA_ATOM_IMAGE = "sigma";
	
	
	public AtomImage(int type, int x, int y) {
		super(type, x, y);
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
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawRect(this.x, this.y, 50, 100);
		
	}


	@Override
	public void update() {
		//this.x = shooter.getX();
		//this.y = shooter.getY();
		repaint();
	}
	
}
