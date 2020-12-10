package UI.GameObjectImages;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Domain.GameObjects.Atom;
import UI.ImageResizer;
import UI.Swing.MainGamePanel;

@SuppressWarnings("serial")
public class AtomImage extends GameObjectImage{
	
	private static final String ALPHA_ATOM_IMAGE = "../../Images/atoms/alpha.png";
	private static final String BETA_ATOM_IMAGE = "../../Images/atoms/beta.png";
	private static final String GAMMA_ATOM_IMAGE = "../../Images/atoms/gamma.png";
	private static final String SIGMA_ATOM_IMAGE = "../../Images/atoms/sigma.png";
	private Atom atom;

	private int L = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10); //TODO
	
	
	public AtomImage(Atom atom, int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
		this.atom = atom;
		this.atom.add(this);
		this.image = new ImageIcon(getClass().getResource(this.imageName)).getImage();
		this.image = ImageResizer.getResizedImage(image, L / 10, L / 10);
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
		g.drawImage(this.image, this.x, this.y, this);
	}


	@Override
	public void update() {
		this.x = atom.getCoordinate().x;
		this.y = atom.getCoordinate().y;
		MainGamePanel.getInstance().repaint();
	}
	
}
