package Domain;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import Domain.Player.Shooter;
import UI.IObserver;

public class Game implements IObservable{
	
	private Thread mainGameLoop;
	private static Game game_instance = null;
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	private Shooter shooter;
	private GameController GC;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //TODO
	
	private Game() {
		mainGameLoop = new Thread(() -> {
			while (true) {
				//System.out.println("game loop");
				//TODO call functions from game controller
				
				
				
				
				//to prevent crash
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
                    while (true) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e2) {
                            break;
                        }
                    }
				}
				//to prevent crash
				
			}
		});
	}
	
	public static Game getInstance() {
		if(game_instance == null) {
			game_instance = new Game();
		}
		
		return game_instance;
	}
	
	public void startGame(){
		mainGameLoop.start();
	}

	@Override
	public void add(IObserver o) {
		this.observers.add(o);
		publish(); //tODO
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
