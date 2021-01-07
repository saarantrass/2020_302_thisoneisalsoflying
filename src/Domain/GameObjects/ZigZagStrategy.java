package Domain.GameObjects;
import java.awt.Point;


public class ZigZagStrategy implements IMovingBehaviour{
	private transient FallingObject obj;
	public ZigZagStrategy(FallingObject obj) {
		this.obj = obj;
	}
	@Override
	public void move() {
		if(obj.getTravelled() < obj.getL() * Math.sin(Math.toRadians(45))) {
			obj.setTravelled(obj.getTravelled() + Math.abs(obj.getxSpeed()));
		} else {
			obj.setxSpeed(-obj.getxSpeed());
			obj.setTravelled(0.0);
		}
		obj.setCoordinate(new Point((int) (obj.getCoordinate().x + obj.getxSpeed()), (int) (obj.getCoordinate().y + obj.getySpeed())));

	}
	

}
