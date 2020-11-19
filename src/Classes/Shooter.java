package Classes;

import java.util.HashMap;

public class Shooter {
	private HashMap<String, Integer> inventory;
	private float coordinates;
	private float velocity;
	public int direction;
	public int angle;
	
	public Shooter(float coordinates, float velocity, int direction, int angle) {
		super();
		this.inventory = new HashMap<String, Integer>();
		this.coordinates = coordinates;
		this.velocity = velocity;
		this.direction = direction;
		this.angle = angle;
	}
	
	
}
