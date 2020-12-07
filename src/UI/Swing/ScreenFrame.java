package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import UI.ImageResizer;

@SuppressWarnings("serial")
public class ScreenFrame extends JFrame{
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public ScreenFrame() {
		super("this one is also flying");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenSize.width, screenSize.height);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		/*Image img;
		try {
			img = ImageIO.read(getClass().getResource("../../Images/kuvid_bc.png"));
			img = ImageResizer.getResizedImage(img, screenSize.width, screenSize.height);
			this.setContentPane(new JLabel(new ImageIcon(img)));
			this.getContentPane().setLayout(new BorderLayout());
			//this.getContentPane().setVisible(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		//this.setFocusable(true);
		//this.setVisible(true);
	}

}
