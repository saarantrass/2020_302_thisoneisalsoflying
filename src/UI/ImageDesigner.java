package UI;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageDesigner {
	
	public static Image getResizedImage(Image src, int width, int height) {
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resized.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(src, 0, 0, width, height, null);
	    g2.dispose();
				
	    return resized;
	}
	
	public static Image getRotatedImage(Image src, double angle, int L) {
		BufferedImage rotated = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = rotated.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.rotate(Math.toRadians(angle), L/8, L/8);
	    g2.drawImage(src, 0, 0, src.getWidth(null), src.getHeight(null), null);
	    
	    g2.dispose();
		
	    return rotated;
	}
}
