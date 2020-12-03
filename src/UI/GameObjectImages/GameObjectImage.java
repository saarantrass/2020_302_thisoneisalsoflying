package UI.GameObjectImages;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObjectImage extends Canvas {

	protected int type;
	protected String imageName;
	protected Image image;
	
	
	public GameObjectImage(int type) {
		super();
		this.type = type;
	}
	
	
	public abstract void setImageName(int type);
	
	
	public void paint(Graphics g) {
		super.paint(g);
	};
	
	
	public int getType() {
		return type;
	}
	
	
	public String getImageName() {
		return imageName;
	}
	
	
	public Image getImage() {
		return image;
	}
	
	
	public void setImage(Image image) {
		this.image = image;
	}
	

}
