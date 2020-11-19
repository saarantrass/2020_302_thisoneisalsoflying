package Classes;

public class Game {
	private boolean created = false;
	private boolean paused = false;
	private float timeLeft;
	
	public Game(float timeLeft) {
		this.timeLeft = timeLeft;
	}
}
