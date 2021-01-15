package Domain;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Domain.GameObjects.AtomFactory;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;
import Domain.Player.Inventory;
import Domain.Player.Player;
import Domain.Player.Shooter;
import Domain.SaveLoad.FileSaveLoadAdapter;
import Domain.SaveLoad.ISaveLoadAdapter;
import Domain.SaveLoad.MongoSaveLoadAdapter;
import UI.IObserver;
public class Game implements IObservable{
	
	private static Game game_instance = null;

	public int L;
	private int difficultyLevel;
	private int timer = 0;

	private List<IObserver> observers = new ArrayList<IObserver>();

	public boolean isPaused = false;
	public boolean isFinished = false;

	ISaveLoadAdapter saveLoadService;
	ISaveLoadAdapter mongoLoadService;

	public CopyOnWriteArrayList<Atom> onScreenAtomList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Molecule> onScreenMoleculeList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<PowerUp> onScreenPowerUpList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<ReactionBlocker> onScreenReactionBlockerList = new CopyOnWriteArrayList<>();

	public Atom barrelAtom = null;
	public PowerUp barrelPowerUp = null; 
	public Shooter shooter = null;
	public Player player = null;



	private Game() {}
	
	
	public static Game getInstance() {
		if(game_instance == null) {
			game_instance = new Game();
		}
		
		return game_instance;
	}
	

	public void startGame(){
		this.L = Settings.getInstance().getLengthUnit();
		this.difficultyLevel = Settings.getInstance().getDifficultyLevel();

		int xShooter = Settings.getInstance().getScreenSize().width * 7/16 - Settings.getInstance().getLengthUnit()/4;
		int yShooter = Settings.getInstance().getScreenSize().height - Settings.getInstance().getLengthUnit();
		this.shooter = new Shooter(new Point(xShooter,  yShooter));
		this.player = new Player();

		if(this.barrelAtom == null) {
			getRandomAtomToBarrel();
		}

		mainGameLoop.start();
	}


	private Thread mainGameLoop = new Thread(() -> {
		while (true) {
			if (Settings.getInstance().timeRemaining <= 0 || this.player.getHealth() <= 0) {
				this.finishGame();
			} else if (!this.isPaused && !this.isFinished) {
				Settings.getInstance().timeRemaining -= 1000 / Settings.timeMult;
				this.continueGame();
			} else {
				try {
					Thread.sleep(1000 / Settings.timeMult);
				} catch(InterruptedException e) {
					// nothing
				}
			}


			//to prevent crash
			try {
				Thread.sleep(1000 / Settings.timeMult);
			} catch (InterruptedException e) {
				while (true) {
					try {
						Thread.sleep(1000 / Settings.timeMult);
					} catch (InterruptedException e2) {
						break;
					}
				}
			}
			//to prevent crash

		}
	});


	private void continueGame() {
		this.timer += 1000 / Settings.timeMult;
		
		if(this.barrelAtom == null)
			if(this.shooter.inventory.getInventoryAtomCount(1) == 0 && this.shooter.inventory.getInventoryAtomCount(2) == 0 && this.shooter.inventory.getInventoryAtomCount(3) == 0 && this.shooter.inventory.getInventoryAtomCount(4) == 0)
				if(this.onScreenAtomList.size() == 0)
					this.finishGame();
		
		if(this.timer % (1000 / this.difficultyLevel) == 0) {
			createRandomFallingObject();
		}
		
		moveThemAll();
		collisionHandler();

		this.publish();
	}


