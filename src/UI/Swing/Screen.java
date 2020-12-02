package UI.Swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.*;

public class Screen extends JFrame implements IObserver{
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double width;
	private double height;
	private List<String> objectList;
	
	
	
	public Screen(){

		JFrame main = new JFrame("KUVID");
		main.setSize(screenSize.width, screenSize.height);
		main.setVisible(true);
		
	}



	public List<String> getObjectList() {
		return objectList;
	}



	public void setObjectList(List<String> objectList) {
		this.objectList = objectList;
	}
	
	

	public void update() {
		
	}
}
