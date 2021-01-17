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
public class Game implements IObservable{
	
	private static Game game_instance = null;

	private int L;
	private int difficultyLevel;
	public int timer = 0;

	private List<IObserver> observers = new ArrayList<IObserver>();

	private boolean isPaused = false;
	private boolean isFinished = false;

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
			}

			try {
				Thread.sleep(1000 / Settings.timeMult);
			} catch (InterruptedException e) {
				System.out.println(e);
				break;
			}
		}
	});


	private void continueGame() {
		this.timer += 1000 / Settings.timeMult;
		
		if(this.barrelAtom == null)
			if(getShooterInventory().getInventoryAtomCount(1) == 0 && getShooterInventory().getInventoryAtomCount(2) == 0 && getShooterInventory().getInventoryAtomCount(3) == 0 && getShooterInventory().getInventoryAtomCount(4) == 0)
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
	
	private ArrayList<Point> rotatePoints(Point lt) {
		ArrayList<Point> resList = new ArrayList<Point>();
		resList.add(new Point(lt.x, lt.y));
		resList.add(new Point(lt.x + L/2, lt.y)); // rt
		resList.add(new Point(lt.x + L/2, lt.y + L)); // rb
		resList.add(new Point(lt.x, lt.y + L)); // lb
		
		if (this.shooter.getAngle() != 0.0) {
			double dy = 0;
			double dx = 0;
			double ang = this.shooter.getAngle();
			for (Point p : resList) {
				double radians = Math.toRadians(ang);
				dy = L * (1 - Math.cos(radians));
				dx = L * Math.sin(radians);
				p.x = (int) (p.getX() + dx);
				p.y = (int) (p.getY() + dy);
			}			
		}
		
		return resList;
	}
	
	private boolean checkPointBounds(ArrayList<Point> bounds, Point c) {
		double sum = 0.0;
		int j = 0;
		for (int i=0; i<bounds.size(); i++) {
			j = i + 1;
			if (j >= bounds.size())
				j = 0;
			
			Point a = bounds.get(i);
			Point b = bounds.get(j);
			double inSum = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y);
			inSum = Math.abs(inSum / 2.0);
			sum += inSum;
		}
		
		double ogSum = L * L/2;

		return sum <= ogSum;
	}

	private void collisionHandler() {
		/*
		 * PowerUp-Shooter Collision
		 */
		ArrayList<Point> shooterBounds = this.rotatePoints(this.shooter.getCoordinate());
		
		for(PowerUp pw: this.onScreenPowerUpList) {
			if (this.checkPointBounds(shooterBounds, pw.getCoordinate())) {
				this.onScreenPowerUpList.remove(pw);
				getShooterInventory().addInventoryPowerUp(pw.getID());
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
		if(this.barrelAtom != null) {
			if(this.shooter.inventory.getInventoryShieldCount(type) > 0) {
				this.barrelAtom = AtomFactory.getInstance().addNewShield(type,this.barrelAtom);
				this.getShooterInventory().removeInventoryShield(type);
			}		
		}
	}


	public void getRandomAtomToBarrel() {
		Atom atom = getShooterInventory().getRandomAtom();
		
		if(atom != null) {
			if(this.barrelAtom != null) 
				getShooterInventory().addInventoryAtom(this.barrelAtom);
			else if(this.barrelPowerUp != null) 
				getShooterInventory().addInventoryPowerUp(this.barrelPowerUp);
			
			this.barrelPowerUp = null;
			this.barrelAtom = atom;
			this.barrelAtom.setCoordinate(this.shooter.getBarrelCoordinate());
			this.barrelAtom.setAngle(this.shooter.getAngle());			
		}
	}


	public void getPowerUpToBarrel(int type) {
		PowerUp pw = getShooterInventory().getPowerUp(type);

		if(pw != null) {
			if(this.barrelAtom != null) 
				getShooterInventory().addInventoryAtom(this.barrelAtom);
			else if(this.barrelPowerUp != null) 
				getShooterInventory().addInventoryPowerUp(this.barrelPowerUp);

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
		
		if(saveMethod == null) {
			System.out.println("No env variable set. Unable to save, please set mongo or file to SAVE_METHOD");
		}else if (saveMethod.equalsIgnoreCase("file")) {
			saveLoadService = new FileSaveLoadAdapter();
			saveLoadService.save();			
		} else if (saveMethod.equalsIgnoreCase("mongo")) {
			this.mongoLoadService = new MongoSaveLoadAdapter();
			this.mongoLoadService.save();		
		} else {
			System.out.println("Wrong env variable set. Unable to save, please set mongo or file to SAVE_METHOD");
		}
	}


	public void loadGame() {
		// Use env variable to switch between
		String saveMethod = System.getenv("SAVE_METHOD");
		
		if(saveMethod == null) {
			System.out.println("No env variable set. Unable to save, please set mongo or file to SAVE_METHOD");
		}else if (saveMethod.equalsIgnoreCase("file")) {
			saveLoadService = new FileSaveLoadAdapter();
			try {
				saveLoadService.load();
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				e.printStackTrace();
			}			
		} else if (saveMethod.equalsIgnoreCase("mongo")) {
			this.mongoLoadService = new MongoSaveLoadAdapter();
			try {
				this.mongoLoadService.load();
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (JsonIOException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}		
		} else {
			System.out.println("Wrong env variable set. Unable to save, please set mongo or file to SAVE_METHOD");
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
		return this.shooter.getInventory();
	}
	
	
	public boolean isPaused() {
		return isPaused;
	}

	
	public boolean isFinished() {
		return isFinished;
	}
	
	
	public double getPlayerScore() {
		return this.player.getScore();
	}


	public double getPlayerHealth() {
		return this.player.getHealth();
	}
	
	
	public void setLengthUnit(int L) {
		this.L = L;
	}
	
	
	public int getLengthUnit() {
		return L;
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
