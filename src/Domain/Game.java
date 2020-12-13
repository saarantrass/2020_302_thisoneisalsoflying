package Domain;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Domain.GameObjects.Atom;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Molecule;
import Domain.GameObjects.PowerUp;
import Domain.GameObjects.ReactionBlocker;
import Domain.Player.Shooter;
import UI.IObserver;
import UI.Swing.ScreenCoordinator;

public class Game implements IObservable{
	
	private GameController GC;
	private int L;
	private static Game game_instance = null;
	private List<IObserver> observers = new ArrayList<IObserver>();
	public boolean isPaused = false;
	
	public ArrayList<Atom> onScreenAtomList = new ArrayList<>();
	public ArrayList<Molecule> onScreenMoleculeList = new ArrayList<>();
	public ArrayList<PowerUp> onScreenPowerUpList = new ArrayList<>();
	public ArrayList<ReactionBlocker> onScreenReactionBlockerList = new ArrayList<>();
	
	public LinkedList<Atom> toBeRemovedAtomList = new LinkedList<Atom>();
	public LinkedList<Molecule> toBeRemovedMoleculeList = new LinkedList<Molecule>();
	public LinkedList<PowerUp> toBeRemovedPowerUpList = new LinkedList<PowerUp>();
	public LinkedList<ReactionBlocker> toBeRemovedReactionBlockerList = new LinkedList<ReactionBlocker>();
	
	public Atom barrelAtom = null;
	public PowerUp barrelPowerUp = null; 
	public Shooter shooter;
	
