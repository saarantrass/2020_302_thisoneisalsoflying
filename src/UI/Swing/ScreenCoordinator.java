package UI.Swing;

import Domain.GameController;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;

public class ScreenCoordinator { //singleton
	
	private ScreenFrame mainFrame;
	private JPanel currentPanel;
	private GameController GC;
	private static ScreenCoordinator screen_coordinator = null;
	public static final Dimension SCREEN_SIZE = new Dimension(1200,700);
	
	
	private ScreenCoordinator() {
		this.mainFrame = new ScreenFrame();
		this.GC = new GameController();
	}
	
	
	public static ScreenCoordinator getInstance(){
		if(screen_coordinator == null) {
			screen_coordinator = new ScreenCoordinator();
		}
		
		return screen_coordinator;
	}
	
	
	public void initialize() {
		this.buildingModeScreen();
	}
	
	
	public void startGame() {
		int xShooter = SCREEN_SIZE.width * 7/16;
		int yShooter = SCREEN_SIZE.height - this.GC.settings.getLengthUnit();
		GC.setInitialShooterCoordinate(new Point(xShooter, yShooter));
		GC.startGame();
		this.gameScreen();
	}
	
	
	public void buildingModeScreen() {
		clean();
		currentPanel = new BuildingModePanel(this.GC);
		mainFrame.setContentPane(currentPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	public void gameScreen() {
		clean();
		currentPanel = new GameModePanel(this.GC);
		mainFrame.setContentPane(currentPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	public JPanel getCurrentPanel() {
		return this.currentPanel;
	}

	
	public void clean() {
		if(currentPanel != null) {
			mainFrame.remove(currentPanel);
		}
	}
	
}
