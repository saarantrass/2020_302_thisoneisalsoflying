package UI.Swing;

import Domain.GameController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Domain.Game;

public class ScreenCoordinator { //singleton
	
	private ScreenFrame mainFrame;
	private JPanel currentPanel;
	private GameController GC;
	private static ScreenCoordinator screen_coordinator = null;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
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
		int xShooter = this.mainFrame.getSize().width * 7/16;
		int yShooter = this.mainFrame.getHeight() - 23 - this.GC.settings.getLengthUnit();
		GC.setInitialShooterCoordinate(new Point(xShooter, yShooter));
		Game.getInstance().startGame(this.GC);
		this.gameScreen();
	}
	
	
	public void pauseGame() {
		mainFrame.getContentPane().add(new PausePanel());
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
	
	
	public JPanel getCurrentPanel() {
		return this.currentPanel;
	}
	
	public ScreenFrame getMainFrame() {
		return this.mainFrame;
	}

	
	public void clean() {
		if(currentPanel != null) {
			mainFrame.remove(currentPanel);
		}
	}
	
}
