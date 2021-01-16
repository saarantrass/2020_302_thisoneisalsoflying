package UI;

import Domain.GameController;
import UI.Swing.Background;
import UI.Swing.BuildingModePanel;
import UI.Swing.ErrorFrame;
import UI.Swing.GameModePanel;
import UI.Swing.ScreenFrame;

import java.awt.Dimension;
import javax.swing.JPanel;

public class ScreenCoordinator { //singleton
	
	private static ScreenCoordinator screen_coordinator = null;

	private JPanel currentPanel;
	private ScreenFrame mainFrame = new ScreenFrame();

	private GameController GC = new GameController();
	
	private Background background = new Background();
	private GameModePanel gameModePanel;
	private BuildingModePanel buildingModePanel;
	private ErrorFrame error;
	
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
	
	
	public void displayError(String msg) {
		this.error = new ErrorFrame(msg);
		this.error.setLocationRelativeTo(this.mainFrame);
		this.mainFrame.setEnabled(false);
		this.mainFrame.setAlwaysOnTop(false);
	}
	
	
	public void removeError() {
		this.error.setAlwaysOnTop(false);
		this.error.setVisible(false);
		this.mainFrame.setEnabled(true);
		this.mainFrame.setAlwaysOnTop(true);
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
