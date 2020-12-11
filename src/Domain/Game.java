package Domain;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import Domain.GameObjects.Atom;
import Domain.GameObjects.Molecule;
import Domain.GameObjects.PowerUp;
import Domain.GameObjects.ReactionBlocker;
import Domain.Player.Shooter;
import UI.IObserver;

public class Game implements IObservable{
	
	private Thread mainGameLoop;
	private static Game game_instance = null;
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	public ArrayList<Atom> onScreenAtomList = new ArrayList<>();
	public ArrayList<Molecule> onScreenMoleculeList = new ArrayList<>();
	public ArrayList<PowerUp> onScreenPowerUpList = new ArrayList<>();
	public ArrayList<ReactionBlocker> onScreenReactionBlockerList = new ArrayList<>();
	
	public Atom barrelAtom = null; //TODO add coordinate change when shooter moves
	public PowerUp barrelPowerUp = null; 
	
	private int timer = 0;
	
	private GameController GC;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //TODO
	
	private Game() {
		mainGameLoop = new Thread(() -> {
			while (true) {
				//TODO call functions from game controller
				this.continueGame();
				System.out.println("Mols: " + this.onScreenMoleculeList);
				System.out.println("Powss: " + this.onScreenPowerUpList);
				System.out.println("blockers: " + this.onScreenReactionBlockerList);
				
				
				//to prevent crash
				try {
					Thread.sleep(100);
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
	
	public void continueGame() {
		this.timer++;
		if(this.timer % 20 == 0) {
			createRandomFallingObject();
		}
		moveThemAll();
	}
	
	private void createRandomFallingObject() {
		int next = (int) (Math.random() * 3);
		int type = (int) ((Math.random() * 4));
		switch (next) {
		case 0:
			Molecule newMol = new Molecule(type , new Point(100, 0), true, false);
			onScreenMoleculeList.add(newMol);
			break;
		case 1:
			PowerUp newPw = new PowerUp(type, new Point(0,0));
			onScreenPowerUpList.add(newPw);
			break;
		case 2:
			ReactionBlocker bl = new ReactionBlocker(type, new Point(0,0));
			onScreenReactionBlockerList.add(bl);
			break;

		default:
			break;
		}
	}
	
	private void moveThemAll() {
		for(Atom atom : onScreenAtomList) {
			atom.move();
		}
		for(Molecule mol : onScreenMoleculeList) {
			mol.move();
		}
		for(ReactionBlocker bl : onScreenReactionBlockerList) {
			bl.move();
		}
		for(PowerUp pw: onScreenPowerUpList) {
			pw.move();
		}
	}
	
	public void shoot() {
		if(this.barrelAtom != null) {
			this.onScreenAtomList.add(this.barrelAtom);
			this.barrelAtom = null;
			//TODO get 1 more random atom to barrel
		}else {
			this.onScreenPowerUpList.add(this.barrelPowerUp);
			this.barrelPowerUp = null;
			//TODO get 1 more random atom to barrel
		}
	}
	
	public void getRandomAtomToBarrel() {
		int type = (int) ((Math.random() * 4));
		this.barrelPowerUp = null;
		this.barrelAtom = new Atom(type, new Point(0,0)); //TODO Inventory checks and coordinates to shooter end
	}
	
	public void getPowerUpToBarrel(int type) {
		this.barrelAtom = null;
		this.barrelPowerUp = new PowerUp(type, new Point(0,0), true); //TODO Inventory checks and coordinates to shooter end
	}
	
	@SuppressWarnings("deprecation")
	public void pauseGame() {this.mainGameLoop.stop();}
	
	public void startGame(GameController GC){
		this.GC = GC;
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
	
	public GameController getGC() {
		return GC;
	}
	
	

}
