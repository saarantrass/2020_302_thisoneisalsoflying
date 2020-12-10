package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ScreenFrame extends JFrame{
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public ScreenFrame() {
		super("this one is also flying");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenSize.width, screenSize.height);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
	}

}
