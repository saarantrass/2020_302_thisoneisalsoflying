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
	
	public  Background() {
		Image bc;
		try {
			Dimension size = new Dimension(ScreenCoordinator.getInstance().getMainFrame().getWidth(), ScreenCoordinator.getInstance().getMainFrame().getHeight() - 23);
			bc = ImageIO.read(getClass().getResource("../../Images/kuvid_bc.png"));
			bc = ImageResizer.getResizedImage(bc, size.width, size.height);
			this.setIcon(new ImageIcon(bc));
			this.setLayout(new BorderLayout());
			this.setSize(size);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
