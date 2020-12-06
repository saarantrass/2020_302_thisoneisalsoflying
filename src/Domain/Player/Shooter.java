package Domain.Player;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import Domain.IObservable;
import UI.IObserver;

public class Shooter implements IObservable{
	
	private int x;
	private int y;
	private int speed = 10; //TODO
	private List<IObserver> observers = new ArrayList<IObserver>();
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public Shooter() {
		this.x = screenSize.width / 2;
		this.y = screenSize.height / 2;
	}
	
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	
	public void move(int direction) {
		
		if(direction == 0) { //left
			//if(this.x - speed) {
				this.x -= speed;
				System.out.println("left");
			//}
		} else if(direction == 1) { //rigth
			//if(this.x + speed) {
				this.x += speed;
			//}
		}
		
		publish();
	}

	
	@Override
	public void add(IObserver o) {
		observers.add(o);
	}


	@Override
	public void remove(IObserver o) {
		observers.remove(o);	
	}
	
	
	@Override
	public void publish() {
		for(IObserver o: observers) o.update();
	}
}
