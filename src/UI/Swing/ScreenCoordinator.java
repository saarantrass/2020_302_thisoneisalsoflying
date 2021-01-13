package UI.Swing;

import Domain.GameController;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ScreenCoordinator { //singleton
	
	private ScreenFrame mainFrame = new ScreenFrame();
	private Background background = new Background();
	private JPanel currentPanel;
	private GameController GC = new GameController();
	private GameModePanel gameModePanel;
	private BuildingModePanel buildingModePanel;
	private static ScreenCoordinator screen_coordinator = null;
	public static final Dimension SCREEN_SIZE = new Dimension(1350,835);
	
	
	private ScreenCoordinator() {
		mainFrame.setContentPane(background);
	}
	
	
	public static ScreenCoordinator getInstance(){
		if(screen_coordinator == null) {
			screen_coordinator = new ScreenCoordinator();
		}
		
		return screen_coordinator;
	}
	
	
	public void initialize() {
		this.buildingModePanel = new BuildingModePanel(this.GC);
		this.buildingModeScreen();
	}
	
	
	public void startGame() {
		GC.startGame();
		this.gameModePanel = new GameModePanel(this.GC);
		this.gameScreen();
	}
	
	
	public void buildingModeScreen() {
		clean();
		currentPanel = this.buildingModePanel;
		mainFrame.getContentPane().add(currentPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.validate();
		mainFrame.repaint();
		currentPanel.requestFocus();
	}
	
	
	public void gameScreen() {
		clean();
		currentPanel = this.gameModePanel;
		mainFrame.getContentPane().add(currentPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.validate();
		mainFrame.repaint();
		currentPanel.requestFocus();
	}
	
	
	public void setCurrentPanel(JPanel currentPanel) {
		this.currentPanel = currentPanel;
	}
	
	
	public JPanel getCurrentPanel() {
		return this.currentPanel;
	}

	
	public void clean() {
		mainFrame.getContentPane().removeAll();
	}
	
}
