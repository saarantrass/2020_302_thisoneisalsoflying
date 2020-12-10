package UI.GameObjectImages;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Domain.GameObjects.ReactionBlocker;
import UI.ImageResizer;
import UI.Swing.MainGamePanel;

@SuppressWarnings("serial")
public class ReactionBlockerImage extends GameObjectImage{
	
	private static final String ALPHA_RB_IMAGE = "../../Images/blockers/alpha-b.png";
	private static final String BETA_RB_IMAGE = "../../Images/blockers/beta-b.png";
	private static final String GAMMA_RB_IMAGE = "../../Images/blockers/gamma-b.png";
	private static final String SIGMA_RB_IMAGE = "../../Images/blockers/sigma-b.png";
	private ReactionBlocker RB;
	
	private int L = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10); //TODO
	

	public ReactionBlockerImage(ReactionBlocker RB, int type, int x, int y) {
		super(type, x, y);
		setImageName(type);
		this.RB = RB;
		this.RB.add(this);
		this.image = new ImageIcon(getClass().getResource(this.imageName)).getImage();
		this.image = ImageResizer.getResizedImage(image, L / 10, L / 10);
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
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, this.x, this.y, this);
	}


	@Override
	public void update() {
		this.x = RB.getCoordinate().x;
		this.y = RB.getCoordinate().y;
		MainGamePanel.getInstance().repaint();
	}
	
}
