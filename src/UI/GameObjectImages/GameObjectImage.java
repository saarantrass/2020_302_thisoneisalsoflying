package UI.GameObjectImages;

import java.awt.Canvas;
import java.awt.Image;
import UI.IObserver;

@SuppressWarnings("serial")
public abstract class GameObjectImage extends Canvas implements IObserver {
	
	protected int x;
	protected int y;
	protected int type;
	protected String imageName;
	protected Image image;
	
	
	public GameObjectImage(int type, int x, int y) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	
	public abstract void setImageName(int type);
	
}
