package UI.GameObjectImages;

import java.awt.Graphics;

import Domain.GameObjects.Molecule;

@SuppressWarnings("serial")
public class MoleculeImage extends GameObjectImage{
	
	private static final String ALPHA_MOLECULE_IMAGE = "alpha";
	private static final String BETA_MOLECULE_IMAGE = "beta";
	private static final String GAMMA_MOLECULE_IMAGE = "gamma";
	private static final String SIGMA_MOLECULE_IMAGE = "sigma";
	private Molecule molecule;
	
	
	public MoleculeImage(Molecule molecule, int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
		this.molecule = molecule;
		this.molecule.add(this);
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
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(this.x, this.y, 25, 25);
		
	}


	@Override
	public void update() {
		this.x = molecule.getCoordinate().x;
		this.y = molecule.getCoordinate().y;
		repaint();
	}

}
