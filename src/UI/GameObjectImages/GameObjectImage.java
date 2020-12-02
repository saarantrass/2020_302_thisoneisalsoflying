package UI.GameObjectImages;

import java.awt.Image;

public abstract class GameObjectImage {
	
	protected double x;
	protected double y;
	protected int type;
	protected String imageName;
	protected Image image;
	
	
	public GameObjectImage(int type) {
		this.type = type;
	}
	
	
	public abstract void setImageName(int type);
	
	
	public double getX() {
		return x;
	}
	
	
	public void setX(double x) {
		this.x = x;
	}
	
	
	public double getY() {
		return y;
	}
	
	
	public void setY(double y) {
		this.y = y;
	}
	
	
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
