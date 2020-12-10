package UI.Swing;

import Domain.GameController;
import Domain.Game;

public class ScreenCoordinator { //singleton
	
	private ScreenFrame mainFrame;
	private ModePanel currentPanel;
	private GameController GC;
	private static ScreenCoordinator screen_coordinator = null;
	
	
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
		Game.getInstance().startGame(this.GC);
		this.gameScreen();
	}
	
	
	public void buildingModeScreen() {
		clean();
		currentPanel = new BuildingModePanel(this.GC);
		mainFrame.setContentPane(currentPanel);
		mainFrame.setVisible(true);
	}
	
	
	public void gameScreen() {
		clean();
		currentPanel = new GameModePanel(this.GC);
		mainFrame.setContentPane(currentPanel);
		mainFrame.setVisible(true);
	}
	
	
	public ModePanel getCurrentPanel() {
		return this.currentPanel;
	}

	
	public void clean() {
		if(currentPanel != null) {
			currentPanel.removeListeners();
			mainFrame.remove(currentPanel);
		}
	}
	
}
