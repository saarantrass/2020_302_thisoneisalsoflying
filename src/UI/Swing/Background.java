package UI.Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import UI.ImageResizer;

@SuppressWarnings("serial")
public class Background extends JLabel{
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public  Background() {
		Image bc;
		try {
			bc = ImageIO.read(getClass().getResource("../../Images/kuvid_bc.png"));
			bc = ImageResizer.getResizedImage(bc, screenSize.width, screenSize.height);
			this.setIcon(new ImageIcon(bc));
			this.setLayout(new BorderLayout());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
