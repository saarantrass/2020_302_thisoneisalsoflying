package UI.GameObjectImages;

import java.awt.Image;

import javax.swing.ImageIcon;

import UI.ImageDesigner;

public class GameObjectImageFactory {
	
	private static final String ALPHA_ATOM_IMAGE = "../../Images/atoms/alpha.png";
	private static final String BETA_ATOM_IMAGE = "../../Images/atoms/beta.png";
	private static final String GAMMA_ATOM_IMAGE = "../../Images/atoms/gamma.png";
	private static final String SIGMA_ATOM_IMAGE = "../../Images/atoms/sigma.png";
	
	private static final String ALPHA_MOLECULE_IMAGE = "../../Images/molecules/alpha-1.png";
	private static final String ALPHA_LINEAR_MOLECULE_IMAGE = "../../Images/molecules/alpha-2.png";
	private static final String BETA_MOLECULE_IMAGE = "../../Images/molecules/beta-1.png";
	private static final String BETA_LINEAR_MOLECULE_IMAGE = "../../Images/molecules/beta-2.png";
	private static final String GAMMA_MOLECULE_IMAGE = "../../Images/molecules/gamma-.png";
	private static final String SIGMA_MOLECULE_IMAGE = "../../Images/molecules/sigma-.png";
	
	private static final String ALPHA_RB_IMAGE = "../../Images/blockers/alpha-b.png";
	private static final String BETA_RB_IMAGE = "../../Images/blockers/beta-b.png";
	private static final String GAMMA_RB_IMAGE = "../../Images/blockers/gamma-b.png";
	private static final String SIGMA_RB_IMAGE = "../../Images/blockers/sigma-b.png";
	
	private static final String ALPHA_POWERUP_IMAGE = "../../Images/powerups/+alpha-b.png";
	private static final String BETA_POWERUP_IMAGE = "../../Images/powerups/+beta-b.png";
	private static final String GAMMA_POWERUP_IMAGE = "../../Images/powerups/+gamma-b.png";
	private static final String SIGMA_POWERUP_IMAGE = "../../Images/powerups/+sigma-b.png";
	
	private int L;
	
	
	public GameObjectImageFactory(int L) {this.L = L;}
	
	public Image getGameObjectImage(String gameObjectName, int type, boolean isLinear, boolean isSpinning, double angle) {
		Image image = null;
		
		switch(gameObjectName) {
		
			case "Atom":
				if(type == 1) {
					image = new ImageIcon(getClass().getResource(ALPHA_ATOM_IMAGE)).getImage();
				} else if(type == 2) {
					image = new ImageIcon(getClass().getResource(BETA_ATOM_IMAGE)).getImage();
				} else if(type == 3) {
					image = new ImageIcon(getClass().getResource(GAMMA_ATOM_IMAGE)).getImage();
				} else if(type == 4){
					image = new ImageIcon(getClass().getResource(SIGMA_ATOM_IMAGE)).getImage();
				}
				image = ImageDesigner.getResizedImage(image, L / 10, L / 10);
				
				return image;
				
			case "Molecule":
				if(type == 1) {
					if(isLinear) {
						image = new ImageIcon(getClass().getResource(ALPHA_LINEAR_MOLECULE_IMAGE)).getImage();
					} else {
						image = new ImageIcon(getClass().getResource(ALPHA_MOLECULE_IMAGE)).getImage();
					}
				} else if(type == 2) {
					if(isLinear) {
						image = new ImageIcon(getClass().getResource(BETA_LINEAR_MOLECULE_IMAGE)).getImage();
					} else {
						image = new ImageIcon(getClass().getResource(BETA_MOLECULE_IMAGE)).getImage();
					}
				} else if(type == 3) {
					image = new ImageIcon(getClass().getResource(GAMMA_MOLECULE_IMAGE)).getImage();
				} else if(type == 4){
					image = new ImageIcon(getClass().getResource(SIGMA_MOLECULE_IMAGE)).getImage();
				}
				
				image = ImageDesigner.getResizedImage(image, L / 4, L / 4);
				if(isSpinning)
					image = ImageDesigner.getRotatedImage(image, angle, L);
				
				return image;
				
			case "ReactionBlocker":
				if(type == 1) {
					image = new ImageIcon(getClass().getResource(ALPHA_RB_IMAGE)).getImage();
				} else if(type == 2) {
					image = new ImageIcon(getClass().getResource(BETA_RB_IMAGE)).getImage();
				} else if(type == 3) {
					image = new ImageIcon(getClass().getResource(GAMMA_RB_IMAGE)).getImage();
				} else if(type == 4){
					image = new ImageIcon(getClass().getResource(SIGMA_RB_IMAGE)).getImage();
				}
				image = ImageDesigner.getResizedImage(image, L / 4, L / 4);
				
				return image;
				
			case "PowerUp":
				if(type == 1) {
					image = new ImageIcon(getClass().getResource(ALPHA_POWERUP_IMAGE)).getImage();
				} else if(type == 2) {
					image = new ImageIcon(getClass().getResource(BETA_POWERUP_IMAGE)).getImage();
				} else if(type == 3) {
					image = new ImageIcon(getClass().getResource(GAMMA_POWERUP_IMAGE)).getImage();
				} else if(type == 4){
					image = new ImageIcon(getClass().getResource(SIGMA_POWERUP_IMAGE)).getImage();
				}
				
				image = ImageDesigner.getResizedImage(image, L / 4, L / 4);
				
				return image;
				
			default:
				return image;
		}
	}
}