	private void createRandomFallingObject() {
		if(Settings.getInstance().checkFallingObjectLeft()) {
			while(true) {
				Random rn = new Random();
				int next = rn.nextInt(3);
				int type = rn.nextInt(4)+1;
				int xCoord = (int) (Math.random() * (Settings.getInstance().getScreenSize().width * 7/8 - (L/4)));

				if(next == 0) {
					if(Settings.getInstance().getMoleculeNumber(type) > 0) {
						Molecule newMol = FallingObjectFactory.getInstance().getNewMolecule(type , new Point(xCoord, -L/4), Settings.getInstance().isLinear(), Settings.getInstance().isSpinning());
						onScreenMoleculeList.add(newMol);
						Settings.getInstance().decreaseMoleculeNumber(type);
						break;
					}

				} else if(next == 1) {
					if(Settings.getInstance().getPowerUpNumber(type) > 0) {
						PowerUp newPw = FallingObjectFactory.getInstance().getNewPowerUp(type, new Point(xCoord, -L/4),false);
						onScreenPowerUpList.add(newPw);
						Settings.getInstance().decreasePowerUpNumber(type);
						break;
					}

				} else if(next == 2) {
					if(Settings.getInstance().getReactionBlockerNumber(type) > 0) {
						ReactionBlocker bl = FallingObjectFactory.getInstance().getNewReactionBlocker(type, new Point(xCoord, -L/4));
						onScreenReactionBlockerList.add(bl);
						Settings.getInstance().decreaseReactionBlockerNumber(type);
						break;
					}
				}
			}
		} else {
			if(this.onScreenMoleculeList.size() == 0 && this.onScreenPowerUpList.size() == 0 && this.onScreenReactionBlockerList.size() == 0)
				this.finishGame();
		}
	}


	private void moveThemAll() {
		for(Atom atom : onScreenAtomList) {
			atom.move();
			if(atom.getCoordinate().y <= 0) {
				this.onScreenAtomList.remove(atom);
			}
		}
		
		for(Molecule mol : onScreenMoleculeList) {
			mol.move();
			if(mol.getCoordinate().y >= Settings.getInstance().getScreenSize().height) {
				this.onScreenMoleculeList.remove(mol);
			}
		}
		
		for(PowerUp pw: onScreenPowerUpList) {
			pw.move();
			if(pw.getCoordinate().y >= Settings.getInstance().getScreenSize().height) {
				this.onScreenPowerUpList.remove(pw);
			}
		}
		
		if(this.barrelAtom != null) {
			this.barrelAtom.setCoordinate(shooter.getBarrelCoordinate());
		} else if(this.barrelPowerUp != null) {
			this.barrelPowerUp.setCoordinate(shooter.getBarrelCoordinate());
		}
		
		for(ReactionBlocker rb : onScreenReactionBlockerList) {
			rb.move();
			if(rb.getCoordinate().y >= Settings.getInstance().getScreenSize().height) {
				this.explosion(rb);
				this.onScreenReactionBlockerList.remove(rb);
			}
		}
	}


	public void shoot() {
		System.out.println("shooter coord ve angle "+shooter.getCoordinate()+" ang "+shooter.getAngle());
		System.out.println(shooter.getCoordinate().x-Math.sqrt(5*L*L/16)*Math.sin(Math.toRadians(-shooter.getAngle()+Math.atan(0.5))));

		if(this.barrelAtom != null) {

			this.barrelAtom.setAngle(this.shooter.getAngle());
			this.onScreenAtomList.add(this.barrelAtom);
			this.barrelAtom = null;
			getRandomAtomToBarrel();

		} else if(this.barrelPowerUp != null) {

			this.barrelPowerUp.setAngle(this.shooter.getAngle());
			this.onScreenPowerUpList.add(this.barrelPowerUp);
			this.barrelPowerUp = null;
			getRandomAtomToBarrel();
		}
	}


