package UI.Swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Domain.GameController;

public class ScreenCoordinator { //singleton
	
	private JPanel currentPanel;
	private GameController GC;
	private static ScreenCoordinator screen_coordinator = null;
	private ScreenFrame mainFrame;
	
	
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
		mainFrame.remove(currentPanel);
		this.gameScreen(GC);
	}
	
	
	public void buildingModeScreen() {
		currentPanel = new BuildingModePanel(GC);
		currentPanel.setFocusable(true);
		mainFrame.getContentPane().add(currentPanel);
		mainFrame.setVisible(true);
	}
	
	
	public void gameScreen(GameController GC) {
		currentPanel = new GameModePanel(GC);
		currentPanel.setFocusable(true);
		mainFrame.getContentPane().add(currentPanel);
		mainFrame.setVisible(true);
	}
	
}
