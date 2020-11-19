package Classes;

public class Player {
	
	public int health;
	public float score;
	
	public Player(int health, float score) {
		this.health = health;
		this.score = score;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	
	
}
