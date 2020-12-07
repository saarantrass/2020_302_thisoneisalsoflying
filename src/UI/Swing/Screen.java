package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Domain.Game;
import Domain.GameController;
import UI.IObserver;
import UI.GameObjectImages.GameObjectImage;
import UI.GameObjectImages.ShooterImage;

@SuppressWarnings("serial")
public class Screen extends JFrame implements IObserver {
	
	private GameController GC;
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private List<GameObjectImage> objectList = new ArrayList<GameObjectImage>();
	private boolean isRunningMode;
	private JPanel currentModePanel;
	
	public Screen(GameController GC, boolean isRunningMode){
		super("this one is also flying");
		this.isRunningMode = isRunningMode;
		this.GC = GC;
		
		//BackgroundPanel panel = new BackgroundPanel();
		
		
		
		/*
		 * screen JFrame design
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenSize.width, screenSize.height);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		/*
		 * if is running mode, display game screen
		 */
		if(this.isRunningMode) {
			
			currentModePanel = new GameModePanel(GC);
			currentModePanel.setFocusable(true);
		
		/*
		 * if is not running mode display building mode screen
		 */
		} else {
			
			currentModePanel = new BuildingModePanel(GC);
			
		}
		
		//panel.add(currentModePanel);
		//this.add(panel);
		this.add(currentModePanel);
		this.setVisible(true);
	}
	
	
	public Screen(GameController GC) {
		this(GC, false);
	}
	
	
	public void setGC(GameController GC) {
		this.GC = GC;
	}
	
	
	public void addGameObjectImage(GameObjectImage im) {
		this.currentModePanel.add(im);
		objectList.add(im);
	}
	
	
	public List<GameObjectImage> getObjectList() {
		return objectList;
	}



	public void setObjectList(List<GameObjectImage> objectList) {
		this.objectList = objectList;
	}
	
	

	public void update() {
		//ShooterImage shooterIm = new ShooterImage(this.game.getShooter(), this.game.getShooter().getCoordinate().x, this.game.getShooter().getCoordinate().y);
		//this.currentModePanel.add(shooterIm);
	}
	
}
