package Domain;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import Domain.GameObjects.AtomFactory;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.PowerUp;
import Domain.GameObjects.ReactionBlocker;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.Atoms.Throwable;
import Domain.GameObjects.Atoms.Shields.ShieldDecorator;
import Domain.GameObjects.Molecules.Molecule;
import Domain.Player.Player;
import Domain.Player.Shooter;
import Domain.SaveLoad.FileSaveLoadAdapter;
import Domain.SaveLoad.ISaveLoadAdapter;
import Domain.SaveLoad.MongoSaveLoadAdapter;
import UI.IObserver;
import UI.Swing.ScreenCoordinator;

public class Game implements IObservable{

	private GameController GC;
	public int L;
	private static Game game_instance = null;
	private List<IObserver> observers = new ArrayList<IObserver>();
	public boolean isPaused = false;
	public boolean isFinished = false;
	ISaveLoadAdapter saveLoadService;
	ISaveLoadAdapter mongoLoadService;

	public CopyOnWriteArrayList<Throwable> onScreenAtomList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Molecule> onScreenMoleculeList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<PowerUp> onScreenPowerUpList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<ReactionBlocker> onScreenReactionBlockerList = new CopyOnWriteArrayList<>();

	public Throwable barrelAtom = null;
	public PowerUp barrelPowerUp = null; 
	public Shooter shooter = null;
	public Player player = null;

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
		this.player = new Player();
		if(this.barrelAtom == null) {
			getRandomAtomToBarrel();
		}
		mainGameLoop.start();
	}


	private Thread mainGameLoop = new Thread(() -> {
		while (true) {
			if (this.GC.settings.timeRemaining <= 0) {
				this.finishGame();
			} else if (!this.isPaused && !this.isFinished) {
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
		//TODO: Adjust this according to the difficulty level/check if correct
		//TODO: settings.getins yerine ba�ta initialize etsek ya?
		if(this.timer % (10/Settings.getInstance().getDifficultyLevel()) == 0) {
			createRandomFallingObject();
		}

		moveThemAll();
		collisionHandler();
		this.publish();
	}


	private void createRandomFallingObject() {
		Random rn = new Random();
		int type = rn.nextInt(4)+1;
		int next = rn.nextInt(3);
		int xCoord = (int) (Math.random() * (ScreenCoordinator.SCREEN_SIZE.getWidth() * 7 / 8)) - (L/4); //TODO: Random check
		switch (next) {
		case 0:
			if(Settings.getInstance().getMoleculeNumber(type) > 0) {
				//TODO: shorten the factory.getinstance.blahblah......
				Molecule newMol = FallingObjectFactory.getInstance().getNewMolecule(type , new Point(xCoord, -L/4), Settings.getInstance().isLinear(), Settings.getInstance().isSpinning());
				onScreenMoleculeList.add(newMol);
				Settings.getInstance().decreaseMoleculeNumber(type);
			}
			break;
		case 1:
			if(Settings.getInstance().getPowerUpNumber(type) > 0) {
				PowerUp newPw = FallingObjectFactory.getInstance().getNewPowerUp(type, new Point(xCoord, -L/4));
				onScreenPowerUpList.add(newPw);
				Settings.getInstance().decreasePowerUpNumber(type);
			}
			break;
		case 2:
			if(Settings.getInstance().getReactionBlockerNumber(type) > 0) {
				ReactionBlocker bl = FallingObjectFactory.getInstance().getNewReactionBlocker(type, new Point(xCoord, -L/4));
				onScreenReactionBlockerList.add(bl);
				Settings.getInstance().decreaseReactionBlockerNumber(type);
			}
			break;

		default:
			break;
		}
	}


	private void moveThemAll() {

		for(Throwable atom : onScreenAtomList) {
			atom.move();
			if(atom.getCoordinate().y <= 0) {
				this.onScreenAtomList.remove(atom);
			}
		}
		for(Molecule mol : onScreenMoleculeList) {
			mol.move();
			if(mol.getCoordinate().y >= ScreenCoordinator.SCREEN_SIZE.height) {
				this.onScreenMoleculeList.remove(mol);
			}
		}
		for(ReactionBlocker rb : onScreenReactionBlockerList) {
			rb.move();
			if(rb.getCoordinate().y >= ScreenCoordinator.SCREEN_SIZE.height) {
				this.onScreenReactionBlockerList.remove(rb);
			}
		}
		for(PowerUp pw: onScreenPowerUpList) {
			pw.move();
			if(pw.getCoordinate().y >= ScreenCoordinator.SCREEN_SIZE.height) {
				this.onScreenPowerUpList.remove(pw);
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
			this.shooter.inventory.removeInventoryAtom(this.barrelAtom.getAtomID(),1);
			this.onScreenAtomList.add(this.barrelAtom);
			getRandomAtomToBarrel();

		} else if(this.barrelPowerUp != null) {

			this.barrelPowerUp.setAngle(this.shooter.getAngle());
			this.shooter.inventory.removeInventoryPowerUp(this.barrelPowerUp.getPowerUpID());
			this.onScreenPowerUpList.add(this.barrelPowerUp);
			getRandomAtomToBarrel();
		}
	}


	private void collisionHandler() {
		//TODO: reaction blocker blocks the atom-molecule unification!!!!
		//TODO: powerup ba�ka reactiona de�ince yok oluyormu�
		/*
		 * Atom-Molecule Collision
		 */
		for(Throwable atom : this.onScreenAtomList) {
			for(Molecule molecule : this.onScreenMoleculeList) {
				if(atom.getAtomID() == molecule.getMoleculeID()) {
					Point acord = atom.getCoordinate();
					Point mcord = molecule.getCoordinate();
					if(mcord.x <= acord.x && acord.x <= (mcord.x + L/4) && mcord.y <= acord.y && acord.y <= (mcord.y + L/4)) {//TODO: Check bounding box
						double score = atom.getEfficiency();
						this.onScreenAtomList.remove(atom);
						this.onScreenMoleculeList.remove(molecule);
						this.player.increaseScore(score); //TODO change score/check here
					}
				}
			}
		}


		/*
		 * PowerUp-Shooter Collision
		 */
		for(PowerUp pw: this.onScreenPowerUpList) { //TODO: Include bounding box calculations for when shooter is rotated
			Point pcord = pw.getCoordinate();
			Point scord = this.shooter.getCoordinate();
			if(scord.x <= pcord.x && pcord.x <= (scord.x + L/2) && scord.y <= pcord.y && pcord.y <= (scord.y + L)) {
				this.onScreenPowerUpList.remove(pw);
				this.shooter.inventory.addInventoryPowerUp(pw.getPowerUpID());
			}
		}

		/*
		 * PowerUp-ReactionBlocker Collision
		 */
		for(ReactionBlocker rb: this.onScreenReactionBlockerList) {
			for(PowerUp pw: this.onScreenPowerUpList) {
				if(rb.getReactionBlockerID() == pw.getPowerUpID()) {
					Point pCoord = pw.getCoordinate();
					Point rCoordCenter = new Point(rb.getCoordinate().x + L/20, rb.getCoordinate().y + L/20);
					double distance1 = Math.sqrt((pCoord.x - rCoordCenter.x)*(pCoord.x - rCoordCenter.x) + (pCoord.y - rCoordCenter.y)*(pCoord.y - rCoordCenter.y));
					double distance2 = Math.sqrt(((pCoord.x + L/10) - rCoordCenter.x)*((pCoord.x + L/10) - rCoordCenter.x) + (pCoord.y - rCoordCenter.y)*(pCoord.y - rCoordCenter.y));
					double distance3 = Math.sqrt(((pCoord.x + L/10) - rCoordCenter.x)*((pCoord.x + L/10) - rCoordCenter.x) + ((pCoord.y + L/10) - rCoordCenter.y)*((pCoord.y + L/10) - rCoordCenter.y));
					double distance4 = Math.sqrt((pCoord.x - rCoordCenter.x)*(pCoord.x - rCoordCenter.x) + ((pCoord.y + L/10) - rCoordCenter.y)*((pCoord.y + L/10) - rCoordCenter.y));
					if(distance1 <= L/2 || distance2 <= L/2 || distance3 <= L/2  || distance4 <= L/2) {
						this.onScreenReactionBlockerList.remove(rb);
					}
				}
			}
		}


	}


	public void addShield(int type) {
		ShieldDecorator at = AtomFactory.getInstance().addNewShield(type,this.barrelAtom);
		at.addShield(type);
		System.out.println(at.getEfficiency());
		this.barrelAtom=at;
		this.shooter.inventory.removeInventoryShield(type);
	}
	
	public void getRandomAtomToBarrel() {
		Random rn = new Random();
		int type = rn.nextInt(4)+1;
		while(!this.shooter.inventory.checkAtomAvailability(type, 1)) {
			type = (int) (1 + (Math.random() * 3));
		}

		this.barrelPowerUp = null;
		this.barrelAtom = AtomFactory.getInstance().getNewAtom(type, this.shooter.getBarrelCoordinate());
		this.barrelAtom.setAngle(this.shooter.getAngle());
	}


	public void getPowerUpToBarrel(int type) {
		if(this.shooter.inventory.checkPowerUpAvailability(type, 1)) {
			this.barrelAtom = null;
			this.barrelPowerUp = new PowerUp(type, this.shooter.getBarrelCoordinate(), true);
			this.barrelPowerUp.setAngle(this.shooter.getAngle());
		}
	}


	public void pauseGame() {
		this.isPaused = true;
		saveLoadService = new FileSaveLoadAdapter();
		saveLoadService.save();
		this.mongoLoadService = new MongoSaveLoadAdapter();
		this.mongoLoadService.save();
	}


	public void resumeGame() {
		this.isPaused = false;
	}


	private void finishGame() {
		this.isFinished = true;
		publish();
	}
	
	
	public void quitGame() {
		game_instance = null;
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
