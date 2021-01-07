package Domain.GameObjects;
import java.awt.Point;

public abstract class Throwable {

	public abstract double getEfficiency();
	public abstract void setCoordinate(Point coordinate) ;
	public abstract Point getCoordinate() ;
	public abstract void move() ;
	public abstract void setAngle(double angle) ;
	public abstract int getAtomID() ;
	public abstract void setSpeed(double speed) ;
	public abstract double getSpeed() ;
	public abstract int getNeutron() ;
	public abstract int getProton() ;
	public abstract double getxSpeed();
	public abstract void setxSpeed(double xSpeed) ;
	public abstract double getySpeed() ;
	public abstract void setySpeed(double ySpeed) ;
}