	private int timer = 0;
	
	
	private Game() {}
	
	
	public static Game getInstance() {
		if(game_instance == null) {
			game_instance = new Game();
		}
		
		return game_instance;
	}
	
	
	public void startGame(GameController GC){
		this.GC = GC;
		this.L = Settings.getInstance().getLengthUnit();
		int xShooter = ScreenCoordinator.SCREEN_SIZE.width * 7/16;
		int yShooter = ScreenCoordinator.SCREEN_SIZE.height - this.GC.settings.getLengthUnit();
		this.shooter = new Shooter(new Point(xShooter,  yShooter));
		if(this.barrelAtom == null) {
			getRandomAtomToBarrel();
		}
		mainGameLoop.start();
	}
	
	
	private Thread mainGameLoop = new Thread(() -> {
		while (true) {
			//TODO call functions from game controller
			if (this.GC.settings.timeRemaining <= 0) {
				this.finishGame();
			} else if (!this.isPaused) {
				this.continueGame();
				this.GC.settings.timeRemaining -= 100;
			} else {
				try {
					Thread.sleep(100);
                } catch(InterruptedException e) {
                    // nothing
                }
			}
			
			
			
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
	
	
	private void continueGame() {
		
		this.timer++;
		if(this.timer % 10 == 0) {
			createRandomFallingObject();
		}
		
		moveThemAll();
		this.removeOutBoundaryGameObjects();
		collisionHandler();
		this.removeOutBoundaryGameObjects();
		this.publish();
	}
	
	
	private void removeOutBoundaryGameObjects() {
		for(Atom atom : this.toBeRemovedAtomList) {
			this.onScreenAtomList.remove(atom);
		}
		for(Molecule mol : this.toBeRemovedMoleculeList) {
			this.onScreenMoleculeList.remove(mol);
		}
		for(ReactionBlocker rb : this.toBeRemovedReactionBlockerList) {
			this.onScreenReactionBlockerList.remove(rb);
		}
		for(PowerUp pw: this.toBeRemovedPowerUpList) {
			this.onScreenPowerUpList.remove(pw);
		}
		this.toBeRemovedAtomList.clear();
		this.toBeRemovedMoleculeList.clear();
		this.toBeRemovedPowerUpList.clear();
		this.toBeRemovedReactionBlockerList.clear();
	}
	
	
	private void createRandomFallingObject() {
		int next = (int) (Math.random() * 3);
		int type = (int) (1 + (Math.random() * 3));
		int xCoord = (int) (Math.random() * (ScreenCoordinator.SCREEN_SIZE.getWidth() * 7 / 8)) - 30;
		switch (next) {
			case 0:
				if(Settings.getInstance().getMoleculeNumber(type) > 0) {
					Molecule newMol = FallingObjectFactory.getInstance().getNewMolecule(type , new Point(xCoord, 0), Settings.getInstance().isLinear(), Settings.getInstance().isSpinning());
					onScreenMoleculeList.add(newMol);
					Settings.getInstance().decreaseMoleculeNumber(type);
				}
				break;
			case 1:
				if(Settings.getInstance().getPowerUpNumber(type) > 0) {
					PowerUp newPw = FallingObjectFactory.getInstance().getNewPowerUp(type, new Point(xCoord, 0));
					onScreenPowerUpList.add(newPw);
					Settings.getInstance().decreasePowerUpNumber(type);
				}
				break;
			case 2:
				if(Settings.getInstance().getReactionBlockerNumber(type) > 0) {
					ReactionBlocker bl = FallingObjectFactory.getInstance().getNewReactionBlocker(type, new Point(xCoord, 0));
					onScreenReactionBlockerList.add(bl);
					Settings.getInstance().decreaseReactionBlockerNumber(type);
				}
				break;

			default:
				break;
		}
	}
	
	
	private void moveThemAll() {
		
		for(Atom atom : onScreenAtomList) {
			atom.move();
			if(atom.getCoordinate().y <= 0) {
				this.toBeRemovedAtomList.add(atom);
			}
		}
		for(Molecule mol : onScreenMoleculeList) {
			mol.move();
			if(mol.getCoordinate().y >= ScreenCoordinator.SCREEN_SIZE.height) {
				this.toBeRemovedMoleculeList.add(mol);
			}
		}
		for(ReactionBlocker rb : onScreenReactionBlockerList) {
			rb.move();
			if(rb.getCoordinate().y >= ScreenCoordinator.SCREEN_SIZE.height) {
				this.toBeRemovedReactionBlockerList.add(rb);
			}
		}
		for(PowerUp pw: onScreenPowerUpList) {
			pw.move();
			if(pw.getCoordinate().y >= ScreenCoordinator.SCREEN_SIZE.height) {
				this.toBeRemovedPowerUpList.add(pw);
			}
		}
		
		if(this.barrelAtom != null) {
			this.barrelAtom.setCoordinate(shooter.getBarrelCoordinate());
		} else if(this.barrelPowerUp != null) {
			this.barrelPowerUp.setCoordinate(shooter.getBarrelCoordinate());
		}
	}
	
	
	public void shoot() {
		if(this.barrelAtom != null) {
			
			this.barrelAtom.setAngle(this.shooter.getAngle());
			this.shooter.inventory.removeInventoryAtom(this.barrelAtom.getAtomID());
			this.onScreenAtomList.add(this.barrelAtom);
			getRandomAtomToBarrel();
			
		} else if(this.barrelPowerUp != null) {
			
			this.barrelPowerUp.setAngle(this.shooter.getAngle());
			this.shooter.inventory.removeInventoryPowerUp(this.barrelPowerUp.getPowerUpID());
			this.onScreenPowerUpList.add(this.barrelPowerUp);
			getRandomAtomToBarrel();
		}
		//this.publish();
	}
		
		
	private void collisionHandler() {
		
		//Atom - Molecule
		
		for(Atom atom : this.onScreenAtomList) {
			for(Molecule molecule : this.onScreenMoleculeList) {
				if(atom.getAtomID() == molecule.getMoleculeID()) {
					Point acord = atom.getCoordinate();
					Point mcord = molecule.getCoordinate();
					if(mcord.x <= acord.x && acord.x <= (mcord.x + L/4) && mcord.y <= acord.y && acord.y <= (mcord.y + L/4)) {
						toBeRemovedAtomList.add(atom);
						toBeRemovedMoleculeList.add(molecule);
						this.shooter.increaseScore(1); //TODO change score
					}
				}
			}
		}
		
		//TODO when shooter is rotated?
		/*for(PowerUp pw: this.onScreenPowerUpList) {
			Point pcord = pw.getCoordinate();
			Point scord = this.shooter.getCoordinate();
			if(scord.x <= pcord.x && pcord.x <= (scord.x + L/2) && scord.y <= pcord.y && pcord.y <= (scord.y + L)) {
				toBeRemovedPowerUpList.add(pw);
				this.shooter.inventory.addInventoryPowerUp(pw.getPowerUpID());
			}
		}*/
		
		
	}

	
	public void getRandomAtomToBarrel() {
		int type = (int) Math.round((1 + (Math.random() * 3)));
		if(this.shooter.inventory.checkAtomAvailability(type, 1)) {
			this.barrelPowerUp = null;
			this.barrelAtom = new Atom(type, this.shooter.getBarrelCoordinate());//TODO Inventory checks and coordinates to shooter end
			this.barrelAtom.setAngle(this.shooter.getAngle());
		} else {
			type = (int) (1 + (Math.random() * 3));
		}
		//this.publish();
	}
	
	
	public void getPowerUpToBarrel(int type) {
		if(this.shooter.inventory.checkPowerUpAvailability(type, 1)) {
			this.barrelAtom = null;
			this.barrelPowerUp = new PowerUp(type, this.shooter.getBarrelCoordinate(), true); //TODO Inventory checks and coordinates to shooter end			
			this.barrelPowerUp.setAngle(this.shooter.getAngle());
		}
		//this.publish();
	}


	public void pauseGame() {
		this.isPaused = true;
	}
	
	
	public void resumeGame() {
		this.isPaused = false;
	}
	
	
	private void finishGame() {
		publish();
		// The time is finished, have to make isPaused = false in GameModePanel to stop shooter
		// TODO write code to display Game Over screen
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

}
