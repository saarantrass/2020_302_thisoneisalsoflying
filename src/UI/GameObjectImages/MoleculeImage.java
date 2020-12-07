package UI.GameObjectImages;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import Domain.GameObjects.Molecule;

@SuppressWarnings("serial")
public class MoleculeImage extends GameObjectImage{
	
	private static final String ALPHA_MOLECULE_IMAGE = "../../Images/molecules/alpha-1.png";
	private static final String BETA_MOLECULE_IMAGE = "../../Images/molecules/beta-1.png";
	private static final String GAMMA_MOLECULE_IMAGE = "../../Images/molecules/gamma-.png";
	private static final String SIGMA_MOLECULE_IMAGE = "../../Images/molecules/sigma-.png";
	private Molecule molecule;
	
	
	public MoleculeImage(Molecule molecule, int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
		this.molecule = molecule;
		this.molecule.add(this);
		this.image = new ImageIcon(getClass().getResource(this.imageName)).getImage();
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
		g.drawImage(this.image, this.x, this.y, this);
	}


	@Override
	public void update() {
		this.x = molecule.getCoordinate().x;
		this.y = molecule.getCoordinate().y;
		repaint();
	}

}
