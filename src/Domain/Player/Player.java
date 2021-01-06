package Domain.Player;

public class Player {
	public double score;
	public double health;
	public Player() {
		this.score = 0;
		this.health = 100;
	}
	public void increaseScore(double points) {
		this.score += points;
	}
	public void decreaseHealth(double health) {
		this.health -= health;
	}
	public double getScore() {
		return this.score;
	}
	public double getHealth() {
		return this.health;
	}
}
