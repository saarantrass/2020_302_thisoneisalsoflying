package Domain.Player;

public class Player {
	public double score;
	public double health;
	public Player() {
		this.score = 0;
		this.health = 100;
	}
	public void increaseScore(double points) {
		//MODIFIES: score
		//EFFECTS: score is increased by points.
		this.score += points;
	}
	public void decreaseHealth(double health) {
		//MODIFIES: health
		//EFFECTS: health is increased by health.
		this.health -= health;
	}
	public double getScore() {
		//EFFECTS: Returns score.
		return this.score;
	}
	public double getHealth() {
		//EFFECTS: Returns health.
		return this.health;
	}
}
