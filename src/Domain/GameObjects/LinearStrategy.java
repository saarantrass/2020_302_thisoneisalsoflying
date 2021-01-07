package Domain.GameObjects;

import java.awt.Point;


public class LinearStrategy implements IMovingBehaviour{
	private transient FallingObject obj;
	public LinearStrategy(FallingObject obj) {
		this.obj = obj;
	}
	@Override
	public void move() {
		obj.setCoordinate(new Point(obj.getCoordinate().x, (int) (obj.getCoordinate().y + obj.getSpeed())));
	}



}
