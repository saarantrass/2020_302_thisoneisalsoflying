package UI.Swing;

import Domain.GameController;
import Domain.Game;

public class ScreenCoordinator { //singleton
	
	private ModePanel currentPanel;
	private GameController GC;
	private static ScreenCoordinator screen_coordinator = null;
	private ScreenFrame mainFrame;
	
	
	private ScreenCoordinator() {
		this.mainFrame = new ScreenFrame();
		//this.mainFrame.setFocusable(true);
		this.GC = new GameController();
	}
	
	
	public static ScreenCoordinator getInstance(){
		if(screen_coordinator == null) {
			screen_coordinator = new ScreenCoordinator();
		}
		
		return screen_coordinator;
	}
	
	
	public void initialize() {
		//this.buildingModeScreen();
		this.startGame();
	}
	
	
	public void clean() {
		currentPanel.removeListeners();
		mainFrame.remove(currentPanel);
	}
	
	
	public void startGame() {
		//clean();
		this.gameScreen(GC);
		Game.getInstance().startGame();
	}
	
	
	public void buildingModeScreen() {
		currentPanel = new BuildingModePanel(GC);
		mainFrame.setContentPane(currentPanel);
		mainFrame.setVisible(true);
	}
	
	
	public void gameScreen(GameController GC) {
		
		currentPanel = new GameModePanel(GC);
		
		currentPanel.setFocusable(true);
		currentPanel.requestFocus();
		
		System.out.println(mainFrame.getFocusOwner());
		currentPanel.transferFocus();
		
		System.out.println(mainFrame.getFocusOwner());
		
		mainFrame.setContentPane(currentPanel);
		mainFrame.setVisible(true);
	}
	
}
