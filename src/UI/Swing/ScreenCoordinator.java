package UI.Swing;

import Domain.GameController;

public class ScreenCoordinator { //singleton
	
	private Screen screen;
	private GameController GC;
	private static ScreenCoordinator screen_coordinator = null;
	
	
	private ScreenCoordinator() {
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
		this.gameScreen(GC);
	}
	
	
	public void buildingModeScreen() {
		screen = new Screen(GC);
	}
	
	
	public void gameScreen(GameController GC) {
		screen = new Screen(GC, true);
	}

	
	public Screen getScreen() {
		return screen;
	}
	
}
