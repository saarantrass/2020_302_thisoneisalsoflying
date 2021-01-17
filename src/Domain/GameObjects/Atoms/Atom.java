package Domain.GameObjects.Atoms;

import java.awt.Point;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Domain.Settings;
import Domain.GameObjects.IMovingBehaviour;
import Domain.GameObjects.ShootedStrategy;

public class Atom {
	
	protected int atomID;
	private Point coordinate;
	private int L;
	private double speed;
	private double xSpeed;
	private double ySpeed;
	private double angle;
	protected int neutron;
	private int proton;
	protected double efficiency;

	protected IMovingBehaviour movingBehaviour;
	
	public Atom (int atomID, Point coordinate) {
		this.coordinate = coordinate;
		this.L = Settings.getInstance().getLengthUnit();
		this.speed = (double) L / Settings.timeMult;
		this.proton = 8;
		for(int i=1;i<atomID;i++) {this.proton *= 2;}
		this.movingBehaviour = new ShootedStrategy(this);
		this.efficiency = 0;
	}
	
	
	public Atom() {}
	
	public int getAtomID() {
		return this.atomID;
	}
	
	public double getEfficiency() {
		return this.efficiency;
	}

	public void setEfficiency(double eff) {
		this.efficiency = eff;	
	}

	public void move() {
		this.movingBehaviour.move();
	}
	
	public IMovingBehaviour getMovingBehaviour() {
		return this.movingBehaviour;
	}
	
	public void setMovingBehaviour(IMovingBehaviour b) {
		this.movingBehaviour = b;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		this.xSpeed = speed * Math.sin(Math.toRadians(this.angle));
		this.ySpeed = speed * Math.cos(Math.toRadians(this.angle));
	}
	
	public Point getCoordinate() {
		return this.coordinate;
	}
	
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
		setxSpeed(speed*Math.sin(Math.toRadians(this.angle)));
		setySpeed(speed*Math.cos(Math.toRadians(this.angle)));
	}
	
	public double getxSpeed() {
		return xSpeed;
	}
	
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	public double getySpeed() {
		return ySpeed;
	}
	
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public int getNeutron() {
		return this.neutron;
	}
	
	public int getProton() {
		return this.proton;
	}
	
	public Atom (JsonObject js) {
		Gson gson = new Gson();
		this.atomID = js.get("atomID").getAsInt();
		this.coordinate = gson.fromJson((JsonObject) js.getAsJsonObject("coordinate"), Point.class);
		this.L = js.get("L").getAsInt();
		this.speed = js.get("speed").getAsDouble();
		this.xSpeed = js.get("xSpeed").getAsDouble();
		this.ySpeed = js.get("ySpeed").getAsDouble();
		this.angle = js.get("angle").getAsDouble();
		this.neutron = js.get("neutron").getAsInt();
		this.proton = js.get("proton").getAsInt();
		this.efficiency = js.get("efficiency").getAsDouble();
		this.movingBehaviour = new ShootedStrategy(this);
	}
}
