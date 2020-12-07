package Domain;

import java.util.ArrayList;
import java.util.List;

import Domain.Player.Shooter;
import UI.IObserver;

public class Game implements IObservable{
	
	private static Game game_instance = null;
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	private Shooter shooter;
	private GameController GC;
	
	private Game() {
		
	}
	
	public static Game getInstance() {
		if(game_instance == null) {
			game_instance = new Game();
		}
		
		return game_instance;
	}

	@Override
	public void add(IObserver o) {
		this.observers.add(o);
	}

	@Override
	public void remove(IObserver o) {
		this.observers.remove(o);
		
	}

	@Override
	public void publish() {
		for(IObserver o: this.observers) o.update();
	}

	public Shooter getShooter() {
		return shooter;
	}
	
	public GameController getGC() {
		return GC;
	}
	
	

}
