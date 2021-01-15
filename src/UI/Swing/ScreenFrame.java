package UI.Swing;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ScreenFrame extends JFrame{
	
	public ScreenFrame() {
		super("this one is also flying");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setAlwaysOnTop(true);
	}

}
