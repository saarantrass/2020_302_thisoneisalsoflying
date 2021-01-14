package Domain;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import Domain.GameObjects.AtomFactory;
import Domain.GameObjects.FallingObjectFactory;
import Domain.GameObjects.Atoms.Atom;
import Domain.GameObjects.Molecules.Molecule;
import Domain.GameObjects.PowerUps.PowerUp;
import Domain.GameObjects.ReactionBlockers.ReactionBlocker;
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
				this.continueGame();
				Settings.getInstance().timeRemaining -= 1000 / Settings.timeMult;
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
		this.timer++;
		
		if(this.timer % (Settings.timeMult / this.difficultyLevel) == 0) { //TODO TAM DEÄ�Ä°L
			createRandomFallingObject();
		}
		
		if(this.shooter.inventory.getInventoryAtomCount(1) == 0 && this.shooter.inventory.getInventoryAtomCount(2) == 0 && this.shooter.inventory.getInventoryAtomCount(3) == 0 && this.shooter.inventory.getInventoryAtomCount(4) == 0) {
			if(this.onScreenAtomList.size() == 0)
				this.finishGame();
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
		for(ReactionBlocker rb : onScreenReactionBlockerList) {
			rb.move();
			if(rb.getCoordinate().y >= Settings.getInstance().getScreenSize().height) {
				this.explosion(rb);
				this.onScreenReactionBlockerList.remove(rb);
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
		//System.out.println("shooter coord ve angle "+this.shooter.getCoordinate()+" ang "+shooter.getAngle());
	}


	private void collisionHandler() {
		//TODO: reaction blocker blocks the atom-molecule unification!!!!
		//TODO: powerup baï¿½ka reactiona deï¿½ince yok oluyormuï¿½
		/*
		 * Atom-Molecule Collision
		 */
		for(Atom atom : this.onScreenAtomList) {
			for(Molecule molecule : this.onScreenMoleculeList) {
				if(atom.getAtomID() == molecule.getID()) {
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
				this.shooter.inventory.addInventoryPowerUp(pw.getID());
			}
		}

		/*
		 * PowerUp-ReactionBlocker Collision
		 */
		for(ReactionBlocker rb: this.onScreenReactionBlockerList) {
			for(PowerUp pw: this.onScreenPowerUpList) {
				if(rb.getID() == pw.getID()) {
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
		
		/*
		 * ReactionBlocker-Atom/Molecule Collision
		 */
		for(ReactionBlocker rb: this.onScreenReactionBlockerList) {
			for(Molecule mol: this.onScreenMoleculeList) {
				if(rb.getID() == mol.getID()) {
					Point mCoord = mol.getCoordinate();
					Point rCoordCenter = new Point(rb.getCoordinate().x + L/20, rb.getCoordinate().y + L/20);
					double distance1 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
					double distance2 = Math.sqrt(((mCoord.x + L/10) - rCoordCenter.x)*((mCoord.x + L/10) - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
					double distance3 = Math.sqrt(((mCoord.x + L/10) - rCoordCenter.x)*((mCoord.x + L/10) - rCoordCenter.x) + ((mCoord.y + L/10) - rCoordCenter.y)*((mCoord.y + L/10) - rCoordCenter.y));
					double distance4 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + ((mCoord.y + L/10) - rCoordCenter.y)*((mCoord.y + L/10) - rCoordCenter.y));
					if(distance1 <= L/2 || distance2 <= L/2 || distance3 <= L/2  || distance4 <= L/2) {
						this.onScreenMoleculeList.remove(mol);
					}
				}
			}
			for(Atom atom: this.onScreenAtomList) {
				if(rb.getID() == atom.getAtomID()) {
					Point aCoord = atom.getCoordinate();
					Point rCoordCenter = new Point(rb.getCoordinate().x + L/20, rb.getCoordinate().y + L/20);
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
	}
	
	
	public void explosion(ReactionBlocker rb) {
		Point sCoord = this.shooter.getCoordinate();
		Point rCoordCenter = new Point(rb.getCoordinate().x + L/20, rb.getCoordinate().y + L/20);
		//System.out.println("shooter coord ve angle "+sCoord+" ang "+shooter.getAngle());
		double distance1 = Math.sqrt((sCoord.x - rCoordCenter.x)*(sCoord.x - rCoordCenter.x) + (sCoord.y - rCoordCenter.y)*(sCoord.y - rCoordCenter.y));
		double distance2 = Math.sqrt(((sCoord.x + L/10) - rCoordCenter.x)*((sCoord.x + L/10) - rCoordCenter.x) + (sCoord.y - rCoordCenter.y)*(sCoord.y - rCoordCenter.y));
		double distance3 = Math.sqrt(((sCoord.x + L/10) - rCoordCenter.x)*((sCoord.x + L/10) - rCoordCenter.x) + ((sCoord.y + L/10) - rCoordCenter.y)*((sCoord.y + L/10) - rCoordCenter.y));
		double distance4 = Math.sqrt((sCoord.x - rCoordCenter.x)*(sCoord.x - rCoordCenter.x) + ((sCoord.y + L/10) - rCoordCenter.y)*((sCoord.y + L/10) - rCoordCenter.y));
		if(distance1 <= L*2 || distance2 <= L*2 || distance3 <= L*2  || distance4 <= L*2) {
			//TODO health azalt
			this.player.decreaseHealth(10.0); //TODO BU YANLIÅ�
		}
	
		for(Molecule mol : this.onScreenMoleculeList) {
			Point mCoord = mol.getCoordinate();
			distance1 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
			distance2 = Math.sqrt(((mCoord.x + L/10) - rCoordCenter.x)*((mCoord.x + L/10) - rCoordCenter.x) + (mCoord.y - rCoordCenter.y)*(mCoord.y - rCoordCenter.y));
			distance3 = Math.sqrt(((mCoord.x + L/10) - rCoordCenter.x)*((mCoord.x + L/10) - rCoordCenter.x) + ((mCoord.y + L/10) - rCoordCenter.y)*((mCoord.y + L/10) - rCoordCenter.y));
			distance4 = Math.sqrt((mCoord.x - rCoordCenter.x)*(mCoord.x - rCoordCenter.x) + ((mCoord.y + L/10) - rCoordCenter.y)*((mCoord.y + L/10) - rCoordCenter.y));
			if(distance1 <= L*2 || distance2 <= L*2 || distance3 <= L*2  || distance4 <= L*2) {
				this.onScreenMoleculeList.remove(mol);
			}
		} 
		
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
		if(this.barrelAtom != null) 
			this.shooter.inventory.addInventoryAtom(this.barrelAtom);
		else if(this.barrelPowerUp != null) 
			this.shooter.inventory.addInventoryPowerUp(this.barrelPowerUp);
		
		this.barrelAtom = this.shooter.inventory.getRandomAtom();
		
		if(this.barrelAtom != null) {
			this.barrelPowerUp = null;
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
		saveLoadService = new FileSaveLoadAdapter();
		saveLoadService.save();
		this.mongoLoadService = new MongoSaveLoadAdapter();
		this.mongoLoadService.save();
	}
	
	
	public void loadGame() {
		
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
