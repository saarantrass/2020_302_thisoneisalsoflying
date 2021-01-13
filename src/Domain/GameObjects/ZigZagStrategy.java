package Domain.GameObjects;
import java.awt.Point;

import Domain.Settings;


public class ZigZagStrategy implements IMovingBehaviour{
	private transient FallingObject obj;
	public ZigZagStrategy(FallingObject obj) {
		this.obj = obj;
	}
	@Override
	public void move() { //TODO BİR MİKTAR SIKINTI VAR
		if(obj.getCoordinate().x < 0 || obj.getCoordinate().x > (Settings.getInstance().getScreenSize().getWidth() * 7/8)) {
			obj.setxSpeed(-obj.getxSpeed());
			obj.setTravelled(obj.getTravelled() - obj.getL() * Math.sin(Math.toRadians(45)));
		}
		if(obj.getTravelled() < obj.getL() * Math.sin(Math.toRadians(45))) {
			obj.setTravelled(obj.getTravelled() + Math.abs(obj.getxSpeed()));
		} else {
			obj.setxSpeed(-obj.getxSpeed());
			obj.setTravelled(0.0);
		}
		obj.setCoordinate(new Point((int) (obj.getCoordinate().x + obj.getxSpeed()), (int) (obj.getCoordinate().y + obj.getySpeed())));

	}
	

}