	private void collisionHandler() {
		/*
		 * PowerUp-Shooter Collision
		 */
		for(PowerUp pw: this.onScreenPowerUpList) { //TODO CHECK BOUNDING BOX WHEN SHOOTER IS ROTATED
			Point pcord = pw.getCoordinate();
			Point scord = this.shooter.getCoordinate();
			
			if(scord.x <= pcord.x && pcord.x <= (scord.x + L/2) && scord.y <= pcord.y && pcord.y <= (scord.y + L)) {
				this.onScreenPowerUpList.remove(pw);
				this.shooter.inventory.addInventoryPowerUp(pw.getID());
			}
		}

		
		/*
		 * ReactionBlocker Collisions
		 */
		for(ReactionBlocker rb: this.onScreenReactionBlockerList) {
			Point rCoordCenter = new Point(rb.getCoordinate().x + L/8, rb.getCoordinate().y + L/8);
			
			//PowerUp-ReactionBlocker Collision
			for(PowerUp pw: this.onScreenPowerUpList) {
				if(rb.getID() == pw.getID()) {
					Point pCoord = pw.getCoordinate();
					double distance1 = Math.sqrt((pCoord.x - rCoordCenter.x)*(pCoord.x - rCoordCenter.x) + (pCoord.y - rCoordCenter.y)*(pCoord.y - rCoordCenter.y));
					double distance2 = Math.sqrt(((pCoord.x + L/4) - rCoordCenter.x)*((pCoord.x + L/4) - rCoordCenter.x) + (pCoord.y - rCoordCenter.y)*(pCoord.y - rCoordCenter.y));
					double distance3 = Math.sqrt(((pCoord.x + L/4) - rCoordCenter.x)*((pCoord.x + L/4) - rCoordCenter.x) + ((pCoord.y + L/4) - rCoordCenter.y)*((pCoord.y + L/4) - rCoordCenter.y));
					double distance4 = Math.sqrt((pCoord.x - rCoordCenter.x)*(pCoord.x - rCoordCenter.x) + ((pCoord.y + L/4) - rCoordCenter.y)*((pCoord.y + L/4) - rCoordCenter.y));
					if(pw.isThrown() && (distance1 <= L/2 || distance2 <= L/2 || distance3 <= L/2  || distance4 <= L/2)) {
						this.onScreenReactionBlockerList.remove(rb);
					}
				}
			}
			
			//Molecule-ReactionBlocker Collision
			for(Molecule mol: this.onScreenMoleculeList) {
				if(rb.getID() == mol.getID()) {
					Point mCoord = mol.getCoordinate();
					double distance1 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
					double distance2 = Math.sqrt(((mCoord.x + L/4) - rCoordCenter.x)*((mCoord.x + L/4) - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
					double distance3 = Math.sqrt(((mCoord.x + L/4) - rCoordCenter.x)*((mCoord.x + L/4) - rCoordCenter.x) + ((mCoord.y + L/4) - rCoordCenter.y)*((mCoord.y + L/4) - rCoordCenter.y));
					double distance4 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + ((mCoord.y + L/4) - rCoordCenter.y)*((mCoord.y + L/4) - rCoordCenter.y));
					if(distance1 <= L/2 || distance2 <= L/2 || distance3 <= L/2  || distance4 <= L/2) {
						this.onScreenMoleculeList.remove(mol);
					}
				}
			}
			
			//Atom-ReactionBlocker Collision
			for(Atom atom: this.onScreenAtomList) {
				if(rb.getID() == atom.getAtomID()) {
					Point aCoord = atom.getCoordinate();
					double distance1 = Math.sqrt((aCoord.x - rCoordCenter.x)*(aCoord.x - rCoordCenter.x) + (aCoord.y - rCoordCenter.y)*(aCoord.y - rCoordCenter.y));
					double distance2 = Math.sqrt(((aCoord.x + L/10) - rCoordCenter.x)*((aCoord.x + L/10) - rCoordCenter.x) + (aCoord.y - rCoordCenter.y)*(aCoord.y - rCoordCenter.y));
					double distance3 = Math.sqrt(((aCoord.x + L/10) - rCoordCenter.x)*((aCoord.x + L/10) - rCoordCenter.x) + ((aCoord.y + L/10) - rCoordCenter.y)*((aCoord.y + L/10) - rCoordCenter.y));
					double distance4 = Math.sqrt((aCoord.x - rCoordCenter.x)*(aCoord.x - rCoordCenter.x) + ((aCoord.y + L/10) - rCoordCenter.y)*((aCoord.y + L/10) - rCoordCenter.y));
					if(distance1 <= L/2 || distance2 <= L/2 || distance3 <= L/2  || distance4 <= L/2) {
						this.onScreenAtomList.remove(atom);
					}
				}
			}
		}
		
		
		/*
		 * Atom-Molecule Collision
		 */
		for(Atom atom : this.onScreenAtomList) {
			for(Molecule molecule : this.onScreenMoleculeList) {
				if(atom.getAtomID() == molecule.getID()) {
					Point acord = atom.getCoordinate();
					Point mcord = molecule.getCoordinate();
					if((mcord.x <= acord.x && acord.x <= (mcord.x + L/4) && mcord.y <= acord.y && acord.y <= (mcord.y + L/4)) ||
							(mcord.x <= (acord.x + L/10) && (acord.x + L/10) <= (mcord.x + L/4) && mcord.y <= acord.y && acord.y <= (mcord.y + L/4)) ||	
							(mcord.x <= (acord.x + L/10) && (acord.x + L/10) <= (mcord.x + L/4) && mcord.y <= (acord.y + L/10) && (acord.y + L/10) <= (mcord.y + L/4)) ||
							(mcord.x <= acord.x && acord.x <= (mcord.x + L/4) && mcord.y <= (acord.y + L/10) && (acord.y + L/10) <= (mcord.y + L/4))) {
						this.onScreenAtomList.remove(atom);
						this.onScreenMoleculeList.remove(molecule);
						double score = atom.getEfficiency() + 1/(this.timer/1000.0);
						this.player.increaseScore(score);
					}
				}
			}
		}
	}


	public void explosion(ReactionBlocker rb) {
		Point rCoordCenter = new Point(rb.getCoordinate().x + L/20, rb.getCoordinate().y + L/20);
		
		/*
		 * Shooter
		 */
		Point sCoord = this.shooter.getCoordinate();
		Point bCoord = this.shooter.getBarrelCoordinate();

		double distance1 = Math.sqrt((sCoord.x + L/2 - rCoordCenter.x)*(sCoord.x + L/2 - rCoordCenter.x) + (sCoord.y - rCoordCenter.y)*(sCoord.y - rCoordCenter.y)); //right bottom shooter
		double distance2 = Math.sqrt((sCoord.x - rCoordCenter.x)*(sCoord.x - rCoordCenter.x) + (sCoord.y - rCoordCenter.y)*(sCoord.y - rCoordCenter.y)); //left bottom shooter
		double distance3 = Math.sqrt((bCoord.x + L/20 - rCoordCenter.x)*(bCoord.x + L/20 - rCoordCenter.x) + (bCoord.y + L/10 - rCoordCenter.y)*(bCoord.y + L/10 - rCoordCenter.y)); //point top barrel
		double distance4;
		
		if(distance1 <= L*2 || distance2 <= L*2 || distance3 <= L*2) {
			double minDist = Math.min(Math.min(distance1, distance2), distance3);
			this.player.decreaseHealth((double)((Settings.getInstance().getScreenSize().getWidth() * 7/8) / minDist)); 
			System.out.println("d1: " + distance1 + " d2: " + distance2 + " d3: " + distance3 + " minDist: " + minDist);
		}
		
		/*
		 * Molecule
		 */
		for(Molecule mol : this.onScreenMoleculeList) {
			Point mCoord = mol.getCoordinate();
			distance1 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
			distance2 = Math.sqrt(((mCoord.x + L/4) - rCoordCenter.x)*((mCoord.x + L/4) - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
			distance3 = Math.sqrt(((mCoord.x + L/4) - rCoordCenter.x)*((mCoord.x + L/4) - rCoordCenter.x) + ((mCoord.y + L/4) - rCoordCenter.y)*((mCoord.y + L/4) - rCoordCenter.y));
			distance4 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + ((mCoord.y + L/4) - rCoordCenter.y)*((mCoord.y + L/4) - rCoordCenter.y));
			if(distance1 <= L*2 || distance2 <= L*2 || distance3 <= L*2  || distance4 <= L*2) {
				this.onScreenMoleculeList.remove(mol);
			}
		}
		
		/*
		 * Atom
		 */
		for(Atom atom : this.onScreenAtomList) {
			Point aCoord = atom.getCoordinate();
			distance1 = Math.sqrt((aCoord.x - rCoordCenter.x)*(aCoord.x - rCoordCenter.x) + (aCoord.y - rCoordCenter.y)*(aCoord.y - rCoordCenter.y));
			distance2 = Math.sqrt(((aCoord.x + L/10) - rCoordCenter.x)*((aCoord.x + L/10) - rCoordCenter.x) + (aCoord.y - rCoordCenter.y)*(aCoord.y - rCoordCenter.y));
			distance3 = Math.sqrt(((aCoord.x + L/10) - rCoordCenter.x)*((aCoord.x + L/10) - rCoordCenter.x) + ((aCoord.y + L/10) - rCoordCenter.y)*((aCoord.y + L/10) - rCoordCenter.y));
			distance4 = Math.sqrt((aCoord.x - rCoordCenter.x)*(aCoord.x - rCoordCenter.x) + ((aCoord.y + L/10) - rCoordCenter.y)*((aCoord.y + L/10) - rCoordCenter.y));
			if(distance1 <= L*2 || distance2 <= L*2 || distance3 <= L*2  || distance4 <= L*2) {
				this.onScreenAtomList.remove(atom);
			}
		}
	}


	public void addShield(int type) {
		if(this.shooter.inventory.getInventoryShieldCount(type) > 0) {
			//System.out.println("önce speed "+this.barrelAtom.getSpeed()+ " eff "+this.barrelAtom.getEfficiency());
			this.barrelAtom = AtomFactory.getInstance().addNewShield(type,this.barrelAtom);

			//System.out.println("snr speed "+this.barrelAtom.getSpeed()+ " eff "+this.barrelAtom.getEfficiency());
			this.shooter.inventory.removeInventoryShield(type);
		}
	}


	public void getRandomAtomToBarrel() {
		Atom atom = this.shooter.inventory.getRandomAtom();
		
		if(atom != null) {
			if(this.barrelAtom != null) 
				this.shooter.inventory.addInventoryAtom(this.barrelAtom);
			else if(this.barrelPowerUp != null) 
				this.shooter.inventory.addInventoryPowerUp(this.barrelPowerUp);
			
			this.barrelPowerUp = null;
			this.barrelAtom = atom;
			this.barrelAtom.setCoordinate(this.shooter.getBarrelCoordinate());
			this.barrelAtom.setAngle(this.shooter.getAngle());			
		}
	}


	public void getPowerUpToBarrel(int type) {
		PowerUp pw = this.shooter.inventory.getPowerUp(type);

		if(pw != null) {
			if(this.barrelAtom != null) 
				this.shooter.inventory.addInventoryAtom(this.barrelAtom);
			else if(this.barrelPowerUp != null) 
				this.shooter.inventory.addInventoryPowerUp(this.barrelPowerUp);

			this.barrelAtom = null;
			this.barrelPowerUp = pw;
			this.barrelPowerUp.setCoordinate(this.shooter.getBarrelCoordinate());
			this.barrelPowerUp.setAngle(this.shooter.getAngle());
		}
	}


	public void pauseGame() {
		this.isPaused = true;

	}


	public void saveGame() {
		// Use env variable to switch between
		String saveMethod = System.getenv("SAVE_METHOD");
		if (saveMethod.equalsIgnoreCase("file")) {
			saveLoadService = new FileSaveLoadAdapter();
			saveLoadService.save();			
		} else if (saveMethod.equalsIgnoreCase("mongo")) {
			this.mongoLoadService = new MongoSaveLoadAdapter();
			this.mongoLoadService.save();		
		} else {
			// Wrong or no env variable set
			System.out.println("Wrong or no env variable set. Unable to save, please set mongo or file to SAVE_METHOD");
		}
	}


	public void loadGame() {
		saveLoadService = new FileSaveLoadAdapter();
		try {
			System.out.println("burda");
			saveLoadService.load();
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void resumeGame() {
		this.isPaused = false;
	}


	private void finishGame() {
		if(!this.isFinished) {
			this.isFinished = true;
			publish();			
		}
	}


	public void quitGame() {
		game_instance = null;
	}


	public double getRemainingTime() {
		return Settings.getInstance().timeRemaining;
	}


	public void moveShooter(int direction) {
		this.shooter.move(direction);
	}
	
	
	public void stopMoveShooter() {
		this.shooter.stopMove();
	}
	
	
	public void rotateShooter(int direction) {
		this.shooter.rotate(direction);
	}
	
	
	public void stopRotateShooter() {
		this.shooter.stopRotate();
	}
	
	
	public Inventory getShooterInventory() {
		return this.shooter.inventory; //TODO
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
